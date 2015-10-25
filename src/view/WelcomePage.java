package view;

import helper.Time;

import java.awt.*;
import java.awt.event.*;
import java.text.*;

import javax.swing.*;

import control.NumericalExternalKeyboard;

/*---------------- the welcome page class that enumerates the welcome page for the kiosk ----------------*/
public class WelcomePage extends JPanel {

	/* list of all FINAL attributes used in the page */
	private static final long serialVersionUID = 6780558661868979313L;

	private final Color BACKGROUND = Color.WHITE;
	private final Color SIDE_PANEL = new Color(255, 25, 25);

	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"hh:mm:ss a, MMM dd/yyyy");

	private final ImageIcon IMAGE = new ImageIcon("images/yorklogo.jpg");

	private final String WELCOME_TEXT = "Welcome to York University Parking Services";
	private final String COPYRIGHT_TEXT = "Copyright \u00a9 2015 - York University";

	private final Color BUTTON_COLOR = new Color(153, 204, 255);

	private final Font TIME_FONT = new Font("Rockwell", Font.PLAIN, 40);
	private final Font COPYRIGHT_FONT = new Font("Calibri", Font.BOLD, 16);
	private final Font BUTTON_FONT = new Font("Calibri", Font.BOLD, 60);
	private final Font WELCOME_TEXT_FONT = new Font("Calibri", Font.BOLD, 70);

	/*
	 * list of all the components used in the page
	 */
	private JPanel center_panel, time_panel, copyright_panel, login_panel,
			signout_panel;
	private JLabel time_label, copyright_label, picture_label, welcome_label;
	private JButton start_button;

	/*********** public constructor with one argument - Frame ***********/
	public WelcomePage(JFrame frame) {
		// initializes the whole page content holder
		this.setLayout(new BorderLayout());
		this.setBackground(BACKGROUND);

		// initializes the time panel holder
		time_panel = new JPanel();
		time_panel.setPreferredSize(new Dimension(80, 100));
		time_panel.setLayout(new BorderLayout());
		time_panel.setBackground(SIDE_PANEL);

		// initializes the time label and add its to the time panel
		time_label = new JLabel("", JLabel.CENTER);
		time_label.setForeground(Color.WHITE);
		time_label.setFont(TIME_FONT);
		time_panel.add(time_label, BorderLayout.CENTER);

		// initializes the hidden panels
		login_panel = new JPanel(new BorderLayout());
		login_panel.setBackground(BACKGROUND);
		login_panel.setPreferredSize(new Dimension(80, 50));

		signout_panel = new JPanel(new BorderLayout());
		signout_panel.setBackground(BACKGROUND);
		signout_panel.setPreferredSize(new Dimension(80, 25));

		// local variable for the power button which is used to close the whole
		// kiosk down -- only for administrative use.
		JLabel power_label = new JLabel(new ImageIcon("images/power.jpg"));

		power_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		power_label.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Object[] options = { "Yes, continue. I have the authority.",
						"No, go back. I do not have the authority." };

				int n = JOptionPane
						.showOptionDialog(
								null,
								"<html><font color=\"red\", size=\"5\">DISCLAIMER: THIS AREA IS ONLY FOR ADMINISTRATIVE USE.\n"
										+ "<html><font color=\"black\", size=\"4\">Are you sure you want to proceed?",
								"", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.WARNING_MESSAGE, null, options,
								options[1]);

				// checks if the user decided to turn off the app
				if (n == 0) {
					// creates our own new panel with the keyboard
					JPanel panel = new JPanel(new BorderLayout());

					JTextField passcode_field = new JPasswordField(10);

					JLabel label = new JLabel("Enter 4-digit passcode");
					label.setFont(new Font("Calibri", Font.PLAIN, 20));

					JPanel panel_north = new JPanel();
					panel_north.add(label);
					panel_north.add(passcode_field);

					panel.add(panel_north, BorderLayout.NORTH);

					JPanel panel_center = new JPanel();

					get_numerical_keyboard(panel_center, passcode_field, 18);

					panel.add(panel_center, BorderLayout.CENTER);
					JOptionPane.showMessageDialog(null, panel, "",
							JOptionPane.PLAIN_MESSAGE);

					// if the user pass matches then shut off the app
					if (passcode_field.getText().equalsIgnoreCase("1234"))
						System.exit(0);
					else
						;
				} else
					;
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		// adding each component one by one, firstly adding the power label to
		// the signout panel and then addign the signout panel to the login
		// panel
		// and then adding the login panel to the time panel
		signout_panel.add(power_label, BorderLayout.CENTER);
		login_panel.add(signout_panel, BorderLayout.SOUTH);
		time_panel.add(login_panel, BorderLayout.SOUTH);

		// the timer which stimulates the real time clock
		Time time = new Time(time_label, DATE_FORMAT);
		time.start();

		// the copyright panel which is located in the south of the page and
		// includes the copyright label
		copyright_panel = new JPanel(new BorderLayout());
		copyright_panel.setPreferredSize(new Dimension(80, 30));
		copyright_panel.setBackground(SIDE_PANEL);
		copyright_label = new JLabel(COPYRIGHT_TEXT, JLabel.CENTER);
		copyright_label.setForeground(Color.WHITE);
		copyright_label.setFont(COPYRIGHT_FONT);
		copyright_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		copyright_panel.add(copyright_label, BorderLayout.CENTER);

		// adding the time panel and the copyright both to the opposite sides of
		// the page, which then sets up free space for the center panel
		this.add(time_panel, BorderLayout.PAGE_START);
		this.add(copyright_panel, BorderLayout.PAGE_END);

		// the center panel which holds the logo, welcome label and the start
		// button
		center_panel = new JPanel();
		center_panel.setBackground(BACKGROUND);
		center_panel.setLayout(new BorderLayout());

		// local jpanel to hold the yorku logo
		JPanel center_north = new JPanel();
		center_north.setBackground(BACKGROUND);
		get_picture(center_north);
		center_panel.add(center_north, BorderLayout.NORTH);

		// local panel to hold the welcome text on the north of this panel and
		// hold the start button on the
		// center of this panel
		JPanel center_center = new JPanel(new BorderLayout());
		center_center.setBackground(BACKGROUND);

		JPanel center_center_north = new JPanel();
		center_center_north.setBackground(BACKGROUND);
		get_welcome_label(center_center_north);

		JPanel center_of_center = new JPanel(new GridBagLayout());
		center_of_center.setBackground(BACKGROUND);
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.CENTER;
		start_button = new JButton("START");
		start_button.setFont(BUTTON_FONT);
		start_button.setBackground(BUTTON_COLOR);
		start_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		center_of_center.add(start_button, c1);

		center_center.add(center_center_north, BorderLayout.NORTH);
		center_center.add(center_of_center, BorderLayout.CENTER);

		// adding the center to the center panel
		center_panel.add(center_center, BorderLayout.CENTER);

		// finally adding the center panel to the main page panel
		this.add(center_panel, BorderLayout.CENTER);
	}

	/*********** public method which gets the main page panel ***********/
	public JPanel get_panel() {
		return this;
	}

	/*********** public method which sets a listener to the start button ***********/
	public void set_start_button_listener(CardLayout layout, JPanel panel,
			String name) {
		start_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				layout.show(panel, name);
			}
		});
	}

	/*********** private method which gets the york university logo given a panel ***********/
	private void get_picture(JPanel panel) {
		picture_label = new JLabel(IMAGE);
		picture_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(picture_label);
	}

	/*********** private method which gets the welcome text given a panel ***********/
	private void get_welcome_label(JPanel panel) {
		welcome_label = new JLabel(WELCOME_TEXT, JLabel.CENTER);
		welcome_label.setFont(WELCOME_TEXT_FONT);
		welcome_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(welcome_label);
	}

	/***********
	 * private method which gets the numerical keyboard given a panel,
	 * textfield, and font size
	 ***********/
	private void get_numerical_keyboard(JPanel panel, JTextField field, int size) {
		new NumericalExternalKeyboard(panel, field, size);
	}
}
