package dbutils;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql .Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBUtils {
 private static Connection cn;
   
 /*
	
	  public static Connection openConnection() throws SQLException {
	  
	  //class.forName("com.mysql.jdbc.ConnectionImple.class")//here we have to load the implentino class but here not need to
	  System.out.println("opened connection"); 
	  String url=
	  "jdbc:mysql://localhost:3306/main_project?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
	 
	  cn=DriverManager.getConnection(url,"root","Sumit@123"); 
	  //dirverManger is the class which takes the credentilas interanlly jvm will 
	  //load the implementtation class in the method area from the jar
	  //class.forName("com.mysql.jdbc.ConnectionImple.class") then it return the object of com.mysql.jdbc.ConnectionImple.class
	  
	  System.out.println("implementaion class:"+cn.getClass()); return cn;
	  
	  
	  
	  }
	 
 */
 
 /*
	  public static Connection openConnection() throws SQLException {
		  
		  //class.forName("com.mysql.jdbc.ConnectionImple.class")//here we have to load the implentino class but here not need to
		  System.out.println("opened connection"); 
		  String url=
		  "jdbc:mysql://q4gj8bafulof74zc:n2ltxi0cusi30fhl@fojvtycq53b2f2kx.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/ld20zgpxb95sjt1g";
		 
		  cn=DriverManager.getConnection(url,"q4gj8bafulof74zc","n2ltxi0cusi30fhl"); 
		  //dirverManger is the class which takes the credentilas interanlly jvm will 
		  //load the implementtation class in the method area from the jar
		  //class.forName("com.mysql.jdbc.ConnectionImple.class") then it return the object of com.mysql.jdbc.ConnectionImple.class
		  
		  System.out.println("implementaion class:"+cn.getClass()); return cn;
		  
		  
		  
		  }*/
	  
	  
	  public static Connection openConnection() throws SQLException {
		    String url = "jdbc:mysql://fojvtycq53b2f2kx.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/ld20zgpxb95sjt1g";
		    cn = DriverManager.getConnection(url, "q4gj8bafulof74zc", "n2ltxi0cusi30fhl");
		    return cn;
		}

	  

/*
private static Connection openConnection() throws URISyntaxException, SQLException {
	
	//class.forName("com.mysql.jdbc.ConnectionImple.class")//here we have to load the implentino class but here not need to 
	  System.out.println("opened connection");   
	
    //URI jdbUri = new URI(System.getenv("JAWSDB_URL"));
  // URI jdbUri = new URI(System.getenv("mysql://q4gj8bafulof74zc:n2ltxi0cusi30fhl@fojvtycq53b2f2kx.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/ld20zgpxb95sjt1g"));
   URI jdbUri = new URI(System.getenv("mysql://q4gj8bafulof74zc:n2ltxi0cusi30fhl@fojvtycq53b2f2kx.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/ld20zgpxb95sjt1g"));
   String username = jdbUri.getUserInfo().split(":")[0];
   System.out.println("username"+username);
   String password = jdbUri.getUserInfo().split(":")[1];
   System.out.println("password"+password);
    String port = String.valueOf(jdbUri.getPort());
    System.out.println("port:"+port);
    System.out.println("path:"+jdbUri.getPath());
    
    String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();

    
    
    //dirverManger is the class which takes the credentilas interanlly  jvm will 
 	 //load the implementtation class in the method area  from the jar 
 	 //class.forName("com.mysql.jdbc.ConnectionImple.class") then it return the object of com.mysql.jdbc.ConnectionImple.class
 	 
    cn =DriverManager.getConnection(jdbUrl, username, password);
 	 System.out.println("implementaion class:"+cn.getClass());
    
    return cn;


}

 */
 
 
    
   public static Connection getConnection() throws SQLException, URISyntaxException {
	   System.out.println("got connection");
	   if(cn==null)
		   return openConnection();
	   
	   else {
			 System.out.println("implementaion class connection interface(upcasting) is :"+cn.getClass());
		 return  cn;
	   }
	   
   }

   public static void closeConnection() throws SQLException {
	   System.out.println("closed connection");// i do not waant to close the connection for each client 
	    if(cn!=null)//b/z then for each client it will be destroyed and for new client agin connectino is made take time 
	    	cn.close();//so we maintaint he object of conncetinon inthe heap so it will  be shared accross the client 
	   // when the server is off automatically all will be off  all object will be ready for the garbage coolection b/w web serever dis of or 
	    //web contaoner will off
   }
   
}
