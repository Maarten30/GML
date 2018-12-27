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
			statement = connection.createStatement(); //este statement es para meter todo lo que queramos a la BD 
			statement.setQueryTimeout(30);// poner timeout 30 msg, esto se pone para el tiempo a esperar aunque no dará problemas 
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
			logger.log( Level.INFO, "La tabla ya estaba creada"+ e.getMessage(), e ); //si hay excepción es que la tabla está creada
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
			logger.log( Level.INFO, "La tabla ya estaba creada"+ e.getMessage(), e ); //si hay excepción es que la tabla está creada
		}
	}
	
	//Añadir filas
	
	public static boolean añadirUsuario (String contraseña, int idUsu)
	{
		try 
		{
			String sentSQL = "insert into usuarios values(" +
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
	
	//CREO QUE ESTE MÉTODO AL IGUAL QUE EL DE USUARIO, VAN EN SU PROPIA CLASS, NO AQUÍ
	public static boolean añadirCancion (File file, String nombre, String autor, int anio, float duracion,String[] ListaReproduccion, int idCa)
	{
		try 
		{
			String sentSQL = "insert into canciones values(" +
					"'" + file.getAbsolutePath() + "', " +
					"'" + nombre + "', " +
					"'" + autor + "'," + 
					 + anio + ", " +
					 + duracion + ", " +
					"'" + ListaReproduccion + "', " +
					 + idCa + ")";
			
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
	
	//Borrar filas
	
	public static boolean borrarUsuario (Object ident, String tabla)
	{
		logger.log( Level.INFO, tabla);
		
		try 
		{
			int idUsu=(Integer)ident;
			String sentSQL = "DELETE FROM ficheroUsuario WHERE codUsu = "+idUsu;
			int val = statement.executeUpdate( sentSQL );
			if (val!=1) return false;   
			//Borrado satisfactorio
			return true;
		}
		catch (SQLException e) 
		{
			logger.log( Level.WARNING, e.getMessage(), e );
			return false;
		}
		
	}
	
	public static boolean borrarCancion (Object ident, String tabla)
	{
		logger.log( Level.INFO, tabla);
		
		try 
		{
			int idCa=(Integer)ident;
			String sentSQL = "DELETE FROM ficheroCancion WHERE codUsu = "+idCa;
			int val = statement.executeUpdate( sentSQL );
			if (val!=1) return false;   
			//Borrado satisfactorio
			return true;
		}
		catch (SQLException e) 
		{
			logger.log( Level.WARNING, e.getMessage(), e );
			return false;
		}
		
	}
	
	//Borrado de tablas
	
	public static boolean borrarTablaUsuarios (String tabla)
	{
		logger.log(Level.INFO,tabla);
		
		try
		{
			logger.log( Level.INFO,"borrar tabla usuario");
			String sentSQL = "drop table ficheroUsu";
			logger.log( Level.INFO, sentSQL);
			int val = statement.executeUpdate( sentSQL );
			return true;
		} 
		catch (SQLException e) 
		{
			logger.log( Level.WARNING, e.getMessage(), e );
			return false;
		}
	
	}
	
	public static boolean borrarTablaCancion (String tabla)
	{
		logger.log(Level.INFO,tabla);
		
		try
		{
			logger.log( Level.INFO,"borrar tabla cancion");
			String sentSQL = "drop table ficheroCancion";
			logger.log( Level.INFO, sentSQL);
			int val = statement.executeUpdate( sentSQL );
			return true;
		} 
		catch (SQLException e) 
		{
			logger.log( Level.WARNING, e.getMessage(), e );
			return false;
		}
	
	}
	
}