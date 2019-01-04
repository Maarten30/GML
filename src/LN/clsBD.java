package LN;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.StringTokenizer;
import java.util.logging.*;
import javax.swing.JOptionPane;

//import LN.clsCancion;
import LN.clsUsuario;
import LP.frmInicioSesion;

public class clsBD 
{
	private static Logger logger = Logger.getLogger(clsBD.class.getName());
	
	private static Connection connection = null;
	private static Statement statement = null;
//	private static ResultSet rs=null;
	
	String nombre = "";
	String apellido="";
	String email = "";
	String nomUs ="";
	String contrasenya = "";

	/** Inicializa una base de datos y devuelve una conexión con ella.
	 * @param nombreBD	Nombre de fichero de la base de datos.
	 * @return	Conexión con la base de datos indicada, null si hay algún error 
	 */
	public static Connection initBD () 
	{		
		try
		{
		    Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection("jdbc:sqlite:BD.bd" );
			statement = connection.createStatement(); //este statement es para meter todo lo que queramos a la BD 
			statement.setQueryTimeout(30);// poner timeout 30 msg, esto se pone para el tiempo a esperar aunque no dará problemas 
		    return connection;
		} 
		
		catch (ClassNotFoundException | SQLException e) 
		{
			logger.log( Level.SEVERE, e.getMessage(), e );

			JOptionPane.showMessageDialog( null, "Error de conexión, no se ha podido conectar " , "ERROR", JOptionPane.ERROR_MESSAGE );
			return null;
		}
	}
	
	
	/** Devuelve la conexión.
	 * @return	Conexión con la base de datos, null si no se ha establecido correctamente.
	 */
	public static Connection getConnection() 
	{
		return connection;
	}
	
	/** Devuelve una sentencia para la base de datos. 
	 * @return	Sentencia, null si no se ha establecido correctamente.
	 */
	public static Statement getStatement() 
	{
		return statement;
	}
	
	//CREACIÓN DE TABLAS 
	
	/** Crea una tabla de usuarios en una base de datos.
	 */
	public static void crearTablaUsuarios() 
	{		
		try 
		{
			statement.executeUpdate("create table usuarios (nombre string, apellido string, email string, nombreUsu string, contrasenya string)");
		} 
		catch (SQLException e) 
		{
			if (!e.getMessage().equals("La tabla de usuarios ya existe."))  // Este error sí es correcto si la tabla ya existe
				e.printStackTrace();
		}
	}
	
	/**
	 * Crea tabla de canciones en base de datos si no existia ya
	 */
	public static void crearTablaCanciones() 
	{
		try 
		{
			statement.executeUpdate("create table canciones (nombre string, autor string, año int, duracion float)");
		} 
		catch (SQLException e) 
		{
			if (!e.getMessage().equals("La tabla de canciones ya existe."))  // Este error sí es correcto si la tabla ya existe
				e.printStackTrace();
		}
	}
	
	//AÑADIR FILAS 
	
	/**
	 * Crea un usuario si no existe ya. 
	 * @param contraseña que introduce el usuario al registrarse.
	 * @param idUsu del  usuario para comprobar si existen o no en la BD. 
	 * @return usuario, si no existía. 
	 */
	public boolean añadirUsuario (Statement st)
	{	
		if(mirarSiYaTabla(st))
		{
			JOptionPane.showMessageDialog(null,"El nombre de usuario ya existe","INICIO SESIÓN",JOptionPane.ERROR_MESSAGE);
		}
		
		try 
		{
			String sentSQL = "insert into usuarios values('" +nombre+"', '"+apellido+"', '"+email+"' ,'"+nomUs+"', '"+contrasenya+"' )";
			statement.executeUpdate(sentSQL);
			int val = st.executeUpdate( sentSQL );
			if (val!=1) return false;  // Se tiene que añadir 1 - error si no
			return true;
	
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean mirarSiYaTabla(Statement st) 
	{
		try 
		{
			String sentSQL = "select * from usuarios where (nombreUsu = '" + frmInicioSesion.txtUsu2.getText() + "')";
//			System.out.println( sentSQL );  // (Quitar) para ver lo que se hace
			ResultSet rs = st.executeQuery( sentSQL );
			if (rs.next()) {  // Normalmente se recorre con un while, pero aquí solo hay que ver si ya existe
				rs.close();
				return true;
			}
			return false;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
		
	/**
	 * Añade una canción si no existía previamente. 
	 * @param file 
	 * @param nombre
	 * @param autor
	 * @param anio
	 * @param duracion
	 * @param ListaReproduccion
	 * @param idCa
	 * @return
	 */
	public static void añadirCancion (File file, String nombre, String autor, int anio, float duracion)
	{
		String sentSQL = "insert into usuarios values('" +file.getAbsolutePath()+"', '"+nombre+"', '"+autor+"' ,'"+anio+"', '"+duracion+"' )";
		
		try 
		{
			statement.executeUpdate(sentSQL);
	
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//BORRADO FILAS 
	
	/**
	 * Borra un usuario de la BD. 
	 * @param ident
	 * @param tabla
	 * @return
	 */
//	public static boolean borrarUsuario (Object ident, String tabla)
//	{
//		logger.log( Level.INFO, tabla);
//		
//		try 
//		{
//			int idUsu=(Integer)ident;
//			String sentSQL = "DELETE FROM ficheroUsuario WHERE codUsu = "+idUsu;
//			int val = statement.executeUpdate( sentSQL );
//			if (val!=1) return false;   
//			//Borrado satisfactorio
//			return true;
//		}
//		catch (SQLException e) 
//		{
//			logger.log( Level.WARNING, e.getMessage(), e );
//			return false;
//		}
//		
//	}
	
	/**
	 * Borra una canción de la BD. 
	 * @param ident
	 * @param tabla
	 * @return
	 */
//	public static boolean borrarCancion (Object ident, String tabla)
//	{
//		logger.log( Level.INFO, tabla);
//		
//		try 
//		{
//			int idCa=(Integer)ident;
//			String sentSQL = "DELETE FROM ficheroCancion WHERE codUsu = "+idCa;
//			int val = statement.executeUpdate( sentSQL );
//			if (val!=1) return false;   
//			//Borrado satisfactorio
//			return true;
//		}
//		catch (SQLException e) 
//		{
//			logger.log( Level.WARNING, e.getMessage(), e );
//			return false;
//		}
//		
//	}
	
	//BORRADO DE TABLAS
	/**
	 * Borra la tabla de usuarios. 
	 * @param tabla
	 * @return
	 */
//	public static boolean borrarTablaUsuarios (String tabla)
//	{
//		logger.log(Level.INFO,tabla);
//		
//		try
//		{
//			logger.log( Level.INFO,"borrar tabla usuario");
//			String sentSQL = "drop table ficheroUsu";
//			logger.log( Level.INFO, sentSQL);
//			int val = statement.executeUpdate( sentSQL );
//			return true;
//		} 
//		catch (SQLException e) 
//		{
//			logger.log( Level.WARNING, e.getMessage(), e );
//			return false;
//		}
//	
//	}
	
	/**
	 * Borra la tabla de canciones. 
	 * @param tabla
	 * @return
	 */
//	public static boolean borrarTablaCancion (String tabla)
//	{
//		logger.log(Level.INFO,tabla);
//		
//		try
//		{
//			logger.log( Level.INFO,"borrar tabla cancion");
//			String sentSQL = "drop table ficheroCancion";
//			logger.log( Level.INFO, sentSQL);
//			int val = statement.executeUpdate( sentSQL );
//			return true;
//		} 
//		catch (SQLException e) 
//		{
//			logger.log( Level.WARNING, e.getMessage(), e );
//			return false;
//		}
//	
//	}
		
	//CIERRE DE LA CONEXIÓN
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
	
}