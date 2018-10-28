package ReproductordeMusica;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class ListasdeReproduccion implements ListModel<String>
{
	ArrayList<File> ficheros;
	
	/**
	 * Constructor para iniciar una lista vacia
	 */
	public ListasdeReproduccion()
	{
		super();
		ficheros = new ArrayList<File>();
	}
	
	public void añadir (File f)
	{
		ficheros.add(f);

	}
	
	public void eliminarLista()
	{
		ficheros.clear();
	}
	
	public void eliminarFichero (int posi)
	{
		ficheros.remove(posi);
	}
	

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getElementAt(int index) 
	{
		return ficheros.get(index).getName();
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
