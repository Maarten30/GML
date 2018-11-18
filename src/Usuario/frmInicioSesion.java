package Usuario;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class frmInicioSesion extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;


	public static void frmInicioSesion(JPanel panel) 
	{	
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
//		ImageIcon img = new ImageIcon("C:/logoGML.png");
//		JLabel fondo = new JLabel();	
//		
//		java.awt.Image imgEscalada = img.getImage().getScaledInstance(fondo.getWidth(),fondo.getHeight(), 0);
//	    Icon iconoEscalado = new ImageIcon(imgEscalada);
//	    fondo.setIcon(iconoEscalado);
//		fondo.setIcon(iconoEscalado);
//		panel.add(fondo);
		
		Font f1 = new Font("Calibri",Font.BOLD,25);
		Font f2 = new Font("Calibri",Font.BOLD,13); 
		Font f3 = new Font("Calibri",1,12);
		Font f4 = new Font("Calibri",Font.BOLD,18);
		Font f5 = new Font("Calibri",Font.BOLD,15); 
		
		JLabel lblInicio = new JLabel("INICIAR SESIÓN"); 
		lblInicio.setFont(f1);
		lblInicio.setBounds(115,30,180,40);
		panel.add(lblInicio);
		
		JTextArea txtInfor = new JTextArea("Introduzca su nombre de usuario y su contraseña para poder acceder.");
		txtInfor.setBounds(80,70,260,100);
		txtInfor.setFont(f3);
		txtInfor.setEditable(false);
		txtInfor.setOpaque(false);
		txtInfor.setFocusable(false);
		txtInfor.setLineWrap(true);
		txtInfor.setWrapStyleWord(true); 
		panel.add(txtInfor);	
		
		JTextObject txtUsu = new JTextObject("Usuario", 20);
		txtUsu.setBounds(115, 110, 150, 25);
		txtUsu.setFont(f2);
		txtUsu.requestFocus();
		panel.add(txtUsu);
		
		JTextObject txtContra = new JTextObject("Contraseña", 20);
		txtContra.setBounds(115, 140, 150, 25);
		txtContra.setFont(f2);
		panel.add(txtContra);
		
		JButton btnEntrar = new JButton ("Entrar"); 
		btnEntrar.setFont(f5);
		btnEntrar.setBounds(140, 180, 100, 30);
		btnEntrar.setBackground(Color.WHITE);
		panel.add(btnEntrar);
		btnEntrar.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						clsUsuario usu = new clsUsuario(); 
						if(!usu.ValidarUsuario(txtUsu.getText(), txtContra.getText()))
						{
							JOptionPane.showMessageDialog(null, "Usuario o contraseña no válidos");
							txtContra.setText("");
							txtUsu.setText("");
						}
					}
				});
		
		JLabel lblRegistro = new JLabel("REGISTRARSE"); 
		lblRegistro.setFont(f4);
		lblRegistro.setBounds(145,220,180,40);
		panel.add(lblRegistro);
		
		JTextArea txtInfReg = new JTextArea("Si todavía no tiene cuenta, puede hacerla de manera sencilla introduciendo los datos solicitados a continuación.");
		txtInfReg.setBounds(80,260,270,100);
		txtInfReg.setFont(f3);
		txtInfReg.setEditable(false);
		txtInfReg.setOpaque(false);
		txtInfReg.setFocusable(false);
		txtInfReg.setLineWrap(true);
		txtInfReg.setWrapStyleWord(true); 
		panel.add(txtInfReg);
		
		JTextObject txtNombre = new JTextObject("Nombre", 20);
		txtNombre.setFont(f2);
		txtNombre.setBounds(25,315, 150,20);
		panel.add(txtNombre);
		
		JTextObject txtApellido = new JTextObject("Apellido", 20);
		txtApellido.setFont(f2);
		txtApellido.setBounds(205,315,150,20);
		panel.add(txtApellido);
		
		JTextObject txtCorreo = new JTextObject("Correo electrónico", 20);
		txtCorreo.setFont(f2);
		txtCorreo.setBounds(25,345,280,20);
		panel.add(txtCorreo);
		
		JTextObject txtUsuario = new JTextObject("Nombre usuario", 20);
		txtUsuario.setFont(f2);
		txtUsuario.setBounds(25,375,150,20);
		panel.add(txtUsuario);
		
		JTextObject txtContraseña = new JTextObject("Contraseña", 20);
		txtContraseña.setFont(f2);
		txtContraseña.setBounds(205,375,150,20);
		panel.add(txtContraseña);
		
		JButton btnRegistrar = new JButton ("Registrar"); 
		btnRegistrar.setFont(f5);
		btnRegistrar.setBounds(140, 410, 100, 30);
		btnRegistrar.setBackground(Color.WHITE);
		panel.add(btnRegistrar);	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Inicio de sesión");
		frame.setSize(400, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		frmInicioSesion(panel);

		frame.setVisible(true);
	}
	
	
	

}
