package generics;

public class Book implements Comparable<Book>{
	private String title;
	private Double price;

	public Book(String title, Double price) {
		this.title = title;
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}
	
	public int compareTo(Book a) {
		return this.getTitle().compareTo(a.getTitle());
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.title);
		buffer.append("\t");
		buffer.append(this.price);
		return buffer.toString();
	}

}
