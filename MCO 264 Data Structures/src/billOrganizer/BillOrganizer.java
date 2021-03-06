package billOrganizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class BillOrganizer implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<PriorityQueue<Bill>> queues;
	private SortedLinkedList<Bill> sortedBills;

	public BillOrganizer() {
		this.queues = new ArrayList<PriorityQueue<Bill>>();
		this.sortedBills = new SortedLinkedList<Bill>();
		queues.add(new PriorityQueue<Bill>(BillCriteria.BILLDUEDATE));
		queues.add(new PriorityQueue<Bill>(BillCriteria.BILLAMOUNT));
		queues.add(new PriorityQueue<Bill>(BillCriteria.BILLTYPE));
	}

	// this is not used
	public BillOrganizer(String billTextFile) throws FileNotFoundException, DuplicateDataException, NotFoundException,
			InvalidDataException {
		this();
		File file = new File(billTextFile);
		Scanner fileReader = new Scanner(file);
		while (fileReader.hasNext()) {
			Bill bill = readBill(file, fileReader);
			this.insert(bill);
		}
	}

	public BillOrganizer(File fileName) throws IOException, ClassNotFoundException, DuplicateDataException {
		this();
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream in = new ObjectInputStream(fis);
		this.sortedBills = (SortedLinkedList<Bill>) in.readObject();
		in.close();
		LinkedListIterator<Bill> iter = new LinkedListIterator<Bill>(sortedBills.head);
		while (iter.hasNext()) {
			Bill bill = iter.next();
			for (PriorityQueue<Bill> pq : this.queues) {
				pq.enqueue(bill);
			}
		}
	}

	public void insert(Bill bill) throws DuplicateDataException {
		this.sortedBills.insert(bill);
		for (PriorityQueue<Bill> pq : this.queues) {
			pq.enqueue(bill);
		}
	}

	public void payNextBill(BillCriteria criteria) throws ListEmptyException, NotFoundException {
		// if more criteria are added, should probably change this to a loop
		// instead of removing explicitly from each
		if (criteria.equals(BillCriteria.BILLDUEDATE)) {
			Bill bill = queues.get(0).dequeue();
			queues.get(1).remove(bill);
			queues.get(2).remove(bill);
			sortedBills.remove(bill);
		} else if (criteria.equals(BillCriteria.BILLAMOUNT)) {
			Bill bill = queues.get(1).dequeue();
			queues.get(0).remove(bill);
			queues.get(2).remove(bill);
			sortedBills.remove(bill);
		} else if (criteria.equals(BillCriteria.BILLTYPE)) {
			Bill bill = queues.get(2).dequeue();
			queues.get(0).remove(bill);
			queues.get(1).remove(bill);
			sortedBills.remove(bill);
		}
	}

	public void paySpecificBill(int billID) throws ListEmptyException, NotFoundException {
		LinkedListIterator<Bill> iter = new LinkedListIterator<Bill>(sortedBills.head);
		Bill bill = null;
		boolean found = false;
		if (iter.hasNext()) {
			while (iter.hasNext()) {
				bill = iter.next();
				if (bill.getBillID().equals(billID)) {
					sortedBills.remove(bill);
					for (PriorityQueue<Bill> pq : queues) {
						pq.remove(bill);
					}
					found = true;
				}
			}
			if (!found) {
				throw new NotFoundException();
			}
		} else {
			throw new ListEmptyException();
		}
	}

	public Bill readBill(File billTextFile, Scanner readFile) throws NotFoundException, InvalidDataException {
		// assumes each entry is stored on one line

		String vendor = readFile.next();
		double amountDue = readFile.nextDouble();
		String[] date = readFile.next().split("/");
		GregorianCalendar dueDate = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[0]),
				Integer.parseInt(date[1]));

		String type = readFile.nextLine();
		BillType billType = StaticMethods.validateBillType(type);

		Bill bill = new Bill(vendor, amountDue, dueDate, billType);
		return bill;
	}

	public double totalBills() {
		double total = 0;
		LinkedListIterator<Bill> iter = new LinkedListIterator<Bill>(sortedBills.head);
		while (iter.hasNext()) {
			total += iter.next().getAmountDue();
		}
		return total;
	}

	public LinkedListIterator<Bill> iteratorByDate() {
		return new LinkedListIterator<Bill>(queues.get(0).getList().head);
	}

	public LinkedListIterator<Bill> iteratorByAmount() {
		return new LinkedListIterator<Bill>(queues.get(1).getList().head);
	}

	public LinkedListIterator<Bill> iteratorByType() {
		return new LinkedListIterator<Bill>(queues.get(2).getList().head);
	}

	public void closeOrganizer() throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("bills.ser"));
		out.writeObject(sortedBills);
		out.close();
	}

	public String toString() {
		LinkedListIterator<Bill> iter = new LinkedListIterator<Bill>(sortedBills.head);
		StringBuilder builder = new StringBuilder();
		while (iter.hasNext()) {
			builder.append(iter.next().toString());
		}
		return builder.toString();
	}
}
