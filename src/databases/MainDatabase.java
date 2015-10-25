package databases;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;


public class MainDatabase 
{
     /********private variables*******/
	private List<String> list_of_student_numbers  = new ArrayList<String>();
	private List<String> list_of_pin_numbers  = new ArrayList<String>();
	private List<String> list_of_student_name  = new ArrayList<String>();
	private List<String> list_of_student_status  = new ArrayList<String>();
	
	
	 /********public constructor which adds a student number to AccessDatabase********/
	public MainDatabase()
	{
		access_database();
		AccessDatabase ad = new AccessDatabase("");
		ad.access_database();
		
		boolean found;
		
		for(int i = 0; i < list_of_student_numbers.size();)
		{
			found = false;
			
			for(int j = 0; j < ad.student_numbers.size();)
			{
				if( list_of_student_numbers.get(i).equalsIgnoreCase(ad.student_numbers.get(j)))
				{
					found = true;
					break;
				}
				j++;
			}
			
			if(found == false)
			{
				ad.add_to_file(list_of_student_numbers.get(i));
			}
				
			i++;
		}
	}
	
	/*
	 * public method which returns the index of the student number
	 * in the list of the student numbers
	 * 
	 */
	public int find_index_of_student_number(String student_number)
	{
		int local = 0;
		for(int i = 0; i < list_of_student_numbers.size(); i++)
		{
			if(student_number.equalsIgnoreCase(list_of_student_numbers.get(i)))
			{
				local = i;
				i = list_of_student_numbers.size()+1;
			}
		}
		
		return local;
	}
	
	/* 
	 * public method which returns true if the student number is in the list of the 
	 * student numbers. Otherwise returns false.
	 */
	public boolean check_student_number(String student_number)
	{
		boolean res = false;
		for(String s : list_of_student_numbers)
		{
			if(student_number.equalsIgnoreCase(s))
				res =  true;
		}
		return res;
	}
	
	/* 
	 * public method which returns true if the pin number is in the list of
	 * the pin numbers. Otherwise returns false.
	 */
	public boolean check_pin_number(String pin_number)
	{
		boolean res = false;
		for(String s : get_pin_number_list())
		{
			if(pin_number.equalsIgnoreCase(s))
				res =  true;
		}
		return res;
	}
	
	/********public method which returns the list of the student numbers********/
	public List<String> get_student_number_list()
	{
		return list_of_student_numbers;
	}
	
	/********public method which returns the list of the pin numbers********/
	public List<String> get_pin_number_list()
	{
		return list_of_pin_numbers;
	}
	
	/********public method which returns the list of the student names********/
	public List<String> get_student_name_list()
	{
		return list_of_student_name;
	}
	
	/********public method which returns the list of the student status********/
	public List<String> get_student_status_list()
	{
		return list_of_student_status;
	}
	
	/********public method which creates a new MainDatabase********/
	public void refresh()
	{
		new MainDatabase();
	}
	
	 /* 
	  * public method which accesses the database from the file "students.txt"
	  * Each line in the file has the following format
	  * student number, pin number, student last name, student first name, status
	  * 
	  */	
	public void access_database()
	{
		String regex_file = "^(\\d{9}),[ ]*(\\d{4}),[ ]*([A-Z|a-z]+[']?[-]?[ ]?[A-Z|a-z]*),[ ]*([A-Z|a-z]+[']?[-]?[ ]?[A-Z|a-z]*),[ ]*(ok|arrears)$";
  	  	Pattern pattern = Pattern.compile(regex_file);
		File f = new File("students.txt");
		Scanner sc;
		try {
			sc = new Scanner(f);
			while(sc.hasNextLine())
			{
				String line = sc.nextLine();
				Matcher matcher = pattern.matcher(line);
				
				if(matcher.find())
				{
					list_of_student_numbers.add(matcher.group(1));
					list_of_pin_numbers.add(matcher.group(2));
					list_of_student_name.add(matcher.group(4) + " " + matcher.group(3));
					list_of_student_status.add(matcher.group(5));
				}
			}
			sc.close();
		} 
		catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "No database found. Please check file path.");
			System.exit(0);
		}
	}
}
