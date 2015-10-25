cse23152, 212497509, Hasan, Sadman Sakib
ksangeev, 213179734, Navaratnam, Sangeev
shfb0019, 213159116, Abdullah, Ashfaq

---------------------
Special Requirements: (optional)
---------------------

**************PLEASE READ THIS BEFORE YOU TEST OUR APPLICATION**************

Our application uses several special features. We have 5 main pages (i.e Welcome page, Login page, Vehicle information page, Insurance page and Print ticket page).
Each page has their own simple additional features and error detections. Below are the description of each pages:

Common features that are shared within all the 5 pages - 
- Local date and time (top of the screen)
- Copyright information (bottom of the screen)
- Logo
- Signout button (when pressed the user is logged out of the current session and goes back to the welcome page and ONLY USED IN VEHICLE INFO PAGE, INSURANCE PAGE, 
TICKET PAGE)
- Login name (ONLY USED IN VEHICLE INFO PAGE, INSURANCE PAGE, TICKET PAGE)

1)**************Welcome Page**************

	-> Power button = the power button utilizes the administrative power to turn off the whole application which requires a four digit password (the password is
			1234). This use is required because our application takes the maximum size of the screen and to terminate the application, you need to FORCE
			QUIT which is not so convenient. That is why we chose to add a power button for this simple usage.

	-> Start button = the start button is used to move onto the login page where the user enters all their credentials.
	
2)**************Login Page**************

	-> Student ID textfield and Pin textfield = these two textfields are used for the user logins. The user can interact with it by our numerical keyboard.

	-> Numerical keyboard = this keyboard is a simple 0-9 numerical keyboard including the delete and enter button.

	-> Login button = the login button validates the user inputs against our database (i.e student id and pin). Once validated, the user is logged onto our 
			kiosk system and moves onto the vehicle information page. If the user has used the kiosk before (i.e entered car details and insurance details)
			all their previous session information is loaded back.

3)**************Vehicle Information Page**************

	-> Make, Model and Plate number textfield = these three textfields are used for user car details. The user can interact with it by our alphabetical keyboard.
			These fields are optional, therefore the user can leave it blank and no validation is required.

	-> Alphabetical keyboard = this keyboard is a simple qwerty keyboard along with 0-9 numbers and other symbols.

	-> Next button = the next button is used to move onto the insurance info page where the user will enter their car insurance details.

4)**************Insurance Page**************

	-> Drop down insurance menu = this drop down list of insurance company from our database are given to the user to choose from.

	-> Policy number textfield = this textfield is used for the user to enter their policy number associated with their insurance company.
				This field is optional, therefore the user can leave it blank and no validation is required.

	-> Numerical keyboard = this keyboard is a simple 0-9 numerical keyboard including the delete and enter button.

	-> Next button = the next button is used to move onto the ticket page where the user can purchase their ticket. This button validates against the chosen
			insurance company (i.e you HAVE to choose a company from the drop down menu). If the user had already purchased a ticket before hand, and DID
			NOT pay off their outstanding balance at that moment, they are prompted to pay it off right now before proceeding to buy another ticket. And 
			if the user has then decided to pay off the amount, the user can ONLY proceed to purchase a new ticket if their previous ticket has expired 
			(i.e NO USER CAN HAVE MORE THAN ONE PARKING TICKET). If the user decides not to pay off, then the user cannot proceed to the next page ONLY,
			however they have access to the previous page and the option to signout.

	-> Back button = the go back button is used to navigate back to the previous page (i.e the vehicle information page).
			
5)**************Ticket Page**************

	-> Expiry date field = this textfield lets the user choose an expiry date for the ticket from a drop down calendar. The drop down calendar is a jar file which 
				IS NOT our code (reference = https://github.com/JDatePicker/JDatePicker). However, we analyzed the code and changed the display of
				the calendar slightly (making the size and fonts bigger).

	-> Back button = the go back button is used to navigate back to the previous page (i.e the insurance info page).

	-> Print ticket button = the print ticket is used to print the parking ticket. When clicked, the e-ticket with the user information and expiry date is shown
				on the ticket. It also gives an option for the user to subscribe to the parking newsletter. It also activates the alphabetical keyboard
				which is used for user entry for the email and also activates the PAY NOW button which gives the user an option to pay off the 
				outstanding balance right away. It also disables go back button and expiry date field.

-------
Design:
-------

We went to the parking lots at york and many different atm machines and saw how they were designed and how they worked. That motivated us to build a system using 
that kind of layout. After a bit of research, we found that card layout works the best with this kind of systems and decided that our main panel will be of Card Layout 
so that we can switch back and forth between pages to make it seem more like a kiosk.

We also thought of other things to include in our kiosk which would make it more of a kiosk-like use. For example the use of animated date and time.

---------------
Communications:
---------------

September 24, 2015; 4:00pm-6:00pm
Sakib, Ashfaq, Sangeev met to create the group and brainstrom the ideas and made a draft.

September 25, 2015; 11:00am
Ashfaq created whats app group for communication purposes.

September 27, 2015; 10:30am-3:30pm
Sakib, Ashfaq and Sangeev met up and took the ideas from the draft and divided the work between the three of us.

September 28, 2015: 9:00am-5:30pm
Sakib, Ashfaq and Sangeev met up and were working on their own responsibility and helping each other out.

September 29, 2015: 10:00am-1:00pm
	- Ashfaq and Sangeev met up and worked on their own responsibility.
	- Sakib and Sangeev met up and went to the prof and showed him what we have got so far and got feedback from him.

October 1, 2015: 10:00am-2:30pm
Sakib, Ashfaq and Sangeev met up and started improving the application based on the feedback.

October 5, 2015: 10:00am-5:30pm
Sakib, Ashfaq and Sangeev met up and discussed new and better features to implement (ex. the power button).

October 7, 2015: 2:30pm-10:00pm
Sakib and Sangeev met up to update the layout color and make it more fancy. We also asked the 1006 lab TA to use our application and give user feedback.

October 8, 2015: 10:00am-2:30pm
Sakib and Ashfaq met up and discussed ideas to implement the pay now option in the ticket page as well as insurance page.

October 13, 2015: 9:00am-2:30pm
Sakib, Ashfaq and Sangeev met up and did the unit testing and debugging of the whole application. Ashfaq emailed the TA to set up a meeting to show her our application.

October 14, 2015: 1:00pm-5:30pm
Sakib, Ashfaq and Sangeev met up with the TA to get user feedback from her and took her feedback positively and improved the application.

October 15, 2015: 10:00am-2:30pm
Sakib, Ashfaq and Sangeev met up and went to random people to get them to test the application and get user feedback.

October 16, 2015: 12:00pm
Sakib, Ashfaq and Sangeev met up to do final testing before submitting.

-----------------
Responsibilities:
-----------------

Sakib:
- Coded and Tested the welcome, login and vehicle info page
- GUI Functionality and Design
- Designed keyboard and calendar
- Implemented separate database for user access information

Ashfaq:
- GUI Layout
- Coded and Tested the Insurance page
- GUI Functionality and Design
- Designed keyboard and calendar

Sangeev:
- GUI Layout
- Coded and Tested the Ticket page
- GUI Functionality and Design
- Designed keyboard and calendar