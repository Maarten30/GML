package Excepciones;

/**
 * Clase que sirve para comprobar si existe un usuario en la aplicacion
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class clsExistente extends Exception
{
	private String usuario; 
	
	public clsExistente(String usuario) 
	{
		this.usuario = usuario; 
	}

	/**
	 *Método que contiene el mensaje que se muestra por pantalla en el caso de que se introduzca un nombre de usuario repetido
	 */
	public String getMessage()
	{
		return "Ya existe un usuario con ese nombre";
		
	}
}
