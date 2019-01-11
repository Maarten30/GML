package LN;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * Clase que representa un usuario dentro de la aplicacion
 * @author Gabriela Garaizabal, Maarten handels y Laura Llorente
 *
 */
public class clsUsuario implements Serializable
{
	private static final long serialVersionUID = 1L;
	
//	private static Logger logger = Logger.getLogger(clsUsuario.class.getName());
//	private static Connection connection = null;
//	private static Statement statement = null;
	
	String nombre;
	String apellido;
	String nombreUs;
	String contrasenya;
	String email;
	ArrayList<clsPlayList> listas;
	
	 
	/**
	 * Atributos empleados para la cancion, constructor con parametros
	 * @param nombre nombre del usuario
	 * @param apellido apellido del usuario
	 * @param email email del usuario
	 * @param nombreUs nombre de usuario
	 * @param contrasenya constraseña
	 * @param listas listas de reproduccion que este posee
	 */
	public clsUsuario(String nombre, String apellido, String email, String nombreUs, String contrasenya, ArrayList<clsPlayList> listas, int idUs) 
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.nombreUs = nombreUs;
		this.contrasenya = contrasenya;
		this.listas = listas;
	}
	
	public clsUsuario(String nombre, String apellido, String email, String nombreUs, String contrasenya) 
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.nombreUs = nombreUs;
		this.contrasenya = contrasenya;
		this.listas = new ArrayList<clsPlayList>();
	}
	
	
	/**
	 * Constructor vacio
	 */
	public clsUsuario() 
	{
		nombre = "";
		apellido = "";
		email = "";
		nombreUs = ""; 
		contrasenya = ""; 
		listas = null; 
	}
		
	//GETTERS Y SETTERS 
	
	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getApellido() 
	{
		return apellido;
	}

	public void setApellido(String apellido) 
	{
		this.apellido = apellido;
	}

	public String getNombreUs() 
	{
		return nombreUs;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public void setNombreUs(String nombreUs) 
	{
		this.nombreUs = nombreUs;
	}

	public String getContrasena() 
	{
		return contrasenya;
	}

	public void setContrasena(String contraseña) 
	{
		this.contrasenya = contraseña;
	}
		
		
	public ArrayList<clsPlayList> getListas()
	{
		return listas;
	}

	public void setListas(ArrayList<clsPlayList> listas) 
	{
		this.listas = listas;
	}
	
	public void añadirPlaylist(clsPlayList a)
	{
		listas.add(a);
	}

	//TOSTRING
	
	@Override
	public String toString() 
	{
		return "Nombre: " + nombre + 
				"\nApellido: "+ apellido + 
				"\nNombreUs: " + nombreUs +
				"\nLista Email: " + email;
	}
	
	//Crea y devuelve un nuevo Usuario partiendo de los datos de una línea separados por comas
	
//	public static clsUsuario crearDeLinea( String linea ) 
//	{
//		clsUsuario u = new clsUsuario();
//		StringTokenizer st = new StringTokenizer( linea, "," ); 
//		try 
//		{
//			u.nombre = st.nextToken();
//			u.apellido = st.nextToken();
//			u.email = st.nextToken();
//			u.nombreUs = st.nextToken();
//			u.idUs = Integer.parseInt(st.nextToken());
//		
//			return u;
//		} 
//		catch (NoSuchElementException e) 
//		{  // Error en datos insuficientes (faltan campos)
//			return null;
//		} 
//		catch (NumberFormatException e) 
//		{  // Error en tipo long de telefono o fechaLogin
//			return null;
//		} 
//		catch (IllegalArgumentException e) 
//		{  // Error en tipo usuario (enumerado)
//			return null;
//		} 
//		catch (Exception e) 
//		{  // Cualquier otro error
//			return null;
//		}
//	}
			
	// Dos usuarios son iguales si TODOS sus campos son iguales
	/**
	 * Metodo que nos dice si hay dos usuarios iguales, teniendo en cuenta por iguales que tengan todos los atributos repetidos
	 */
	public boolean equals( Object o ) 
	{
		clsUsuario u2 = null;
		if (o instanceof clsUsuario) u2 = (clsUsuario) o;
		else return false;  // Si no es de la clase, son diferentes
		return (nombre.equals(u2.nombre))
			&& (apellido.equals(u2.apellido))
			&& (nombreUs.equals(u2.nombreUs))
			&& (email.equals( u2.email ));
	}
}
