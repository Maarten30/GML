package Excepciones;

/**
 * Clase que sirve para saber si un usuario no existe dentro de la aplicación
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class clsUsuarioInexistente extends Exception 
{
	/**
	 *Método que contiene el mensaje que se muestra por pantalla en el caso de que se introduzca un nombre de usuario no existente 
	 */
	public String getMessage()
	{
		return "Ese nombre de usuario no esta registrado en la aplicacion";
		
	}
}
