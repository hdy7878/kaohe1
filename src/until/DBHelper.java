package until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class DBHelper {
	private static  String url ;
	private static  String driver ;
	private static String user ;
	private static  String password ;
	private static Connection connection;
	

  static {
	  ResourceBundle bundle=ResourceBundle.getBundle("db");
	  url=bundle.getString("jdbc.url");
	  driver=bundle.getString("jdbc.driver");
	  user=bundle.getString("jdbc.user");
	  password=bundle.getString("jdbc.password");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}

	public static Connection getConnection() throws SQLException {
       DBHelper helper=new DBHelper();
       connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
}
