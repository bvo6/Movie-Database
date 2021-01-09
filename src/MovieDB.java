import java.sql.*;
import java.util.Scanner;
/**
 * A Movie Database that allows users to access and query the database.
 * CS 450 - 001
 * @author Bao Vo
 */ 
public class MovieDB 
{
  // Empty constructor.
  public MovieDB () {}
  /*
   * Get a connection by the given username and password.
   */ 
  public Connection getConnection(String username, String password) 
  {
    String jdbc_url = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    // create a connection
    Connection connection = null;
    try {
      connection = DriverManager.getConnection (jdbc_url, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }
  /* -------------------------------View Table---------------------------------*/
  
  /**
   * Show the user a list of tables and user can choose which table to view.
   */ 
  public void viewTable(Scanner scanner, Connection connection) 
  {
    String [] tables = {"Member", "Genre", "Movie", "Actor", "Profile", "Favorite_Genre", "Movied_Watched", 
      "Movie_Genre", "Starred_By", "Return to menu"};
    while (true) 
    {
      System.out.println("\n-- Select a table to view --OR-- Return to Menu \n");
      for (int i = 0; i < tables.length; i++) {
        System.out.println((i+1) + ") " + tables[i]);
      }
      System.out.print("\nEnter option number: ");
      int choice = scanner.nextInt();
      scanner.nextLine();
      if(choice < 1 || choice > 11) {
        System.out.println("Invalid option!");
        continue;
      } else if (choice == 10) {
        return;
      } else {
        switch (choice) {
          case 1: printMember(connection); break;
          case 2: printGenre(connection); break;
          case 3: printMovie(connection); break;
          case 4: printActor(connection); break;
          case 5: printProfile(connection); break;
          case 6: printFavoriteGenre(connection); break;
          case 7: printMoviesWatched(connection); break;
          case 8: printMovieGenre(connection); break;
          case 9: printStarredBy(connection); break;
        }
      }
    } 
  }
  
  /**
   * Print out the member table.
   */ 
  public void printMember(Connection connection) 
  {
    try {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("Select * from Member");
      System.out.println("\nMember Table: \n");
      System.out.println("Member ID  |  First Name  |  Last Name  |  Credit Card  |  Expiration Date\n");
      while(rs.next()) {
        System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getString(4) + " | " + rs.getString(5));
      }
      stmt.close();
      rs.close();
    }catch (SQLException e){ System.out.println(e);}
  }
  
  /**
   * Print out the Movie table.
   */ 
  public void printMovie(Connection connection) 
  {
    try 
    {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("Select * from Movie");
      System.out.println("\nMovie Table: \n");
      System.out.println("Movie ID  |  Title  |  Movie Year  |  Producer | Average Rating \n");
      while(rs.next()) {
        System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getInt(3) + " | " + rs.getString(4) + " | " + rs.getFloat(5));
      }
      stmt.close();
      rs.close();
    } catch (SQLException e){ System.out.println(e);}
  }
  
  /**
   * Print out the Actor table.
   */ 
  public void printActor(Connection connection) 
  {
    try 
    {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("Select * from Actor");
      System.out.println("\nActor Table: \n");
      System.out.println("Actor ID  |  First Name  |  Last Name\n");
      while(rs.next()) {
        System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
      }
      stmt.close();
      rs.close();
    } catch (SQLException e){ System.out.println(e);}
  }
  
  /**
   * Print out the Profile table.
   */ 
  public void printProfile(Connection connection) 
  {
    try 
    {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("Select * from Profile");
      System.out.println("\nProfile Table: \n");
      System.out.println("Member ID  |  Profile Name\n");
      while(rs.next()) {
        System.out.println(rs.getString(1) + " | " + rs.getString(2));
      }
      stmt.close();
      rs.close();
    } catch (SQLException e){ System.out.println(e);}
    
  }
  
  /**
   * Print out the Genre table.
   */ 
  public void printGenre(Connection connection) 
  {
    try 
    {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("Select * from Genre");
      System.out.println("\nGenre Table: \n");
      while(rs.next()) {
        System.out.println(rs.getString(1));
      }
      stmt.close();
      rs.close();
    } catch (SQLException e){ System.out.println(e);}
  }
  
  /**
   * Print out the Favorite_Genre table.
   */ 
  public void printFavoriteGenre(Connection connection) 
  {
    try 
    {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("Select * from Favorite_Genre");
      System.out.println("\nFavorite Genre Table: \n");
      System.out.println("Member ID  |  Profile's Name  |  Genre\n");
      while(rs.next()) {
        System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
      }
      stmt.close();
      rs.close();
    } catch (SQLException e){ System.out.println(e);}
  }
  
  /**
   * Print out the Movies_Watched table (history of movies that users have watched).
   */ 
  public void printMoviesWatched(Connection connection) 
  {
    try 
    {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("Select * from Movies_Watched");
      System.out.println("\nMovies Watched Table: \n");
      System.out.println("Member ID  |  Profile's Name  |  Movie ID  |  Rating\n");
      while(rs.next()) {
        System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getInt(4));
      }
      stmt.close();
      rs.close();
    } catch (SQLException e){ System.out.println(e);}
  }
  
  /**
   * Print out the Movie Genre table.
   */ 
  public void printMovieGenre(Connection connection) 
  {
    try 
    {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("Select * from Movie_Genre");
      System.out.println("\nMovie Genre Table: \n");
      System.out.println("Movie ID  |  Genre\n");
      while(rs.next()) {
        System.out.println(rs.getString(1) + " | " + rs.getString(2));
      }
      stmt.close();
      rs.close();
    } catch (SQLException e){ System.out.println(e);}
  }
  
  /**
   * Print out the Starred_By table (list of actors and movies which they have starred).
   */ 
  public void printStarredBy(Connection connection) 
  {
    try 
    {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("Select * from Starred_By");
      System.out.println("\nStarred By Table: \n");
      System.out.println("Movie ID  |  Actor ID\n");
      while(rs.next()) {
        System.out.println(rs.getString(1) + " | " + rs.getString(2));
      }
      stmt.close();
      rs.close();
    } catch (SQLException e) { System.out.println(e);}
  }
  
  /* --------------------------------Insert Table--------------------------------*/
  
  /**
   * Show the user the list of tables to pick.
   * Call the approriate method of the chosen table to insert. 
   */ 
  public void insertTable(Scanner scanner, Connection connection) 
  {
    String [] tables = {"Member", "Genre", "Movie", "Actor", "Profile", "Favorite_Genre", "Movied_Watched", 
      "Movie_Genre", "Starred_By", "Return to menu"};
    while (true) 
    {
      System.out.println("\n-- Select a table to insert into --OR-- Return to Menu \n");
      for (int i = 0; i < tables.length; i++) {
        System.out.println((i+1) + ") " + tables[i]);
      }
      System.out.print("\nEnter option number: ");
      int choice = scanner.nextInt();
      scanner.nextLine();
      if(choice < 1 || choice > 11) {
        System.out.println("Invalid option!");
        continue;
      } else if (choice == 10) {
        return;
      } else {
        switch (choice) {
          case 1: insertMember(scanner, connection); break;
          case 2: insertGenre(scanner, connection); break;
          case 3: insertMovie(scanner, connection); break;
          case 4: insertActor(scanner, connection); break;
          case 5: insertProfile(scanner, connection); break;
          case 6: insertFavoriteGenre(scanner, connection); break;
          case 7: insertMoviesWatched(scanner, connection); break;
          case 8: insertMovieGenre(scanner, connection); break;
          case 9: insertStarredBy(scanner, connection); break;
        }
      }
    } 
  }
  
  /**
   * Insert new record into Member table.
   */ 
  public void insertMember(Scanner scanner, Connection connection) 
  {
    System.out.print("Enter new Member ID: ");
    String memberID = scanner.nextLine();
    System.out.print("Enter First Name: ");
    String fName = scanner.nextLine();
    System.out.print("Enter Last Name: ");
    String lName = scanner.nextLine();
    System.out.print("Enter Credit Card Number: ");
    String creditCard = scanner.nextLine();
    System.out.print("Enter Expiration Date (MM/YY): ");
    String exp = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = "INSERT INTO MEMBER values('" + memberID + "', '" + fName + "', '" + lName + "', '" + creditCard + "', '" + exp + "')";
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Inserted");             
      else            
        System.out.println("Insert Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);} 
  }
  
  /**
   * Insert new record into Genre table.
   */
  public void insertGenre(Scanner scanner, Connection connection) 
  {
    System.out.print("Enter new Genre: ");
    String genre = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = "INSERT INTO Genre values('" + genre + "')";
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Inserted");             
      else            
        System.out.println("Insert Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);} 
  }
  
  /**
   * Insert new record into Movie table.
   */
  public void insertMovie(Scanner scanner, Connection connection) 
  {
    System.out.print("Enter new Movie ID: ");
    String movieID = scanner.nextLine();
    System.out.print("Enter new Title: ");
    String title = scanner.nextLine();
    System.out.print("Enter Movie Year: ");
    int year = scanner.nextInt();
    scanner.nextLine();
    System.out.print("Enter Producer: ");
    String producer = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = "INSERT INTO Movie values('" + movieID + "', '" + title + "', " + year + ", '" + producer + "', " + "null" + ")";
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Inserted");             
      else            
        System.out.println("Insert Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);} 
  }
  
  /**
   * Insert new record into Actor table.
   */
  public void insertActor(Scanner scanner, Connection connection) 
  {
    System.out.print("Enter new Actor ID: ");
    String actorID = scanner.nextLine();
    System.out.print("Enter Actor's First Name: ");
    String fName = scanner.nextLine();
    System.out.print("Enter Actor's Last Name: ");
    String lName = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = "INSERT INTO Actor values('" + actorID + "', '" + fName + "', '" + lName + "')";
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Inserted");             
      else            
        System.out.println("Insert Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);} 
  }
  
  /**
   * Insert new record into Profile table.
   * Cannot exceed more than 5 profiles per member/account.
   */
  public void insertProfile(Scanner scanner, Connection connection) 
  {
    System.out.print("Enter Member ID: ");
    String memberID = scanner.nextLine();
    System.out.print("Enter Profile Name: ");
    String profileName = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = "INSERT INTO Profile values('" + memberID + "', '" + profileName + "')";
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Inserted");             
      else            
        System.out.println("Insert Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);} 
  }
  
  /**
   * Insert new record into Favorite_Genre table.
   */
  public void insertFavoriteGenre(Scanner scanner, Connection connection) 
  {
    System.out.print("Enter Member ID: ");
    String memberID = scanner.nextLine();
    System.out.print("Enter Profile Name: ");
    String profileName = scanner.nextLine();
    System.out.print("Enter Profile's Favorite Genre: ");
    String genre = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = "INSERT INTO Favorite_Genre values('" + memberID + "', '" + profileName + "', '" + genre + "')";
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Inserted");             
      else            
        System.out.println("Insert Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);} 
  }
  
  /**
   * Insert new record into Movies_watched table.
   */
  public void insertMoviesWatched(Scanner scanner, Connection connection) 
  {
    System.out.print("Enter Member ID: ");
    String memberID = scanner.nextLine();
    System.out.print("Enter Profile Name: ");
    String profileName = scanner.nextLine();
    System.out.print("Enter Movie ID: ");
    String movieID = scanner.nextLine();
    System.out.print("Enter Rating: ");
    int rating = scanner.nextInt();
    scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = "INSERT INTO Movies_Watched values('" + memberID + "', '" + profileName + "', '" + movieID + "', " + rating + ")";
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Inserted");             
      else            
        System.out.println("Insert Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);} 
  }
  
  /**
   * Insert new record into Movie_Genre table.
   */
  public void insertMovieGenre(Scanner scanner, Connection connection) 
  {
    System.out.print("Enter Movie ID: ");
    String movieID = scanner.nextLine();
    System.out.print("Enter Movie Genre: ");
    String genre = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = "INSERT INTO Movie_Genre values('" + movieID + "', '" + genre + "')";
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Inserted");             
      else            
        System.out.println("Insert Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);} 
  }
  
  /**
   * Insert new record into Starred_By table.
   */
  public void insertStarredBy(Scanner scanner, Connection connection) 
  {
    System.out.print("Enter Movie ID: ");
    String movieID = scanner.nextLine();
    System.out.print("Enter Actor ID: ");
    String actorID = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = "INSERT INTO Starred_By values('" + movieID + "', '" + actorID + "')";
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Inserted");             
      else            
        System.out.println("Insert Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);} 
  }
  
  /* --------------------------------Update Table--------------------------------*/
  
  /**
   * Show the user the list of tables to be updated.
   * Get option number from user.
   */ 
  public void updateTable(Scanner scanner, Connection connection) 
  {
    String [] tables = {"Member", "Genre", "Movie", "Actor", "Profile", "Favorite_Genre", "Movied_Watched", 
      "Movie_Genre", "Starred_By", "Return to menu"};
    while (true) 
    {
      System.out.println("\n-- Select a table to update --OR-- Return to Menu \n");
      for (int i = 0; i < tables.length; i++) {
        System.out.println((i+1) + ") " + tables[i]);
      }
      System.out.print("\nEnter option number: ");
      int choice = scanner.nextInt();
      scanner.nextLine();
      if(choice < 1 || choice > 11) {
        System.out.println("Invalid option!");
        continue;
      } else if (choice == 10) {
        return;
      } else {
        switch (choice) {
          case 1: updateMember(scanner, connection); break;
          case 2: updateGenre(scanner, connection); break;
          case 3: updateMovie(scanner, connection); break;
          case 4: updateActor(scanner, connection); break;
          case 5: updateProfile(scanner, connection); break;
          case 6: updateFavoriteGenre(scanner, connection); break;
          case 7: updateMoviesWatched(scanner, connection); break;
          case 8: updateMovieGenre(scanner, connection); break;
          case 9: updateStarredBy(scanner, connection); break;
        }
      }
    } 
  }
  
  /**
   * Update the Member table.
   */ 
  public void updateMember(Scanner scanner, Connection connection) 
  {
    System.out.println("Member Table Columns list: ");
    System.out.println("Member_ID, First_Name, Last_Name, Card_Number, Exp_Date. \n");
    System.out.print("Enter Set Command: ");
    String set = scanner.nextLine();
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "UPDATE MEMBER Set " + set;
      } else {
        sql = "UPDATE MEMBER Set " + set + " where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Updated");             
      else            
        System.out.println("Update Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  
  /**
   * Update the Genre table.
   */ 
  public void updateGenre(Scanner scanner, Connection connection) 
  {
    System.out.println("Genre Table Columns list: ");
    System.out.println("m_Genre. \n");
    System.out.print("Enter Set Command: ");
    String set = scanner.nextLine();
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "UPDATE Genre Set " + set;
      } else {
        sql = "UPDATE Genre Set " + set + " where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Updated");             
      else            
        System.out.println("Update Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  
  /**
   * Update the Movie table.
   */ 
  public void updateMovie(Scanner scanner, Connection connection) 
  {
    System.out.println("Movie Table Columns list: ");
    System.out.println("Movie_ID, Title, Movie_Year (Int), Producer. \n");
    System.out.print("Enter Set Command: ");
    String set = scanner.nextLine();
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "UPDATE Movie Set " + set;
      } else {
        sql = "UPDATE Movie Set " + set + " where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Updated");             
      else            
        System.out.println("Update Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  
  /**
   * Update the Actor table.
   */ 
  public void updateActor(Scanner scanner, Connection connection) 
  {
    System.out.println("Actor Table Columns list: ");
    System.out.println("Actor_ID, First_Name, Last_Name. \n");
    System.out.print("Enter Set Command: ");
    String set = scanner.nextLine();
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "UPDATE Actor Set " + set;
      } else {
        sql = "UPDATE Actor Set " + set + " where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Updated");             
      else            
        System.out.println("Update Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  /**
   * Update the Profile table.
   */ 
  public void updateProfile(Scanner scanner, Connection connection) 
  {
    System.out.println("Profile Table Columns list: ");
    System.out.println("Member_ID, Profile_Name. \n");
    System.out.print("Enter Set Command: ");
    String set = scanner.nextLine();
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "UPDATE Profile Set " + set;
      } else {
        sql = "UPDATE Profile Set " + set + " where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Updated");             
      else            
        System.out.println("Update Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  
  /**
   * Update the Favorite_Genre table.
   */ 
  public void updateFavoriteGenre(Scanner scanner, Connection connection) 
  {
    System.out.println("Favorite Genre Table Columns list: ");
    System.out.println("Member_ID, Profile_Name, m_Genre. \n");
    System.out.print("Enter Set Command: ");
    String set = scanner.nextLine();
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "UPDATE Favorite_Genre Set " + set;
      } else {
        sql = "UPDATE Favorite_Genre Set " + set + " where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Updated");             
      else            
        System.out.println("Update Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  /**
   * Update the Movies_Watched table.
   */ 
  public void updateMoviesWatched(Scanner scanner, Connection connection) 
  {
    System.out.println("Movies Watched Table Columns list: ");
    System.out.println("Member_ID, Profile_Name, Movie_ID, Rating (Int). \n");
    System.out.print("Enter Set Command: ");
    String set = scanner.nextLine();
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "UPDATE Movies_Watched Set " + set;
      } else {
        sql = "UPDATE Movies_Watched Set " + set + " where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Updated");             
      else            
        System.out.println("Update Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  
  /**
   * Update the Movie_Genre table.
   */ 
  public void updateMovieGenre(Scanner scanner, Connection connection) 
  {
    System.out.println("Movie Genre Table Columns list: ");
    System.out.println("Movie_ID, m_Genre. \n");
    System.out.print("Enter Set Command: ");
    String set = scanner.nextLine();
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "UPDATE Movie_genre Set " + set;
      } else {
        sql = "UPDATE Movie_genre Set " + set + " where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Updated");             
      else            
        System.out.println("Update Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  
  /**
   * Update the Starred_By table.
   */ 
  public void updateStarredBy(Scanner scanner, Connection connection) 
  {
    System.out.println("Starred-By Table Columns list: ");
    System.out.println("Movie_ID, Actor_ID. \n");
    System.out.print("Enter Set Command: ");
    String set = scanner.nextLine();
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "UPDATE Starred_By Set " + set;
      } else {
        sql = "UPDATE Starred_By Set " + set + " where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Updated");             
      else            
        System.out.println("Update Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  /* --------------------------------Delete Table--------------------------------*/
  /**
   * Show the user the list of tables to be deleted from.
   * Get option number from user.
   */ 
  public void deleteTable(Scanner scanner, Connection connection) 
  {
    String [] tables = {"Member", "Genre", "Movie", "Actor", "Profile", "Favorite_Genre", "Movied_Watched", 
      "Movie_Genre", "Starred_By", "Return to menu"};
    while (true) 
    {
      System.out.println("\n-- Select a table to delete --OR-- Return to Menu \n");
      for (int i = 0; i < tables.length; i++) {
        System.out.println((i+1) + ") " + tables[i]);
      }
      System.out.print("\nEnter option number: ");
      int choice = scanner.nextInt();
      scanner.nextLine();
      if(choice < 1 || choice > 11) {
        System.out.println("Invalid option!");
        continue;
      } else if (choice == 10) {
        return;
      } else {
        switch (choice) {
          case 1: deleteMember(scanner, connection); break;
          case 2: deleteGenre(scanner, connection); break;
          case 3: deleteMovie(scanner, connection); break;
          case 4: deleteActor(scanner, connection); break;
          case 5: deleteProfile(scanner, connection); break;
          case 6: deleteFavoriteGenre(scanner, connection); break;
          case 7: deleteMoviesWatched(scanner, connection); break;
          case 8: deleteMovieGenre(scanner, connection); break;
          case 9: deleteStarredBy(scanner, connection); break;
        }
      }
    } 
  }
  
  /**
   * Delete from Member table.
   */ 
  public void deleteMember(Scanner scanner, Connection connection) 
  {
    System.out.println("Member Table Columns list: ");
    System.out.println("Member_ID, First_Name, Last_Name, Card_Number, Exp_Date. \n");
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "DELETE FROM MEMBER ";
      } else {
        sql = "DELETE FROM MEMBER where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Deleted");             
      else            
        System.out.println("Delete Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  /**
   * Delete from Genre table.
   */ 
  public void deleteGenre(Scanner scanner, Connection connection) 
  {
    System.out.println("Genre Table Columns list: ");
    System.out.println("m_Genre. \n");
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "DELETE FROM Genre ";
      } else {
        sql = "DELETE FROM Genre where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Deleted");             
      else            
        System.out.println("Delete Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  /**
   * Delete from Movie table.
   */ 
  public void deleteMovie(Scanner scanner, Connection connection) 
  {
    System.out.println("Movie Table Columns list: ");
    System.out.println("Movie_ID, Title, Movie_Year (Int), Producer. \n");
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "DELETE FROM MOVIE ";
      } else {
        sql = "DELETE FROM MOVIE where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Deleted");             
      else            
        System.out.println("Delete Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  
  /**
   * Delete from Actor table.
   */ 
  public void deleteActor(Scanner scanner, Connection connection) 
  {
    System.out.println("Actor Table Columns list: ");
    System.out.println("Actor_ID, First_Name, Last_Name. \n");
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "DELETE FROM Actor ";
      } else {
        sql = "DELETE FROM Actor where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Deleted");             
      else            
        System.out.println("Delete Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  /**
   * Delete from Profile table.
   */ 
  public void deleteProfile(Scanner scanner, Connection connection) 
  {
    System.out.println("Profile Table Columns list: ");
    System.out.println("Member_ID, Profile_Name, m_Genre. \n");
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "DELETE FROM PROFILE ";
      } else {
        sql = "DELETE FROM PROFILE where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Deleted");             
      else            
        System.out.println("Delete Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }

  /**
   * Delete from Favorite_Genre table.
   */ 
  public void deleteFavoriteGenre(Scanner scanner, Connection connection) 
  {
    System.out.println("Favorite Genre Table Columns list: ");
    System.out.println("Member_ID, Profile_Name, m_Genre. \n");
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "DELETE FROM Favorite_Genre ";
      } else {
        sql = "DELETE FROM Favorite_Genre where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Deleted");             
      else            
        System.out.println("Delete Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  
  /**
   * Delete from Movies_Watched table.
   */ 
  public void deleteMoviesWatched(Scanner scanner, Connection connection) 
  {
    System.out.println("Movies Watched Table Columns list: ");
    System.out.println("Member_ID, Profile_Name, Movie_ID, Rating (Int). \n");
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "DELETE FROM MOVIES_WATCHED ";
      } else {
        sql = "DELETE FROM MOVIES_WATCHED where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Deleted");             
      else            
        System.out.println("Delete Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  
  /**
   * Delete from Movie_Genre table.
   */ 
  public void deleteMovieGenre(Scanner scanner, Connection connection) 
  {
    System.out.println("Movie Genre Table Columns list: ");
    System.out.println("Movie_ID, m_Genre. \n");
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "DELETE FROM MOVIE_GENRE ";
      } else {
        sql = "DELETE FROM MOVIE_GENRE where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Deleted");             
      else            
        System.out.println("Delete Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  
  /**
   * Delete from Starred_By table.
   */ 
  public void deleteStarredBy(Scanner scanner, Connection connection) 
  {
    System.out.println("Starred-By Table Columns list: ");
    System.out.println("Movie_ID, Actor_ID. \n");
    System.out.print("Enter condition(s) - (Enter to skip): ");
    String condition = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = null;
      if (condition.isEmpty()) { 
        sql = "DELETE FROM Starred_By ";
      } else {
        sql = "DELETE FROM Starred_By where " + condition;
      }
      int x = stmt.executeUpdate(sql); 
      if (x > 0)             
        System.out.println("Successfully Deleted");             
      else            
        System.out.println("Delete Failed"); 
      stmt.close();
    } catch (SQLException e){ System.out.println(e);}  
  }
  
  /* --------------------------------Search Table--------------------------------*/
  
  /**
   * Search for movies using a keyword.
   * Keyword can be anything from partial title, actor's first/last name, etc. 
   */ 
  public void searchTable(Scanner scanner, Connection connection) 
  {
     System.out.print("\nEnter a keyword OR Press Enter to return to Menu: ");
     String keyword = scanner.nextLine();
     if (keyword.isEmpty()) {
       return;
     }
     try 
     {
       Statement stmt = connection.createStatement();
       String sql = "Select distinct title, movie_year, avg_rating from movie natural join (select * from Movie_Genre natural join (select * from starred_by natural join actor))" 
           + " where movie_ID LIKE '%" + keyword + "%' OR title like '%" + keyword + "%' OR producer LIKE '% " + keyword + "%' OR actor_ID LIKE '%" 
           + keyword + "%' OR first_name LIKE '%" + keyword + "%' OR last_name LIKE '%" + keyword + "%' OR m_genre LIKE '%" + keyword + "%'";
       ResultSet rs = stmt.executeQuery(sql);
       System.out.println("\nMovie Name  |  Movie Year | Average Rating \n");
       while(rs.next()) {
         System.out.println(rs.getString(1) + " | " + rs.getInt(2) + " | " + rs.getFloat(3));
       }
       stmt.close();
       rs.close();
     } catch (SQLException e){ System.out.println(e);}
     searchTable(scanner, connection);
  }
  
  /* --------------------------------Show member's info--------------------------------*/
  
  /**
   * Print out the member including member's ID, profile's name, history, ratings.
   */ 
  public void memberInfo(Scanner scanner, Connection connection) {
    
    System.out.print("\nEnter Member ID OR Press Enter to return to Menu: ");
    String memberID = scanner.nextLine();
    if (memberID.isEmpty()) {
      return;
    }
    System.out.print("Enter Profile Name : ");
    String profile = scanner.nextLine();
    try 
    {
      Statement stmt = connection.createStatement();
      String sql = "Select member_id, profile_name, title, rating from movies_watched natural join movie" 
        + " where member_id = '" + memberID + "' AND profile_name = '" + profile + "'";
      ResultSet rs = stmt.executeQuery(sql);
      System.out.println("\nMember ID  |  Profile Name  |  Movie Name  |  Rating\n");
      while(rs.next()) {
        System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getInt(4));
      }
      stmt.close();
      rs.close();
    } catch (SQLException e){ System.out.println(e);}
    memberInfo(scanner, connection);
  }
  /* --------------------------------Menu & Main--------------------------------*/ 
  
  /**
   * Main menu. 
   */ 
  public static int menu (Scanner in) 
  {
    while(true) {
      System.out.println("\nMenu: ");
      System.out.println(" 1) View table content");
      System.out.println(" 2) Insert new record");
      System.out.println(" 3) Update record");
      System.out.println(" 4) Delete record");
      System.out.println(" 5) Search for movies");
      System.out.println(" 6) Show information for a member's profile");
      System.out.println(" 7) Exit");
      System.out.print("\nEnter option number: ");
      int choice = in.nextInt();
      in.nextLine();
      if(choice < 1 || choice > 7) {
        System.out.println("Invalid option!");
        continue;
      } else {
        return choice;
      }
    }
  }
  
  /**
   * Main method of the program.
   * Show the user the menu and get input as option number.
   */ 
  public static void main(String args[])
  {
    Scanner scanner = new Scanner(System.in);
    System.out.println("\nWelcome to bvo6 Movie Database!");
    System.out.print("\nEnter username: ");
    String username = scanner.nextLine();
    System.out.print("\nEnter password: ");
    String password = scanner.nextLine();
    MovieDB movieDB = new MovieDB();
    Connection connection = movieDB.getConnection(username, password);
    if (connection == null) {
      System.out.println("Failed to connect!");
      System.exit(0);
    }
    while (true) 
    {
      int choice = menu(scanner);
      switch(choice) {
        case 1: movieDB.viewTable(scanner, connection); break;
        case 2: movieDB.insertTable(scanner, connection); break;
        case 3: movieDB.updateTable(scanner, connection); break;
        case 4: movieDB.deleteTable(scanner, connection); break;       
        case 5: movieDB.searchTable(scanner, connection); break;
        case 6: movieDB.memberInfo(scanner, connection); break;
        case 7: 
          scanner.close();
          try {
            connection.close();
          } catch (SQLException e){ System.out.println(e);}
          System.out.println("Good Bye.");
          System.exit(0);
      }
    }
  }
}