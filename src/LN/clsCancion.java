package LN;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Logger;

public class clsCancion implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(clsUsuario.class.getName());
	private static Connection connection = null;
	private static Statement statement = null;
	
	private String nombre;
	private String autor;
	private int anio;
	private float duracion;
	private String[] ListaReproduccion; //Esto ya no hace falta no?
//	private int idCa;
	private File file;  
//	private String ruta;
	
	
	public clsCancion(File file, String nombre, String autor, int anio, float duracion)
	{
		this.file = file;
		this.nombre= nombre;
		this.autor = autor;
		this.anio = anio;
		this.duracion = duracion;
//		this.idCa = idCa;
//		this.ruta = ruta;
	}
	
	public clsCancion(File fl)
	{
		file = fl;
		nombre = "";
		autor = "";
		anio = 0;
		duracion = 0.0f;
//		ruta = "";
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

	public int getAnio() 
	{
		return anio;
	}

	public void setAnio(int anio) 
	{
		this.anio = anio;
	}

	public float getDuracion() 
	{
		return duracion;
	}

	public void setDuracion(float duracion) 
	{
		this.duracion = duracion;
	}

//	public int getIdCa() 
//	{
//		return idCa;
//	}
//
//	public void setIdCa(int idCa) 
//	{
//		this.idCa = idCa;
//	}	

	public String[] getListaReproduccion() 
	{
		return ListaReproduccion;
	}

	public void setListaReproduccion(String[] listaReproduccion) 
	{
		ListaReproduccion = listaReproduccion;
	}
	
	
//	public String getRuta() {
//		return ruta;
//	}
//
//	public void setRuta(String ruta) {
//		this.ruta = ruta;
//	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	//ToString
	@Override
	public String toString() 
	{
		return "Nombre: " + nombre + 
				"\nAutor: " + autor + 
				"\nAnio: " + anio + 
				"\nDuracion: " + duracion +
				"\nListaReproduccion: " + Arrays.toString(ListaReproduccion) + 
//				"\nidCa: " + idCa + 
				"\nFile=" + file;
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
					"'" + ListaReproduccion + "', " + ")";
			
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
//					"idCancion =" + idCa +
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
//				this.idCa = rs.getInt("IdCancion");
				rs.close();
			}
			// else No hay ninguno en la tabla
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	

}
