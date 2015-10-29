package EvalExpressions;

public class InvalidExpressionException extends RuntimeException{
	public InvalidExpressionException () {
		super("invalid expression");
	}

}
