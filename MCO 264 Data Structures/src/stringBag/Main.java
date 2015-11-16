package stringBag;

public class Main {
	public static void main(String[] args) {
		StringBagADT bag = new StringBagADT("array", 3);
		try {
			bag.insert("peach"); // test insert() and isFull()
			bag.insert("plum");
			bag.insert("cherry");
			bag.clear(); // test clear()

			bag.insert("peach");
			bag.insert("plum");
			bag.insert("cherry");

			System.out.println("Bag Name: " + bag.getName()); // test getName()
			System.out.println("Bag capacity: " + bag.size()); // test getSize()

			// test remove() // toString()
			System.out.println("The array contains: " + bag.toString());

			// test remove() and isEmpty()
			System.out.println(bag.remove() + " has been removed from the bag.");

			System.out.println(bag.remove() + " has been removed from the bag");
			System.out.println(bag.remove() + " has been removed from the bag");

			System.out.println("The array contains: " + bag.toString());

			bag.insert("grape"); // test isFull()
			System.exit(0);
		} catch (ArrayFullException e) {
			System.out.println(e.toString());
		} catch (ArrayEmptyException e) {
			System.out.println(e.toString());
		}
	}
}
