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
	    	System.out.println("Se ha establecido la conexión");
	    	Statement statement = connection.createStatement(); //el statement es para meter todo lo que queramos en la BD
	    	statement.setQueryTimeout(30); 
	    	
	    	//SINTAXIS SQL
	    	statement.executeUpdate("drop table if exists cantante");
	    	statement.executeUpdate("drop table if exists listaCanciones");
	    	statement.executeUpdate("create table cantante(idCantante integer, nombre string, apellido string, grupo string)"); 
	    	statement.executeUpdate("create table listaCanciones(idCancion integer,titulo string, cantante string, grupo string)");
	    	statement.executeUpdate("insert into listaCanciones values(11,'Hello','Adele','null')");
	    	statement.executeUpdate("insert into cantante values(1,'Marta','Fernández','La Oreja')"); 
	    	
	    	statement.executeUpdate("create table usuario(nombre string, apellido string, email string, nombreUs string, contraseña string)");

	    	ResultSet rs1 = statement.executeQuery("select * from cantante");
	    	ResultSet rs2 = statement.executeQuery("select * from listaCanciones");
	    	 
	    	 while(rs1.next()) 
	         {					
	           // Leer el resultset
	           System.out.println("idCantante: " + rs1.getInt("idCantante")); 
	           System.out.println("Nombre: " + rs1.getString("nombre")); 
	           System.out.println("Apellido: " + rs1.getString("apellido"));
	           System.out.println("Grupo: " + rs1.getString("grupo"));
	         }
	    	 
	    	 while(rs2.next())
	         {					
	           // Leer el resultset
	           System.out.println("idCancion: " + rs2.getInt("idCancion")); 
	           System.out.println("Titulo: " + rs2.getString("titulo")); 
	           System.out.println("Cantante: " + rs2.getString("cantante"));
	           System.out.println("Grupo: " + rs2.getString("grupo"));
	         }
	    }
	    catch(SQLException e) 
	    {
	    	 System.err.println(e.getMessage());
	    }
	    finally 
	    {
	        try
	        {
	          if(connection != null)
	            connection.close();
	        }
	        catch(SQLException e)
	        {
	          // Cierre de conexión fallido
	          System.err.println(e);
	        }
	      }
	}
	
	 

}