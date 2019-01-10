package LN;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import LN.clsBD;

/**
 * Clase con los metodos necesarios para el funcionamiento de las listas de reproduccion
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class ListadeReproduccion implements ListModel<String>
{
	ArrayList<clsCancion> ficheros;
	
	/**
	 * Constructor para iniciar una lista vacia
	 */
	public ListadeReproduccion()
	{
		super();
		ficheros = new ArrayList<clsCancion>();
	}
	
	/**
	 * Metodo para añadir una cancion
	 * @param f fichero audio
	 * @param cargarBD
	 */
	public void añadir (File f, boolean cargarBD)
	{
		clsCancion cancion = new clsCancion( f );
		ficheros.add( cancion );
		//Anyadido( ficheros.size()-1 );  --> Hay que hacerlo con escuchadores pero no tenemos aún
		if (cargarBD) cancion.cargarFicdeTabla( clsBD.getStatement() );

	}
	
	/**
	 * Metodo para eliminar una lista de reproduccion
	 */
	public void eliminarLista()
	{
		ficheros.clear();
	}
	
	/**
	 * Metodo para eliminar una cancion de una lista de reproduccion
	 * @param posi posicion de la cancion que se desea eliminar
	 */
	public void eliminarFichero (int posi)
	{
		ficheros.remove(posi);
	}
	
	/**
	 * Metodo que devuelve la posicion de una cancion dentro de una lista de reproduccion
	 * @param posi posicion de la cancion
	 * @return devuelve la posicion de ese fichero
	 */
	public clsCancion PosFichero ( int posi ) 
	{
		return ficheros.get( posi );
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getElementAt(int index) 
	{
		return ficheros.get(index).getNombre();
	}

	@Override
	public int getSize() 
	{
		return ficheros.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

}
