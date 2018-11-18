package Excepciones;

import Usuario.clsUsuario;


public class clsExistente extends Exception
{
	public String clsExistente(clsUsuario a) 
	{
		return "No se puede acceder a la aplicación";
	}

	/**
	 *Método que contiene el mensaje que se muestra por pantalla en el caso de que se introduzca un nombre de usuario repetido
	 */
	public String getMessage()
	{
		return "Ya existe un usuario con ese nombre";
		
	}
}
