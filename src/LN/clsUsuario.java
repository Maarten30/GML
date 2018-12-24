package LN;


import static LN.clsConstantes.Apellido;
import static LN.clsConstantes.Contraseña;
import static LN.clsConstantes.Email;
import static LN.clsConstantes.Nombre;
import static LN.clsConstantes.NombreUs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;




import Excepciones.clsExistente;
import Excepciones.clsPropertyException;

public class clsUsuario implements itfProperty, Serializable
{
	private String nombre;
	private String apellido;
	private String nombreUs;
	private String contraseña;
	private String email;
	private int idUs;
	private int siguienteIdUs;
	
	
	public clsUsuario(String nombre, String apellido, String email, String nombreUs, String contraseña, int idUs, boolean leerBD, int idBD) 
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.nombreUs = nombreUs;
		this.contraseña = contraseña;
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
		contraseña = ""; 
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


	public String getContraseña() 
	{
		return contraseña;
	}


	public void setContraseña(String contraseña) 
	{
		this.contraseña = contraseña;
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
			case Contraseña : return getContraseña(); 
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
