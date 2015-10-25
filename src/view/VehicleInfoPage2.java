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
import helper.Time;
import control.NumericalExternalKeyboard;
import databases.AccessDatabase;
import databases.MainDatabase;

/*---------------- the vehicle info page 2 class that enumerates the insurance info of the user for the kiosk ----------------*/
public class VehicleInfoPage2 extends JPanel {

	/* list of all FINAL attributes used in the page */
	private static final long serialVersionUID = -1639434012666786432L;

	private final String[] companies = { "",
			"Co-operators Insurance Company of Canada",
			"State Farm Underwriters Incorporated",
			"Allstate International Insurance Company Ltd.",
			"Metropolitan Life Insurance Company",
			"Bang Em Up Insurance Company of Vaughan",
			"Tightwads Incorporated", "Gottcha Insurance Company, Inc." };

	private final int KEYBOARD_FONT_SIZE = 26;

	private final Color BACKGROUND = Color.WHITE;
	private final Color SIDE_PANEL = new Color(255, 25, 25);

	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"hh:mm:ss a, MMM dd/yyyy");

	private final Border BLACK = BorderFactory.createLineBorder(Color.BLACK, 3);
	private final Border RED = BorderFactory.createLineBorder(Color.RED, 3);
	private final Border DEFAULT = new JTextField().getBorder();
	private final Border DEFAULT_COMBO_BOX = new JComboBox<String>()
			.getBorder();

	private final String COPYRIGHT_TEXT = "Copyright \u00a9 2015 - York University";

	private final Font PAGE_LABEL_FONT = new Font("Goudy Old Style Bold",
			Font.BOLD, 60);
	private final Font ENTRY_TEXT_FONT = new Font("Calibri", Font.PLAIN, 45);
	private final Font LABEL_FONT = new Font("Calibri", Font.BOLD, 55);
	private final Font TIME_FONT = new Font("Rockwell", Font.PLAIN, 40);
	private final Font COPYRIGHT_FONT = new Font("Calibri", Font.BOLD, 16);
	private final Font BUTTON_FONT = new Font("Calibri", Font.BOLD, 40);
	private final Font SIGNOUT_BUTTON_FONT = new Font("Calibri", Font.BOLD, 18);
	private final Font ERROR_FONT = new Font("Calibri", Font.BOLD, 26);

	/*
	 * list of all the components used in the page
	 */
	private JPanel main_panel, login_panel, time_panel, copyright_panel,
			center_keyboard;
	private JLabel insurance_label, policy_num_label, copyright_label,
			time_label, error_label;
	private JButton next_button, back_button;
	private JButton pay_now;

	private static MainDatabase db;
	private static JLabel login_name_label;
	private static JPanel signout_panel;
	private static JButton signout_button;
	private static int index;
	private static JTextField policy_num_field;
	private static JComboBox<String> list_of_insurance_companies;

	/***********
	 * public constructor with two arguments - Frame, Database
	 ***********/
	public VehicleInfoPage2(JFrame frame, MainDatabase d) {
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
				.getScreenSize().getWidth() / 3.5), 35));

		JLabel label = new JLabel("<html><u>INSURANCE</u></html>",
				JLabel.CENTER);
		label.setFont(PAGE_LABEL_FONT);
		left.add(label, BorderLayout.NORTH);

		JLabel logo = new JLabel(new ImageIcon("images/insurance_logo.jpg"));
		left.add(logo, BorderLayout.CENTER);

		JPanel south_left = new JPanel(new FlowLayout(FlowLayout.CENTER));
		south_left.setBackground(BACKGROUND);

		back_button = new JButton("\u2190 BACK");
		back_button.setFont(BUTTON_FONT);
		south_left.add(back_button);

		left.add(south_left, BorderLayout.SOUTH);

		// right panel to the right of the main panel to hold the keyboard in
		// the center of the panel
		// usses north, south, east to make perfect placement for the keyboard
		JPanel right = new JPanel();
		right.setBackground(BACKGROUND);
		right.setLayout(new BorderLayout());
		right.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit()
				.getScreenSize().getWidth() / 3.5), 35));

		JPanel north = new JPanel();
		north.setBackground(BACKGROUND);
		north.setPreferredSize(new Dimension(10, 180));

		right.add(north, BorderLayout.NORTH);

		JPanel south = new JPanel(new BorderLayout());
		south.setBackground(BACKGROUND);
		south.setPreferredSize(new Dimension(10, 180));

		right.add(south, BorderLayout.SOUTH);

		JPanel east = new JPanel();
		east.setBackground(BACKGROUND);

		right.add(east, BorderLayout.EAST);

		JPanel west = new JPanel();
		west.setBackground(BACKGROUND);

		right.add(west, BorderLayout.WEST);

		center_keyboard = new JPanel();
		center_keyboard.setBackground(new Color(246, 246, 246));
		center_keyboard.setLayout(new BorderLayout());
		center_keyboard.setBorder(BorderFactory.createEtchedBorder());
		center_keyboard.setLayout(new BorderLayout());

		right.add(center_keyboard, BorderLayout.CENTER);

		JPanel south_right = new JPanel();
		south_right.setBackground(BACKGROUND);

		south.add(south_right, BorderLayout.SOUTH);

		// adds the left and right panel to the main panel
		main_panel.add(left, BorderLayout.WEST);
		main_panel.add(right, BorderLayout.EAST);

		// center main is in the center of the main panel which is used to hold
		// the main contents
		JPanel centerMain = new JPanel();
		centerMain.setBackground(BACKGROUND);
		centerMain.setLayout(new BoxLayout(centerMain, BoxLayout.Y_AXIS));

		centerMain.add(Box.createGlue());
		get_insurance_company_label(centerMain);
		centerMain.add(Box.createGlue());
		get_insurance_company_combobox(centerMain);
		list_of_insurance_companies.setBorder(BLACK);
		centerMain.add(Box.createGlue());
		get_policy_number_label(centerMain);
		centerMain.add(Box.createGlue());
		get_policy_number_field(centerMain);
		centerMain.add(Box.createGlue());
		get_next_button(south_right);
		centerMain.add(Box.createGlue());
		get_error_label(centerMain);
		centerMain.add(Box.createGlue());
		get_pay_button(centerMain);
		centerMain.add(Box.createGlue());
		error_label.removeAll();

		// inserts the keyboard to the panel and sets its initial jtextfield to
		// null
		get_numerical_keyboard(center_keyboard, null, KEYBOARD_FONT_SIZE);

		Border b = BorderFactory.createTitledBorder("");
		centerMain.setBorder(b);
		centerMain.setBackground(new Color(246, 246, 246));

		// adding center main to the main panel
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

	/***********
	 * public method which sets the chosen company to be the one chosen by the
	 * user
	 ***********/
	public static void set_chosen_company(String s) {
		list_of_insurance_companies.setSelectedItem((String) s);
	}

	/*********** public method which sets the user policy number ***********/
	public static void set_policy_num(String s) {
		policy_num_field.setText(s);
	}

	/*********** public method which gets the user policy number ***********/
	public static String get_policy_number() {
		return policy_num_field.getText();
	}

	/*********** public method which gets the chosen company chosen by the user ***********/
	public static String get_chosen_company() {
		return (String) list_of_insurance_companies.getSelectedItem();
	}

	/*********** public method which sets the back page button listener ***********/
	public void set_back_button_listener(CardLayout layout, JPanel panel,
			String name) {
		back_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				error_label.removeAll();
				error_label.setVisible(false);
				pay_now.removeAll();
				pay_now.setVisible(false);
				layout.show(panel, name);
			}
		});
	}

	/*********** public method which sets the next page button listener ***********/
	public void set_proceed_button_listener(CardLayout layout, JPanel panel,
			String name) {
		next_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object selected = (String) list_of_insurance_companies
						.getSelectedItem();
				AccessDatabase ad = new AccessDatabase(LoginPage.get_number());
				ad.access_database();
				try {
					if (((String) selected).equalsIgnoreCase("")) {
						pay_now.removeAll();
						pay_now.setVisible(false);
						error_label.removeAll();
						error_label
								.setText("Please select a valid insurance company.");
						error_label.setForeground(Color.RED);
						error_label.setVisible(true);
						list_of_insurance_companies.setBorder(RED);
						error_label.setMaximumSize(error_label
								.getPreferredSize());
					} else {
						if (!db.get_student_status_list().get(index)
								.equalsIgnoreCase("ok")) {
							error_label.removeAll();

							error_label
									.setText("<html>Could not proceed because the user<br></br>has an outstanding balance of $"
											+ ad.get_amount_due());
							error_label.setForeground(Color.RED);
							error_label.setVisible(true);
							error_label.setMaximumSize(error_label
									.getPreferredSize());

							pay_now.setVisible(true);
							pay_now.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									Object[] options2 = { "Yes, I am sure.",
											"No, go back." };
									int n = JOptionPane
											.showOptionDialog(
													null,
													"<html><font color=\"red\", size=\"5\">Are you sure you want to proceed with the ticket payment of $"
															+ ad.get_amount_due()
															+ "?\n<html><font color=\"red\", size=\"3\">Note: The amount will be charged directly to your student account.</font></html>",
													null,
													JOptionPane.YES_NO_CANCEL_OPTION,
													JOptionPane.WARNING_MESSAGE,
													null, options2, options2[1]);
									if (n == 0) {
										update_main_database();
										update_access_database(ad.get_date(), 1);

										db = new MainDatabase();

										error_label.removeAll();
										error_label.setVisible(true);
										error_label
												.setText("<html>Thank you!<br></br>An amount of $"
														+ ad.get_amount_due()
														+ " has been charged on your student account.</html>");
										error_label.setForeground(new Color(0,
												153, 51));
										pay_now.setVisible(false);
									}
								}
							});

						} else if (!ad.get_date().equalsIgnoreCase("")
								&& new SimpleDateFormat("dd/MM/yyyy").parse(
										ad.get_date()).after(new Date())) {

							if (TicketPage.daysBetween(new SimpleDateFormat(
									"dd/MM/yyyy").parse(ad.get_date())) == 1) {
								error_label.removeAll();
								error_label.setVisible(true);
								error_label.setText("<html>You already have a ticket bought till "
										+ ad.get_date()
										+ ".<br></br>Please try again in "
										+ TicketPage
												.daysBetween(new SimpleDateFormat(
														"dd/MM/yyyy").parse(ad
														.get_date()))
										+ " day.</html>");
								error_label.setForeground(Color.RED);
								error_label.setMaximumSize(error_label
										.getPreferredSize());
								pay_now.setVisible(false);
							} else {
								error_label.removeAll();
								error_label.setVisible(true);
								error_label.setText("<html>You already have a ticket bought till "
										+ ad.get_date()
										+ ".<br></br>Please try again in "
										+ TicketPage
												.daysBetween(new SimpleDateFormat(
														"dd/MM/yyyy").parse(ad
														.get_date()))
										+ " days.</html>");
								error_label.setForeground(Color.RED);
								error_label.setMaximumSize(error_label
										.getPreferredSize());
								pay_now.setVisible(false);
							}

						} else {
							update_access_database("", 0);
							error_label.removeAll();
							error_label.setVisible(false);
							layout.show(panel, name);
						}
					}
				} catch (ParseException e) {
				}
			}
		});
	}

	/*********** private method which updates the main database ***********/
	private void update_main_database() {
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
						student_num = matcher.group(1);
						pin_num = matcher.group(2);
						last_name = matcher.group(3);
						first_name = matcher.group(4);

						saved_line = student_num + "," + pin_num + ","
								+ last_name + "," + first_name + "," + "ok";
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
	private void update_access_database(String input, int sign) {
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
			if (sign == 0) {
				bufferWritter.write(LoginPage.get_number() + " ->"
						+ VehicleInfoPage1.get_make() + "|"
						+ VehicleInfoPage1.get_model() + "|"
						+ VehicleInfoPage1.get_plate() + "|"
						+ VehicleInfoPage2.get_chosen_company() + "|"
						+ VehicleInfoPage2.get_policy_number() + "|" + "|");
			} else if (sign == 1) {
				bufferWritter.write(LoginPage.get_number() + " ->"
						+ VehicleInfoPage1.get_make() + "|"
						+ VehicleInfoPage1.get_model() + "|"
						+ VehicleInfoPage1.get_plate() + "|"
						+ VehicleInfoPage2.get_chosen_company() + "|"
						+ VehicleInfoPage2.get_policy_number() + "|" + "|"
						+ input);
			} else if (sign == 2) {
				bufferWritter.write(LoginPage.get_number() + " ->"
						+ VehicleInfoPage1.get_make() + "|"
						+ VehicleInfoPage1.get_model() + "|"
						+ VehicleInfoPage1.get_plate() + "|"
						+ VehicleInfoPage2.get_chosen_company() + "|"
						+ VehicleInfoPage2.get_policy_number() + "|" + input
						+ "|");
			}

			bufferWritter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***********
	 * private method which sets up a default numerical keyboard from the
	 * numerical keyboard class
	 ***********/
	private void get_numerical_keyboard(JPanel panel, JTextField field, int size) {
		new NumericalExternalKeyboard(panel, field, size);
	}

	/*********** private method which gets the error label given a panel ***********/
	private void get_error_label(JPanel panel) {
		error_label = new JLabel("");
		error_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		error_label.setMaximumSize(error_label.getPreferredSize());
		error_label.setFont(ERROR_FONT);
		error_label.setVisible(false);

		panel.add(error_label);
	}

	/*********** private method which gets the insurance label given a panel ***********/
	private void get_insurance_company_label(JPanel panel) {
		insurance_label = new JLabel("COMPANY");
		insurance_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		insurance_label.setFont(LABEL_FONT);
		insurance_label.setMaximumSize(insurance_label.getPreferredSize());

		panel.add(insurance_label);
	}

	/*********** private method which gets the insurance combo box given a panel ***********/
	private void get_insurance_company_combobox(JPanel panel) {
		list_of_insurance_companies = new JComboBox<String>(companies);
		list_of_insurance_companies
				.setFont(new Font("Calibri", Font.PLAIN, 24));
		list_of_insurance_companies.setMaximumSize(list_of_insurance_companies
				.getPreferredSize());

		NumericalKeyboardHandlerClass handler = new NumericalKeyboardHandlerClass();
		list_of_insurance_companies.addFocusListener(handler);

		panel.add(list_of_insurance_companies);
	}

	/*********** private method which gets the policy number label given a panel ***********/
	private void get_policy_number_label(JPanel panel) {
		policy_num_label = new JLabel("POLICY NUMBER");
		policy_num_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		policy_num_label.setFont(LABEL_FONT);
		policy_num_label.setMaximumSize(policy_num_label.getPreferredSize());

		panel.add(policy_num_label);
	}

	/*********** private method which gets the policy number field given a panel ***********/
	private void get_policy_number_field(JPanel panel) {
		policy_num_field = new JTextField(15);
		policy_num_field.setFont(ENTRY_TEXT_FONT);
		policy_num_field.setMaximumSize(policy_num_field.getPreferredSize());
		policy_num_field.setHorizontalAlignment(JTextField.CENTER);

		NumericalKeyboardHandlerClass handler = new NumericalKeyboardHandlerClass();
		policy_num_field.addFocusListener(handler);
		policy_num_field.setCaretColor(Color.WHITE);

		panel.add(policy_num_field);
	}

	/*********** private method which gets the next button given a panel ***********/
	private void get_next_button(JPanel panel) {
		next_button = new JButton("NEXT \u2192");
		next_button.setFont(BUTTON_FONT);

		panel.add(next_button);
	}

	/*********** private method which gets the next button given a panel ***********/
	private void get_pay_button(JPanel panel) {
		pay_now = new JButton("PAY NOW");
		pay_now.setAlignmentX(Component.CENTER_ALIGNMENT);
		pay_now.setFont(new Font("Calibri", Font.BOLD, 30));
		pay_now.setVisible(false);

		panel.add(pay_now);
	}

	/***********
	 * private class which which enumerates the focus listeneres on one
	 * textfield and one combo box
	 ***********/
	private class NumericalKeyboardHandlerClass implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			if (e.getSource() == policy_num_field) {
				error_label.setText("");
				error_label.setVisible(false);
				policy_num_field.setBorder(BLACK);
				list_of_insurance_companies.setBorder(DEFAULT_COMBO_BOX);
				center_keyboard.removeAll();
				new NumericalExternalKeyboard(center_keyboard,
						policy_num_field, KEYBOARD_FONT_SIZE);
				center_keyboard.revalidate();
			} else if (e.getSource() == list_of_insurance_companies) {
				pay_now.setVisible(false);
				error_label.setText("");
				error_label.setVisible(false);
				policy_num_field.setBorder(DEFAULT);
				list_of_insurance_companies.setBorder(BLACK);
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
		}
	}
}