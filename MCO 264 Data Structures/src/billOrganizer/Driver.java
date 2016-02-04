package billOrganizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Driver {
	private String[] options;
	private JList<String> jlist;
	private DefaultListModel<String> model;
	BillOrganizer organizer;

	public Driver(JList<String> jlist, DefaultListModel<String> model) {
		// automatically need to start with an organizer before you can do
		// anything
		// if user wants to open a saved organizer, the instance variable will
		// be reset.
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

	public void doSomething(int selectedIndex, JFrameMenu menu) {
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
			LinkedListIterator<Bill> iter = organizer.iteratorByDate();
			viewSortedBills(iter);
			break;
		}
		// View bills sorted by amount
		case 3: {
			LinkedListIterator<Bill> iter = organizer.iteratorByAmount();
			viewSortedBills(iter);
			break;
		}
		// View bills sorted by type
		case 4: {
			LinkedListIterator<Bill> iter = organizer.iteratorByType();
			viewSortedBills(iter);
			break;
		}

		// Pay next bill due
		case 5: {
			case5();
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
		// Save bill organizer
		case 7: {
			try {
				JOptionPane.showMessageDialog(null, "Saving and exiting ... Have a nice day!");
				organizer.closeOrganizer();
				// close the window and program
				menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));
			} catch (IOException e) {
				e.printStackTrace();
				// JOptionPane.showMessageDialog(null, "File not found");
			}
			break;
		}
		// Restore bill organizer
		case 8: {
			case8();
			break;
		}
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
		JOptionPane.showMessageDialog(null, format.format(total), "Total bills", JOptionPane.INFORMATION_MESSAGE);
	}

	private void case5() {
		JComboBox<BillCriteria> box = new JComboBox<BillCriteria>(BillCriteria.values());
		JOptionPane.showInputDialog(box, "Pay next bill by criteria", "Pay next bill", JOptionPane.QUESTION_MESSAGE,
				null, BillCriteria.values(), null);
		// box.setSelectedIndex(-1);
		Object[] criteria = box.getSelectedObjects();
		try {
			organizer.payNextBill((BillCriteria) criteria[0]);
		} catch (ListEmptyException | NotFoundException e) {
			catchException(e);
		}
	}

	private void case8() {
		JFileChooser fileChooser = new JFileChooser();

		JOptionPane.showMessageDialog(null, "choose data file");
		fileChooser.showOpenDialog(null);
		File file = fileChooser.getSelectedFile();

		try {
			organizer = new BillOrganizer(file);
		} catch (ClassNotFoundException | IOException | DuplicateDataException e) {
			catchException(e);
		}
	}

	private void viewSortedBills(LinkedListIterator<Bill> iter) {
		JTextArea list = new JTextArea();

		if (iter.hasNext()) {
			while (iter.hasNext()) {
				list.append(iter.next().toString());
			}
		} else {
			list.setText("There are currently no bills in your organizer");
		}
		JOptionPane.showMessageDialog(null, list);
	}

	private void catchException(Exception e) {
		JOptionPane.showMessageDialog(null, e.toString());
	}
}
