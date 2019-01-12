package LN;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import LP.frmReproductor;



/**
 * Clase que sirve para hacer test unitarios de las diferentes opciones que ofrece la aplicacion
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class clsGestorTest
{
	private clsCancion cancion1;
	private clsCancion cancion2;
	
	
	private Connection con;
	private Statement st;
	
	private final File ACDC = new File( "test/res/ACDC - Back in Black.wav");
	private final File Beatles = new File( "test/res/The Beatles - Help.wav");
	
	@Before
	public void setUp() 
	{
		cancion1 = new clsCancion(ACDC);
		cancion2 = new clsCancion(Beatles);
		
		
		clsBD.initBD(); 
	    clsBD.crearTablaUsuarios();
	    clsBD.crearTablaCanciones();
	    clsBD.crearTablaPlaylist();
	    
		con = clsBD.getConnection();
		st = clsBD.getStatement();
		
		
	
	}
	
	@After
//	public void tearDown() 
//	{
//		lista2.eliminarLista();
//	}
	
	@Test
	public void TestAñadir() 
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
		try {
			ruta = rs.getString("ruta");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clsBD.añadirCancion(cancion1);
		assertEquals( cancion1.getFile().getPath(), ruta  );

	}

//	@Test
//	public void TestEliminar() 
//	{
//		lista2.eliminarFichero(1);
//		assertEquals( lista2.getSize(), 1);
//	}


}
