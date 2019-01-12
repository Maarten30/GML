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
			File file1 = new File("test/res/ACDC - Back in Black.wav");
			clsCancion cancion1 = new clsCancion(file1, "Back in Black", "ACDC", 2010, 178);
			
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
			
			File file7= new File("test/res/ACDC - Hells Bells.wav");
			clsCancion cancion7 = new clsCancion(file7,  "Hells Bells", "Revelries & Henri Purnell", 2018, 213);
			
			File file8= new File("test/res/ACDC - Highway To Hell.wav");
			clsCancion cancion8 = new clsCancion(file8,  "Highway To Hell", "ACDC", 2018, 213);
			
			File file9= new File("test/res/ACDC - Let There Be Rock.wav");
			clsCancion cancion9 = new clsCancion(file9,  "Let There Be Rock", "ACDC", 2018, 213);
			
			File file10= new File("test/res/ACDC - T.N.T..wav");
			clsCancion cancion10 = new clsCancion(file10,  "T.N.T.", "ACDC", 2018, 213);
			
			File file11= new File("test/res/ACDC - Thunderstruck.wav");
			clsCancion cancion11 = new clsCancion(file11,  "Thunderstruck", "ACDC", 2018, 213);
			
			File file12= new File("test/res/ACDC - Whole Lotta Rosie.wav");
			clsCancion cancion12 = new clsCancion(file12,  "Whole Lotta Rosie", "ACDC", 2018, 213);
			
			File file13= new File("test/res/ACDC - Thunderstruck.wav");
			clsCancion cancion13 = new clsCancion(file13,  "Thunderstruck", "ACDC", 2018, 213);
			
			File file14= new File("test/res/ACDC - You Shook Me All Night Long.wav");
			clsCancion cancion14 = new clsCancion(file14,  "You Shook Me All Night Long", "ACDC", 2018, 213);
			
			File file15= new File("test/res/Macklemore  Ryan Lewis - Cant Hold Us feat. Ray Dalton (live on triple j).wav");
			clsCancion cancion15 = new clsCancion(file15,  "Cant Hold Us", "Macklemore  Ryan Lewis", 2018, 213);
			
			File file16= new File("test/res/Macklemore  Ryan Lewis - Same Love Feat. Tegan and Sara [HD VERSION LIVE FROM OSHEAGA 2013].wav");
			clsCancion cancion16= new clsCancion(file16,  "Same Love", "Macklemore  Ryan Lewis", 2018, 213);
			
			File file17= new File("test/res/MACKLEMORE FEAT DAVE B  TRAVIS THOMPSON - CORNER STORE (Official Music Video).wav");
			clsCancion cancion17 = new clsCancion(file17,  "CORNER STORE", "MACKLEMORE FEAT DAVE B  TRAVIS THOMPSON", 2018, 213);
			
			File file18= new File("test/res/MACKLEMORE FEAT KESHA - GOOD OLD DAYS (OFFICIAL MUSIC VIDEO).wav");
			clsCancion cancion18 = new clsCancion(file18,  "GOOD OLD DAYS", "MACKLEMORE FEAT KESHA ", 2018, 213);
			
			File file19= new File("test/res/MACKLEMORE FEAT LIL YACHTY - MARMALADE (OFFICIAL MUSIC VIDEO).wav");
			clsCancion cancion19 = new clsCancion(file19,  "MARMALADE", "MACKLEMORE FEAT LIL YACHTY", 2018, 213);
			
			File file20= new File("test/res/MACKLEMORE FEAT SKYLAR GREY - GLORIOUS (OFFICIAL MUSIC VIDEO).wav");
			clsCancion cancion20 = new clsCancion(file20,  "GLORIOUS", "MACKLEMORE FEAT SKYLAR GREY", 2018, 213);
			
			File file21= new File("test/res/MACKLEMORE X RYAN LEWIS - AND WE DANCED [OFFICIAL VIDEO].wav");
			clsCancion cancion21 = new clsCancion(file21,  "AND WE DANCED", "MACKLEMORE X RYAN LEWIS", 2018, 213);
			
			File file22= new File("test/res/Queen - Bohemian Rhapsody (Official Video).wav");
			clsCancion cancion22 = new clsCancion(file22,  "Bohemian Rhapsody", "Queen", 2018, 213);
			
			File file23= new File("test/res/Queen - Love Of My Life (Official Video).wav");
			clsCancion cancion23 = new clsCancion(file23,  "Love Of My Life", "Queen", 2018, 213);
			
			File file24= new File("test/res/The Beatles - Come Together.wav");
			clsCancion cancion24 = new clsCancion(file24,  "Come Together", "The Beatles", 2018, 213);
			
			File file25= new File("test/res/The Beatles - Dont Let Me Down.wav");
			clsCancion cancion25 = new clsCancion(file25,  "Dont Let Me Down", "The Beatles", 2018, 213);
			
			File file26= new File("test/res/The Beatles - Help.wav");
			clsCancion cancion26 = new clsCancion(file26,  "Help", "The Beatles", 2018, 213);
			
			File file27= new File("test/res/The Beatles - Hey Jude.wav");
			clsCancion cancion27 = new clsCancion(file27,  "Hey Jude", "The Beatles", 2018, 213);
			
			File file28= new File("test/res/The Beatles - Yellow Submarine.wav");
			clsCancion cancion28 = new clsCancion(file28,  "Yellow Submarine", "The Beatles", 2018, 213);
			
			File file29= new File("test/res/Yesterday (With Spoken Word Intro  Live From Studio 50, New York City  1965).wav");
			clsCancion cancion29 = new clsCancion(file29,  "Yesterday", "The Beatles", 2018, 213);
			
			clsBD.añadirCancion(cancion1);
			clsBD.añadirCancion(cancion2);
			clsBD.añadirCancion(cancion3);
			clsBD.añadirCancion(cancion4);
			clsBD.añadirCancion(cancion5);
			clsBD.añadirCancion(cancion6);
			clsBD.añadirCancion(cancion7);
			clsBD.añadirCancion(cancion8);
			clsBD.añadirCancion(cancion9);
			clsBD.añadirCancion(cancion10);
			clsBD.añadirCancion(cancion11);
			clsBD.añadirCancion(cancion12);
			clsBD.añadirCancion(cancion13);
			clsBD.añadirCancion(cancion14);
			clsBD.añadirCancion(cancion15);
			clsBD.añadirCancion(cancion16);
			clsBD.añadirCancion(cancion17);
			clsBD.añadirCancion(cancion18);
			clsBD.añadirCancion(cancion19);
			clsBD.añadirCancion(cancion20);
			clsBD.añadirCancion(cancion21);
			clsBD.añadirCancion(cancion22);
			clsBD.añadirCancion(cancion23);
			clsBD.añadirCancion(cancion24);
			clsBD.añadirCancion(cancion25);
			clsBD.añadirCancion(cancion26);
			clsBD.añadirCancion(cancion27);
			clsBD.añadirCancion(cancion28);
			clsBD.añadirCancion(cancion29);
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
