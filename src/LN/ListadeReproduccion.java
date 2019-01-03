package LN;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import LN.clsBD;


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
	
	public void añadir (File f, boolean cargarBD)
	{
		clsCancion cancion = new clsCancion( f );
		ficheros.add( cancion );
		//Anyadido( ficheros.size()-1 );  --> Hay que hacerlo con escuchadores pero no tenemos aún
		if (cargarBD) cancion.cargarFicdeTabla( clsBD.getStatement() );

	}
	
	public void eliminarLista()
	{
		ficheros.clear();
	}
	
	public void eliminarFichero (int posi)
	{
		ficheros.remove(posi);
	}
	
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
