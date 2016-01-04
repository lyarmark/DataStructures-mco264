package billOrganizer;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class BillMenuJFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	Driver driver;
	JList<String> menu;
	DefaultListModel<String> model;

	public BillMenuJFrame() {
		setTitle("Bill Organizer Menu");
		setSize(300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());
		menu = new JList<String>();
		model = new DefaultListModel<String>();
		driver = new Driver(menu, model);
		driver.getList();

		menu.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		menu.setLayoutOrientation(JList.VERTICAL_WRAP);
		menu.setVisibleRowCount(-1);

		menu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {
					int click = menu.getSelectedIndex();
					try {
						driver.doSomething(menu.getSelectedIndex());
					} catch (ListEmptyException | NotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		add(menu, BorderLayout.CENTER);
		getContentPane();
	}
}
