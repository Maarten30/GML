package LN;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import LN.ListadeReproduccion;


/**
 * Clase que sirve para hacer test unitarios de las diferentes opciones que ofrece la aplicacion
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class ListadeReproduccionTest
{
	private ListadeReproduccion lista1;
	private ListadeReproduccion lista2;
	
	private final File STONE = new File( "test/res/Demi Lovato - Stone Cold (Official Video).mp3");
	
	@Before
	public void setUp() 
	{
		lista1 = new ListadeReproduccion();
		lista2 = new ListadeReproduccion();
		lista2.añadir( STONE, true);
		
	}
	
	@After
	public void tearDown() 
	{
		lista2.eliminarLista();
	}
	
	@Test
	public void TestAñadir() 
	{
		lista2.añadir( null, true ); 
		assertEquals( lista2.PosFichero(1), null );

	}

	@Test
	public void TestEliminar() 
	{
		lista2.eliminarFichero(1);
		assertEquals( lista2.getSize(), 1);
	}


}
