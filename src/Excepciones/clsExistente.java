package Excepciones;


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
