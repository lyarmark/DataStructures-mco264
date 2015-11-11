package genericBag;


public class BagMain {
	public static void main(String[] args) {
		Bag bag = new Bag(3); // constructor

		Book a = new Book("Alice in Wonderland", 10.0);
		Book b = new Book("Through the Looking Glass", 11.0);
		Book c = new Book("WabberJockey", 12.0);
		Book d = new Book("JabberWockey", 13.0);

		bag.dropIn(a); // dropIn()
		bag.dropIn(b);
		bag.dropIn(c);
		bag.dropIn(d); // isFull()

		if (bag.takeOut(d) != null) {
			System.out.println("isFull failed");
		}

		bag.dump(); // dump()

		if (!bag.isEmpty()) {
			System.out.print("dump failed"); // isEmpty()
		}

		bag.dropIn(a);
		bag.dropIn(b);
		bag.dropIn(c);

		System.out.println(bag.takeOut());
		System.out.print(bag.takeOut(b));
		System.exit(0);
	}

}
