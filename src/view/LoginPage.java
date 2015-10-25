package view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;

import javax.swing.*;
import javax.swing.border.*;

import helper.Time;
import control.NumericalExternalKeyboard;
import databases.AccessDatabase;
import databases.MainDatabase;

/*---------------- the login page class that enumerates the login page for the kiosk ----------------*/
public class LoginPage extends JPanel {

	/* list of all FINAL attributes used in the page */
	private static final long serialVersionUID = 3814792608140287765L;

	private final int KEYBOARD_FONT_SIZE = 26;

	private final Color BACKGROUND = Color.WHITE;
	private final Color SIDE_PANEL = new Color(255, 25, 25);

	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"hh:mm:ss a, MMM dd/yyyy");

	private final String REGEX_STUD_NUM = "\\d{9}";
	private final String REGEX_PIN_NUM = "\\d{4}";

	private final Border DEFAULT = new JTextField().getBorder();
	private final Border RED = BorderFactory.createLineBorder(Color.RED, 3);
	private final Border BLACK = BorderFactory.createLineBorder(Color.BLACK, 3);

	private final String COPYRIGHT_TEXT = "Copyright \u00a9 2015 - York University";

	private final String ERROR_INVALID_STUDENT_NUMBER = "Please enter a 9 digit valid student number.";
	private final String ERROR_INVALID_PIN_NUMBER = "Please enter a 4 digit valid pin number.";
	private final String ERROR_STUDENT_NOT_IN_DATABASE = "Student not in database.";
	private final String ERROR_WRONG_PIN_NUMBER = "Incorrect pin. Please try again.";

	private final Font PAGE_LABEL_FONT = new Font("Goudy Old Style Bold",
			Font.BOLD, 60);
	private final Font ENTRY_TEXT_FONT = new Font("Calibri", Font.PLAIN, 45);
	private final Font ERROR_FONT = new Font("Calibri", Font.BOLD, 26);
	private final Font LABEL_FONT = new Font("Calibri", Font.BOLD, 55);
	private final Font TIME_FONT = new Font("Rockwell", Font.PLAIN, 40);
	private final Font COPYRIGHT_FONT = new Font("Calibri", Font.BOLD, 16);
	private final Font BUTTON_FONT = new Font("Calibri", Font.BOLD, 40);

	/*
	 * list of all the components used in the page
	 */
	private MainDatabase db;
	private JPanel main_panel, login_panel, signout_panel, time_panel,
			copyright_panel, keyboard, center;
	private JLabel stud_num_label, pin_num_label, time_label, copyright_label,
			error_label;
	public JTextField pin_num_field;
	private JButton login_button;

	public static JTextField stud_num_field;

	/***********
	 * public constructor with two arguments - Frame, Database
	 ***********/
	public LoginPage(JFrame frame, MainDatabase d) {
		// initializes the whole page content holder
		this.setLayout(new BorderLayout());
		this.setBackground(BACKGROUND);

		// initializes the database to access student info
		db = d;

		// initializes the time panel holder
		time_panel = new JPanel();
		time_panel.setPreferredSize(new Dimension(80, 100));
		time_panel.setLayout(new BorderLayout());
		time_panel.setBackground(SIDE_PANEL);

		// initializes the time label and add its to the time panel
		time_label = new JLabel("", JLabel.CENTER);
		time_label.setFont(TIME_FONT);
		time_label.setForeground(Color.WHITE);
		time_panel.add(time_label, BorderLayout.CENTER);

		// initializes the hidden panels
		login_panel = new JPanel(new BorderLayout());
		login_panel.setBackground(BACKGROUND);
		login_panel.setPreferredSize(new Dimension(80, 50));

		signout_panel = new JPanel(new BorderLayout());
		signout_panel.setBackground(BACKGROUND);
		signout_panel.setPreferredSize(new Dimension(80, 25));

		// adding each component one by one, addign the signout panel to the
		// login panel
		// and then adding the login panel to the time panel
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

		// main panel to hold the center elements
		main_panel = new JPanel(new BorderLayout());

		// left panel to the left of the main panel to hold the page name label
		// and login logo
		JPanel left = new JPanel(new BorderLayout());
		left.setBackground(BACKGROUND);
		left.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit()
				.getScreenSize().getWidth() / 4), 35));

		JLabel label = new JLabel("<html><u>SIGN IN</u></html>", JLabel.CENTER);
		label.setFont(PAGE_LABEL_FONT);
		left.add(label, BorderLayout.NORTH);

		JLabel logo = new JLabel(new ImageIcon("images/login_logo.jpg"));
		left.add(logo, BorderLayout.CENTER);

		// right panel to the right of the main panel to hold the keyboard in
		// the center of the panel
		// usses north, south, east to make perfect placement for the keyboard
		JPanel right = new JPanel();
		right.setBackground(BACKGROUND);
		right.setLayout(new BorderLayout());
		right.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit()
				.getScreenSize().getWidth() / 2.5), 35));

		JPanel north = new JPanel();
		north.setBackground(BACKGROUND);
		north.setPreferredSize(new Dimension(10, 180));

		right.add(north, BorderLayout.NORTH);

		JPanel south = new JPanel();
		south.setBackground(BACKGROUND);
		south.setPreferredSize(new Dimension(10, 180));

		right.add(south, BorderLayout.SOUTH);

		JPanel east = new JPanel();
		east.setBackground(BACKGROUND);

		right.add(east, BorderLayout.EAST);

		JPanel west = new JPanel();
		west.setBackground(BACKGROUND);

		right.add(west, BorderLayout.WEST);

		keyboard = new JPanel();
		// keyboard.setBackground(BACKGROUND);
		keyboard.setBackground(new Color(246, 246, 246));
		keyboard.setLayout(new BorderLayout());
		keyboard.setBorder(BorderFactory.createEtchedBorder());

		right.add(keyboard, BorderLayout.CENTER);

		// adds the left and right panel to the main panel
		main_panel.add(left, BorderLayout.WEST);
		main_panel.add(right, BorderLayout.EAST);

		// center main is in the center of the main panel which is used to hold
		// the main contents
		// such as login label and login box and pin label and pin box and
		// proceed button
		center = new JPanel();
		center.setBackground(BACKGROUND);
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

		center.add(Box.createGlue());
		get_student_number_label(center);
		center.add(Box.createGlue());

		get_student_number_field(center);
		stud_num_field.setBorder(BLACK);
		get_numerical_keyboard(keyboard, stud_num_field, KEYBOARD_FONT_SIZE);
		center.add(Box.createGlue());
		get_pin_number_label(center);
		center.add(Box.createGlue());
		get_pin_number_field(center);
		center.add(Box.createGlue());
		get_login_button(center);
		center.add(Box.createGlue());
		get_error_label(center);

		Border b = BorderFactory.createTitledBorder("");
		center.setBorder(b);
		center.setBackground(new Color(246, 246, 246));

		// adds the center main to the main panel
		main_panel.add(center, BorderLayout.CENTER);

		// adds the main panel to THIS panel
		this.add(main_panel, BorderLayout.CENTER);
	}

	/*********** public method which gets the main page panel ***********/
	public JPanel get_panel() {
		return this;
	}

	/*********** public method which returns the student number ***********/
	public static String get_number() {
		return stud_num_field.getText();
	}

	/*********** public method which sets a listener to the login button ***********/
	public void set_login_button_listener(CardLayout layout, JPanel panel,
			String name) {
		login_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// finds the location index of the current student number
				// inputed by the user
				int loc = db.find_index_of_student_number(stud_num_field
						.getText());

				// checks all the conditions for user logins
				if (stud_num_field.getText().length() == 0
						|| !stud_num_field.getText().matches(REGEX_STUD_NUM)) {
					error_label.removeAll();
					error_label.setText(ERROR_INVALID_STUDENT_NUMBER);
					error_label.setVisible(true);
					error_label.setForeground(Color.RED);
					error_label.setAlignmentX(Component.CENTER_ALIGNMENT);

					stud_num_field.setBorder(RED);
					keyboard.removeAll();
					new NumericalExternalKeyboard(keyboard, null,
							KEYBOARD_FONT_SIZE);
					keyboard.revalidate();
					pin_num_field.setBorder(DEFAULT);

					stud_num_field.setText("");
					pin_num_field.setText("");
				} else if (pin_num_field.getText().length() == 0
						|| !pin_num_field.getText().matches(REGEX_PIN_NUM)) {
					error_label.removeAll();
					error_label.setText(ERROR_INVALID_PIN_NUMBER);
					error_label.setVisible(true);
					error_label.setForeground(Color.RED);
					error_label.setAlignmentX(Component.CENTER_ALIGNMENT);

					pin_num_field.setBorder(RED);
					keyboard.removeAll();
					new NumericalExternalKeyboard(keyboard, null,
							KEYBOARD_FONT_SIZE);
					keyboard.revalidate();
					stud_num_field.setBorder(DEFAULT);

					pin_num_field.setText("");
				} else if (!db.get_student_number_list().get(loc)
						.equalsIgnoreCase(stud_num_field.getText())) {
					error_label.removeAll();
					error_label.setText(ERROR_STUDENT_NOT_IN_DATABASE);
					error_label.setVisible(true);
					error_label.setForeground(Color.RED);
					error_label.setAlignmentX(Component.CENTER_ALIGNMENT);

					pin_num_field.setBorder(DEFAULT);
					stud_num_field.setBorder(RED);
					keyboard.removeAll();
					new NumericalExternalKeyboard(keyboard, null,
							KEYBOARD_FONT_SIZE);
					keyboard.revalidate();

					stud_num_field.setText("");
					pin_num_field.setText("");
				} else if (!db.get_pin_number_list().get(loc)
						.equalsIgnoreCase(pin_num_field.getText())) {
					error_label.removeAll();
					error_label.setText(ERROR_WRONG_PIN_NUMBER);
					error_label.setVisible(true);
					error_label.setForeground(Color.RED);
					error_label.setAlignmentX(Component.CENTER_ALIGNMENT);

					pin_num_field.setBorder(RED);
					keyboard.removeAll();
					new NumericalExternalKeyboard(keyboard, null,
							KEYBOARD_FONT_SIZE);
					keyboard.revalidate();
					stud_num_field.setBorder(DEFAULT);

					pin_num_field.setText("");
				} else {
					// this case deals with the else statement meaning it only
					// works when the user enters a valid student number
					// followed by a valid pin number and is in the database and
					// does not owe any money
					stud_num_field.setBorder(DEFAULT);
					pin_num_field.setBorder(DEFAULT);
					File f = new File("currentsession.txt");

					FileWriter fw;
					try {
						fw = new FileWriter(f.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(stud_num_field.getText());
						bw.close();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(
								null,
								"Could not write to file "
										+ f.getAbsolutePath(), "Error",
								JOptionPane.ERROR_MESSAGE);
						System.exit(0);
					}

					VehicleInfoPage1.get_login_name();
					VehicleInfoPage2.get_login_name();
					TicketPage.get_login_name();

					// accessing the access database which has all the info of
					// the current user and glue the info
					// into each page
					AccessDatabase adb = new AccessDatabase(stud_num_field
							.getText());
					adb.access_database();

					VehicleInfoPage1.set_make_field(adb.get_make());
					VehicleInfoPage1.set_model_field(adb.get_model());
					VehicleInfoPage1.set_plate_num_field(adb.get_plate_num());

					VehicleInfoPage2.set_chosen_company(adb
							.get_insurance_company());
					VehicleInfoPage2.set_policy_num(adb.get_policy_num());

					layout.show(panel, name);
				}
			}
		});
	}

	/***********
	 * private method which sets up a default numerical keyboard from the
	 * numerical keyboard class
	 ***********/
	private void get_numerical_keyboard(JPanel panel, JTextField field, int size) {
		new NumericalExternalKeyboard(panel, field, size);
	}

	/*********** private method which gets the student number label given a panel ***********/
	private void get_student_number_label(JPanel panel) {
		stud_num_label = new JLabel("STUDENT NUMBER");
		stud_num_label.setFont(LABEL_FONT);
		stud_num_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(stud_num_label);
	}

	/*********** private method which gets the student number textfield given a panel ***********/
	private void get_student_number_field(JPanel panel) {
		stud_num_field = new JTextField(20);

		stud_num_field.setFont(ENTRY_TEXT_FONT);
		stud_num_field.setMaximumSize(stud_num_field.getPreferredSize());
		stud_num_field.setAlignmentX(Component.CENTER_ALIGNMENT);
		stud_num_field.setHorizontalAlignment(JTextField.CENTER);

		NumericalKeyboardHandlerClass handler = new NumericalKeyboardHandlerClass();
		stud_num_field.addFocusListener(handler);

		stud_num_field.setCaretColor(Color.WHITE);

		panel.add(stud_num_field);
	}

	/*********** private method which gets the pin number label given a panel ***********/
	private void get_pin_number_label(JPanel panel) {
		pin_num_label = new JLabel("PIN NUMBER");
		pin_num_label.setFont(LABEL_FONT);
		pin_num_label.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(pin_num_label);
	}

	/*********** private method which gets the pin number textfield given a panel ***********/
	private void get_pin_number_field(JPanel panel) {
		pin_num_field = new JPasswordField(20);

		pin_num_field.setFont(ENTRY_TEXT_FONT);
		pin_num_field.setMaximumSize(pin_num_field.getPreferredSize());
		pin_num_field.setAlignmentX(Component.CENTER_ALIGNMENT);
		pin_num_field.setHorizontalAlignment(JTextField.CENTER);

		NumericalKeyboardHandlerClass handler = new NumericalKeyboardHandlerClass();
		pin_num_field.addFocusListener(handler);

		pin_num_field.setCaretColor(Color.WHITE);

		panel.add(pin_num_field);
	}

	/*********** private method which gets the login button given a panel ***********/
	private void get_login_button(JPanel panel) {
		login_button = new JButton("LOG IN");

		login_button.setFont(BUTTON_FONT);
		login_button.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(login_button);
	}

	/*********** private method which gets the error label given a panel ***********/
	private void get_error_label(JPanel panel) {
		error_label = new JLabel("");

		error_label.setFont(ERROR_FONT);
		error_label.setVisible(false);
		panel.add(error_label);
	}

	/***********
	 * private class which which enumerates the focus listeneres on two
	 * textfields
	 ***********/
	private class NumericalKeyboardHandlerClass implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			if (arg0.getSource() == stud_num_field) {
				stud_num_field.setBorder(BLACK);
				pin_num_field.setBorder(DEFAULT);

				error_label.setText("");
				error_label.setVisible(false);

				keyboard.removeAll();
				new NumericalExternalKeyboard(keyboard, stud_num_field,
						KEYBOARD_FONT_SIZE);
				keyboard.revalidate();
			} else if (arg0.getSource() == pin_num_field) {
				pin_num_field.setBorder(BLACK);
				stud_num_field.setBorder(DEFAULT);

				error_label.setText("");
				error_label.setVisible(false);

				keyboard.removeAll();
				new NumericalExternalKeyboard(keyboard, pin_num_field,
						KEYBOARD_FONT_SIZE);
				keyboard.revalidate();
			} else
				;
		}

		@Override
		public void focusLost(FocusEvent arg0) {
		}
	}
}
