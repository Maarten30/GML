package Usuario;

public class clsUsuario 
{

	
	private String nombre;
	private String apellido;
	private String nombreUs;
	private String contraseña;
	
	
	public clsUsuario(String nombre, String apellido, String nombreUs, String contraseña) 
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.nombreUs = nombreUs;
		this.contraseña = contraseña;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getNombreUs() {
		return nombreUs;
	}


	public void setNombreUs(String nombreUs) {
		this.nombreUs = nombreUs;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	
	
	
}
