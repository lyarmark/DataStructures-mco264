package school;

public class InvalidDataException extends Exception{
	public InvalidDataException() {
		super("Some are all of the data you entered is not valid.");
	}
}
