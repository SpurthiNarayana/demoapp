package demoapp.test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demoappbackend {
      
       static Connection con = null;
       private static Statement stmt;
       public static String DB_URL = "jdbc:mysql://localhost:3306/demodb";   
       public static String DB_USER = "root";
       public static String DB_PASSWORD = "toor";

       @BeforeTest
       public void setUp() throws Exception {

              try{

                     String dbClass = "com.mysql.jdbc.Driver";
                     Class.forName(dbClass).newInstance();
                     Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                     stmt = con.createStatement();

                     }
              catch (Exception e)
                     {
                           e.printStackTrace();
                     }

       }

       @Test(priority=1,description="get all data")
       public void test() {
              try{
              String query = "select * from articles";
              ResultSet res = stmt.executeQuery(query);
              while (res.next())
              {
              System.out.print(res.getString(1));
              System.out.print("\t" + res.getString(2));
              System.out.print("\t" + res.getString(3));
              System.out.println("\t" + res.getString(4));
              }
              }
              catch(Exception e)
              {
                     e.printStackTrace();
              }     
       }
       @Test(priority=2, description="positive testing")
       public void getsingledata() throws SQLException {
    	   String query = "select * from articles where id='" + 2 + "'";
    	   ResultSet res = stmt.executeQuery(query);
    	   while (res.next())
           {
           System.out.print(res.getString(1));
           assertEquals("2",res.getString(1));
           System.out.print("\t" + res.getString(2));
           assertEquals("Spurthi",res.getString(2));
           System.out.print("\t" + res.getString(3));
           assertEquals("article1 content ...",res.getString(3));
           System.out.println("\t" + res.getString(4));
           assertEquals("article1",res.getString(4));
           }  	  
       }
       @Test(priority=3, description="positive testing")
       public void getid() throws SQLException {
    	   String query = "select Id from articles";
    	   ResultSet res = stmt.executeQuery(query);
    	   while (res.next())
           {
           System.out.print("\t" +res.getString(1));
           }  	  
       }
       @Test(priority=3, description="positive testing")
       public void gettitle() throws SQLException {
    	   String title ="My Article";
    	   String query = "select Id,title from articles where title='"+title+"'";
           ResultSet res = stmt.executeQuery(query);
    	   while (res.next())
           {
           System.out.print("\t" +res.getString(1));
           System.out.print("\t" + res.getString(2));
           }  	  
       }
//       @Test(priority=4, description="positive testing")
       public void insertdata() throws SQLException {
    	   String query = " Insert into demodb.articles (title, author , content) values(?,?,?)";
    	   System.out.println(query);
    	   PreparedStatement preparedStmt = con.prepareStatement(query);
    	   System.out.println(preparedStmt);
    	   preparedStmt.setString(0, "title1");
    	   preparedStmt.setString(1, "author1");
    	   preparedStmt.setString(2, "content1");
    	   preparedStmt.execute();
    	   	  
       }
       
       @AfterTest
       public void tearDown() throws Exception {
              if (con != null) {
              con.close();
              }
       }
       
}
