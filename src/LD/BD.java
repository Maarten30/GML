package LD;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.*;
import javax.swing.JOptionPane;

public class BD 
{
	private static Logger logger = Logger.getLogger(BD.class.getName());
	
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet rs=null;


	/** Inicializa una base de datos y devuelve una conexión con ella.
	 * @param nombreBD	Nombre de fichero de la base de datos.
	 * @return	Conexión con la base de datos indicada. Si hay algún error, se devuelve null
	 */
	public static Connection initBD ( String nombreBD ) 
	{		
		try
		{
		    Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			statement = connection.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
		    return connection;
		} 
		
		catch (ClassNotFoundException | SQLException e) 
		{
			logger.log( Level.SEVERE, e.getMessage(), e );

			JOptionPane.showMessageDialog( null, "Error de conexión, no se ha podido conectar con " + nombreBD , "ERROR", JOptionPane.ERROR_MESSAGE );
			System.out.println( "Error de conexión, no se ha podido conectar con " + nombreBD );
			return null;
		}
	}
	
	/** Cierre de la conexión.
	 */
	public static void close() 
	{
		try 
		{
			statement.close();
			connection.close();
		} 
		catch (SQLException e)
		{
			logger.log( Level.SEVERE, e.getMessage(), e );

			e.printStackTrace();
		}
	}
	
	/** Devuelve la conexión si ha sido establecida.
	 * @return	Conexión con la base de datos, null si no se ha establecido correctamente.
	 */
	public static Connection getConnection() 
	{
		return connection;
	}
	
	/** Devuelve una sentencia para trabajar con la base de datos, si la conexión si ha sido establecida previamente.
	 * @return	Sentencia de trabajo con la base de datos, null si no se ha establecido correctamente.
	 */
	public static Statement getStatement() 
	{
		return statement;
	}
	
	//Crear tablas 
	
	/** Crea una tabla de usuarios en una base de datos, si no existía ya.
	 */
	public static void crearTablaUsuarios() 
	{
		if (statement==null) return;
		try
		{ 
			logger.log( Level.INFO, "Creando tabla");
			
			statement.executeUpdate("create table usuarios " +
					"("
					+ "nomUsu string,"
					+ "apeUsu string,"
					+ "emailUsu string,"
					+ "contraseñaUsu string,"
					+ "idUsu string,"
					+ "primary key(idUsu)"
					+ ")");
			
			logger.log( Level.INFO, "Tabla creada");
		} 
		
		catch (SQLException e) 
		{
			logger.log( Level.INFO, "La tabla ya estaba creada"+e.getMessage(), e ); //si hay excepción es que la tabla está creada
		}
	}
	
	/**
	 * Crea tabla de canciones en base de datos si no existia ya
	 */
	public static void crearTablaCanciones() 
	{
		if (statement==null) return;
		try
		{ 
			logger.log( Level.INFO, "Creando tabla");
			
			statement.executeUpdate("create table canciones " +
					"("
					+ "nomCa string,"
					+ "autorCa string,"
					+ "Anio String,"
					+ "duracion String,"
					+ "idCa string,"
					+ "primary key(idCa)"
					+ ")");
			
			logger.log( Level.INFO, "Tabla creada");
		} 
		
		catch (SQLException e) 
		{
			logger.log( Level.INFO, "La tabla ya estaba creada"+e.getMessage(), e ); //si hay excepción es que la tabla está creada
		}
	}
	
	public static boolean añadirUsuario (String contraseña, int idUsu)
	{
		try 
		{
			String sentSQL = "insert into ficheroUsu values(" +
					"'" + idUsu + "', " +
					"'" + contraseña + "')";
			int val = statement.executeUpdate( sentSQL );
			if (val!=1) return false; 
			return true;
		} 
		catch (SQLException e) 
		{
			logger.log( Level.WARNING, e.getMessage(), e );
			return false;
		}
	}
}