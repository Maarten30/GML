package Usuario;

public class clsUsuario 
{

	
	private String nombre;
	private String apellido;
	private String nombreUs;
	private String contrase�a;
	
	
	public clsUsuario(String nombre, String apellido, String nombreUs, String contrase�a) 
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.nombreUs = nombreUs;
		this.contrase�a = contrase�a;
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


	public String getContrase�a() {
		return contrase�a;
	}


	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
	
	
	
}
