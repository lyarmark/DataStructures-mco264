package billOrganizer;

import java.util.Comparator;

public class ComparatorBillType implements Comparator<Bill> {
	public int compare(Bill bill1, Bill bill2) {
		return bill1.getBillType().compareTo(bill2.getBillType());
	}
}
