package Excepciones;

public class clsNoExistente extends Exception 
{
	private String usuario; 
	
	public clsNoExistente(String usuario) 
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
