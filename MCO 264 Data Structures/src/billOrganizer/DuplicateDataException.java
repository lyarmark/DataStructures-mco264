package billOrganizer;

public class DuplicateDataException extends Exception {
	public DuplicateDataException() {
		super("Duplicate entry");
	}
}
