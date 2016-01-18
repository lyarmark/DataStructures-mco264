package billOrganizer;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JComboBox<BillType> choices;

	private JButton doneButton;

	public JFrameAddBill() {
		setTitle("ADD A BILL");
		setSize(300, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container container = getContentPane();
		setLayout((new BoxLayout(container, BoxLayout.Y_AXIS)));

		this.vendorLabel = new JLabel("Enter the vendor name");
		this.amountDueLabel = new JLabel("Enter the amount due");
		this.dueDateLabel = new JLabel("Enter the due date");

		this.billTypeLabel = new JLabel("Enter the bill type");
		this.choices = new JComboBox<BillType>(BillType.values());
		this.choices.setSelectedIndex(-1);

		this.vendorText = new JTextField();
		this.amountDueText = new JTextField();
		this.dueDateText = new JTextField();

		this.doneButton = new JButton("ENTER BILL");

		add(vendorLabel);
		add(vendorText);
		add(amountDueLabel);
		add(amountDueText);
		add(dueDateLabel);
		add(dueDateText);
		add(billTypeLabel);
		add(choices);
		add(doneButton);

		getContentPane();
	}

	public Bill addBill() throws InvalidDataException {
		String vendor = vendorText.getText();
		double amountDue = Double.parseDouble(amountDueText.getText());

		String dueDate = dueDateText.getText();
		GregorianCalendar date = StaticMethods.convertStringToDate(dueDate);

		Object[] type = choices.getSelectedObjects();
		return new Bill(vendor, amountDue, date, (BillType) type[0]);
	}

	public JButton getDoneButton() {
		return this.doneButton;
	}
}
