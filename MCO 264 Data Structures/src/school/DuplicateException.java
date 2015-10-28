package school;

public class DuplicateException extends Exception {
	public DuplicateException() {
		super("You cannot enter duplicate items into the school.");
	}
}
