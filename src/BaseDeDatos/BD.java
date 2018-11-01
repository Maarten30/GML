package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD 
{
	public static void main (String[] args) throws ClassNotFoundException
	{
	    Class.forName("org.sqlite.JDBC"); 
	    
	    Connection connection = null;
	    
	    try
	    {
	    	connection = DriverManager.getConnection("jdbc:sqlite:BaseDatosUsuarios.db");
	    	Statement statement = connection.createStatement();
	    	statement.setQueryTimeout(30);
	    }
	    catch(SQLException e) 
	    {
	    	 System.err.println(e.getMessage());
	    }
	}

}
