package billOrganizer;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JFrameAddBill extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel vendorLabel;
	private JLabel amountDueLabel;
	private JLabel dueDateLabel;
	private JLabel billTypeLabel;

	private JTextField vendorText;
	private JTextField amountDueText;
	private JTextField dueDateText;
	private JTextField billTypeText;

	private JButton doneButton;

	private Bill bill;

	public JFrameAddBill() {
		setTitle("ADD A BILL");
		setSize(300, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container container = getContentPane();
		setLayout((new BoxLayout(container, BoxLayout.Y_AXIS)));

		this.vendorLabel = new JLabel("Enter the vendor name");
		this.amountDueLabel = new JLabel("Enter the amount due");
		this.dueDateLabel = new JLabel("Enter the due date");

		// MAKE THIS A COMBO BOX
		this.billTypeLabel = new JLabel("Enter the bill type");

		this.vendorText = new JTextField();
		this.amountDueText = new JTextField();
		this.dueDateText = new JTextField();
		this.billTypeText = new JTextField();

		this.doneButton = new JButton("ENTER BILL");

		this.doneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String vendor = vendorText.getText();
				double amountDue = Double.parseDouble(amountDueText.getText());

				String dueDate = dueDateText.getText();
				GregorianCalendar date = StaticMethods.convertStringToDate(dueDate);

				String billType = billTypeText.getText();
				BillType type = StaticMethods.validateBillType(billType);
				try {
					addBill(vendor, amountDue, date, type);
				} catch (InvalidDataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		add(vendorLabel);
		add(vendorText);
		add(amountDueLabel);
		add(amountDueText);
		add(dueDateLabel);
		add(dueDateText);
		add(billTypeLabel);
		add(billTypeText);
		add(doneButton);

		getContentPane();
	}

	private void addBill(String vendor, double amountDue, GregorianCalendar dueDate, BillType billType)
			throws InvalidDataException {
		this.bill = new Bill(vendor, amountDue, dueDate, billType);
	}

	public Bill getBillInfo() {
		return this.bill;
	}

	public boolean buttonClick() {
		return doneButton.isSelected();
		/*
		 * if (this.bill == null) { return false; } return true;
		 */
	}
}
