package LN;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

/**
 * Clase diseñada para gestionar la base de datos. 
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class clsBD 
{
	private static Logger logger = Logger.getLogger(clsBD.class.getName());
	
	private static Connection connection = null;
	public static Statement statement = null;
	

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
			statement.executeUpdate("create table if not exists usuarios (nombre string, apellido string, email string, nombreUsu string, contrasenya string, playlist string)");
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
			statement.executeUpdate("create table if not exists canciones (ruta string, nombre string, autor string, imagen string)");
		} 
		catch (SQLException e) 
		{
			if (!e.getMessage().equals("La tabla de canciones ya existe."))  // Este error sí es correcto si la tabla ya existe
				e.printStackTrace();
		}
	}
	
	/**
	 * Crea tabala de listas de reproducción en la base de datos si no existia ya
	 */
	public static void crearTablaPlaylist()
	{
		try 
		{
			statement.executeUpdate("create table if not exists playlist (nombre string, cancion string)");
		} 
		catch (SQLException e) 
		{
			if (!e.getMessage().equals("La tabla de playlist ya existe."))  // Este error sí es correcto si la tabla ya existe
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
	public  static void  añadirUsuario (String nombre, String apellido, String email, String nomUs, String contrasenya, String playlist)
	{	
		try 
		{
			String sentSQL = "insert into usuarios values('" +nombre+"', '"+apellido+"', '"+email+"' ,'"+nomUs+"', '"+contrasenya+"', '"+playlist+"' )";
			statement.executeUpdate(sentSQL);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			
		}
		
	}
	
	/**
	 * Metodo que sirve para verificar si un nombre de usuario ya existe en la base de datos o no
	 * @param nomUsu nombre de usuario que inserta el usuario por pantalla 
	 * @return devuelve verdadero si ese nombre de usuario esta en la base de datos y falso si no
	 */
	public static boolean comprUsuario (String nomUsu)
	{
		boolean devolver = false;
		
		try {
			
			PreparedStatement upd = connection.prepareStatement("SELECT nombreUsu FROM usuarios");
			 ResultSet rs = upd.executeQuery();
			
//			statement = connection.prepareStatement("select nombreUsu from usuarios");
//			
//			ResultSet rs = statement.executeQuery("select nombreUsu from usuarios");
//		
//			statement.executeQuery("select nombreUsu from usuarios");
			
			while (rs.next())
			{
			String nomUsuDB = rs.getString("nombreUsu");
			
			if (nomUsu.equals(nomUsuDB))
					
			{
				 devolver = true;
				 break;
			}
			else
			{
				 devolver = false;
			}
			}
		}
		 catch (SQLException e) {
		
			e.printStackTrace();
			 devolver = false;
		}
		return devolver;
		
		
	}
	
	
	/**
	 * Método que sirve para saber si la contraseña introducida por el usuario es la que corresponde con el nombre de usuario introducido
	 * @param nomUsu Nombre de usuario que inserta el usuario en la pantalla de inicio de sesion
	 * @param Contra Contraseña que inserta el usuario por la pantalla de inicio de sesion
	 * @return Si la contraseña es correcta, este metodo devuelve verdadero, sino falso. 
	 */
	public static boolean comprContra (String nomUsu, String Contra)
	{
		boolean devolver = true;
		
try {
			
			PreparedStatement upd = connection.prepareStatement("SELECT nombreUsu FROM usuarios");
			
			 ResultSet rs = upd.executeQuery();
			
//			statement = connection.prepareStatement("select nombreUsu from usuarios");
//			
//			ResultSet rs = statement.executeQuery("select nombreUsu from usuarios");
//		
//			statement.executeQuery("select nombreUsu from usuarios");
			
			while (rs.next())
			{
			String nomUsuDB = rs.getString("nombreUsu");
			
			if (nomUsu.equals(nomUsuDB))
					
			{
				PreparedStatement upd1 = connection.prepareStatement("SELECT contrasenya FROM usuarios WHERE nombreUsu ='" +nomUsu+"'");
				ResultSet rs1 = upd1.executeQuery();
			
				String contraBD = rs1.getString("contrasenya");
					
					if (Contra.equals(contraBD))
					{
						devolver = true;
						break;
					}
					else
					{
						devolver=false;
					}
				
				}
			
			else
			{
				 devolver = false;
			}
			}
		}
		 catch (SQLException e) {
		
			e.printStackTrace();
			 devolver = false;
		}
		
		return devolver;
		
	}
		
	/**
	 * Añade una cancion si no existia previamente
	 * @param cancion objeto de la clase cancion
	 */
	public static void añadirCancion (clsCancion cancion)
	{
		String sentSQL = "insert into canciones values('" +cancion.getFile().getPath()+"', '"+cancion.getNombre()+"', '"+cancion.getAutor()+"' ,'"+cancion.getRutaImg()+"' )";
		
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
	 * Añade una cominacionde playlis-cancion
	 * @param nombre Nombre de la Playlist
	 * @param cancion Nombre de la cancion
	 */
	public static void añadirCanPlaylist (String nombre, clsCancion cancion)
	{
		String sentSQL = "insert into playlist values('"+nombre+"', '"+cancion.getNombre()+"')";
		
		try 
		{
			statement.executeUpdate(sentSQL);
	
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
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