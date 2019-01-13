package LN;

import java.awt.EventQueue;

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
				
		EventQueue.invokeLater( new Runnable() 
		{
			@Override
			public void run()
			{
				frmInicioSesion frame = new frmInicioSesion(gestor);
				frame.setVisible(true);
			}
		});

	}
}
