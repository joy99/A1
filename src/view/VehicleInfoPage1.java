package view;

import helper.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import control.*;
import model.*;
import databases.*;

/*---------------- the vehicle info page 1 class that enumerates the vehicle info of the user for the kiosk ----------------*/
public class VehicleInfoPage1 extends JPanel {

	/* list of all FINAL attributes used in the page */
	private static final long serialVersionUID = 2298670004759703146L;

	private final int KEYBOARD_FONT_SIZE = 26;

	private final Color BACKGROUND = Color.WHITE;
	private final Color SIDE_PANEL = new Color(255, 25, 25);

	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"hh:mm:ss a, MMM dd/yyyy");

	private final Border BLACK = BorderFactory.createLineBorder(Color.BLACK, 3);
	private final Border DEFAULT = new JTextField().getBorder();

	private final String COPYRIGHT_TEXT = "Copyright \u00a9 2015 - York University";

	private final Font PAGE_LABEL_FONT = new Font("Goudy Old Style Bold",
			Font.BOLD, 60);
	private final Font ENTRY_TEXT_FONT = new Font("Calibri", Font.PLAIN, 45);
	private final Font LABEL_FONT = new Font("Calibri", Font.BOLD, 55);
	private final Font TIME_FONT = new Font("Rockwell", Font.PLAIN, 40);
	private final Font COPYRIGHT_FONT = new Font("Calibri", Font.BOLD, 16);
	private final Font BUTTON_FONT = new Font("Calibri", Font.BOLD, 40);
	private final Font SIGNOUT_BUTTON_FONT = new Font("Calibri", Font.BOLD, 18);

	/*
	 * list of all the components used in the page
	 */
	private JPanel main_panel, time_panel, copyright_panel, login_panel,
			central_keyboard;
	private JLabel make_label, model_label, plate_num_label, time_label,
			copyright_label;

	private static JLabel login_name_label;
	private static JButton signout_button;
	private static MainDatabase db;
	private static JPanel signout_panel;
	private static int index;
	private static JTextField make_field, model_field, plate_num_field;
	private static JButton proceed_button;

	/***********
	 * public constructor with two arguments - Frame, Database
	 ***********/
	public VehicleInfoPage1(JFrame frame, MainDatabase d) {
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

		// initializes the login and sigout panel
		login_panel = new JPanel(new BorderLayout());
		login_panel.setBackground(BACKGROUND);
		login_panel.setPreferredSize(new Dimension(80, 50));

		signout_panel = new JPanel(new BorderLayout());
		signout_panel.setBackground(BACKGROUND);
		signout_panel.setPreferredSize(new Dimension(80, 25));

		// initialize the label for login name
		login_name_label = new JLabel("", JLabel.CENTER);
		login_panel.add(login_name_label, BorderLayout.EAST);

		// initialize the sign out button and add its listener
		signout_button = new JButton("Sign out");
		signout_button.setFont(SIGNOUT_BUTTON_FONT);
		signout_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new a1();
			}
		});

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
		copyright_panel.setPreferredSize(new Dimension(80, 220));
		copyright_panel.setBackground(SIDE_PANEL);

		copyright_label = new JLabel(COPYRIGHT_TEXT, JLabel.CENTER);
		copyright_label.setForeground(Color.WHITE);
		copyright_label.setFont(COPYRIGHT_FONT);

		copyright_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		copyright_panel.add(copyright_label, BorderLayout.CENTER);

		// northern panel of copyright panel used to hold the keyboard
		// the left and right panel are used as a glue panel so there can be
		// space to the
		// right and left of the keyboard
		JPanel copyright_up = new JPanel(new BorderLayout());
		copyright_up.setPreferredSize(new Dimension(80, 190));

		JPanel copyright_right = new JPanel(new FlowLayout(FlowLayout.CENTER));
		copyright_right.setBackground(BACKGROUND);

		JPanel copyright_left = new JPanel();
		copyright_left.setBackground(BACKGROUND);

		copyright_up.add(copyright_right, BorderLayout.WEST);
		copyright_up.add(copyright_left, BorderLayout.EAST);

		// the keyboard panel which is the center of the copyright up panel
		central_keyboard = new JPanel();
		central_keyboard.setBorder(BorderFactory.createEtchedBorder());
		central_keyboard.setBackground(new Color(246, 246, 246));
		copyright_up.add(central_keyboard);

		// finally adding the copyright up panel to the north of copyright panel
		copyright_panel.add(copyright_up, BorderLayout.NORTH);

		// adding the time panel and copyright panel to the page
		this.add(time_panel, BorderLayout.PAGE_START);
		this.add(copyright_panel, BorderLayout.PAGE_END);

		// main panel to hold the center elements
		main_panel = new JPanel(new BorderLayout());

		// left panel to the left of the main panel to hold the page name label
		// and logo
		JPanel left = new JPanel(new BorderLayout());
		left.setBackground(BACKGROUND);
		left.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit()
				.getScreenSize().getWidth() / 3.5), 35));

		JLabel label = new JLabel("<html><u>VEHICLE</u></html>", JLabel.CENTER);
		label.setFont(PAGE_LABEL_FONT);
		left.add(label, BorderLayout.NORTH);

		JLabel logo = new JLabel(new ImageIcon("images/vehicle_logo.jpg"));
		left.add(logo, BorderLayout.CENTER);

		// right panel used to create glue...holds nothing
		JPanel right = new JPanel();
		right.setBackground(BACKGROUND);
		right.setLayout(new BorderLayout());
		right.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit()
				.getScreenSize().getWidth() / 3), 35));

		// south panel of the right panel
		JPanel south = new JPanel(new BorderLayout());
		south.setBackground(BACKGROUND);
		right.add(south, BorderLayout.SOUTH);

		// right of the south of the right panel used to hold the proceed button
		// to the next page
		JPanel south_right = new JPanel();
		south_right.setBackground(BACKGROUND);

		proceed_button = new JButton("NEXT \u2192");
		proceed_button.setFont(BUTTON_FONT);
		south_right.add(proceed_button);

		south.add(south_right, BorderLayout.SOUTH);

		// center main panel to hold all the input fields and labels
		JPanel centerMain = new JPanel();
		// centerMain.setBackground(BACKGROUND);
		centerMain.setLayout(new BoxLayout(centerMain, BoxLayout.Y_AXIS));

		centerMain.add(Box.createGlue());
		get_make_label(centerMain);
		centerMain.add(Box.createGlue());
		get_make_field(centerMain);
		centerMain.add(Box.createGlue());
		get_model_label(centerMain);
		centerMain.add(Box.createGlue());
		get_model_field(centerMain);
		centerMain.add(Box.createGlue());
		get_plate_num_label(centerMain);
		centerMain.add(Box.createGlue());
		get_plate_num_field(centerMain);
		centerMain.add(Box.createGlue());

		// get the keyboard and glue in onto the panel
		make_field.setBorder(BLACK);
		get_alphabetical_keyboard(central_keyboard, make_field,
				KEYBOARD_FONT_SIZE);

		Border b = BorderFactory.createTitledBorder("");
		centerMain.setBorder(b);
		centerMain.setBackground(new Color(246, 246, 246));

		// add the center main to the main panel
		main_panel.add(centerMain, BorderLayout.CENTER);

		// add the right and the left to the main panel
		main_panel.add(left, BorderLayout.WEST);
		main_panel.add(right, BorderLayout.EAST);

		// add the main panel to this panel
		this.add(main_panel, BorderLayout.CENTER);
	}

	/*********** public method which gets the main page panel ***********/
	public JPanel get_panel() {
		return this;
	}

	/*********** public method which sets the login label to the current user name ***********/
	public static void get_login_name() {
		File f = new File("currentsession.txt");
		Scanner file_scan;
		try {
			file_scan = new Scanner(f);
			while (file_scan.hasNextLine()) {
				String line = file_scan.nextLine();
				if (line.matches("\\d{9}")) {
					index = db.find_index_of_student_number(line);
					login_name_label.setText("Logged in as: "
							+ db.get_student_name_list().get(index));
					login_name_label.setFont(new Font("Times new roman",
							Font.BOLD, 22));
					signout_panel.add(signout_button, BorderLayout.EAST);
				}
			}
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null,
					"Could not read from file " + f.getAbsolutePath(), "Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	/*********** public method which returns the make field text ***********/
	public static String get_make() {
		return make_field.getText();
	}

	/*********** public method which returns the model field text ***********/
	public static String get_model() {
		return model_field.getText();
	}

	/*********** public method which returns the plate field text ***********/
	public static String get_plate() {
		return plate_num_field.getText();
	}

	/*********** public method which sets the model field text ***********/
	public static void set_model_field(String s) {
		model_field.setText(s);
	}

	/*********** public method which sets the make field text ***********/
	public static void set_make_field(String s) {
		make_field.setText(s);
	}

	/*********** public method which sets the plate field text ***********/
	public static void set_plate_num_field(String s) {
		plate_num_field.setText(s);
	}

	/*********** public method which sets the proceed button listener ***********/
	public void set_proceed_button_listener(CardLayout layout, JPanel panel,
			String name) {
		proceed_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				layout.show(panel, name);
			}
		});
	}

	/***********
	 * private method which sets up a default alphabetical keyboard from the
	 * alphabetical keyboard class
	 ***********/
	private void get_alphabetical_keyboard(JPanel panel, JTextField field,
			int size) {
		new AlphabeticalExternalKeyboard(panel, field, size);
	}

	/*********** private method which gets the make label given a panel ***********/
	private void get_make_label(JPanel panel) {
		make_label = new JLabel("MAKE");
		make_label.setFont(LABEL_FONT);
		make_label.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(make_label);
	}

	/*********** private method which gets the make text field given a panel ***********/
	private void get_make_field(JPanel panel) {
		make_field = new JTextField(20);
		make_field.setFont(ENTRY_TEXT_FONT);
		make_field.setMaximumSize(make_field.getPreferredSize());
		make_field.setAlignmentX(Component.CENTER_ALIGNMENT);
		make_field.setHorizontalAlignment(JTextField.CENTER);

		AlphabeticalKeyboardHandlerClass handler = new AlphabeticalKeyboardHandlerClass();
		make_field.addFocusListener(handler);

		make_field.setCaretColor(Color.WHITE);

		panel.add(make_field);
	}

	/*********** private method which gets the model label given a panel ***********/
	private void get_model_label(JPanel panel) {
		model_label = new JLabel("MODEL");
		model_label.setFont(LABEL_FONT);
		model_label.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(model_label);
	}

	/*********** private method which gets the model text field given a panel ***********/
	private void get_model_field(JPanel panel) {
		model_field = new JTextField(20);
		model_field.setFont(ENTRY_TEXT_FONT);
		model_field.setMaximumSize(model_field.getPreferredSize());
		model_field.setAlignmentX(Component.CENTER_ALIGNMENT);
		model_field.setHorizontalAlignment(JTextField.CENTER);

		AlphabeticalKeyboardHandlerClass handler = new AlphabeticalKeyboardHandlerClass();
		model_field.addFocusListener(handler);

		model_field.setCaretColor(Color.WHITE);

		panel.add(model_field);
	}

	/*********** private method which gets the plate num label given a panel ***********/
	private void get_plate_num_label(JPanel panel) {
		plate_num_label = new JLabel("PLATE NUMBER");
		plate_num_label.setFont(LABEL_FONT);
		plate_num_label.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(plate_num_label);
	}

	/*********** private method which gets the plate num text field given a panel ***********/
	private void get_plate_num_field(JPanel panel) {
		plate_num_field = new JTextField(20);
		plate_num_field.setFont(ENTRY_TEXT_FONT);
		plate_num_field.setMaximumSize(plate_num_field.getPreferredSize());
		plate_num_field.setAlignmentX(Component.CENTER_ALIGNMENT);
		plate_num_field.setHorizontalAlignment(JTextField.CENTER);

		AlphabeticalKeyboardHandlerClass handler = new AlphabeticalKeyboardHandlerClass();
		plate_num_field.addFocusListener(handler);

		plate_num_field.setCaretColor(Color.WHITE);

		panel.add(plate_num_field);
	}

	/***********
	 * private class which which enumerates the focus listeneres on three
	 * textfields
	 ***********/
	private class AlphabeticalKeyboardHandlerClass implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			if (arg0.getSource() == make_field) {
				make_field.setBorder(BLACK);
				model_field.setBorder(DEFAULT);
				plate_num_field.setBorder(DEFAULT);

				central_keyboard.removeAll();
				new AlphabeticalExternalKeyboard(central_keyboard, make_field,
						KEYBOARD_FONT_SIZE);
				central_keyboard.revalidate();
			} else if (arg0.getSource() == model_field) {
				make_field.setBorder(DEFAULT);
				model_field.setBorder(BLACK);
				plate_num_field.setBorder(DEFAULT);

				central_keyboard.removeAll();
				new AlphabeticalExternalKeyboard(central_keyboard, model_field,
						KEYBOARD_FONT_SIZE);
				central_keyboard.revalidate();
			} else if (arg0.getSource() == plate_num_field) {
				make_field.setBorder(DEFAULT);
				model_field.setBorder(DEFAULT);
				plate_num_field.setBorder(BLACK);

				central_keyboard.removeAll();
				new AlphabeticalExternalKeyboard(central_keyboard,
						plate_num_field, KEYBOARD_FONT_SIZE);
				central_keyboard.revalidate();
			}
		}

		@Override
		public void focusLost(FocusEvent arg0) {
		}
	}
}