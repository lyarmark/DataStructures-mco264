package billOrganizer;

import java.util.GregorianCalendar;

public class StaticMethods {

	public static BillType validateBillType(String type) {
		for (BillType t : BillType.values()) {
			if (type.equalsIgnoreCase(t.toString())) {
				return t;
			}
		}
		return null;
	}

	public static BillCriteria validateBillCriteria(String criteria) {
		for (BillCriteria c : BillCriteria.values()) {
			if (criteria.equalsIgnoreCase(c.toString())) {
				return c;
			}
		}
		return null;
	}

	public static GregorianCalendar convertStringToDate(String dateString) {
		String[] split = dateString.split("/");
		GregorianCalendar date = new GregorianCalendar(Integer.parseInt(split[2]), Integer.parseInt(split[0]),
				Integer.parseInt(split[1]));
		return date;
	}
}
