package helper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.Timer;

/*---------------- the time class enumerates real time clock ----------------*/
public class Time {
	private JLabel my_label;
	private SimpleDateFormat my_sdf;
	
	public Time(JLabel label, SimpleDateFormat sdf)
	{
		my_label = label;
		my_sdf = sdf;
	}
	
	public void start()
	{
		Timer t = new Timer(1000, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				my_label.setText(my_sdf.format(new Date()));	
			}
			
		});
		t.start();
	}
}
