package LN;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import LP.frmInicioSesion;

public class clsGestor 
{
	
	private Connection conection = null;
	private Statement statement = null;
	private ArrayList<clsCancion> canciones = new ArrayList <clsCancion>();
	
	public void InicializarBD()
	{
		clsBD.initBD(); 
	    clsBD.crearTablaUsuarios();
	    clsBD.crearTablaCanciones();
	    clsBD.crearTablaPlaylist();
	    
	    conection = clsBD.getConnection();
		statement = clsBD.getStatement();
		
	}
	

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
		
		System.out.println(numero);
		
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
			clsCancion cancion6 = new clsCancion(file6, "Revelries & Henri Purnell", "Feel It Still", 2018, 213);
			
//			clsBD.añadirCancion(cancion1);
			clsBD.añadirCancion(cancion2);
			clsBD.añadirCancion(cancion3);
			clsBD.añadirCancion(cancion4);
			clsBD.añadirCancion(cancion5);
			clsBD.añadirCancion(cancion6);
		}
		
		RecibirCanciones();
		System.out.println("EL numero de canciones es" + canciones.size());
		
		
		
	}
	
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
				String nombre = rs.getString("nombre");
				System.out.println(nombre);
				
				String autor = rs.getString("autor");
				System.out.println(autor);
				
				int año = rs.getInt("año");
				System.out.println(año);
				
				float duracion = rs.getFloat("duracion");
				System.out.println(duracion);
				
				String Path = rs.getString("ruta");
				System.out.println(Path);
				
				File file = new File(Path);
				
				clsCancion cancion = new clsCancion(file, nombre, autor, año, duracion);
				canciones.add(cancion);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void enviarCorreo()
	{
		try 
		{	
			String correo = frmInicioSesion.txtCorreo.getText();
			//Propiedades para la conexión en nuestra cuenta
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");
			
			Session session = Session.getDefaultInstance(props);
			
			//Datos para el correo
			String correoRemitente = "gabrigaraizabal@opendeusto.es"; 
			String passwordRemitente = "";
			String correoReceptor = correo;
			String asunto = "Correo de prueba";
			String mensaje = "Este es el contenido del mensaje";
			
			MimeMessage message = new MimeMessage(session); 
			message.setFrom(new InternetAddress(correoRemitente));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
			message.setSubject(asunto);
			message.setText(mensaje,"ISO-8859-1","html");

			Transport t = session.getTransport("smtp");
			t.connect(correoRemitente, passwordRemitente);
			t.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
			t.close();
			
			JOptionPane.showMessageDialog(null, "Correo electrónico enviado");
		
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
