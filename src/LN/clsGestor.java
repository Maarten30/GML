package LN;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import LP.frmReproductor;

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
	private ArrayList <clsPlayList> playlists = new ArrayList <clsPlayList>();
	private clsUsuario usuarioActual;
	
	private clsPlayList playnueva;
	
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
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		int numero = 0;
		try 
		{
			numero = (Integer) rs.getObject(1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
				
		if(numero == 0)
		{
			File file1 = new File("test/res/ACDC - Back in Black.wav");
			clsCancion cancion1 = new clsCancion(file1, "Back in Black", "ACDC", "src/img/acdc.png");
			
			File file2 = new File("test/res/Calvin Harris, Sam Smith - Promises (Official Lyric Video).wav");
			clsCancion cancion2 = new clsCancion(file2, "Promises", "Calvin Harris-Sam Smith", "src/img/promises.png");
			
			File file3 = new File("test/res/Demi Lovato - Stone Cold (Official Video).wav");
			clsCancion cancion3 = new clsCancion(file3, "Stone Cold", "Demi Lovato", "src/img/demi.png");
			
			File file4 = new File("test/res/Dua Lipa - Homesick (Lyrics).wav");
			clsCancion cancion4 = new clsCancion(file4, "Homesick", "Dua Lipa", "src/img/DuaLipa.png");
			
			File file5 = new File("test/res/George Ezra - Shotgun (Lyric Video).wav");
			clsCancion cancion5 = new clsCancion(file5, "George Ezra", "Shotgun", "src/img/george.png");
			
			File file6 = new File("test/res/Revelries & Henri Purnell - Feel It Still (Lyric Video).wav");
			clsCancion cancion6 = new clsCancion(file6,  "Feel It Still", "Revelries & Henri Purnell", "src/img/feelit.png");
			
			File file7= new File("test/res/ACDC - Hells Bells.wav");
			clsCancion cancion7 = new clsCancion(file7,  "Hells Bells", "Revelries & Henri Purnell", "src/img/acdc.png");
			
			File file8= new File("test/res/ACDC - Highway To Hell.wav");
			clsCancion cancion8 = new clsCancion(file8,  "Highway To Hell", "ACDC", "src/img/acdc.png");
			
			File file9= new File("test/res/ACDC - T.N.T..wav");
			clsCancion cancion9 = new clsCancion(file9,  "T.N.T.", "ACDC", "src/img/acdc.png");
			
			File file10= new File("test/res/ACDC - Thunderstruck.wav");
			clsCancion cancion10 = new clsCancion(file10,  "Thunderstruck", "ACDC","src/img/acdc.png");
			
			File file11= new File("test/res/ACDC - Whole Lotta Rosie.wav");
			clsCancion cancion11 = new clsCancion(file11,  "Whole Lotta Rosie", "ACDC", "src/img/acdc.png");
			
			File file13= new File("test/res/ACDC - You Shook Me All Night Long.wav");
			clsCancion cancion13 = new clsCancion(file13,  "You Shook Me All Night Long", "ACDC", "src/img/acdc.png");
			
			File file18= new File("test/res/MACKLEMORE FEAT LIL YACHTY - MARMALADE (OFFICIAL MUSIC VIDEO).wav");
			clsCancion cancion18= new clsCancion(file18,  "MARMALADE", "MACKLEMORE FEAT LIL YACHTY", "src/img/macklemore.png");
			
			File file19= new File("test/res/MACKLEMORE FEAT SKYLAR GREY - GLORIOUS (OFFICIAL MUSIC VIDEO).wav");
			clsCancion cancion19 = new clsCancion(file19,  "GLORIOUS", "MACKLEMORE FEAT SKYLAR GREY", "src/img/macklemore.png");
			
			File file20= new File("test/res/Queen - Love Of My Life (Official Video).wav");
			clsCancion cancion20 = new clsCancion(file20,  "Love Of My Life", "Queen", "src/img/queen.png");
			
			File file21= new File("test/res/The Beatles - Come Together.wav");
			clsCancion cancion21 = new clsCancion(file21,  "Come Together", "The Beatles", "src/img/beatles.png");
			
			File file22= new File("test/res/The Beatles - Dont Let Me Down.wav");
			clsCancion cancion22 = new clsCancion(file22,  "Dont Let Me Down", "The Beatles", "src/img/beatles.png");
			
			File file23= new File("test/res/The Beatles - Help.wav");
			clsCancion cancion23 = new clsCancion(file23,  "Help", "The Beatles", "src/img/beatles.png");
			
			File file24= new File("test/res/The Beatles - Yellow Submarine.wav");
			clsCancion cancion24 = new clsCancion(file24,  "Yellow Submarine", "The Beatles", "src/img/beatles.png");
			
			File file25= new File("test/res/Yesterday (With Spoken Word Intro  Live From Studio 50, New York City  1965).wav");
			clsCancion cancion25= new clsCancion(file25,  "Yesterday", "The Beatles", "src/img/beatles.png");

			File file26= new File("test/res/Imagine Dragons - Demons (Official).wav");
			clsCancion cancion26= new clsCancion(file26,  "Demons", "Imagine Dragons", "src/img/imagine.png");
			
			File file27= new File("test/res/Imagine Dragons - Natural.wav");
			clsCancion cancion27= new clsCancion(file27,  "Natural", "Imagine Dragons", "src/img/imagine.png");
			
			File file28= new File("test/res/Imagine Dragons - Next To Me (Audio).wav");
			clsCancion cancion28= new clsCancion(file28,  "Next To Me", "Imagine Dragons", "src/img/imagine.png");

			File file29= new File("test/res/Imagine Dragons - Thunder.wav");
			clsCancion cancion29= new clsCancion(file29,  "Thunder", "Imagine Dragons", "src/img/imagine.png");

			File file30= new File("test/res/Imagine Dragons - Whatever It Takes.wav");
			clsCancion cancion30= new clsCancion(file30,  "Whatever It Takes", "Imagine Dragons", "src/img/imagine.png");

			File file31= new File("test/res/Imagine Dragons - Zero (From the Original Motion Picture Ralph Breaks The Internet).wav");
			clsCancion cancion31= new clsCancion(file31,  "Zero", "Imagine Dragons", "src/img/imagine.png");

			File file32= new File("test/res/Kygo  Imagine Dragons - Born To Be Yours (Lyric Video).wav");
			clsCancion cancion32= new clsCancion(file32,  "Born To Be Yours", "Imagine Dragons", "src/img/imagine.png");

			File file33= new File("test/res/Passenger  He Leaves You Cold (Acoustic Live from Unityville, PA).wav");
			clsCancion cancion33= new clsCancion(file33,  "He Leaves You Cold", "Passenger", "src/img/passenger.png");

			File file34= new File("test/res/Passenger  Hell Or High Water (Official Video).wav");
			clsCancion cancion34= new clsCancion(file34,  "Hell Or High Water", "Passenger", "src/img/passenger.png");

			File file35= new File("test/res/Passenger  Simple Song (Official Video).wav");
			clsCancion cancion35= new clsCancion(file35,  "Simple Song", "Passenger", "src/img/passenger.png");

			File file36= new File("test/res/Passenger  Survivors (Official Video).wav");
			clsCancion cancion36= new clsCancion(file36,  "Survivors", "Passenger", "src/img/passenger.png");

			File file37= new File("test/res/Passenger  Why Cant I Change (Official Video).wav");
			clsCancion cancion37= new clsCancion(file37,  "Why Cant I Change", "Passenger", "src/img/passenger.png");

			File file38= new File("test/res/Passenger - Hearts On Fire (Official Video).wav");
			clsCancion cancion38= new clsCancion(file38,  "Hearts On Fire", "Passenger", "src/img/passenger.png");

			File file39= new File("test/res/Passenger - Holes (Official Video).wav");
			clsCancion cancion39= new clsCancion(file39,  "Holes", "Passenger", "src/img/passenger.png");

			File file40= new File("test/res/Passenger - The Wrong Direction - Official video.wav");
			clsCancion cancion40= new clsCancion(file40,  "The Wrong Direction", "Passenger", "src/img/passenger.png");
			
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
			clsBD.añadirCancion(cancion13);
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
			clsBD.añadirCancion(cancion30);
			clsBD.añadirCancion(cancion31);
			clsBD.añadirCancion(cancion32);
			clsBD.añadirCancion(cancion33);
			clsBD.añadirCancion(cancion34);
			clsBD.añadirCancion(cancion35);
			clsBD.añadirCancion(cancion36);
			clsBD.añadirCancion(cancion37);
			clsBD.añadirCancion(cancion38);
			clsBD.añadirCancion(cancion39);
			clsBD.añadirCancion(cancion40);
		}
		
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
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			while(rs.next())
			{
				String nombre = rs.getString("nombre");
				
				
				String autor = rs.getString("autor");
			
				
				String rutaImg = rs.getString("imagen");
				
				
				String Path = rs.getString("ruta");
				
				
				File file = new File(Path);
				
				clsCancion cancion = new clsCancion(file, nombre, autor, rutaImg);
				canciones.add(cancion);
				
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
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
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		int numero = 0;
		try 
		{
			numero = (Integer) rs.getObject(1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
						
		if(numero == 0)
		{				
			for(clsCancion a:canciones)
			{
				clsBD.añadirCanPlaylist("TODAS", a);
			}
		}					
	}
		
	public void RecibirPlayList(clsUsuario usuario)
	{
		for(clsPlayList a:usuario.getListas())
		{
			String nombre = a.getNombre();
			
			ResultSet rs = null;
			try 
			{
				rs = statement.executeQuery("SELECT * FROM playlist WHERE nombre ='" +nombre+"' ");
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
			String aux = "";
			boolean primeravuelta = true;
			int numero = 0;
			
			try 
			{
				while(rs.next())
				{
					String nombreplay = rs.getString("nombre");
					
					if(nombreplay.equalsIgnoreCase(aux))
					{
						
					}
					else
					{
						playnueva = new clsPlayList();
						playnueva.setNombre(nombreplay);
						numero = numero +1;
					}
					
					String cancion = rs.getString("cancion");

					for(clsCancion b:canciones)
					{		
						if(b.getNombre().equalsIgnoreCase(cancion))
						{							playnueva.añadirCancion(b);
						}
					}
					if(nombreplay.equalsIgnoreCase(aux))
					{
						
					}
					else
					{
						playlists.add(playnueva);
						aux=nombreplay;
					}
					
					if(primeravuelta == true)
					{
						aux = nombreplay;
						primeravuelta = false;
					}	
				}
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void RecontruirUsuario(String usuario, String contraseña)
	{
		ResultSet rs = null;
		try 
		{
			rs = statement.executeQuery("SELECT * FROM usuarios WHERE nombreUsu ='" +usuario+"' AND contrasenya ='" +contraseña+"'");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			usuarioActual = new clsUsuario(rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("nombreUsu"), rs.getString("contrasenya"));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
			
		ResultSet rs2 = null;
		
		try 
		{
			rs2 = statement.executeQuery("SELECT playlist FROM usuarios WHERE nombreUsu ='" +usuario+"'");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		ArrayList <String> playlistsss = new ArrayList<String>();
		
		try 
		{
			while(rs2.next())
			{
				String añadir = rs2.getString("playlist");
				playlistsss.add(añadir);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	
		for(String h:playlistsss)
		{
			
				String nombreplay = h;
				clsPlayList listnueva = new clsPlayList(nombreplay);
				
				ResultSet rs3 = null;
				try 
				{
					rs3 = statement.executeQuery("SELECT cancion FROM playlist WHERE nombre = '"+nombreplay+"'");
				} 
				catch (SQLException e) 
				{
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
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}	
			}
	}
	
	public void AbrirMenu()
	{
		frmReproductor Pantalla = new frmReproductor();
		
		Pantalla.GUI(usuarioActual);	
	}	
}

