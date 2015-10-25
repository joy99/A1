package model;

import java.awt.*;
import javax.swing.*;
import view.*;
import databases.MainDatabase;

/*---------------- the main class for the hub of the kiosk ----------------*/
public class a1 extends JFrame {

	private static final long serialVersionUID = 5651334255485459573L;

	private JPanel main;

	private CardLayout cl;

	private MainDatabase db = new MainDatabase();
	private WelcomePage wcp_page;
	private LoginPage lgp_page;
	private VehicleInfoPage1 vipg1;
	private VehicleInfoPage2 vipg2;
	private TicketPage tpg;

	public a1() {
		// begin by accessing the main database (i.e students.txt)
		db.access_database();

		// set the layout of the whole application to be of
		cl = new CardLayout();

		// the main panel which has the layout set as the cardlayout
		main = new JPanel();
		main.setLayout(cl);

		// declare all pages
		wcp_page = new WelcomePage(this);
		lgp_page = new LoginPage(this, db);
		vipg1 = new VehicleInfoPage1(this, db);
		vipg2 = new VehicleInfoPage2(this, db);
		tpg = new TicketPage(this, db);

		// listeners for all pages
		wcp_page.set_start_button_listener(cl, main, "2");
		lgp_page.set_login_button_listener(cl, main, "3");
		vipg1.set_proceed_button_listener(cl, main, "4");
		vipg2.set_back_button_listener(cl, main, "3");
		vipg2.set_proceed_button_listener(cl, main, "5");
		tpg.set_back_button_listener(cl, main, "4");

		// adding all pages
		main.add(wcp_page, "1");
		main.add(lgp_page, "2");
		main.add(vipg1, "3");
		main.add(vipg2, "4");
		main.add(tpg, "5");

		// begin by showing page-1 and add the main panel to the frame
		cl.show(main, "1");
		add(main);

		// sets the resizable to be false
		setResizable(false);

		// sets the screen size to be the maximum window size
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		setBounds(ge.getMaximumWindowBounds());

		// sets the default close operation to exit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// makes the screen full screeen by removing the decoration
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		// setting it to be visible
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new a1();
			}
		});
	}

}
