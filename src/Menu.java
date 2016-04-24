//  File:  Menu.java

//  This program llustrates the use of a menu, which would be the basis
//  for constructing a larger program by adding more options, where each
//  option is handled by a separate function.

//  This file is in the directory ~kdjackso/cs665

import java.sql.*;
import org.apache.derby.jdbc.ClientDriver;
import java.util.Scanner;

public class Menu
{
   public static void main(String[] args)
   {
      int choice;
      Connection conn = null;
      try
      {
         // Step 1:  connect to database server
         Driver d = new ClientDriver();
         String url = "jdbc:derby://localhost:5555/RealEstateDb; create=false";
         conn = d.connect(url, null);

         // Make a menu selection
         choice = PrintMenuAndGetResponse();

         switch (choice)
         {
            case 1: // Step 2 for case 1 -- build and execute the student major
                    // query
                    InputCountryQuery(conn);
                    break;
            case 2: // Step 2 for case 2 build and execute the query to find the
                    // name and major of all students for a given
                    //MajorGradYearQuery(conn);
                    break;
            case 3: // Step 2 for case 1 -- build and execute the student major
                    // query
                    //StudentMajorQuery(conn);
                    break;
            case 4: // Step 2 for case 2 build and execute the query to find the
                    // name and major of all students for a given
                    //MajorGradYearQuery(conn);
                    break;
            case 5: // Step 2 for case 1 -- build and execute the student major
                    // query
                    //StudentMajorQuery(conn);
                    break;
            case 6: // Step 2 for case 2 build and execute the query to find the
                    // name and major of all students for a given
                    //MajorGradYearQuery(conn);
                    break;
            case 7: // Step 2 for case 1 -- build and execute the student major
                    // query
                    //StudentMajorQuery(conn);
                    break;
            case 8: // Step 2 for case 2 build and execute the query to find the
                    // name and major of all students for a given
                    //MajorGradYearQuery(conn);
                    break;
            case 9: // Step 2 for case 2 build and execute the query to find the
                    // name and major of all students for a given
                    //MajorGradYearQuery(conn);
                    break;
            case 10: // Step 2 for case 1 -- build and execute the student major
                    // query
                    //StudentMajorQuery(conn);
                    break;
            case 11: // Step 2 for case 2 build and execute the query to find the
                    // name and major of all students for a given
                    //MajorGradYearQuery(conn);
                    break;
            case 12: // Step 2 for case 1 -- build and execute the student major
                    // query
                    //StudentMajorQuery(conn);
                    break;
            case 13: // Step 2 for case 2 build and execute the query to find the
                    // name and major of all students for a given
                    //MajorGradYearQuery(conn);
                    break;
            default: System.out.println("Illegal choice");
                     break;
         }
      }
      catch(SQLException e)
      {
         e.printStackTrace();
      }
      finally
      {
         // Step 4: Disconnect from the server
         try
         {
            if(conn != null)
               conn.close();
         }
         catch(SQLException e)
         {
            e.printStackTrace();
         }
      }
   }

   public static int PrintMenuAndGetResponse()
   {
      Scanner keyboard = new Scanner(System.in);
      int response;

      System.out.println("Choose from one of the following options:
                         + \t1:  Search listing by country
                         + \t2:  Search listing by state
                         + \t3:  Search listing by city
                         + \t4:  Search listing by postal code
                         + \t5:  Search listing by address
                         + \t6:  Search listing by category
                         + \t7:  Search listing by type
                         + \t8:  Search listing by number of bedrooms
                         + \t9:  Search listing by number of bathrooms
                         + \t10: Search listing by price
                         + \t11: Search listing by date
                         + \t12: Search listing by MLS company
                         + \t13: Search listing by listing number");
      System.out.print("Your choice ==> ");
      response = keyboard.nextInt();
      System.out.println( );
      return response;
   }

      // This method is for the query for all students with their majors.
   public static void InputCountryQuery(Connection conn) throws SQLException
   {
      Statement stmt = conn.createStatement();

      String country;
      Scanner scannerObject = new Scanner(System.in);
      System.out.print("Enter a country: ");
      country = scannerObject.nextLine( );

      String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.StateOrProvince, a.City, a.FullStreetAddress "
                   + "from MLS m, LISTING l, ADDRESS a "
                   + "where a.Country = " + country + " "
                   + "and l.AddressId = a.AddressId "
                   + "and l.MlsId = m.MlsId";
      ResultSet rs = stmt.executeQuery(qry);

      // Step 3: loop through the result set
      System.out.println("MLS Id" + "\tMLS Name" + "\tPrice" + "\tState" + "\tCity" + "\tAddress");
      while (rs.next())
      {
         String mlsId = rs.getString("MlsId");
         String mlsName = rs.getString("MlsName");
         int price = rs.getInt("ListPrice");
         String state = rs.getString("StateOrProvince");
         String city = rs.getString("City");
         String address = rs.getString("FullStreetAddress");
         System.out.println(mlsId + "\t" + mlsName + "\t" + price + "\t" + state + "\t" + city + "\t" + address);
      }
      System.out.println( );
      rs.close();
   }

   // This method is for the query of names of majors graduating in a particular
   // year.
   public static void MajorGradYearQuery(Connection conn) throws SQLException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.println("The majors are; compsci, drama, and math");
      System.out.print("Enter the major of students you wish to inquire about: ");

      String major = keyboard.nextLine();
      major = "'" + major + "'";

      System.out.print("Enter the graduation year of those students: ");
      String year = keyboard.next();
      
      Statement stmt = conn.createStatement();

      String qry = "select SName, DName "
                   + "from DEPT, STUDENT "
                   + "where MajorId = DId"
                   + " and  DName = " + major
                   + "and GradYear = " + year;
      ResultSet rs = stmt.executeQuery(qry);

      // Step 3: loop through the result set
      System.out.println("Name\tMajor");
      while (rs.next())
      {
         String sname = rs.getString("SName");
         String dname = rs.getString("DName");
         System.out.println(sname + "\t" + dname);
      }
      System.out.println( );
      rs.close();
   }
}
