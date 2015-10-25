package view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.*;

import model.a1;
import helper.DatePicker;
import helper.ObservingTextField;
import helper.Time;
import control.AlphabeticalExternalKeyboard;
import databases.MainDatabase;

/*---------------- the ticket page class that enumerates the e-ticket of the user for the kiosk ----------------*/
public class TicketPage extends JPanel {

	/* list of all FINAL attributes used in the page */
	private static final long serialVersionUID = -5208631070615472600L;

	private final int KEYBOARD_FONT_SIZE = 26;

	private final Color BACKGROUND = Color.WHITE;
	private final Color SIDE_PANEL = new Color(255, 25, 25);

	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"hh:mm:ss a, MMM dd/yyyy");

	private final Border BLACK = BorderFactory.createLineBorder(Color.BLACK, 3);
	private final Border DEFAULT = new JTextField().getBorder();

	private final String COPYRIGHT_TEXT = "Copyright \u00a9 2015 - York University";

	private final Font ERROR_FONT = new Font("Calibri", Font.BOLD, 24);
	private final Font PAGE_LABEL_FONT = new Font("Goudy Old Style Bold",
			Font.BOLD, 60);
	private final Font ENTRY_TEXT_FONT = new Font("Calibri", Font.PLAIN, 45);
	private final Font LABEL_FONT = new Font("Calibri", Font.BOLD, 55);
	private final Font TIME_FONT = new Font("Rockwell", Font.PLAIN, 40);
	private final Font COPYRIGHT_FONT = new Font("Calibri", Font.BOLD, 16);
	private final Font BUTTON_FONT = new Font("Calibri", Font.BOLD, 40);
	private final Font SIGNOUT_BUTTON_FONT = new Font("Calibri", Font.BOLD, 18);

	private final String regex_email = "^[A-Z|a-z]+[A-Z|a-z|0-9|.|-|_]*@[A-Z|a-z]+.[A-Z|a-z]+";

	private final Border RED = BorderFactory.createLineBorder(Color.RED, 3);
	private final DateHandlerClass handler = new DateHandlerClass();

	/*
	 * list of all the components used in the page
	 */
	private static JLabel login_name_label;
	private static int index;
	private static MainDatabase db;
	private static JPanel signout_panel;
	private static JButton signout_button;

	private JPanel time_panel, login_panel, copyright_panel, main_panel,
			centerMain, central_keyboard, south, ticket_panel, ticket_north,
			ticket_panel_main, centerWest;
	private JLabel time_label, copyright_label, exp_date_label,
			subscribe_label, error_label, ticket_north_label, name_ticket,
			stud_num_ticket, ticket_length_date, ticket_length_days, amount,
			picture_bar_code, note, parking_lot;

	private ObservingTextField exp_date_field;
	private JButton proceed_button;

	private JButton subscribe_button, back_button, pay_now, finish;

	private JTextField email_field;

	private Border b;

	/***********
	 * public constructor with two arguments - Frame, Database
	 ***********/
	public TicketPage(JFrame frame, MainDatabase d) {
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
		central_keyboard.setBackground(BACKGROUND);
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

		JLabel label = new JLabel("<html><u>E-TICKET</u></html>", JLabel.CENTER);
		label.setFont(PAGE_LABEL_FONT);
		left.add(label, BorderLayout.NORTH);

		JLabel logo = new JLabel(new ImageIcon("images/ticket_logo.jpg"));
		left.add(logo, BorderLayout.CENTER);

		// south left panel to add the go back button
		JPanel south_left = new JPanel(new FlowLayout(FlowLayout.CENTER));
		south_left.setBackground(BACKGROUND);

		back_button = new JButton("\u2190 BACK");
		back_button.setFont(BUTTON_FONT);
		south_left.add(back_button);

		left.add(south_left, BorderLayout.SOUTH);

		// adding the left panel to the main panel
		main_panel.add(left, BorderLayout.WEST);

		// the center main panel to hold all the center elements
		centerMain = new JPanel();
		centerMain.setBackground(BACKGROUND);
		centerMain.setLayout(new BorderLayout());

		// center west panel which holds the user input infos
		centerWest = new JPanel();
		centerWest.setBackground(BACKGROUND);
		centerWest.setLayout(new BoxLayout(centerWest, BoxLayout.Y_AXIS));

		centerWest.add(Box.createGlue());
		get_expiry_date_label(centerWest);
		centerWest.add(Box.createGlue());
		get_expiry_date_field(centerWest);
		exp_date_field.setBorder(BLACK);
		centerWest.add(Box.createGlue());
		get_proceed_button(centerWest);
		centerWest.add(Box.createGlue());
		get_pay_button(centerWest);
		centerWest.add(Box.createGlue());
		get_error_label(centerWest);
		centerWest.add(Box.createGlue());
		get_finish_button(centerWest);
		centerWest.add(Box.createGlue());

		// action listener for finish button
		finish.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new a1();
			}
		});

		// adding the center west panel to the west of the center main
		centerMain.add(centerWest, BorderLayout.WEST);

		// center east panel to hold the ticket
		JPanel centerEast = new JPanel();
		centerEast.setBackground(BACKGROUND);
		centerEast.setLayout(new BorderLayout());

		// south label for the subscribe email option
		south = new JPanel(new FlowLayout(FlowLayout.CENTER));
		south.setBackground(BACKGROUND);
		south.setPreferredSize(new Dimension(0, 100));
		centerEast.add(south, BorderLayout.SOUTH);

		// the ticket panel which consists of other small panels
		ticket_panel = new JPanel(new BorderLayout());
		ticket_panel.setBackground(BACKGROUND);

		ticket_north = new JPanel();
		ticket_north.setBackground(BACKGROUND);

		ticket_north_label = new JLabel();
		ticket_north_label.setFont(new Font("Calibri", Font.BOLD, 20));

		ticket_north.add(ticket_north_label);
		ticket_panel.add(ticket_north, BorderLayout.NORTH);

		ticket_panel_main = new JPanel();
		ticket_panel_main.setBackground(BACKGROUND);

		name_ticket = new JLabel();
		name_ticket.setFont(new Font("Calibri", Font.PLAIN, 18));

		stud_num_ticket = new JLabel();
		stud_num_ticket.setFont(new Font("Calibri", Font.PLAIN, 18));

		ticket_panel.add(ticket_panel_main, BorderLayout.CENTER);

		// adding the ticket panel to the center of center east panel
		centerEast.add(ticket_panel, BorderLayout.CENTER);

		// adding the center east to the center main panel
		centerMain.add(centerEast, BorderLayout.EAST);

		b = BorderFactory.createTitledBorder("");
		centerWest.setBorder(b);
		centerWest.setBackground(new Color(246, 246, 246));

		// adding the center main to the main panel
		main_panel.add(centerMain, BorderLayout.CENTER);

		// adding the main panel to this panel
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

	/*********** public method which sets the back page button listener ***********/
	public void set_back_button_listener(CardLayout layout, JPanel panel,
			String name) {
		back_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(panel, name);
			}
		});
	}

	/***********
	 * private method which returns the number of days between today and a given
	 * date
	 ***********/
	public static int daysBetween(Date day2) {

		Calendar dayOne = DateToCalendar(new Date()), dayTwo = DateToCalendar(day2);

		if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)) {
			return Math.abs(dayOne.get(Calendar.DAY_OF_YEAR)
					- dayTwo.get(Calendar.DAY_OF_YEAR));
		} else {
			if (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
				Calendar temp = dayOne;
				dayOne = dayTwo;
				dayTwo = temp;
			}
			int extraDays = 0;

			while (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
				dayOne.add(Calendar.YEAR, -1);
				extraDays += dayOne.getActualMaximum(Calendar.DAY_OF_YEAR);
			}

			return extraDays - dayTwo.get(Calendar.DAY_OF_YEAR)
					+ dayOne.get(Calendar.DAY_OF_YEAR);
		}
	}

	/***********
	 * private method which sets up a default alphabetical keyboard from the
	 * alphabetical keyboard class
	 ***********/
	private void get_alphabetical_keyboard(JPanel panel, JTextField field,
			int size) {
		new AlphabeticalExternalKeyboard(panel, field, size);
	}

	/*********** private method which gets the expiry date label given a panel ***********/
	private void get_expiry_date_label(JPanel panel) {
		exp_date_label = new JLabel("SELECT EXPIRY DATE");
		exp_date_label.setFont(LABEL_FONT);
		exp_date_label.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(exp_date_label);
	}

	/*********** private method which gets the expiry date field given a panel ***********/
	private void get_expiry_date_field(JPanel panel) {
		exp_date_field = new ObservingTextField();

		exp_date_field.setFont(ENTRY_TEXT_FONT);
		exp_date_field.setMaximumSize(new Dimension((int) (Toolkit
				.getDefaultToolkit().getScreenSize().getWidth() / 5), 50));
		exp_date_field.setAlignmentX(Component.CENTER_ALIGNMENT);
		exp_date_field.setHorizontalAlignment(JTextField.CENTER);
		exp_date_field.setCaretColor(Color.WHITE);

		exp_date_field.addMouseListener(handler);

		panel.add(exp_date_field);
	}

	/*********** private method which gets the error label given a panel ***********/
	private void get_error_label(JPanel panel) {
		error_label = new JLabel("");

		error_label.setFont(ERROR_FONT);
		error_label.setVisible(false);
		error_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		error_label.setMaximumSize(error_label.getPreferredSize());
		panel.add(error_label);
	}

	/*********** private method which gets the next button given a panel ***********/
	private void get_pay_button(JPanel panel) {
		pay_now = new JButton("PAY FOR YOUR TICKET NOW \u2192");
		pay_now.setAlignmentX(Component.CENTER_ALIGNMENT);
		pay_now.setFont(BUTTON_FONT);
		pay_now.setVisible(false);

		panel.add(pay_now);
	}

	/*********** private method which gets the next button given a panel ***********/
	private void get_finish_button(JPanel panel) {
		finish = new JButton("PAY LATER");
		finish.setAlignmentX(Component.CENTER_ALIGNMENT);
		finish.setFont(BUTTON_FONT);
		finish.setVisible(false);

		panel.add(finish);
	}

	/*********** private method which updates the main database ***********/
	private void update_main_database(int i) {
		File file = new File("students.txt");
		File temp = new File(file.getAbsolutePath() + ".tmp");
		String saved_line = "";
		String student_num = "", pin_num = "", last_name = "", first_name = "";

		FileReader inputFile;
		;
		try {
			FileWriter fw = new FileWriter(temp.getAbsoluteFile());

			String line;
			inputFile = new FileReader(file);
			BufferedReader bufferReader = new BufferedReader(inputFile);
			String regex_file = "^(\\d{9}),(.*),(.*),(.*),(.*)$";
			Pattern pattern = Pattern.compile(regex_file);

			while ((line = bufferReader.readLine()) != null) {
				Matcher matcher = pattern.matcher(line);

				if (matcher.find()) {
					if (matcher.group(1).equalsIgnoreCase(
							LoginPage.get_number())) {
						if (i == 1) {
							student_num = matcher.group(1);
							pin_num = matcher.group(2);
							last_name = matcher.group(3);
							first_name = matcher.group(4);

							saved_line = student_num + "," + pin_num + ","
									+ last_name + "," + first_name + "," + "ok";
						} else if (i == 2) {
							student_num = matcher.group(1);
							pin_num = matcher.group(2);
							last_name = matcher.group(3);
							first_name = matcher.group(4);

							saved_line = student_num + "," + pin_num + ","
									+ last_name + "," + first_name + ","
									+ "arrears";
						}

					} else {
						fw.write(line);
						fw.append("\r\n");
					}
				}

			}
			bufferReader.close();
			fw.close();
		} catch (Exception e) {
		}

		file.delete();
		File newFile = new File("students.txt");
		temp.renameTo(newFile);

		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter(newFile.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(saved_line);
			bufferWritter.close();
			fileWritter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*********** private method which updates the access database ***********/
	private void update_access_database(int sign) {
		File file = new File("access.txt");
		File temp = new File(file.getAbsolutePath() + ".tmp");

		FileReader inputFile;
		;
		try {
			FileWriter fw = new FileWriter(temp.getAbsoluteFile());

			String line;
			inputFile = new FileReader(file);
			BufferedReader bufferReader = new BufferedReader(inputFile);
			String regex_file = "^(\\d{9}) ->(.*)\\|(.*)\\|(.*)\\|(.*)\\|(.*)\\|(.*)\\|(.*)$";
			Pattern pattern = Pattern.compile(regex_file);

			while ((line = bufferReader.readLine()) != null) {
				Matcher matcher = pattern.matcher(line);

				if (matcher.find()) {
					if (matcher.group(1).equalsIgnoreCase(
							LoginPage.get_number()))
						;
					else {
						fw.write(line);
						fw.append("\r\n");
					}
				}

			}
			bufferReader.close();
			fw.close();
		} catch (Exception e) {
		}

		file.delete();
		File newFile = new File("access.txt");
		temp.renameTo(newFile);

		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter(newFile.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			try {
				if (sign == 0) {
					bufferWritter.write(LoginPage.get_number()
							+ " ->"
							+ VehicleInfoPage1.get_make()
							+ "|"
							+ VehicleInfoPage1.get_model()
							+ "|"
							+ VehicleInfoPage1.get_plate()
							+ "|"
							+ VehicleInfoPage2.get_chosen_company()
							+ "|"
							+ VehicleInfoPage2.get_policy_number()
							+ "|"
							+ new DecimalFormat("#0.00")
									.format(daysBetween(new SimpleDateFormat(
											"dd/MM/yyyy").parse(exp_date_field
											.getText())) * 3.50) + "|"
							+ exp_date_field.getText());
				} else if (sign == 1) {
					bufferWritter.write(LoginPage.get_number() + " ->"
							+ VehicleInfoPage1.get_make() + "|"
							+ VehicleInfoPage1.get_model() + "|"
							+ VehicleInfoPage1.get_plate() + "|"
							+ VehicleInfoPage2.get_chosen_company() + "|"
							+ VehicleInfoPage2.get_policy_number() + "|" + "|"
							+ exp_date_field.getText());
				}

			} catch (ParseException e) {
			}
			bufferWritter.close();
		} catch (IOException e) {
		}
	}

	/*********** private method which sets the proceed button listener ***********/
	private void get_proceed_button(JPanel panel) {
		proceed_button = new JButton("PRINT MY E-TICKET");
		proceed_button.setFont(BUTTON_FONT);
		proceed_button.setMaximumSize(proceed_button.getPreferredSize());
		proceed_button.setAlignmentX(Component.CENTER_ALIGNMENT);

		proceed_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object[] options = { "Yes, I am sure.", "No, go back." };

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				final String[] alphabets = { "A", "B", "C", "D", "E", "F", "G",
						"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
						"S", "T", "U", "V", "X", "Y", "Z" };
				final String[] lots = { "Albany Road Lot", "Atkinson Lot",
						"Chimney Stack Lot", "East Office Building Lot",
						"Founders Road Lot", "Library Lot", "Lumbers Lot",
						"Nelson Road Lot", "Passy Crescent Lot",
						"Physical Resources Lot", "Rideau Road Lot",
						"Sentinel Road Lot", "Shoreham Drive Lot",
						"Steacie Lot", "Tait McKenzie Lot", "Pond Road  Lot",
						"Vanier Lot", "York Blvd Lot" };
				try {
					if (sdf.parse(exp_date_field.getText()).before(new Date())) {
						error_label.removeAll();
						error_label.setText("Date choosen must be after "
								+ new SimpleDateFormat("dd/MM/yyyy")
										.format(new Date()));
						error_label.setMaximumSize(error_label
								.getPreferredSize());
						error_label.setForeground(Color.RED);
						error_label.setVisible(true);
					} else {
						String s = exp_date_field.getText();
						error_label.setVisible(false);

						int n = JOptionPane
								.showOptionDialog(
										null,
										"<html><font size=\"6\">You are about to get your parking ticket. Are you sure you want to proceed?\n"
												+ "<html><font color = \"red\",size=\"5\">WARNING: Proceeding from here will be a final choice and cannot be reverted back once proceeded.",
										null, JOptionPane.YES_NO_CANCEL_OPTION,
										JOptionPane.WARNING_MESSAGE, null,
										options, options[1]);

						// if the user proceeds on buying the ticket
						if (n == 0) {
							// dealing with the database first
							update_main_database(2);

							// ticket part
							pay_now.setVisible(true);
							finish.setVisible(true);
							AlphabeticalKeyboardHandlerClass handler = new AlphabeticalKeyboardHandlerClass();
							exp_date_field.setEnabled(false);
							exp_date_field.setVisible(false);
							centerWest.setBackground(BACKGROUND);
							centerWest.setBorder(BorderFactory
									.createEmptyBorder());

							back_button.setEnabled(false);

							exp_date_label.setVisible(false);

							proceed_button.setEnabled(false);
							proceed_button.setVisible(false);

							ticket_panel_main.setBackground(new JPanel()
									.getBackground());

							ticket_north_label.setText("My e-Ticket");

							Border b = BorderFactory.createLineBorder(
									Color.GRAY, 5);
							ticket_north.setBorder(b);
							ticket_panel_main.setBorder(b);

							name_ticket = new JLabel();
							name_ticket.setText("Student name: "
									+ db.get_student_name_list().get(index));
							name_ticket
									.setAlignmentX(Component.CENTER_ALIGNMENT);
							name_ticket.setFont(new Font("Calibri", Font.BOLD,
									25));

							stud_num_ticket = new JLabel();
							stud_num_ticket.setText("Student number: "
									+ db.get_student_number_list().get(index));
							stud_num_ticket
									.setAlignmentX(Component.CENTER_ALIGNMENT);
							stud_num_ticket.setFont(new Font("Calibri",
									Font.BOLD, 25));

							ticket_length_date = new JLabel();
							ticket_length_date.setText("Ticket valid from: "
									+ new SimpleDateFormat("dd/MM/yyyy")
											.format(new Date()) + " to " + s);
							ticket_length_date
									.setAlignmentX(Component.CENTER_ALIGNMENT);
							ticket_length_date.setFont(new Font("Calibri",
									Font.BOLD, 18));

							ticket_length_days = new JLabel();
							ticket_length_days.setText("Number of days: "
									+ daysBetween(new SimpleDateFormat(
											"dd/MM/yyyy").parse(s)) + "");
							ticket_length_days
									.setAlignmentX(Component.CENTER_ALIGNMENT);
							ticket_length_days.setFont(new Font("Calibri",
									Font.BOLD, 18));

							amount = new JLabel();
							amount.setText("Amount due: $"
									+ new DecimalFormat("#0.00")
											.format(daysBetween(new SimpleDateFormat(
													"dd/MM/yyyy").parse(s)) * 3.50));
							amount.setAlignmentX(Component.CENTER_ALIGNMENT);
							amount.setFont(new Font("Calibri", Font.BOLD, 18));

							Random random = new Random();
							int lot_num = random.nextInt(50);

							int alph = random.nextInt(alphabets.length);
							int lot = random.nextInt(lots.length);

							parking_lot = new JLabel();
							parking_lot.setText("Parking spot: "
									+ alphabets[alph] + (lot_num + 1) + " @ "
									+ lots[lot]);
							parking_lot
									.setAlignmentX(Component.CENTER_ALIGNMENT);
							parking_lot.setFont(new Font("Calibri", Font.BOLD,
									18));

							note = new JLabel(
									"*Please pay your outstanding balance by "
											+ s);
							note.setAlignmentX(Component.CENTER_ALIGNMENT);
							note.setFont(new Font("Calibri", Font.BOLD, 15));

							picture_bar_code = new JLabel(new ImageIcon(
									"images/bar_code.jpg"));
							picture_bar_code
									.setAlignmentX(Component.CENTER_ALIGNMENT);

							ticket_panel_main.setLayout(new BoxLayout(
									ticket_panel_main, BoxLayout.Y_AXIS));

							ticket_panel_main.add(Box.createGlue());
							ticket_panel_main.add(name_ticket);

							ticket_panel_main.add(Box.createGlue());
							ticket_panel_main.add(stud_num_ticket);

							ticket_panel_main.add(Box.createGlue());
							ticket_panel_main.add(parking_lot);

							ticket_panel_main.add(Box.createGlue());
							ticket_panel_main.add(ticket_length_date);

							ticket_panel_main.add(Box.createGlue());
							ticket_panel_main.add(ticket_length_days);

							ticket_panel_main.add(Box.createGlue());
							ticket_panel_main.add(amount);

							ticket_panel_main.add(Box.createGlue());
							ticket_panel_main.add(note);

							ticket_panel_main.add(Box.createGlue());
							ticket_panel_main.add(picture_bar_code);

							subscribe_label = new JLabel(
									"Subscribe to our newsletter");
							subscribe_label.setFont(new Font("Calibri",
									Font.BOLD, 20));
							south.add(subscribe_label);

							email_field = new JTextField(20);
							email_field.setFont(new Font("Calibri", Font.PLAIN,
									18));
							email_field.setMaximumSize(email_field
									.getPreferredSize());
							email_field.addMouseListener(handler);
							email_field.setCaretColor(Color.WHITE);
							south.add(email_field);

							central_keyboard.setBackground(new Color(246, 246,
									246));
							central_keyboard.setLayout(new BorderLayout());
							central_keyboard.setBorder(BorderFactory
									.createEtchedBorder());
							get_alphabetical_keyboard(central_keyboard,
									email_field, KEYBOARD_FONT_SIZE);

							subscribe_button = new JButton("Subscribe");
							subscribe_button.setFont(new Font("Calibri",
									Font.BOLD, 20));
							subscribe_button.addMouseListener(handler);
							south.add(subscribe_button);

							south.setBackground(new Color(246, 246, 246));

							update_access_database(0);

							pay_now.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									try {
										Object[] options2 = {
												"Yes, I am sure.",
												"No, go back." };
										String s1 = new DecimalFormat("#0.00")
												.format(daysBetween(new SimpleDateFormat(
														"dd/MM/yyyy").parse(s)) * 3.50);
										int n = JOptionPane
												.showOptionDialog(
														null,
														"<html><font color=\"red\", size=\"5\">Are you sure you want to proceed with the ticket payment of $"
																+ s1
																+ "?\n<html><font color=\"red\", size=\"3\">Note: The amount will be charged directly to your student account.</font></html>",
														null,
														JOptionPane.YES_NO_CANCEL_OPTION,
														JOptionPane.WARNING_MESSAGE,
														null, options2,
														options2[1]);
										if (n == 0) {
											finish.setText("FINISH");
											update_main_database(1);
											error_label.removeAll();
											error_label.setVisible(true);
											error_label
													.setText("<html>Thank you!<br></br>An amount of $"
															+ s1
															+ " has been charged<br></br>on your student account.</html>");
											error_label.setMaximumSize(error_label
													.getPreferredSize());
											error_label
													.setForeground(new Color(0,
															153, 51));
											pay_now.setVisible(false);
											update_access_database(1);
										}
									} catch (HeadlessException e) {
									} catch (ParseException e) {
									}

								}
							});

						} else
							;
					}
				} catch (ParseException e) {
					exp_date_field.setBorder(RED);
					error_label.removeAll();
					error_label.setText("Please choose a valid date after "
							+ new SimpleDateFormat("dd/MM/yyyy")
									.format(new Date()));
					error_label.setMaximumSize(error_label.getPreferredSize());
					error_label.setForeground(Color.RED);
					error_label.setVisible(true);
				}
			}

		});
		panel.add(proceed_button);
	}

	/*********** private method returns a calendar given a certain date ***********/
	private static Calendar DateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/*********** private method which returns the Canada locale ***********/
	private Locale getlocale() {
		return Locale.CANADA;
	}

	/***********
	 * private class which which enumerates the mouse listener on the expiry
	 * date textfield
	 ***********/
	private class DateHandlerClass implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			final Locale locale = getlocale();
			Border black = BorderFactory.createLineBorder(Color.BLACK, 3);
			if (arg0.getSource() == exp_date_field) {
				error_label.setText("");
				error_label.setVisible(false);
				exp_date_field.setBorder(black);
				DatePicker dp = new DatePicker(exp_date_field, locale);
				Date selectedDate = dp.parseDate(exp_date_field.getText());

				dp.setSelectedDate(selectedDate);
				dp.start(exp_date_field);
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}

	/***********
	 * private class which which enumerates the focus listeneres on two
	 * textfields
	 ***********/
	private class AlphabeticalKeyboardHandlerClass implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (arg0.getSource() == email_field) {
				email_field.setBorder(BLACK);
				central_keyboard.removeAll();

				new AlphabeticalExternalKeyboard(central_keyboard, email_field,
						KEYBOARD_FONT_SIZE);

				central_keyboard.revalidate();
			} else if (arg0.getSource() == subscribe_button) {
				if (!email_field.getText().matches(regex_email))
					email_field.setBorder(RED);
				else {
					email_field.setBorder(DEFAULT);
					JOptionPane
							.showMessageDialog(
									null,
									"Email successfully added to the subscription list.",
									"", JOptionPane.PLAIN_MESSAGE);
				}

			}

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

	}

}
