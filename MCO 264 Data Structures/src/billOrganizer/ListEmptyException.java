package billOrganizer;

public class ListEmptyException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListEmptyException() {
		super("list empty");
	}
}