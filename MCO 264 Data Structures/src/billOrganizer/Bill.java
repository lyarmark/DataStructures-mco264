package billOrganizer;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Bill implements Comparable<Bill>, Serializable {
	private static final long serialVersionUID = 1L;

	private static int lastID = 0;
	private Integer billID;
	private String vendor;
	private double amountDue;
	private GregorianCalendar dueDate;
	private BillType billType;

	public Bill(String vendor, double amountDue, GregorianCalendar dueDate, BillType billType)
			throws InvalidDataException {
		if (vendor == null || vendor == "" || amountDue <= 0 || dueDate == null || billType == null) {
			throw new InvalidDataException();
		}

		this.billID = ++lastID;
		this.vendor = vendor.toUpperCase();
		this.amountDue = amountDue;
		this.dueDate = dueDate;
		this.billType = billType;
	}

	@Override
	public int compareTo(Bill other) {
		return this.billID.compareTo(other.billID);
	}

	public Integer getBillID() {
		return billID;
	}

	public String getVendor() {
		return vendor;
	}

	public Double getAmountDue() {
		return amountDue;
	}

	public GregorianCalendar getDueDate() {
		GregorianCalendar dueDate = new GregorianCalendar(this.dueDate.get(Calendar.YEAR),
				this.dueDate.get(Calendar.MONTH) - 1, this.dueDate.get(Calendar.DAY_OF_MONTH));

		return dueDate;
	}

	public BillType getBillType() {
		return billType;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n" + this.billID + "\t");
		builder.append(this.vendor + "\t");
		DecimalFormat decimalFormat = new DecimalFormat("$###,###,###0.00");
		builder.append(decimalFormat.format(this.amountDue) + "\t");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		builder.append(dateFormat.format(this.getDueDate().getTime()) + "\t");
		builder.append(this.billType);
		return builder.toString();
	}
}
