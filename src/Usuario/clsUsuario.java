package Usuario;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;




import Excepciones.clsExistente;
import Excepciones.clsPropertyException;

import static Usuario.clsConstantes.Nombre;
import static Usuario.clsConstantes.Apellido;
import static Usuario.clsConstantes.Email;
import static Usuario.clsConstantes.NombreUs;
import static Usuario.clsConstantes.Contrase�a;



public class clsUsuario implements itfProperty, Serializable
{

	
	private String nombre;
	private String apellido;
	private String nombreUs;
	private String contrase�a;
	private String email;
	
	
	public clsUsuario(String nombre, String apellido, String email, String nombreUs, String contrase�a) 
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.nombreUs = nombreUs;
		this.contrase�a = contrase�a;
	}


	public clsUsuario() 
	{
		nombre = ""; 
		apellido = "";
		email = "";
		nombreUs = "";
		contrase�a = ""; 
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


	public String getContrase�a() 
	{
		return contrase�a;
	}


	public void setContrase�a(String contrase�a) 
	{
		this.contrase�a = contrase�a;
	}

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nombreUs == null) ? 0 : nombreUs.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		clsUsuario other = (clsUsuario) obj;
		if (nombreUs == null) {
			if (other.nombreUs != null)
				return false;
		} else if (!nombreUs.equals(other.nombreUs))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "clsUsuario Nombre" + nombre
				+ "Apellido: " + apellido + "" + "email" + email + ""
						+ "Nombre de usuario: " + nombreUs + "";
	}
	
	
	public boolean ValidarUsuario(String usuario, String contrase�a)
	{
		if(usuario.equals("1234")&& contrase�a.equals("ejemplo"))
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
			case Contrase�a : return getContrase�a(); 
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
