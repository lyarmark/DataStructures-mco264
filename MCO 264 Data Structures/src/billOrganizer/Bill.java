package billOrganizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Bill implements Comparable<Bill>, Serializable {
	private static final long serialVersionUID = 1L;

	private static int lastID = 0;
	private Integer billID;
	private String vendor;
	private double amountDue;
	private GregorianCalendar dueDate;
	private BillType billType;

	public Bill(String vendor, double amountDue, GregorianCalendar dueDate, BillType billType) {
		this.billID = ++lastID;
		this.vendor = vendor;
		this.amountDue = amountDue;
		this.dueDate = dueDate;
		this.billType = billType;
	}

	@Override
	public int compareTo(Bill other) {
		return this.billID.compareTo(other.billID);
	}

	public String getVendor() {
		return vendor;
	}

	public Double getAmountDue() {
		return amountDue;
	}

	public GregorianCalendar getDueDate() {
		return dueDate;
	}

	public BillType getBillType() {
		return billType;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n" + this.billID + "\t");
		builder.append(this.vendor + "\t");
		builder.append(this.amountDue + "\t");
		builder.append(this.dueDate + "\t");
		builder.append(this.billType);
		return builder.toString();
	}
}
