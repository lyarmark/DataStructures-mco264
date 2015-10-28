package school;

public class NotFoundException extends Exception{
	public NotFoundException() {
		super("Data not found");
	}
}