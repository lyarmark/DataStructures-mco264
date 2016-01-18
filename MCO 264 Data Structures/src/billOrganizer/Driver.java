package billOrganizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Driver {
	private String[] options;
	private JList<String> jlist;
	private DefaultListModel<String> model;
	BillOrganizer organizer;

	public Driver(JList<String> jlist, DefaultListModel<String> model) {
		organizer = new BillOrganizer();

		options = new String[9];
		options[0] = "Add a bill";
		options[1] = "Total bills";
		options[2] = "View bills sorted by date";
		options[3] = "View bills sorted by amount";
		options[4] = "View bills sorted by type";
		options[5] = "Pay next bill";
		options[6] = "Pay bill by Bill ID";
		options[7] = "Save bill organizer";
		options[8] = "Restore bill organizer";

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
			case0();
			break;
		}

		// Total bills
		case 1: {
			case1();
			break;
		}
		// View bills sorted by date
		case 2: {
			// String[] text =
			// organizer.toStringByCriteria(BillCriteria.BILLDUEDATE);

			// JList<String> list = new JList<String>();
			// DefaultListModel<String> model = new DefaultListModel<String>();

			// for (String s : text) {
			// model.addElement(s);
			// }

			// list.setModel(model);
			// JPanel panel = new JPanel();
			// panel.add(list);
			// JOptionPane.showMessageDialog(null, panel);

			System.out.println(organizer.toStringByCriteria(BillCriteria.BILLDUEDATE));
			break;
		}
		// View bills sorted by amount
		case 3: {
			System.out.println(organizer.toStringByCriteria(BillCriteria.BILLAMOUNT));
			break;
		}

		// View bills sorted by type
		case 4: {
			JComboBox<BillCriteria> box = new JComboBox<BillCriteria>(BillCriteria.values());
			JOptionPane.showInputDialog(new JComboBox<BillCriteria>(), "View bills sorted by:", "View sorted bills",
					JOptionPane.QUESTION_MESSAGE, null, BillCriteria.values(), -1);
			Object[] criteria = box.getSelectedObjects();
			System.out.println(organizer.toStringByCriteria(BillCriteria.BILLTYPE));

			System.out.println(organizer.toStringByCriteria(BillCriteria.BILLTYPE));
			break;
		}

		// Pay next bill due
		case 5: {
			JComboBox<BillCriteria> box = new JComboBox<BillCriteria>(BillCriteria.values());
			JOptionPane.showInputDialog(new JComboBox<BillCriteria>(), "Pay next bill by criteria", "Pay next bill",
					JOptionPane.QUESTION_MESSAGE, null, BillCriteria.values(), -1);
			Object[] criteria = box.getSelectedObjects();
			try {
				organizer.payNextBill((BillCriteria) criteria[0]);
			} catch (ListEmptyException | NotFoundException e) {
				catchException(e);
			}
			break;
		}

		// Pay bill by Bill ID
		case 6: {
			String billID = JOptionPane.showInputDialog("Enter the bill ID");
			try {
				organizer.paySpecificBill(Integer.parseInt(billID));
			} catch (NumberFormatException | ListEmptyException | NotFoundException e) {
				catchException(e);
			}
			break;
		}
		/*
		 * // Save bill organizer case 9: {
		 * 
		 * break; }
		 */
		// Restore bill organizer

		}
	}

	private void case0() {
		JFrameAddBill addBillWindow = new JFrameAddBill();
		addBillWindow.setVisible(true);
		addBillWindow.getDoneButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Bill bill;
				try {
					bill = addBillWindow.addBill();
					organizer.insert(bill);
					addBillWindow.dispose();
				} catch (InvalidDataException | DuplicateDataException e1) {
					catchException(e1);
				}
			}
		});
	}

	private void case1() {
		double total = organizer.totalBills();
		DecimalFormat format = new DecimalFormat("$#,###,###,###,###0.00");
		JOptionPane.showMessageDialog(null, format.format(total));
	}

	private void case2() {
	}

	private void case3() {
	}

	private void case4() {
	}

	private void case5() {
	}

	private void case6() {
	}

	private void case7() {
	}

	private void case8() {
	}

	private void case9() {
	}

	private void catchException(Exception e) {
		JOptionPane.showMessageDialog(null, e.toString());
	}
}
