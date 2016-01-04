package billOrganizer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Driver {
	private String[] options;
	private JList<String> jlist;
	private DefaultListModel<String> model;
	BillOrganizer organizer;

	public Driver(JList<String> jlist, DefaultListModel<String> model) {
		organizer = new BillOrganizer();

		options = new String[11];
		options[0] = "Add a bill";
		options[1] = "Total bills";
		options[2] = "View bills sorted by date";
		options[3] = "View bills sorted by amount";
		options[4] = "View bills sorted by type";
		options[5] = "Pay next bill due";
		options[6] = "Pay next bill by amount";
		options[7] = "Pay next bill by type";
		options[8] = "Pay bill by Bill ID";
		options[9] = "Save bill organizer";
		options[10] = "Restore bill organizer";

		this.jlist = jlist;
		this.model = model;
	}

	public void getList() {
		for (String s : options) {
			model.addElement(s);
		}
		this.jlist.setModel(model);
	}

	/*
	 * System.out.println("Create a bill organizer"); BillOrganizer organizer =
	 * new BillOrganizer(); System.out.println("Add a bill"); String vendor =
	 * "touro"; double amount = 10000.0; GregorianCalendar dueDate = new
	 * GregorianCalendar(); BillType type = BillType.EDUCATION;
	 * 
	 * Bill bill = new Bill(vendor, amount, dueDate, type);
	 * organizer.insert(bill); System.out.println("Total bills");
	 * System.out.println(organizer.totalBills());
	 * 
	 * BillCriteria criteria = BillCriteria.BILLAMOUNT;
	 * System.out.println(organizer.toStringByCriteria(criteria)); criteria =
	 * BillCriteria.BILLDUEDATE;
	 * System.out.println(organizer.toStringByCriteria(criteria)); criteria =
	 * BillCriteria.BILLTYPE;
	 * System.out.println(organizer.toStringByCriteria(criteria));
	 */

	public void doSomething(int selectedIndex) {
		int click = selectedIndex;
		switch (selectedIndex) {
		// Add a bill
		case 0: {
			organizer.insert(bill);
			break;
		}
		// Total bills
		case 1: {
			double total = organizer.totalBills();
			JOptionPane.showMessageDialog(null, total);
			break;
		}
		// View bills sorted by date
		case 2: {
			organizer.toStringByCriteria(BillCriteria.BILLDUEDATE);
			break;
		}
		// View bills sorted by amount
		case 3: {
			organizer.toStringByCriteria(BillCriteria.BILLAMOUNT);
			break;
		}
		// View bills sorted by type
		case 4: {
			organizer.toStringByCriteria(BillCriteria.BILLTYPE);
			break;
		}
		// Pay next bill due
		case 5: {
			organizer.payNextBill(bill, BillCriteria.BILLDUEDATE);
			break;
		}
		// Pay next bill by amount
		case 6: {
			organizer.payNextBill(bill, BillCriteria.BILLAMOUNT);
			break;
		}
		// Pay next bill by type
		case 7: {
			organizer.payNextBill(bill, BillCriteria.BILLTYPE);
			break;
		}
		// Pay bill by Bill ID
		case 8: {
			organizer.paySpecificBill(billID);
			break;
		}
		// Save bill organizer
		case 9: {

			break;
		}
		// Restore bill organizer
		case 10: {
			break;
		}
		}
	}
}
