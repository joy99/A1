package databases;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class AccessDatabase 
{
	 /********public and private variables********/
	private String student_num;
	public List<String> student_numbers = new ArrayList<String>();
	public String make, model, insurance_company, plate_num, policy_num;
	private File f = new File("access.txt");
	private String due;
	private String date;
	
	
	 /********public constructor which takes as a parameter a student number ********/
	public AccessDatabase(String stud_num)
	{
		student_num = stud_num;
	}
	
	 /********public method which returns the make of the car********/
	public String get_make()
	{
		return make;
	}
	
	 /********public method which returns the model of the car********/
	public String get_model()
	{
		return model;
	}
	
	 /********public method which returns the plate number of the car********/
	public String get_plate_num()
	{
		return plate_num;
	}
	
	 /********public method which returns the student number********/
	public String get_student_number()
	{
		return student_num;
	}
	
	 /********public method which returns the policy number of the car insurance********/
	public String get_policy_num()
	{
		return policy_num;
	}
	
	 /********public method which returns the insurance company********/
	public String get_insurance_company()
	{
		return insurance_company;
	}
	
	 /********public method which returns the balance due for a purchased parking ticket********/
	public String get_amount_due()
	{
		return due;
	}
	
	 /********public method which returns the expiry date for a purchased parking ticket********/
	public String get_date()
	{
		return date;
	}
	
	 /*
	  * public method which takes as a parameter the student number.
	  * This method add a new line to the file "access.txt." with the following pattern 
	  * "\r\n" + student_number + " ->" + "|" + "|" + "|" + "|" +"|" +"|"
	  */
	public void add_to_file(String student_number)
	{
		FileWriter fileWritter;
		try 
		{
			fileWritter = new FileWriter(f.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write("\r\n" + student_number + " ->" + "|" + "|" + "|" + "|" +"|" +"|");
			bufferWritter.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	 /* 
	  * public method which accesses the database from the file "access.txt"
	  * Each line in the file has the following format
	  * student number -> make| model| plate number| insurance company| policy number| balance due| ticket expiry date
	  * 
	  */
	public void access_database()
	{
		
		String regex_file = "^(\\d{9}) ->(.*)\\|(.*)\\|(.*)\\|(.*)\\|(.*)\\|(.*)\\|(.*)$";
  	  	Pattern pattern = Pattern.compile(regex_file);
		
		Scanner sc;
		try 
		{
			sc = new Scanner(f);
			while(sc.hasNextLine())
			{
				String line = sc.nextLine();
				Matcher matcher = pattern.matcher(line);
				
				
				if(matcher.find())
				{
					student_numbers.add(matcher.group(1));
					
					if(matcher.group(1).equalsIgnoreCase(student_num))
					{
						make = matcher.group(2);
						model = matcher.group(3);
						plate_num = matcher.group(4);
						insurance_company = matcher.group(5);
						policy_num = matcher.group(6);
						due = matcher.group(7);
						date = matcher.group(8);
					}
				}
			}
			sc.close();
		} 
		catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "No external database found.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}
