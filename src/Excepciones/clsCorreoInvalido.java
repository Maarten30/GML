package Excepciones;

/**
 * Clase que sirve para comprobar si existe un usuario en la aplicacion
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class clsCorreoInvalido extends Exception
{
	/**
	 *Método que contiene el mensaje que se muestra por pantalla en el caso de que se introduzca un correo invalido
	 */
	public String getMessage()
	{
		return "El correo ingresado no es válido";
		
	}
}
