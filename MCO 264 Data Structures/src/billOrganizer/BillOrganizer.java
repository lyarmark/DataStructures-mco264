package billOrganizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class BillOrganizer {

	private ArrayList<PriorityQueue<Bill>> queues;
	private SortedLinkedList<Bill> sortedBills;

	public BillOrganizer() {
		this.queues = new ArrayList<PriorityQueue<Bill>>();
		this.sortedBills = new SortedLinkedList<Bill>();
		queues.add(new PriorityQueue<Bill>(BillCriteria.BILLDUEDATE));
		queues.add(new PriorityQueue<Bill>(BillCriteria.BILLAMOUNT));
		queues.add(new PriorityQueue<Bill>(BillCriteria.BILLTYPE));
	}

	public BillOrganizer(String billTextFile) throws FileNotFoundException {
		File file = new File(billTextFile);
		Scanner fileReader = new Scanner(file);
		while (fileReader.hasNext()) {
			Bill bill = readBill(file, fileReader);
			sortedBills.insert(bill);
			for (PriorityQueue<Bill> pq : this.queues) {
				pq.enqueue(bill);
			}
		}

	}

	public void insert(Bill bill) {
		for (PriorityQueue<Bill> pq : this.queues) {
			// INSERT NEEDS TO SORT
			// COMMENT ALL CLASSES BASED ON SPEC
			pq.enqueue(bill);
		}
	}

	public void payNextBill(Bill bill, BillCriteria criteria) throws ListEmptyException, NotFoundException {
		if (criteria.equals(BillCriteria.BILLDUEDATE)) {
			queues.get(0).dequeue();
			queues.get(1).remove(bill);
			queues.get(2).remove(bill);
		} else if (criteria.equals(BillCriteria.BILLAMOUNT)) {
			queues.get(1).dequeue();
			queues.get(0).remove(bill);
			queues.get(2).remove(bill);
		} else if (criteria.equals(BillCriteria.BILLTYPE)) {
			queues.get(2).dequeue();
			queues.get(0).remove(bill);
			queues.get(1).remove(bill);
		}
		sortedBills.remove(bill);
	}

	public void paySpecificBill(int billID) {
		//DO SOMETHING
	}

	public Bill readBill(File billTextFile, Scanner readFile) {
		// assumes each entry is stored on one line

		String vendor = readFile.next();
		double amountDue = readFile.nextDouble();
		String[] date = readFile.next().split("/");
		GregorianCalendar dueDate = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[0]),
				Integer.parseInt(date[1]));

		String type = readFile.nextLine();
		BillType billType = Validations.validateBillType();

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

	public String toString() {
		LinkedListIterator<Bill> iter = new LinkedListIterator<Bill>(sortedBills.head);
		StringBuilder builder = new StringBuilder();
		while (iter.hasNext()) {
			builder.append(iter.next().toString());
		}
		return builder.toString();
	}

	public String toStringByCriteria(BillCriteria criteria) {
		LinkedListIterator<Bill> iter = null;
		if (criteria.equals(BillCriteria.BILLDUEDATE)) {
			iter = iteratorByDate();
		} else if (criteria.equals(BillCriteria.BILLAMOUNT)) {
			iter = iteratorByAmount();
		} else if (criteria.equals(BillCriteria.BILLTYPE)) {
			iter = iteratorByType();
		}
		StringBuilder builder = new StringBuilder();
		while (iter.hasNext()) {
			builder.append(iter.next().toString());
		}
		return builder.toString();
	}
}
