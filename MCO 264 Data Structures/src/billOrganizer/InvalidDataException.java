package billOrganizer;

public class InvalidDataException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDataException() {
		super("Some or all of the data you entered is not valid.");
	}
}
