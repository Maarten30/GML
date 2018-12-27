package LN;

import java.io.File;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	private File file;  
	
	
	public clsCancion(File file, String nombre, String autor, int anio, float duracion,String[] ListaReproduccion, int idCa, boolean leerBD, int idBD)
	{
		this.file = file;
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
	
	public clsCancion(File fl)
	{
		file = fl;
		nombre = "";
		autor = "";
		anio = 0;
		duracion = 1.5f;
		ListaReproduccion = null;
	}
	
	
	//Comprobar si ese fichero ya existe en la BD
	public boolean existente ( Statement st ) 
	{
		try {
			String sentSQL = "select * from canciones " +
					"where (fichero = '" + file.getAbsolutePath() + "')";
			System.out.println( sentSQL );  
			ResultSet rs = st.executeQuery( sentSQL );
			if (rs.next()) 
			{  
				rs.close();
				return true;
			}
			return false;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
	//Una vez comprobado, si no esta repetido podemos añadir ese fichero 
	public boolean anyadirFichero( Statement st ) 
	{
		if (existente(st)) 
		{  
			return modificarFila(st);
		}
		try 
		{
			String sentSQL = "insert into canciones values(" +
					"'" + file.getAbsolutePath() + "', " +
					"'" + nombre + "', " +
					"'" + autor + "', " +
					 + anio + ","  +
					 + duracion + ", " +
					"'" + ListaReproduccion + "', " +
					 + idCa + ")";
			
			System.out.println( sentSQL ); 
			int val = st.executeUpdate( sentSQL );
			if (val!=1) return false; 
			return true;
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean modificarFila( Statement st ) 
	{
		try {
			String sentSQL = "update canciones set " +
					"nombre = '" + nombre + "', " +
					"autor = '" + autor + "', " +
					"anio = " + anio + ","  +
					"duracion = " + duracion + 
					"Lista = '" + ListaReproduccion + "'," +
					"idCancion =" + idCa +
					"where (fichero = '" + file.getAbsolutePath() + "')";
			System.out.println( sentSQL );  
			int val = st.executeUpdate( sentSQL );
			if (val!=1) return false;  
			return true;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	//Cargar fichero de la tabla
	public void cargarFicdeTabla( Statement st ) 
	{
		try {
			String sentSQL = "select * from canciones " +
					"where (fichero = '" + this.file.getAbsolutePath() + "')";
			System.out.println( sentSQL );  
			ResultSet rs = st.executeQuery( sentSQL );
			if (rs.next()) {  
				this.nombre = rs.getString( "nombre" );
				this.autor = rs.getString( "autor" );
				this.anio = rs.getInt( "anio" );
				this.duracion = rs.getFloat( "duracion" );
			//	this.ListaReproduccion = rs.getArray("Lista");
				this.idCa = rs.getInt("IdCancion");
				rs.close();
			}
			// else No hay ninguno en la tabla
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
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
