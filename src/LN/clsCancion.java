package LN;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Clase que representa una cancion dentro de la aplicacion
 * @author Gabriela Garaizabal, Maarten handels y Laura Llorente
 *
 */
public class clsCancion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String autor;
	private File file; 
	private String rutaImg;
	
	/**
	 * Atributos empleados para la cancion, constructor con parametros
	 * @param file fichero de musica
	 * @param nombre nombre de la cancion
	 * @param autor autor de la cancion
	 * @param anio año de creacion de la cancion
	 * @param duracion duracion de la cancion
	 */
	public clsCancion(File file, String nombre, String autor, String rutaImg)
	{
		this.file = file;
		this.nombre= nombre;
		this.autor = autor;
		this.rutaImg = rutaImg;
	}
	
	public clsCancion(File fl)
	{
		file = fl;
		nombre = "";
		autor = "";
		rutaImg = "";
	}
	
	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getAutor() 
	{
		return autor;
	}

	public void setAutor(String autor) 
	{
		this.autor = autor;
	}	

	public String getRutaImg() 
	{
		return rutaImg;
	}

	public void setRutaImg(String rutaImg) 
	{
		this.rutaImg = rutaImg;
	}

	public File getFile() 
	{
		return file;
	}

	public void setFile(File file) 
	{
		this.file = file;
	}

	/**
	 * Metodo que sirve para imprimir un mensaje por pantalla
	 */
	@Override
	public String toString() 
	{
		return "Nombre: " + nombre + 
				"\nAutor: " + autor + 
				"\nFile=" + file;
	}
}
