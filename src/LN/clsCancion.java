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
	
	private static Logger logger = Logger.getLogger(clsUsuario.class.getName());
	private static Connection connection = null;
	private static Statement statement = null;
	
	private String nombre;
	private String autor;
	private String[] ListaReproduccion; //Esto ya no hace falta no?
//	private int idCa;
	private File file; 
	private String rutaImg;
//	private String ruta;
	
	/**
	 * Atributos empleados para la cancion, constructor con parametros
	 * @param file fichero de musica
	 * @param nombre nombre de la cancion
	 * @param autor autor de la cancion
	 * @param anio a�o de creacion de la cancion
	 * @param duracion duracion de la cancion
	 */
	public clsCancion(File file, String nombre, String autor, String rutaImg)
	{
		this.file = file;
		this.nombre= nombre;
		this.autor = autor;
		this.rutaImg = rutaImg;
//		this.idCa = idCa;
//		this.ruta = ruta;
	}
	
	public clsCancion(File fl)
	{
		file = fl;
		nombre = "";
		autor = "";
		rutaImg = "";
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



//	public int getIdCa() 
//	{
//		return idCa;
//	}
//
//	public void setIdCa(int idCa) 
//	{
//		this.idCa = idCa;
//	}	

	public String getRutaImg() {
		return rutaImg;
	}

	public void setRutaImg(String rutaImg) {
		this.rutaImg = rutaImg;
	}

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

	/**
	 * Metodo que sirve para imprimir un mensaje por pantalla
	 */
	@Override
	public String toString() 
	{
		return "Nombre: " + nombre + 
				"\nAutor: " + autor + 
				"\nListaReproduccion: " + Arrays.toString(ListaReproduccion) + 
//				"\nidCa: " + idCa + 
				"\nFile=" + file;
	}

	/**
	 * Metodo que sirve para comprobar si el metodo ya existe en la base de datos
	 */
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
	
	
	/**
	 * Metodo para a�adir un fichero una vez comprobado que no existe
	 */
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
	
	/**
	 * Metodo utilizado para modificar filas en la base de datos
	 */
	public boolean modificarFila( Statement st ) 
	{
		try {
			String sentSQL = "update canciones set " +
					"nombre = '" + nombre + "', " +
					"autor = '" + autor + "', " +
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
	
	/**
	 * Metodo utilizado para cargar un fichero en la tabla de la base de datos
	 */
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
