package LN;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/**
 * Clase que sirve para hacer test unitarios de las diferentes opciones que ofrece la aplicacion
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class clsBDTest
{
	private clsCancion cancion1;
	private clsCancion cancion2;
	private clsUsuario Usuario1;
	private clsPlayList Listas1;
	
	private Connection con;
	private Statement st;
	private ResultSetMetaData rsmd;
	
	private final File ACDC = new File( "test/res/ACDC - Back in Black.wav");
	private final File Beatles = new File( "test/res/The Beatles - Help.wav");
	
	
	@Before
	public void setUp() 
	{
		cancion1 = new clsCancion(ACDC);
		cancion2 = new clsCancion(Beatles);
		
		Usuario1 = new clsUsuario();
		
		Listas1 = new clsPlayList();

		clsBD.initBD(); 
	    clsBD.crearTablaUsuarios();
	    clsBD.crearTablaCanciones();
	    clsBD.crearTablaPlaylist();
	    
		con = clsBD.getConnection();
		st = clsBD.getStatement();
	}
	
	@After
	public void tearDown() 
	{

	}
	
	@Test
	public void TestAñadirCancion() 
	{
		String nombre = "Back in Black"; 
		ResultSet rs = null;
		try 
		{
			rs = st.executeQuery("SELECT ruta FROM canciones WHERE nombre='" + nombre +"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String ruta = "";
		try 
		{
			ruta = rs.getString("ruta");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		clsBD.añadirCancion(cancion1);
		assertEquals( cancion1.getFile().getPath(), ruta  );

	}

	
	@Test
	public void TestAñadirPersona() 
	{
		String nombreUs = "Laura17";
		clsBD.añadirUsuario("Laura", "Llorente", "laullorente17@gmail.com", "Laura17", "135", "TODAS");
		ResultSet rs = null;
		try 
		{
			rs = st.executeQuery("SELECT nombreUsu FROM usuarios WHERE nombreUsu='" + nombreUs +"'");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		String us = "";
		try 
		{
			while(rs.next())
			{	
			us = rs.getString("nombreUsu");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		assertEquals( us, nombreUs  );
	}
	
	@Test
	public void TestAñadirPlaylist() 
	{
		 String lista ="TODAS";
		 ResultSet rs = null;
			try 
			{
				rs = st.executeQuery("SELECT nombre FROM playlist WHERE cancion = 'Back in Black'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			String name = "";
			try 
			{
				name = rs.getString("nombre");
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			clsBD.añadirCanPlaylist(name, cancion1);
			assertEquals( lista, name);
	}
}
