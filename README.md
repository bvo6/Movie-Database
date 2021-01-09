# Movie-Database

# General. 
Your project is to design and implement a database for an online movie streaming company like Netflix. At the back-end, the database manages all of its data in the Oracle database management system. The database stores information about customers, movies, actors, and customer viewing history. At the front-end, you are required to implement a command-line interface to its users (DBMS users) using Java and JDBC (although if you love challenge and wantto build a web interfaceor a GUI, you can do so for extra credit). 

1. Members. Store the following for eachmember:\
  a. Unique member ID\
  b. Full name (first name, last name)\
  c. Credit card on filed.Profiles (see below)
  
2. Movies. For each movie:\
  a. Unique movieID\
  b. Movie name\
  c. Year it was produced\
  d. Cast(i.e. actors in the movie)\
  e. Producerf.Genre(s)\
  g. Average member rating
  
3. Actors/Actresses. For each actor/actress:\
  a. Unique ID\
  b. Name(first, last)\
  
4. Each member can have up to 5 profiles in the account for different members in the household. For example, an account owner “princeharry” may have a profile for himself “Harry”, another profile “Meghan” for his wife, and a separate profile “Kid.”For each profile, we have:\
  a. Profile name (not unique across all members, but unique within an account)\
  b. Favoritemovie genre(s)\
  c. Viewinghistory (see below)
  
5. There is a separate viewing history for each “profile” created by a member. For the history, we want to store\
  a. Movies watched\
  b. Ratings given by this profile, if available. Rating scale is from 1 to 5, 1 being “Do not like” and 5 being “Love the movie”
  
 You’re only required to make the interface for the DBMS user, who has access to manipulate and query from all relations.
  • View table content: Give user a list of existing tables so that he/she can select one to view the tuples.\
  • Add records: Enter information for new members, movies, actors, etc. Update/deleteinformation.\ 
  • Search database: Allow users to search for movies based on title or actor; display all matching movies (movie name, year, and average rating) with their average ratings. You should use partial matching instead of exact matching for strings.\ 
  • Show rental history for a given profile (you’ll need to ask for account info first, and then profile).
  • Exit the program
  
  Here is a suggestion on what your (command-line) menu should look like:
  
  When the program starts, prompt the user for his/her login and password. This is the Oracle login/password. Please do not hard-code your own login information in the code. Once the program connects to the database successfully, display the following menu options. After each selection is executed, your program should go back to the main menu.
  
  1. View table content –list your tables for the user to choose, then upon user selection, display the content (tuples) in the table
  
  2. Insert new record into... (the exact attributes depend on your database design) The design is up to you –you could follow the attribute-by-attribute steps as described below once the user selects a table to insert into, or you could list all the attributes and accepts a string containing all attribute values (delimited by commas). When inserting a new rating, your trigger should update the average rating for the given movie. Also make sure that the number of profiles per account does not exceed 5.
    a. Account
      i. Member ID\
      ii. First name\
      iii. Last name\
      iv. Etc.\   
    b. Movie\
      i. Movie ID\
      ii. Movie name\
    c. Actor\
      i. Actor ID\
      ii. First name\
      iii. Last name\
      iv. Etc.\
    d. Profile\
      i. Member ID\
      ii. Profile name\
    e. History\
      i. Member ID\
      ii. Profile name\
      iii. Movie ID\
      iv. rating\
      v. Etc.\
      
  3. Update record\
    a. Update (then ask for information to update)\
    b. Delete (then ask for record to delete)
    
  4. Search for movies\
    a. Movie name\
    b. Actor name
  
  5. Show information for a member’s profile.\
    a. Prompt for member ID and then profile name\
    b. See rental history
    
  6. Exit
      
      
      
      
