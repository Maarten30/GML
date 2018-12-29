package LN;


import static LN.clsConstantes.Apellido;
import static LN.clsConstantes.Contraseña;
import static LN.clsConstantes.Email;
import static LN.clsConstantes.Nombre;
import static LN.clsConstantes.NombreUs;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;







import Excepciones.clsExistente;
import Excepciones.clsPropertyException;

public class clsUsuario implements itfProperty, Serializable
{
	private String nombre;
	private String apellido;
	private String nombreUs;
	private String contrasena;
	private String email;
	private int idUs;
	private int siguienteIdUs;
	
	
	public clsUsuario(String nombre, String apellido, String email, String nombreUs, String contrasena, int idUs, boolean leerBD, int idBD) 
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.nombreUs = nombreUs;
		this.contrasena = contrasena;
		this.idUs = idUs;
		
		if(leerBD)
		{
			this.idUs = idBD; 
		}
		else
		{
			this.idUs = siguienteIdUs; 
			siguienteIdUs ++; 
		}
	}


	public clsUsuario() 
	{
		nombre = ""; 
		apellido = "";
		email = "";
		nombreUs = "";
		contrasena = ""; 
	}
	
	public boolean existenteUs ( Statement st ) 
	{
		try {
			String sentSQL = "select * from usuarios " +
					"where (idUs = '" + idUs + "')";
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
	
	public boolean anyadirUsuario( Statement st ) 
	{
		if (existenteUs(st)) 
		{  
			return modificarUsuario(st);
		}
		try 
		{
			String sentSQL = "insert into usuarios values(" +
					"'" + nombre + "', " +
					"'" + apellido + "', " +
					"'" + nombreUs + "', " +
					"'" + contrasena + "', " +
					"'" + email + "', " +
					idUs + "," +
					 ")";
			
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
	
	public boolean modificarUsuario( Statement st ) 
	{
		try {
			String sentSQL = "update canciones set " +
					"nombre = '" + nombre + "', " +
					"apellido = '" + apellido + "', " +
					"nombreUs = " + nombreUs + ","  +
					"contrasena = '" + contrasena + "', " +
					"email = " + email + ","  +
					"idUsuario =" + idUs +
					"where (idUs = '" + idUs + "')";
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

	public String getNombre() 
	{
		return nombre;
	}


	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}


	public String getApellido() 
	{
		return apellido;
	}


	public void setApellido(String apellido) 
	{
		this.apellido = apellido;
	}


	public String getNombreUs() 
	{
		return nombreUs;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}


	public String getEmail() 
	{
		return email;
	}


	public void setNombreUs(String nombreUs) 
	{
		this.nombreUs = nombreUs;
	}


	public String getContrasena() 
	{
		return contrasena;
	}


	public void setContrasena(String contraseña) 
	{
		this.contrasena = contraseña;
	}
	
	public int getIdUs() 
	{
		return idUs;
	}


	public void setIdUs(int idUs) 
	{
		this.idUs = idUs;
	}


	public int getSiguienteIdUs() 
	{
		return siguienteIdUs;
	}


	public void setSiguienteIdUs(int siguienteIdUs) 
	{
		this.siguienteIdUs = siguienteIdUs;
	}


	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + idUs;
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		clsUsuario other = (clsUsuario) obj;
		if (idUs != other.idUs)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "clsUsuario Nombre" + nombre
				+ "Apellido: " + apellido + "" + "email" + email + ""
						+ "Nombre de usuario: " + nombreUs + "";
	}
	
	
	public boolean ValidarUsuario(String usuario, String contraseña)
	{
		if(usuario.equals("1234")&& contraseña.equals("ejemplo"))
		{
			return true;
		}
		else
		{
			return false; 
		}
	}
	
	@Override
	public String getStringProperty(String propiedad)
	{
		switch (propiedad)
		{
			case Nombre : return getNombre();
			case Apellido : return getApellido(); 
			case Email : return getEmail(); 
			case NombreUs : return getNombreUs(); 
			case Contraseña : return getContrasena(); 
			default: throw new clsPropertyException(propiedad); 
		}
	}

	@Override
	public Integer getIntegerProperty(String propiedad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float getFloatProperty(String propiedad) {
		// TODO Auto-generated method stub
		return null;
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
