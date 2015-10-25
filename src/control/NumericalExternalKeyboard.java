package control;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/******* Button instance for all the keys on keyboard ******/
public class NumericalExternalKeyboard {
	private final JButton one, two, three, four, five, six, seven, eight, nine, zero,
			del, done;
	private final Font TEXT;

	/******* The outer layout for the keyboard *******/
	public NumericalExternalKeyboard(JPanel panel, JTextField field, int size) {
		TEXT = new Font("Calibri", Font.BOLD, size);
		panel.setLayout(new GridLayout(4, 3, 8, 8));

		/******* NUMBERS *******/
		one = new JButton("1");
		one.setFont(TEXT);
		one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					field.setText(field.getText() + "1");
				} catch (Exception excep) {
				}
			}
		});

		two = new JButton("2");
		two.setFont(TEXT);
		two.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					field.setText(field.getText() + "2");
				} catch (Exception excep) {
				}
			}
		});

		three = new JButton("3");
		three.setFont(TEXT);
		three.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					field.setText(field.getText() + "3");
				} catch (Exception excep) {
				}
			}
		});

		four = new JButton("4");
		four.setFont(TEXT);
		four.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					field.setText(field.getText() + "4");
				} catch (Exception excep) {
				}
			}
		});

		five = new JButton("5");
		five.setFont(TEXT);
		five.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					field.setText(field.getText() + "5");
				} catch (Exception excep) {
				}
			}
		});

		six = new JButton("6");
		six.setFont(TEXT);
		six.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					field.setText(field.getText() + "6");
				} catch (Exception excep) {
				}
			}
		});

		seven = new JButton("7");
		seven.setFont(TEXT);
		seven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					field.setText(field.getText() + "7");
				} catch (Exception excep) {
				}
			}
		});

		eight = new JButton("8");
		eight.setFont(TEXT);
		eight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					field.setText(field.getText() + "8");
				} catch (Exception excep) {
				}
			}
		});

		nine = new JButton("9");
		nine.setFont(TEXT);
		nine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					field.setText(field.getText() + "9");
				} catch (Exception excep) {
				}
			}
		});

		zero = new JButton("0");
		zero.setFont(TEXT);
		zero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					field.setText(field.getText() + "0");
				} catch (Exception excep) {
				}
			}
		});

		/******* Delete Key *******/
		del = new JButton("del");
		del.setFont(new Font("Calibri", Font.BOLD, size));
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String now = field.getText();
					String f = now.substring(0, now.length() - 1);
					field.setText(f);
				} catch (Exception e1) {
				}

			}
		});

		/******* Enter Key *******/
		done = new JButton("Enter");
		done.setFont(new Font("Calibri", Font.BOLD, size));
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					field.transferFocus();
				} catch (NullPointerException exception) {
				}
				;

			}
		});

		panel.add(one);
		panel.add(two);
		panel.add(three);
		panel.add(four);
		panel.add(five);
		panel.add(six);
		panel.add(seven);
		panel.add(eight);
		panel.add(nine);

		panel.add(del);
		panel.add(zero);
		panel.add(done);

	}
}
