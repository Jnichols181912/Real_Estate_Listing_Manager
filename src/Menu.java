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
                    InputStateQuery(conn);
                    break;
            case 3: // Step 2 for case 1 -- build and execute the student major
                    // query
                    InputCityQuery(conn);
                    break;
            case 4: // Step 2 for case 2 build and execute the query to find the
                    // name and major of all students for a given
                    InputPostalCodeQuery(conn);
                    break;
            case 5: // Step 2 for case 1 -- build and execute the student major
                    // query
                    //InputAddressQuery(conn);
                    break;
            case 6: // Step 2 for case 2 build and execute the query to find the
                    // name and major of all students for a given
                    //InputCategoryQuery(conn);
                    break;
            case 7: // Step 2 for case 1 -- build and execute the student major
                    // query
                    //InputTypeQuery(conn);
                    break;
            case 8: // Step 2 for case 2 build and execute the query to find the
                    // name and major of all students for a given
                    //InputNumberOfBedroomsQuery(conn);
                    break;
            case 9: // Step 2 for case 2 build and execute the query to find the
                    // name and major of all students for a given
                    //InputNumberOfBathroomsQuery(conn);
                    break;
            case 10: // Step 2 for case 1 -- build and execute the student major
                    // query
                    //InputPriceQuery(conn);
                    break;
            case 11: // Step 2 for case 2 build and execute the query to find the
                    // name and major of all students for a given
                    //InputDateQuery(conn);
                    break;
            case 12: // Step 2 for case 1 -- build and execute the student major
                    // query
                    //InputCompanyQuery(conn);
                    break;
            case 13: // Step 2 for case 2 build and execute the query to find the
                    // name and major of all students for a given
                    //InputListingNumberQuery(conn);
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

      System.out.println("Choose from one of the following options:"
                         + "\t1:  Search listing by country"
                         + "\t2:  Search listing by state"
                         + "\t3:  Search listing by city"
                         + "\t4:  Search listing by postal code"
                         + "\t5:  Search listing by address"
                         + "\t6:  Search listing by category"
                         + "\t7:  Search listing by type"
                         + "\t8:  Search listing by number of bedrooms"
                         + "\t9:  Search listing by number of bathrooms"
                         + "\t10: Search listing by price"
                         + "\t11: Search listing by date"
                         + "\t12: Search listing by MLS company"
                         + "\t13: Search listing by listing number");
      System.out.print("Your choice ==> ");
      response = keyboard.nextInt();
      System.out.println( );
      return response;
   }

   // This method is for the query for all listings in a particular country.
   public static void InputCountryQuery(Connection conn) throws SQLException
   {
      Statement stmt = conn.createStatement();

      String country;
      Scanner scannerObject = new Scanner(System.in);
      System.out.print("Enter a country: ");
      country = scannerObject.nextLine( );
      
      country = country.toUpperCase();

      String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.StateOrProvince, a.City, a.FullStreetAddress "
                   + "from MLS m, LISTING l, ADDRESS a "
                   + "where a.Country = " + country + " "
                   + "and l.AddressId = a.AddressId "
                   + "and l.MlsId = m.MlsId";
      ResultSet rs = stmt.executeQuery(qry);

      // If nothing is returned then say so. Otherwise display table results
      if (!rs.isBeforeFirst() ) {    
    	  System.out.println("No listings exist for this country."); 
      } else {
      
    	  // Loop through the result set
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
   }
   
   // This method is for the query for all listings in a particular state or province.
   public static void InputStateQuery(Connection conn) throws SQLException
   {
      Statement stmt = conn.createStatement();

      String state;
      Scanner scannerObject = new Scanner(System.in);
      System.out.print("Enter a state or province: ");
      state = scannerObject.nextLine( );
      
      state = state.toUpperCase();

      String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.City, a.FullStreetAddress "
                   + "from MLS m, LISTING l, ADDRESS a "
                   + "where a.StateOrProvince = " + state + " "
                   + "and l.AddressId = a.AddressId "
                   + "and l.MlsId = m.MlsId";
      ResultSet rs = stmt.executeQuery(qry);

      // If nothing is returned then say so. Otherwise display table results
      if (!rs.isBeforeFirst() ) {    
    	  System.out.println("No listings exist for this state or province."); 
      } else {
      
    	  // Loop through the result set
    	  System.out.println("MLS Id" + "\tMLS Name" + "\tPrice" + "\tCountry" + "\tCity" + "\tAddress");
    	  while (rs.next())
    	  {
    		  String mlsId = rs.getString("MlsId");
    		  String mlsName = rs.getString("MlsName");
    		  int price = rs.getInt("ListPrice");
    		  String country = rs.getString("Country");
    		  String city = rs.getString("City");
    		  String address = rs.getString("FullStreetAddress");
    		  System.out.println(mlsId + "\t" + mlsName + "\t" + price + "\t" + country + "\t" + city + "\t" + address);
    	  }
    	  System.out.println( );
    	  rs.close();
      }
   }
   
   // This method is for the query for all listings in a particular city.
   public static void InputCityQuery(Connection conn) throws SQLException
   {
      Statement stmt = conn.createStatement();

      String city;
      Scanner scannerObject = new Scanner(System.in);
      System.out.print("Enter a city: ");
      city = scannerObject.nextLine( );
      
      city = city.toUpperCase();

      String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.State, a.FullStreetAddress "
                   + "from MLS m, LISTING l, ADDRESS a "
                   + "where a.City = " + city + " "
                   + "and l.AddressId = a.AddressId "
                   + "and l.MlsId = m.MlsId";
      ResultSet rs = stmt.executeQuery(qry);

      // If nothing is returned then say so. Otherwise display table results
      if (!rs.isBeforeFirst() ) {    
    	  System.out.println("No listings exist for this city."); 
      } else {
      
    	  // Loop through the result set
    	  System.out.println("MLS Id" + "\tMLS Name" + "\tPrice" + "\tCountry" + "\tState" + "\tAddress");
    	  while (rs.next())
    	  {
    		  String mlsId = rs.getString("MlsId");
    		  String mlsName = rs.getString("MlsName");
    		  int price = rs.getInt("ListPrice");
    		  String country = rs.getString("Country");
    		  String state = rs.getString("State");
    		  String address = rs.getString("FullStreetAddress");
    		  System.out.println(mlsId + "\t" + mlsName + "\t" + price + "\t" + country + "\t" + state + "\t" + address);
    	  }
    	  System.out.println( );
    	  rs.close();
      }
   }

   // This method is for the query for all listings in a particular postal code.
   public static void InputPostalCodeQuery(Connection conn) throws SQLException
   {
      Statement stmt = conn.createStatement();

      int postalCode;
      Scanner scannerObject = new Scanner(System.in);
      System.out.print("Enter a postal code: ");
      postalCode = scannerObject.nextInt( );

      String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress "
                   + "from MLS m, LISTING l, ADDRESS a "
                   + "where a.PostalCode = " + postalCode + " "
                   + "and l.AddressId = a.AddressId "
                   + "and l.MlsId = m.MlsId";
      ResultSet rs = stmt.executeQuery(qry);

      // If nothing is returned then say so. Otherwise display table results
      if (!rs.isBeforeFirst() ) {    
    	  System.out.println("No listings exist for this postal code."); 
      } else {
      
    	  // Loop through the result set
    	  System.out.println("MLS Id" + "\tMLS Name" + "\tPrice" + "\tCountry" + "\tState" + "\tCity" + "\tAddress");
    	  while (rs.next())
    	  {
    		  String mlsId = rs.getString("MlsId");
    		  String mlsName = rs.getString("MlsName");
    		  int price = rs.getInt("ListPrice");
    		  String country = rs.getString("Country");
    		  String state = rs.getString("StateOrProvince");
    		  String city = rs.getString("City");
    		  String address = rs.getString("FullStreetAddress");
    		  System.out.println(mlsId + "\t" + mlsName + "\t" + price + "\t" + country + "\t" + state + "\t" + city + "\t" + address);
    	  }
    	  System.out.println( );
    	  rs.close();
      }
   }
   
   // This method is for the query for all listings in a particular address.
   public static void InputAddressQuery(Connection conn) throws SQLException
   {
      Statement stmt = conn.createStatement();

      String address;
      Scanner scannerObject = new Scanner(System.in);
      System.out.print("Enter an address: ");
      address = scannerObject.nextLine( );
      
      address = address.toUpperCase();

      String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City,"
                   + "from MLS m, LISTING l, ADDRESS a "
                   + "where a.PostalCode = " + address + " "
                   + "and l.AddressId = a.AddressId "
                   + "and l.MlsId = m.MlsId";
      ResultSet rs = stmt.executeQuery(qry);

      // If nothing is returned then say so. Otherwise display table results
      if (!rs.isBeforeFirst() ) {    
    	  System.out.println("No listings exist for this adderss."); 
      } else {
      
    	  // Loop through the result set
    	  System.out.println("MLS Id" + "\tMLS Name" + "\tPrice" + "\tCountry" + "\tState" + "\tCity");
    	  while (rs.next())
    	  {
    		  String mlsId = rs.getString("MlsId");
    		  String mlsName = rs.getString("MlsName");
    		  int price = rs.getInt("ListPrice");
    		  String country = rs.getString("Country");
    		  String state = rs.getString("StateOrProvince");
    		  String city = rs.getString("City");
    		  System.out.println(mlsId + "\t" + mlsName + "\t" + price + "\t" + country + "\t" + state + "\t" + city);
    	  }
    	  System.out.println( );
    	  rs.close();
      }
   }
}
