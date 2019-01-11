package LN;

import java.io.File;
import java.util.ArrayList;

/**
 * Clase que representa una lista de reproduccion dentro de la aplicacion
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class clsPlayList 
{

	
	private String nombre;
	private ArrayList<clsCancion> canciones;
	
	/**
	 * Atributos empleados para una lista de reproduccion
	 * @param nombre nombre de la lista de reproduccion
	 * @param canciones canciones que incluye la lista
	 */
	public clsPlayList(String nombre, ArrayList<clsCancion> canciones)
	{
		this.nombre = nombre;
		this.canciones = canciones;
	}
	
	public clsPlayList()
	{
		this.nombre = "";
		this.canciones = new ArrayList<clsCancion>();
	}
	
	public clsPlayList(String nombre)
	{
		
		this.nombre = nombre;
		canciones = new ArrayList<clsCancion>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<clsCancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(ArrayList<clsCancion> canciones) {
		this.canciones = canciones;
	}
	
	public void añadirCancion(clsCancion cancion)
	{
		canciones.add(cancion);
	}
	
	
}
