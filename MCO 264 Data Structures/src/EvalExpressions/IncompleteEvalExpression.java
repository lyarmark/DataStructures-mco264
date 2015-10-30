package EvalExpressions;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class IncompleteEvalExpression {
	private Stack<String> opStack;

	public IncompleteEvalExpression() {
		opStack = new Stack<String>();
	}

	public String PostFix(String InfixExpression) throws InvalidExpressionException {
		// convert the String to a set of tokens - the whitespace will be
		// used to divide up the String into a set of tokens

		StringTokenizer infix = new StringTokenizer(InfixExpression);

		String token;
		String topToken;
		char operator;
		StringBuffer postFixExp = new StringBuffer(); // contains PostFix
		// expression
		final String BLANK = " ";
		while (infix.hasMoreElements()) {
			token = infix.nextToken();
			// since + in an expression means one or more occurrences when we
			// want to compiler to treat it as the character +
			// we must preface it with a \ but since the backslash also has
			// special meaning we preface the \ with another \
			// so now it becomes \\+ to compare to a + sign
			// we have to do the same thing for a * since the * means zero or
			// more occurrences in a string!

			if (token.matches("[\\+-/\\*()]")) {
				operator = token.charAt(0);
				// this is a mathematical operator or a parenthesis
				switch (operator) {
				case '(':
					opStack.push(token);
					break;
				case ')':
					for (;;) { // for loop that goes on forever
						// to pop things off the stack
						// until '(' or invalid entry
						// a stack is good for checking pairs
						try {
							topToken = opStack.pop();
							if (topToken.charAt(0) == '(') {
								break;
							} else {
								postFixExp.append(BLANK); // make sure to append
								// the blank for the
								// next time you
								// evaluate
								postFixExp.append(topToken);
							}
						} catch (EmptyStackException e) {
							// doesn't have a matching open parenthesis
							throw new InvalidExpressionException();

						}
					}
					break;

				case '+':
				case '-':
				case '*':
				case '/':
					// what you do with operator depends whats on the top
					// use peek()
					for (;;) {
						char topTok;
						if (!opStack.empty()) {
							topTok = opStack.peek().charAt(0);
						} else {
							topTok = ' '; // set it to blank
						}
						// current has higher precedence
						if (opStack.empty() || (topTok == '(')
								|| (((operator == '*') || (operator == '/')) && ((topTok == '+') || (topTok == '-')))) {
							opStack.push(token);
							break;
							// current has lower precedence
						} else {
							topToken = opStack.pop();
							postFixExp.append(BLANK + topToken);

						}
					}
					break;
				}

			} else {
				// it is a number , just place it into the expression
				postFixExp.append(BLANK + token);

			}

		}
		while (!opStack.empty()) { // pop what is left in the operator stack
			topToken = opStack.pop();
			if (topToken.charAt(0) != '(') {
				postFixExp.append(BLANK + topToken);
			} else {
				// found an unmatched ( parenthesis
				throw new InvalidExpressionException();

			}
		}

		return postFixExp.toString();

	}

	public Double evalPostFix(String postFixExpression) {

		// pseudocode:
		/*
		 * this method receives a string of the mathematical expression in
		 * postfix form loop through the characters in the string
		 */
		StringTokenizer postFix = new StringTokenizer(postFixExpression);

		String chara;
		String topOfStack;
		char operator;
		Double value = null; // will contain answer
		final String BLANK = " ";

		while (postFix.hasMoreElements()) {
			chara = postFix.nextToken();
			if (chara.matches("[\\+-/\\*()]")) {
				operator = chara.charAt(0);

				switch (operator) {
				case '+': {
					value = Double.parseDouble(opStack.pop());
					Double temp = value + Double.parseDouble(opStack.pop());
					opStack.push(temp.toString());
					break;

				}
				case '-': {
					value = Double.parseDouble(opStack.pop());
					Double temp = value - Double.parseDouble(opStack.pop());
					opStack.push(temp.toString());
					break;

				}
				case '*': {
					value = Double.parseDouble(opStack.pop());
					Double temp = value * Double.parseDouble(opStack.pop());
					opStack.push(temp.toString());
					break;
				}
				case '/': {

					value = Double.parseDouble(opStack.pop());
					Double temp = value / Double.parseDouble(opStack.pop());
					opStack.push(temp.toString());
					break;
				}
				}
			} else {
				opStack.push(chara);
			}
		}
		return Double.parseDouble(opStack.pop());

		/*
		 * 
		 * push them onto the stack until you hit an operator when you hit an
		 * operator, pop the last 2 values of the stack and calculate them using
		 * the operator push the result onto the stack continue looping through
		 * the string
		 * 
		 * 
		 * while (postFix.hasMoreElements()) {
		 * 
		 * if (token.matches("[\\+-/\\*()]")) { operator = token.charAt(0); //
		 * this is a mathematical operator or a parenthesis switch (operator) {
		 * case '(': opStack.push(token); break; case ')': for (;;) { try {
		 * topToken = opStack.pop(); if (topToken.charAt(0) == '(') { break; }
		 * else { postFixExp.append(BLANK); // make sure to append // the blank
		 * for the // next time you // evaluate postFixExp.append(topToken); } }
		 * catch (EmptyStackException e) { // doesn't have a matching open
		 * parenthesis throw new InvalidExpressionException();
		 * 
		 * } } break;
		 * 
		 * case '+': case '-': case '*': case '/': // what you do with operator
		 * depends whats on the top // use peek() for (;;) { char topTok; if
		 * (!opStack.empty()) { topTok = opStack.peek().charAt(0); } else {
		 * topTok = ' '; // set it to blank } // current has higher precedence
		 * if (opStack.empty() || (topTok == '(') || (((operator == '*') ||
		 * (operator == '/')) && ((topTok == '+') || (topTok == '-')))) {
		 * opStack.push(token); break; // current has lower precedence } else {
		 * topToken = opStack.pop(); postFixExp.append(BLANK + topToken);
		 * 
		 * } } break; }
		 * 
		 * } else { opStack.push(token); }
		 * 
		 * } while (!opStack.empty()) { // pop what is left in the operator
		 * stack topToken = opStack.pop(); if (topToken.charAt(0) != '(') {
		 * postFixExp.append(BLANK + topToken); } else { // found an unmatched (
		 * parenthesis throw new InvalidExpressionException();
		 * 
		 * } }
		 * 
		 * return Double.parseDouble(postFixExp.toString());
		 */
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		IncompleteEvalExpression anExpression = new IncompleteEvalExpression();
		Scanner console = new Scanner(System.in);
		String completeExpression;

		Double value;
		System.out.println("Enter a complete algebraic expression");
		completeExpression = console.nextLine();
		String postFixExp;
		postFixExp = anExpression.PostFix(completeExpression);
		System.out.println("postfix " + postFixExp);

		value = anExpression.evalPostFix(postFixExp);
		System.out.println("result " + value);

	}
}
