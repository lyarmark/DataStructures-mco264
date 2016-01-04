package billOrganizer;

public class ListEmptyException extends Exception {
	public ListEmptyException() {
		super("list empty");
	}
}