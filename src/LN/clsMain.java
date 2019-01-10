package LN;

import LP.frmInicioSesion;

/**
 * Clase en la que se inicializa la aplicacion
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class clsMain 
{

	public static void main(String[] args)
	{
		
		clsGestor gestor = new clsGestor();
		gestor.InicializarBD();
		gestor.InsertarCanciones();
		
		frmInicioSesion frame = new frmInicioSesion();
		frame.setVisible(true);
	}

}
