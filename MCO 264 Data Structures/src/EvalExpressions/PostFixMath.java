package EvalExpressions;

public class PostFixMath {
	/*
	 * split expression using ' ' as delimeter add all operators to the stack
	 * (may have to check operator precedence)
	 * 
	 * purpose of this is to read math straight left to right
	 * 
	 * check if operand or operatorcreate operation based on string
	 * 
	 * 1st pop operand on right, then on left of expressionex: 9-7 is different
	 * than 7-9
	 */

	/*
	 * ex: 5 + 3 * 7 ( 1 - 2)
	 * 
	 * put operators on a stack put operands in an expression go from left to
	 * right except if the operator is of higher precedence that the previous
	 * one when an operator has high precedence, pop the previous one and put it
	 * on the expression and put the higher operator on the stack
	 * 
	 * expression is to put the stuff in the correct order : 5 3 * 7 1 2 - * +
	 * ????????????????????
	 * 
	 * homework should have number on the stack keep pushing on until hit
	 * operator then pop 2 and calculate and push is back on
	 */

}
