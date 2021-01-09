To compile:
javac MovieDB.java

To run: 
java MovieDB

Then, enter Oracle username and password to connect to the database. 
The menu should pop up and the user can pick any options from the menu. 

Menu: 
 1) View table content (to view all tables from the database)

 2) Insert new record (insert a new record to any table in the database, the user will have option numbers of all tables to pick and the program will ask for each info of each attribute one by one, and after each insertion it will show the user a confirmation to check whether the insertion was successful or not.

 3) Update record (update any record from any table in the database, the program will print out the list of attributes, and the user will enter the set command to set new information (attribute = new value) and conditions if any).
 Demo:
	-- Select a table to update --OR-- Return to Menu 
	Member Table Columns list: 
	Member_ID, First_Name, Last_Name, Card_Number, Exp_Date. 

	Enter Set Command: last_name = 'John'
	Enter condition(s) - (Enter to skip): member_id = 'djo'
	Update Failed -- Confirmation (check for primary/foreign key constraint).

 4) Delete record (delete any record from any table from the database, the user will have options to enter conditions/if any).
 
 5) Search for movies (search for any movies by using a keyword, the key word can be anything from actor's first name/last name, title, etc. The program will join all needed tables and find all distinct movies using the given keyword).

 6) Show information for a member's profile (the program will ask for member_ID and profile_id) 

 7) Exit
 
 Once the user picks an option from the menu, the program will keep asking the user if he/she wants to continue or go back to the menu by choosing the "Return to menu" option or Press enter to return. Any errors will be raised and output to the screen for invalid input and ask the user to try again.



