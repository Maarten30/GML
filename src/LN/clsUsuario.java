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

public class clsUsuario implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(clsUsuario.class.getName());
	private static Connection connection = null;
	private static Statement statement = null;
	
	private String nombre;
	private String apellido;
	private String nombreUs;
	private String contrasena;
	private ArrayList<String> listaEmail;
	private int idUs;
	
	//Constructor con parámetros 
	public clsUsuario(String nombre, String apellido, ArrayList <String> listaEmail, String nombreUs, String contrasena, int idUs) 
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.listaEmail = listaEmail;
		this.nombreUs = nombreUs;
		this.contrasena = contrasena;
		this.idUs = idUs;
	}
	
	//Constructor vacío
	public clsUsuario() 
	{
		nombre = "";
		apellido = "";
		listaEmail = null;
		nombreUs = ""; 
		contrasena = ""; 
		idUs = 0; 
	}
	
	//Constructor de usuario recibiendo los emails como una lista de parámetros de tipo string
	public clsUsuario(String nombre, String apellido, String nombreUs,
			String contrasena, int idUs, String... emails ) 
	{
		this( nombre, apellido, new ArrayList<String>( Arrays.asList(emails)), nombreUs, contrasena, idUs);
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

		public ArrayList<String> getListaEmails() {
			return listaEmail;
		}
		
		public void setListaEmails(ArrayList<String> listaEmails) {
			this.listaEmail = listaEmails;
		}
		
		public String getEmails() {
			String ret = "";
			if (listaEmail.size()>0) ret = listaEmail.get(0);
			for (int i=1; i<listaEmail.size(); i++) ret += (", " + listaEmail.get(i));
			return ret;
		}
		
		public void setNombreUs(String nombreUs) 
		{
			this.nombreUs = nombreUs;
		}

		public String getContrasena() 
		{
			return contrasena;
		}

		public void setContrasena(String contraseña) 
		{
			this.contrasena = contraseña;
		}
		
		public int getIdUs() 
		{
			return idUs;
		}

		public void setIdUs(int idUs) 
		{
			this.idUs = idUs;
		}
	
	//ToString	
	@Override
	public String toString() {
		return "Nombre: " + nombre + 
				"\nApellido: "+ apellido + 
				"\nNombreUs: " + nombreUs +
				"\nLista Email: " + listaEmail + 
				"\nidUs: " + idUs;
	}
	
	//Crea y devuelve un nuevo Usuario partiendo de los datos de una línea separados por comas
	public static clsUsuario crearDeLinea( String linea ) {
		clsUsuario u = new clsUsuario();
		StringTokenizer st = new StringTokenizer( linea, "," ); 
		try {
			u.nombre = st.nextToken();
			u.apellido = st.nextToken();
			u.listaEmail = new ArrayList<String>();
			u.nombreUs = st.nextToken();
			u.idUs = Integer.parseInt(st.nextToken());
			
			while (st.hasMoreTokens()) {
				u.listaEmail.add( st.nextToken() );
			}
			return u;
		} catch (NoSuchElementException e) {  // Error en datos insuficientes (faltan campos)
			return null;
		} catch (NumberFormatException e) {  // Error en tipo long de telefono o fechaLogin
			return null;
		} catch (IllegalArgumentException e) {  // Error en tipo usuario (enumerado)
			return null;
		} catch (Exception e) {  // Cualquier otro error
			return null;
		}
	}
	
	public static void main( String[] s ) {
		clsUsuario u = new clsUsuario( "buzz", "#9abbf", "Buzz", "Lightyear", 101202303, "buzz@gmail.com", "amigo.de.woody@gmail.com" );
		u.getListaEmails().add( "buzz@hotmail.com" );
		// String ape = u.getApellidos(); ape = "Apellido falso";  // esto no cambia nada
		System.out.println( u );
	}
		
	// Dos usuarios son iguales si TODOS sus campos son iguales
	public boolean equals( Object o ) {
		clsUsuario u2 = null;
		if (o instanceof clsUsuario) u2 = (clsUsuario) o;
		else return false;  // Si no es de la clase, son diferentes
		return (nombre.equals(u2.nombre))
			&& (apellido.equals(u2.apellido))
			&& (nombreUs.equals(u2.nombreUs))
			&& (listaEmail.equals( u2.listaEmail ))
			;
	}

	//Iniciamos la conexión
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
	
	//Creación tabla de usuario
	public static void crearTablaUsuarios() 
	{
		if (statement==null) return;
		try
		{ 
			logger.log( Level.INFO, "Creando tabla");
			
			statement.executeUpdate("create table usuarios (nom_usu string, ape_usu string, email_usu string, contra_usu string, id_usu string,"
					+ "primary key(id_usu)"); 
			
			logger.log( Level.INFO, "Tabla creada");
		} 
		
		catch (SQLException e) 
		{
			logger.log( Level.INFO, "La tabla ya estaba creada"+ e.getMessage(), e ); //si hay excepción es que la tabla está creada
		}
	}
	
	
	public boolean existenteUs ( Statement st ) 
	{
		try {
			String sentSQL = "select * from usuarios " +
					"where (idUs = '" + idUs + "')";
			System.out.println( sentSQL );  
			ResultSet rs = st.executeQuery( sentSQL );
			if (rs.next()) 
			{  
				rs.close();
				return true;
			}
			return false;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean anyadirUsuario( Statement st ) 
	{
		if (existenteUs(st)) 
		{  
			return modificarUsuario(st);
		}
		try 
		{
			String sentSQL = "insert into usuarios values(" +
					"'" + nombre + "', " +
					"'" + apellido + "', " +
					"'" + nombreUs + "', " +
					"'" + contrasena + "', " +
					"'" + listaEmail + "', " +
					idUs + "," +
					 ")";
			
			System.out.println( sentSQL ); 
			int val = st.executeUpdate( sentSQL );
			if (val!=1) return false; 
			return true;
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean modificarUsuario( Statement st ) 
	{
		try 
		{
			String sentSQL = "update canciones set " +
					"nombre = '" + nombre + "', " +
					"apellido = '" + apellido + "', " +
					"nombreUs = " + nombreUs + ","  +
					"contrasena = '" + contrasena + "', " +
					"email = " + listaEmail + ","  +
					"idUsuario =" + idUs +
					"where (idUs = '" + idUs + "')";
			System.out.println( sentSQL );  
			int val = st.executeUpdate( sentSQL );
			if (val!=1) return false;  
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
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
				u.listaEmail = new ArrayList<String>();
				u.nombreUs = rs.getString( "nombreUs" );
				u.contrasena = rs.getString("contrasena"); 
				
				StringTokenizer stt = new StringTokenizer( rs.getString("emails"), "," );
				while (stt.hasMoreTokens()) {
					u.listaEmail.add( stt.nextToken() );
				}
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
	
//	public boolean ValidarUsuario(String usuario, String contraseña)
//	{
//		if(usuario.equals("1234")&& contraseña.equals("ejemplo"))
//		{
//			return true;
//		}
//		else
//		{
//			return false; 
//		}
//	}
	
	public static void cerrarBD( Connection con, Statement st ) { //
		try 
		{
			if (st!=null) st.close();
			if (con!=null) con.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
