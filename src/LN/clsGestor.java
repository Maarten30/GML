package LN;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

//import org.simplejavamail.email.Email;
//import org.simplejavamail.email.EmailBuilder;
//import org.simplejavamail.mailer.Mailer;
//import org.simplejavamail.mailer.MailerBuilder;
//import org.simplejavamail.mailer.config.TransportStrategy;
//import org.simplejavamail.util.ConfigLoader;


import LP.frmInicioSesion;
import LP.frmReproductor;
import javafx.scene.control.Alert;

/**
 * Clase utilizada para la ejecucion del programa
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class clsGestor 
{
	
	private Connection conection = null;
	private Statement statement = null;
	private ArrayList<clsCancion> canciones = new ArrayList<clsCancion>();
	private clsPlayList playlist = new clsPlayList();
	private clsUsuario usuarioActual;
	
	/**
	 * Metodo a traves del cual se inicializa la base de datos
	 */
	public void InicializarBD()
	{
		clsBD.initBD(); 
	    clsBD.crearTablaUsuarios();
	    clsBD.crearTablaCanciones();
	    clsBD.crearTablaPlaylist();
	    
	    conection = clsBD.getConnection();
		statement = clsBD.getStatement();
		
		InsertarCanciones();
		InsertarListasIniciales();	
		RecibirPlayList();
	}
	
/**
 * Metodo utilizado para insertar canciones en la base de datos
 */
	public void InsertarCanciones()
	{
		ResultSet rs = null;
		try 
		{
			rs = statement.executeQuery("SELECT count(nombre) FROM canciones");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int numero = 0;
		try {
			numero = (Integer) rs.getObject(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("El número de canciones en BD es: " + numero);
		
		if(numero == 0)
		{
//			File file1 = new File("test/res/Ben L'Oncle Soul - Seven Nation Army.wav");
//			clsCancion cancion1 = new clsCancion(file1, "Ben LOncle Soul", "Seven Nation Army", 2010, 178);
			
			File file2 = new File("test/res/Calvin Harris, Sam Smith - Promises (Official Lyric Video).wav");
			clsCancion cancion2 = new clsCancion(file2, "Promises", "Calvin Harris-Sam Smith", 2018, 213);
			
			File file3 = new File("test/res/Demi Lovato - Stone Cold (Official Video).wav");
			clsCancion cancion3 = new clsCancion(file3, "Stone Cold", "Demi Lovato", 2018, 213);
			
			File file4 = new File("test/res/Dua Lipa - Homesick (Lyrics).wav");
			clsCancion cancion4 = new clsCancion(file4, "Homesick", "Dua Lipa", 2018, 213);
			
			File file5 = new File("test/res/George Ezra - Shotgun (Lyric Video).wav");
			clsCancion cancion5 = new clsCancion(file5, "George Ezra", "Shotgun", 2018, 213);
			
			File file6 = new File("test/res/Revelries & Henri Purnell - Feel It Still (Lyric Video).wav");
			clsCancion cancion6 = new clsCancion(file6,  "Feel It Still", "Revelries & Henri Purnell", 2018, 213);
			
//			clsBD.añadirCancion(cancion1);
			clsBD.añadirCancion(cancion2);
			clsBD.añadirCancion(cancion3);
			clsBD.añadirCancion(cancion4);
			clsBD.añadirCancion(cancion5);
			clsBD.añadirCancion(cancion6);
		}
		
		
		System.out.println("HA LLEGADO AQUI");
		
		RecibirCanciones();
		
		
	}
	
	/**
	 * Metodo utilizado para cargar canciones de la base de datos
	 */
	public void RecibirCanciones()
	{
		ResultSet rs = null;
		try 
		{
			rs = statement.executeQuery("SELECT * FROM canciones");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try 
		{
			while(rs.next())
			{
				System.out.println("HA ENTRADO EN EL RS.NEXT");
				String nombre = rs.getString("nombre");
				
				
				String autor = rs.getString("autor");
			
				
				int año = rs.getInt("año");
				
				
				float duracion = rs.getFloat("duracion");
				
				
				String Path = rs.getString("ruta");
				
				
				File file = new File(Path);
				
				clsCancion cancion = new clsCancion(file, nombre, autor, año, duracion);
				canciones.add(cancion);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("HA LLEGADO A RECIBIR CANCIONES");
		System.out.println("EL numero de canciones es en memoria" + canciones.size());
		
		for(clsCancion a:canciones)
		{
			System.out.println("PRUEBA" + a.getNombre());
		}
		
	}
	
	/**
	 * Metodo utilizado para insertar la playlist de TODAS en la base de datos
	 */
	public void InsertarListasIniciales()
	{
		ResultSet rs = null;
		try 
		{
			rs = statement.executeQuery("SELECT count(nombre) FROM playlist");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int numero = 0;
		try {
			numero = (Integer) rs.getObject(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		System.out.println("El número de playlists en BD es: " + numero);
			
		if(numero == 0)
		{
				
			for(clsCancion a:canciones)
			{
				clsBD.añadirCanPlaylist("TODAS", a);
			}

				
		}
			
			
		System.out.println("HA LLEGADO A INSERTAR LA PLAYLIST DE TODAS");
			
//			RecibirCanciones();
			
			
	}
		
	public void RecibirPlayList()
	{
		
		ResultSet rs = null;
		try 
		{
			rs = statement.executeQuery("SELECT * FROM playlist");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try 
		{
			while(rs.next())
			{
				String nombreplay = rs.getString("nombre");
				playlist.setNombre(nombreplay);
				
				String cancion = rs.getString("cancion");
				
				
				for(clsCancion a:canciones)
				{
					
					if(a.getNombre().equalsIgnoreCase(cancion))
					{
						System.out.println("CAAAAAAAAANCION: " + a.getNombre());
						playlist.añadirCancion(a);
					}
				}
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		System.out.println("HA LLEGADO A RECIBIR CANCIONES");
		System.out.println("EL numero de canciones en la playlist es: " + playlist.getCanciones().size());
			
		for(clsCancion a:playlist.getCanciones())
		{
			System.out.println("CANCIONES EN LA PLAYLIST" + a.getNombre());
		}
			
	}
	
	public void RecontruirUsuario(String usuario, String contraseña)
	{
		
		ResultSet rs = null;
		try 
		{
			rs = statement.executeQuery("SELECT * FROM usuarios WHERE nombreUsu ='" +usuario+"' AND contrasenya ='" +contraseña+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try 
		{
			
			usuarioActual = new clsUsuario(rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("nombreUsu"), rs.getString("contrasenya"));
		
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		ResultSet rs2 = null;
		
		try 
		{
			rs2 = statement.executeQuery("SELECT DISTINCT playlist FROM usuarios WHERE nombreUsu ='" +usuario+"' AND contrasenya ='" +contraseña+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try 
		{
			while(rs2.next())
			{
				String nombreplay = rs2.getString("playlist");
				clsPlayList listnueva = new clsPlayList(nombreplay);
				
				ResultSet rs3 = null;
				try 
				{
					rs3 = statement.executeQuery("SELECT cancion FROM playlist WHERE nombre = '"+nombreplay+"'");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try
				{
				
					while(rs3.next())
					{
						String cancion = rs3.getString("cancion");
						
						
						for(clsCancion a:canciones)
						{
							
							if(a.getNombre().equalsIgnoreCase(cancion))
							{
								listnueva.añadirCancion(a);
							}
						}
						
					}

					usuarioActual.añadirPlaylist(listnueva);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void AbrirMenu()
	{
		
		for(clsCancion a:canciones)
		{
			System.out.println(a.getNombre());
		}
		frmReproductor Pantalla = new frmReproductor();
		
		Pantalla.GUI(usuarioActual);
		
		
	}
		
	
	}
//}
