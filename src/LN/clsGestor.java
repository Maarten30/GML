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

/**
 * Clase utilizada para la ejecucion del programa
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class clsGestor 
{
	
	private Connection conection = null;
	private Statement statement = null;
	private ArrayList<clsCancion> canciones = new ArrayList <clsCancion>();
	
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
	
	/**
	 * Metodo utilizado para seleccionar canciones de la base de datos
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
	
	// EMAIL CON .JAR DE MAARTEN
//	public void correo (String correo)
//	{
//		
//
////		EmailBuilder.startingBlank();
////		EmailBuilder.replyingTo(email);
////		EmailBuilder.forwarding(email);
////		EmailBuilder.copying(email);
//		
//		Email email = EmailBuilder.startingBlank()
//				  .from("GML", "gmlmusic3@gmail.com")
//		          .to("GML", "gmlmusic3@gmail.com")
////		          .to("C. Cane", "candycane@candyshop.org")
////		          .ccWithFixedName("C. Bo group", "chocobo1@candyshop.org", "chocobo2@candyshop.org")
////		          .withRecipientsUsingFixedName("Tasting Group", BCC,
////		                        "taster1@cgroup.org;taster2@cgroup.org;tester <taster3@cgroup.org>")
////		          .bcc("Mr Sweetnose <snose@candyshop.org>")
////		          .withReplyTo("lollypop", "lolly.pop@othermail.com")
////		          .withSubject("hey")
////		          .withHTMLText("<img src='cid:wink1'><b>We should meet up!</b><img src='cid:wink2'>")
//		          .withPlainText("Please view this email in a modern email client!")
////		          .withEmbeddedImage("wink1", imageByteArray, "image/png")
////		          .withEmbeddedImage("wink2", imageDatesource)
////		          .withAttachment("invitation", pdfByteArray, "application/pdf")
////		          .withAttachment("dresscode", odfDatasource)
////		          .withHeader("X-Priority", 5)
////		          .withReturnReceiptTo()
////		          .withDispositionNotificationTo("notify-read-emails@candyshop.com")
////		          .withBounceTo("tech@candyshop.com")
////		          .signWithDomainKey(privateKeyData, "somemail.com", "selector")
//		          .buildEmail();
//		
//		
//
//		Mailer mailer = MailerBuilder
//		          .withSMTPServer("smtp.host.com", 587, "gmlmusic3@gmail.com", "gmlprog3")
////		          .withTransportStrategy(TransportStrategy.SMTP_TLS)
////		          .withProxy("socksproxy.host.com", 1080, "proxy user", "proxy password")
////		          .withSessionTimeout(10 * 1000)
////		          .clearEmailAddressCriteria() // turns off email validation
////		          .withProperty("mail.smtp.sendpartial", true)
////		          .withDebugLogging(true)
//		          .buildMailer();
//		
//		System.out.println("Hola");
//			mailer.sendMail(email);
//	}
	
	/**
	 * Metodo utilizado para enviar correos a los usuarios a la hora de registrarse
	 */
	public void enviarCorreo(String correo)
	{
	      String to = correo;
	      String user = "gmlmusic3@gmail.com";
	      String password = "gmlprog3";
	      String host = "mail.javatpoint.com";  
	      
	      Properties props = new Properties();
	      props.setProperty("mail.smtp.host", host);
	      props.setProperty("mail.smtp.auth", "true");
	      props.put("mail.smtp.port","25");
	      
	      
	      Session session = Session.getDefaultInstance(props,  new javax.mail.Authenticator() {  
	          protected PasswordAuthentication getPasswordAuthentication() 
	          {  
	        	   return new PasswordAuthentication(user,password);  
	          }  
	        	    }); 
	      
		try 
		{	
			
			MimeMessage message = new MimeMessage(session); 
			
			
			System.out.println("El mensaje se ha enviado1");
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Creacion de cuenta");
			message.setText("Bienvenido a GML music, su cuenta se ha creado correctamente");
			
			System.out.println("El mensaje se ha enviado2");
//			Transport.send(message);
			Transport t = session.getTransport("smtp");
//			t.connect(user, password);
			t.connect((String) props.get("mail.smtp.host"),
					(Integer) props.get("mail.smtp.port"), user, password);
//			t.connect((String)props.get("mail.smtp.user"), "password");
			t.sendMessage(message, message.getAllRecipients());
			
			System.out.println("El mensaje se ha enviado3");
			t.close();
			
			
			
			//String correo = frmInicioSesion.txtCorreo.getText();
			//Propiedades para la conexión en nuestra cuenta
//			Properties props = new Properties();
//			props.setProperty("mail.smtp.host", "smtp.gmail.com");
//			props.setProperty("mail.smtp.starttls", "true");
//			props.setProperty("mail.smtp.auth", "true");
//			props.put("mail.smtp.port",25);
//			props.put("mail.smtp.mail.sender","laullorente17@gmail.com");
//			props.put("mail.smtp.user", "usuario");
			
			
//			Session session = Session.getDefaultInstance(props);
			
			//Datos para el correo
//			String correoRemitente = "laullorente17@gmail.com"; 
//			String passwordRemitente = "";
//			String correoReceptor = correo;
//			String asunto = "Correo de prueba";
//			String mensaje = "Este es el contenido del mensaje";
			
//			MimeMessage message = new MimeMessage(session); 
//			message.setFrom(new InternetAddress((String)props.get("mail.smtp.mail.sender")));
//			message.setFrom(new InternetAddress(correoRemitente));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
//			message.setSubject("Creacion de cuenta");
//			message.setText("Bienvenido a GML music, su cuenta se ha creado correctamente");
//			message.setFrom(new InternetAddress(correoRemitente));
			
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
//			message.setSubject(asunto);
//			message.setText(mensaje,"ISO-8859-1","html");

//			Transport t = session.getTransport("smtp");
//			t.connect(correoRemitente, passwordRemitente);
//			//t.connect((String)props.get("mail.smtp.user"), "password");
//			t.sendMessage(message, message.getAllRecipients());
//			t.close();
			
//			t.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
//			t.close();
			
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
