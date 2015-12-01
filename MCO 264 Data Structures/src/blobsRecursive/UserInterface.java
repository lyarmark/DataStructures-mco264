package blobsRecursive;

public class UserInterface {

	public static void main(String[] args) {

		Cell<Character> cell = new Cell<Character>('X', 0, 0);

		// can't figure out how define a grid of cell objects
		// each cell can store an object (among other things)
		GridNonRecursive<Cell<Character>> theGrid = new GridNonRecursive<Cell<Character>>(10, 10);
		theGrid.setGrid(40, cell);
		System.out.println(theGrid.toString());
		System.out.println(theGrid.countBlobs(cell));

	}
}