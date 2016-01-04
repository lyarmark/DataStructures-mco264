package billOrganizer;

import java.util.Comparator;

public class ComparatorBillDate implements Comparator<Bill> {
	public int compare(Bill bill1, Bill bill2) {
		return bill1.getDueDate().compareTo(bill2.getDueDate());
	}

}
