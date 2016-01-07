package billOrganizer;

public class InvalidDataException extends Exception{
	public InvalidDataException() {
		super("Some or all of the data you entered is not valid.");
	}
}
