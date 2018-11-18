package Excepciones;

/**
 * Clase creada para tratar la excepci�n de runtime, excepci�n que salta si el programador pregunta por una propiedad inexistente
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 */
public class clsPropertyException extends RuntimeException 
{
	private String mensaje= ""; 
	
	/**
	 * M�todo que contiene un mensaje que salta por pantalla cuando el porgramador busca una propiedad que no existe
	 * @param propiedad, propiedad que el programador llama
	 */
	public clsPropertyException (String propiedad)
	{
		mensaje = mensaje + "Propiedad inexistente: " + propiedad; 
	}
}
