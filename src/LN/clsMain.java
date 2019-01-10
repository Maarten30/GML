package LN;

import LP.frmInicioSesion;

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
