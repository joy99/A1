package control;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AlphabeticalExternalKeyboard {

	/******* Button instance for all the keys on keyboard ******/
	private final JButton one, two, three, four, five, six, seven, eight, nine, zero,
			del, space, period, done, shift, q, w, e, r, t, y, u, i,
			o, p, a, s, d, f, g, h, j, k, l, z, x, c, v, b, n, m, underscore,
			at, dash;

	private int control = 0;
	private final Font font;

	/******* The outer layout for the keyboard *******/
	public AlphabeticalExternalKeyboard(JPanel panel, JTextField field, int size) {
		panel.setLayout(new GridLayout(4, 11, 8, 8));
		font = new Font("Calibri", Font.BOLD, size);

		/******* NUMBERS *******/
		one = new JButton("1");
		one.setFont(font);
		one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(field.getText() + "1");
			}
		});

		panel.add(one);

		two = new JButton("2");
		two.setFont(font);
		two.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(field.getText() + "2");
			}
		});
		panel.add(two);

		three = new JButton("3");
		three.setFont(font);
		three.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(field.getText() + "3");
			}
		});
		panel.add(three);

		four = new JButton("4");
		four.setFont(font);
		four.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(field.getText() + "4");
			}
		});
		panel.add(four);

		five = new JButton("5");
		five.setFont(font);
		five.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(field.getText() + "5");
			}
		});
		panel.add(five);

		six = new JButton("6");
		six.setFont(font);
		six.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(field.getText() + "6");
			}
		});
		panel.add(six);

		seven = new JButton("7");
		seven.setFont(font);
		seven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(field.getText() + "7");
			}
		});
		panel.add(seven);

		eight = new JButton("8");
		eight.setFont(font);
		eight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(field.getText() + "8");
			}
		});
		panel.add(eight);

		nine = new JButton("9");
		nine.setFont(font);
		nine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(field.getText() + "9");
			}
		});
		panel.add(nine);

		zero = new JButton("0");
		zero.setFont(font);
		zero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(field.getText() + "0");
			}
		});
		panel.add(zero);

		del = new JButton("del");
		del.setFont(font);
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String now = field.getText();
					String f = now.substring(0, now.length() - 1);
					field.setText(f);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"Cannot delete anything!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		panel.add(del);

		/******* Alphabets *******/
		q = new JButton("q");
		q.setFont(font);
		q.addActionListener(new ActionListener() {
			/***** The letter types in upper case when ⇧ is pressed *****/
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "q");
				} else {
					field.setText(field.getText() + "Q");
				}
			}
		});
		panel.add(q);

		w = new JButton("w");
		w.setFont(font);
		w.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "w");
				} else {
					field.setText(field.getText() + "W");
				}
			}
		});
		panel.add(w);

		e = new JButton("e");
		e.setFont(font);
		e.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "e");
				} else {
					field.setText(field.getText() + "E");
				}
			}
		});
		panel.add(e);

		r = new JButton("r");
		r.setFont(font);
		r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "r");
				} else {
					field.setText(field.getText() + "R");
				}
			}
		});
		panel.add(r);

		t = new JButton("t");
		t.setFont(font);
		t.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "t");
				} else {
					field.setText(field.getText() + "T");
				}
			}
		});
		panel.add(t);

		y = new JButton("y");
		y.setFont(font);
		y.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "y");
				} else {
					field.setText(field.getText() + "Y");
				}
			}
		});
		panel.add(y);

		u = new JButton("u");
		u.setFont(font);
		u.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "u");
				} else {
					field.setText(field.getText() + "U");
				}
			}
		});
		panel.add(u);

		i = new JButton("i");
		i.setFont(font);
		i.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "i");
				} else {
					field.setText(field.getText() + "I");
				}
			}
		});
		panel.add(i);

		o = new JButton("o");
		o.setFont(font);
		o.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "o");
				} else {
					field.setText(field.getText() + "O");
				}
			}
		});
		panel.add(o);

		p = new JButton("p");
		p.setFont(font);
		p.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "p");
				} else {
					field.setText(field.getText() + "P");
				}
			}
		});
		panel.add(p);

		dash = new JButton("-");
		dash.setFont(font);
		dash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(field.getText() + "-");
			}
		});

		panel.add(dash);

		at = new JButton("@");
		at.setFont(font);
		at.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(field.getText() + "@");
			}
		});
		panel.add(at);

		a = new JButton("a");
		a.setFont(font);
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "a");
				} else {
					field.setText(field.getText() + "A");
				}
			}
		});
		panel.add(a);

		s = new JButton("s");
		s.setFont(font);
		s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "s");
				} else {
					field.setText(field.getText() + "S");
				}
			}
		});
		panel.add(s);

		d = new JButton("d");
		d.setFont(font);
		d.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "d");
				} else {
					field.setText(field.getText() + "D");
				}
			}
		});
		panel.add(d);

		f = new JButton("f");
		f.setFont(font);
		f.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "f");
				} else {
					field.setText(field.getText() + "F");
				}
			}
		});
		panel.add(f);

		g = new JButton("g");
		g.setFont(font);
		g.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "g");
				} else {
					field.setText(field.getText() + "G");
				}
			}
		});
		panel.add(g);

		h = new JButton("h");
		h.setFont(font);
		h.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "h");
				} else {
					field.setText(field.getText() + "H");
				}
			}
		});
		panel.add(h);

		j = new JButton("j");
		j.setFont(font);
		j.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "j");
				} else {
					field.setText(field.getText() + "J");
				}
			}
		});
		panel.add(j);

		k = new JButton("k");
		k.setFont(font);
		k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "k");
				} else {
					field.setText(field.getText() + "K");
				}
			}
		});
		panel.add(k);

		l = new JButton("l");
		l.setFont(font);
		l.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "l");
				} else {
					field.setText(field.getText() + "L");
				}
			}
		});
		panel.add(l);

		done = new JButton("Enter");
		done.setFont(font);
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.transferFocus();
			}
		});

		panel.add(done);

		/***** The letters in buttons become upper case when ⇧ is pressed *****/
		shift = new JButton("\u2191");
		shift.setFont(font);
		shift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (control == 0) {
					q.setText("Q");
					q.setSize(q.getPreferredSize());

					w.setText("W");
					w.setSize(w.getPreferredSize());

					e.setText("E");
					e.setSize(e.getPreferredSize());

					r.setText("R");
					r.setSize(r.getPreferredSize());

					t.setText("T");
					t.setSize(t.getPreferredSize());

					y.setText("Y");
					y.setSize(y.getPreferredSize());

					u.setText("U");
					u.setSize(u.getPreferredSize());

					i.setText("I");
					i.setSize(i.getPreferredSize());

					o.setText("O");
					o.setSize(o.getPreferredSize());

					p.setText("P");
					p.setSize(p.getPreferredSize());

					a.setText("A");
					a.setSize(a.getPreferredSize());

					s.setText("S");
					s.setSize(s.getPreferredSize());

					d.setText("D");
					d.setSize(d.getPreferredSize());

					f.setText("F");
					f.setSize(f.getPreferredSize());

					g.setText("G");
					g.setSize(g.getPreferredSize());

					h.setText("H");
					h.setSize(h.getPreferredSize());

					j.setText("J");
					j.setSize(j.getPreferredSize());

					k.setText("K");
					k.setSize(k.getPreferredSize());

					l.setText("L");
					l.setSize(l.getPreferredSize());

					z.setText("Z");
					z.setSize(z.getPreferredSize());

					x.setText("X");
					x.setSize(x.getPreferredSize());

					c.setText("C");
					c.setSize(c.getPreferredSize());

					v.setText("V");
					v.setSize(v.getPreferredSize());

					b.setText("B");
					b.setSize(b.getPreferredSize());

					n.setText("N");
					n.setSize(n.getPreferredSize());

					m.setText("M");
					m.setSize(m.getPreferredSize());

					control = 1;
				} else {
					q.setText("q");
					q.setSize(q.getPreferredSize());

					w.setText("w");
					w.setSize(w.getPreferredSize());

					e.setText("e");
					e.setSize(e.getPreferredSize());

					r.setText("r");
					r.setSize(r.getPreferredSize());

					t.setText("t");
					t.setSize(t.getPreferredSize());

					y.setText("y");
					y.setSize(y.getPreferredSize());

					u.setText("u");
					u.setSize(u.getPreferredSize());

					i.setText("i");
					i.setSize(i.getPreferredSize());

					o.setText("o");
					o.setSize(o.getPreferredSize());

					p.setText("p");
					p.setSize(p.getPreferredSize());

					a.setText("a");
					a.setSize(a.getPreferredSize());

					s.setText("s");
					s.setSize(s.getPreferredSize());

					d.setText("d");
					d.setSize(d.getPreferredSize());

					f.setText("f");
					f.setSize(f.getPreferredSize());

					g.setText("g");
					g.setSize(g.getPreferredSize());

					h.setText("h");
					h.setSize(h.getPreferredSize());

					j.setText("j");
					j.setSize(j.getPreferredSize());

					k.setText("k");
					k.setSize(k.getPreferredSize());

					l.setText("l");
					l.setSize(l.getPreferredSize());

					z.setText("z");
					z.setSize(z.getPreferredSize());

					x.setText("x");
					x.setSize(x.getPreferredSize());

					c.setText("c");
					c.setSize(c.getPreferredSize());

					v.setText("v");
					v.setSize(v.getPreferredSize());

					b.setText("b");
					b.setSize(b.getPreferredSize());

					n.setText("n");
					n.setSize(n.getPreferredSize());

					m.setText("m");
					m.setSize(m.getPreferredSize());

					control = 0;
				}
			}
		});
		panel.add(shift);

		z = new JButton("z");
		z.setFont(font);
		z.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "z");
				} else {
					field.setText(field.getText() + "Z");
				}
			}
		});

		panel.add(z);

		x = new JButton("x");
		x.setFont(font);
		x.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "x");
				} else {
					field.setText(field.getText() + "X");
				}
			}
		});

		panel.add(x);

		c = new JButton("c");
		c.setFont(font);
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "c");
				} else {
					field.setText(field.getText() + "C");
				}
			}
		});

		panel.add(c);

		v = new JButton("v");
		v.setFont(font);
		v.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "v");
				} else {
					field.setText(field.getText() + "V");
				}
			}
		});

		panel.add(v);

		b = new JButton("b");
		b.setFont(font);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "b");
				} else {
					field.setText(field.getText() + "B");
				}
			}
		});

		panel.add(b);

		n = new JButton("n");
		n.setFont(font);
		n.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "n");
				} else {
					field.setText(field.getText() + "N");
				}
			}
		});

		panel.add(n);

		m = new JButton("m");
		m.setFont(font);
		m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (control == 0) {
					field.setText(field.getText() + "m");
				} else {
					field.setText(field.getText() + "M");
				}

			}
		});

		/***** Space *****/
		panel.add(m);

		space = new JButton("Space");
		space.setFont(font);
		space.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(field.getText() + " ");
			}
		});

		panel.add(space);

		/***** Period *****/
		period = new JButton(".");
		period.setFont(font);
		period.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(field.getText() + ".");
			}
		});

		panel.add(period);

		/***** Underscore *****/
		underscore = new JButton("_");
		underscore.setFont(font);
		underscore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(field.getText() + "_");
			}
		});

		panel.add(underscore);
	}

}
