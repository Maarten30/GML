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

public class clsBD 
{
	private static Logger logger = Logger.getLogger(clsBD.class.getName());
	
	private static Connection connection = null;
	private static Statement statement = null;
//	private static ResultSet rs=null;


	/** Inicializa una base de datos y devuelve una conexi�n con ella.
	 * @param nombreBD	Nombre de fichero de la base de datos.
	 * @return	Conexi�n con la base de datos indicada, null si hay alg�n error 
	 */
	public static Connection initBD () 
	{		
		try
		{
		    Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection("jdbc:sqlite:BD.bd" );
			statement = connection.createStatement(); //este statement es para meter todo lo que queramos a la BD 
			statement.setQueryTimeout(30);// poner timeout 30 msg, esto se pone para el tiempo a esperar aunque no dar� problemas 
		    return connection;
		} 
		
		catch (ClassNotFoundException | SQLException e) 
		{
			logger.log( Level.SEVERE, e.getMessage(), e );

			JOptionPane.showMessageDialog( null, "Error de conexi�n, no se ha podido conectar " , "ERROR", JOptionPane.ERROR_MESSAGE );
			return null;
		}
	}
	
	
	/** Devuelve la conexi�n.
	 * @return	Conexi�n con la base de datos, null si no se ha establecido correctamente.
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
	
	//CREACI�N DE TABLAS 
	
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
			if (!e.getMessage().equals("La tabla de usuarios ya existe."))  // Este error s� es correcto si la tabla ya existe
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
			statement.executeUpdate("create table canciones (nombre string, autor string, a�o int, duracion float)");
		} 
		catch (SQLException e) 
		{
			if (!e.getMessage().equals("La tabla de canciones ya existe."))  // Este error s� es correcto si la tabla ya existe
				e.printStackTrace();
		}
	}
	
	//CONSULTA DE TABLAS
	
	public static ArrayList<clsUsuario> consultaATabla( Statement st, String codigoSelect )
	{
		ArrayList<clsUsuario> ret = new ArrayList<>();
		try 
		{
			String sentSQL = "select * from usuarios";
			if (codigoSelect!=null && !codigoSelect.equals(""))
				sentSQL = sentSQL + " where " + codigoSelect;
			System.out.println( sentSQL );  // (Quitar) para ver lo que se hace
			ResultSet rs = st.executeQuery( sentSQL );
			while (rs.next()) {
				clsUsuario u = new clsUsuario();
				u.nombre = rs.getString( "nombre" );
				u.apellido = rs.getString( "apellido" );
				u.email = rs.getString( "email" );
				u.nombreUs = rs.getString( "nombreUs" );
				
				ret.add( u );
			}
			rs.close();
			return ret;
		} 
		catch (IllegalArgumentException e) 
		{  // Error en tipo usuario (enumerado)
			e.printStackTrace();
			return null;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	//A�ADIR FILAS 
	
	/**
	 * Crea un usuario si no existe ya. 
	 * @param contrase�a que introduce el usuario al registrarse.
	 * @param idUsu del  usuario para comprobar si existen o no en la BD. 
	 * @return usuario, si no exist�a. 
	 */
	public static void a�adirUsuario (String nombre, String apellido, String email, String nomUs, String contrasenya)
	{	
//		boolean existe = false; 
		
		String sentSQL = "insert into usuarios values('" +nombre+"', '"+apellido+"', '"+email+"' ,'"+nomUs+"', '"+contrasenya+"' )";
		
		try 
		{
			statement.executeUpdate(sentSQL);
	
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * A�ade una canci�n si no exist�a previamente. 
	 * @param file 
	 * @param nombre
	 * @param autor
	 * @param anio
	 * @param duracion
	 * @param ListaReproduccion
	 * @param idCa
	 * @return
	 */
	public static void a�adirCancion (File file, String nombre, String autor, int anio, float duracion)
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
	
	/**
	 * Borra una canci�n de la BD. 
	 * @param ident
	 * @param tabla
	 * @return
	 */
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
	
	
	
	//CIERRE DE LA CONEXI�N
	/** Cierre de la conexi�n.
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