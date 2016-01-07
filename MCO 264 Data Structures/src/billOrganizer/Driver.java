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

	public void doSomething(int selectedIndex) {
		switch (selectedIndex) {
		// Add a bill
		case 0: {
			JFrameAddBill addBillWindow = new JFrameAddBill();
			addBillWindow.setVisible(true);

			Bill bill = addBillWindow.getBillInfo();
			organizer.insert(bill);
			break;
		}
		/*
		 * // Total bills case 1: { double total = organizer.totalBills();
		 * JOptionPane.showMessageDialog(null, total); break; } // View bills
		 * sorted by date case 2: {
		 * organizer.toStringByCriteria(BillCriteria.BILLDUEDATE); break; } //
		 * View bills sorted by amount case 3: {
		 * organizer.toStringByCriteria(BillCriteria.BILLAMOUNT); break; } //
		 * View bills sorted by type case 4: {
		 * organizer.toStringByCriteria(BillCriteria.BILLTYPE); break; } // Pay
		 * next bill due case 5: { organizer.payNextBill(bill,
		 * BillCriteria.BILLDUEDATE); break; } // Pay next bill by amount case
		 * 6: { organizer.payNextBill(bill, BillCriteria.BILLAMOUNT); break; }
		 * // Pay next bill by type case 7: { organizer.payNextBill(bill,
		 * BillCriteria.BILLTYPE); break; } // Pay bill by Bill ID case 8: {
		 * organizer.paySpecificBill(billID); break; } // Save bill organizer
		 * case 9: {
		 * 
		 * break; }
		 */
		// Restore bill organizer
		case 10: {
			break;
		}
		}
	}
}
