package Excepciones;

/**
 * Clase que sirve para saber si un usuario no existe dentro de una aplicacion 
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class clsNoExistente extends Exception 
{
	private String usuario; 
	
	public clsNoExistente() 
	{
		this.usuario = usuario; 
	}

	/**
	 *Método que contiene el mensaje que se muestra por pantalla en el caso de que se introduzca un nombre de usuario no existente 
	 */
	public String getMessage()
	{
		return "No existe un usuario con ese nombre";
		
	}
}
