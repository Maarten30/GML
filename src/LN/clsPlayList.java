package LN;

import java.io.File;
import java.util.ArrayList;

public class clsPlayList 
{

	
	private String nombre;
	private ArrayList<clsCancion> canciones;
	
	
	public clsPlayList(String nombre, ArrayList<clsCancion> canciones)
	{
		this.nombre = nombre;
		this.canciones = canciones;
	}
	
	public clsPlayList(String nombre)
	{
		
		this.nombre = nombre;
		canciones = null;
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
	
	
}
