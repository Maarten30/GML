package LN;

import java.io.Serializable;

import Excepciones.clsPropertyException;
import static LN.clsConstantes.Anio;
import static LN.clsConstantes.AutorCa;
import static LN.clsConstantes.Duracion;
import static LN.clsConstantes.NombreCa;


public class clsCancion implements itfProperty, Serializable
{
	private String nombre;
	private String autor;
	private int anio;
	private float duracion;
	private String[] ListaReproduccion;
	private int idCa;
	private int siguienteIdCa;
	
	
	public clsCancion(String nombre, String autor, int anio, float duracion,String[] ListaReproduccion, int idCa, boolean leerBD, int idBD)
	{
		this.nombre= nombre;
		this.autor = autor;
		this.anio = anio;
		this.duracion = duracion;
		this.ListaReproduccion = ListaReproduccion;
		this.idCa = idCa;
		
		if(leerBD)
		{
			this.idCa = idBD; 
		}
		else
		{
			this.idCa = siguienteIdCa; 
			siguienteIdCa ++; 
		}
		
		
	}
	
	public clsCancion()
	{
		nombre = "";
		autor = "";
		anio = 0;
		duracion = 1.5f;
		ListaReproduccion = null;
	}
	
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public float getDuracion() {
		return duracion;
	}

	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}

	public int getIdCa() {
		return idCa;
	}

	public void setIdCa(int idCa) {
		this.idCa = idCa;
	}

	public int getSiguienteIdCa() {
		return siguienteIdCa;
	}

	public void setSiguienteIdCa(int siguienteIdCa) {
		this.siguienteIdCa = siguienteIdCa;
	}
	
	

	public String[] getListaReproduccion() {
		return ListaReproduccion;
	}

	public void setListaReproduccion(String[] listaReproduccion) {
		ListaReproduccion = listaReproduccion;
	}

	@Override
	public String getStringProperty(String propiedad) {
		
		switch (propiedad)
		{
			case NombreCa : return getNombre();
			case AutorCa: return getAutor(); 
			
			default: throw new clsPropertyException(propiedad); 
		}
	}

	@Override
	public Integer getIntegerProperty(String propiedad) 
	{
		switch (propiedad)
		{
			case Anio : return getAnio();
			
			default: throw new clsPropertyException(propiedad); 
		}
	}

	@Override
	public Float getFloatProperty(String propiedad) {
		
		switch (propiedad)
		{
			case Duracion : return getDuracion();
			
			default: throw new clsPropertyException(propiedad); 
		}
	}

	@Override
	public Double getDoubleProperty(String propiedad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char getCharProperty(String propiedad) {
		// TODO Auto-generated method stub
		return 0;
	}

}
