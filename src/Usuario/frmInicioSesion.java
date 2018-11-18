package Usuario;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import ReproductordeMusica.frmReproductor;

public class frmInicioSesion extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;


	public static void frmInicioSesion(JPanel panel) 
	{	
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		ImageIcon img = new ImageIcon("C:/logoGML.png");
		JLabel fondo = new JLabel(img);	
		fondo.setBounds(0,0,150,150);
		panel.add(fondo);
		fondo.setVisible(true);
		
		Font f1 = new Font("Lucida Bright",Font.BOLD,25);
		Font f2 = new Font("Segoe UI Semibold",1,12); 
		Font f3 = new Font("Segoe UI Semibold",1,12);
		Font f4 = new Font("Lucida Bright",1,18);
		Font f5 = new Font("Segoe UI Semibold",Font.BOLD,13); 
		
		JLabel lblInicio = new JLabel("INICIAR SESIÓN"); 
		lblInicio.setFont(f1);
		lblInicio.setBounds(180,30,220,40);
		panel.add(lblInicio);
		
		JTextArea txtInfor = new JTextArea("Introduzca su nombre de usuario y su contraseña para poder acceder.");
		txtInfor.setBounds(170,70,260,100);
		txtInfor.setFont(f3);
		txtInfor.setEditable(false);
		txtInfor.setOpaque(false);
		txtInfor.setFocusable(false);
		txtInfor.setLineWrap(true);
		txtInfor.setWrapStyleWord(true); 
		panel.add(txtInfor);	
		
		JTextObject txtUsu = new JTextObject("Usuario", 20);
		txtUsu.setBounds(200, 110, 150, 25);
		txtUsu.setFont(f2);
		txtUsu.requestFocus();
		panel.add(txtUsu);
		
		JPasswordField contraField = new JPasswordField();
		contraField.setBounds(200, 140, 150, 25);
		contraField.setFont(f2);
		panel.add(contraField);
		
		InputMap map = new InputMap(); 
		
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0,false),"pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
		JButton btnEntrar = new JButton ("Entrar"); 
		btnEntrar.setInputMap(0, map);
		btnEntrar.setFont(f5);
		btnEntrar.setBounds(225, 180, 100, 30);
		btnEntrar.setBackground(Color.WHITE);
		panel.add(btnEntrar);
		btnEntrar.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						char[] clave = contraField.getPassword();
						String claveFinal = new String(clave); 
						clsUsuario usu = new clsUsuario(); 
						
						if(txtUsu.getText().equals(usu.getNombre())&& claveFinal.equals(usu.getContraseña()))
						{
							JOptionPane.showMessageDialog(null, "Bienvenido a GML music","INICIO SESIÓN",JOptionPane.INFORMATION_MESSAGE);
//							frmReproductor pagina = new frmReproductor(); //LLEVAR A LA PANTALLA PRINCIPAL
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos","ERROR",JOptionPane.ERROR_MESSAGE);
							txtUsu.setText("");
							contraField.setText("");
							txtUsu.requestFocus(); 
						}
					}
				});
		
		JLabel lblRegistro = new JLabel("REGISTRARSE"); 
		lblRegistro.setFont(f4);
		lblRegistro.setBounds(210,220,180,40);
		panel.add(lblRegistro);
		
		JTextArea txtInfReg = new JTextArea("Si todavía no tiene cuenta, puede hacerla de manera sencilla introduciendo los datos solicitados a continuación.");
		txtInfReg.setBounds(140,260,270,100);
		txtInfReg.setFont(f3);
		txtInfReg.setEditable(false);
		txtInfReg.setOpaque(false);
		txtInfReg.setFocusable(false);
		txtInfReg.setLineWrap(true);
		txtInfReg.setWrapStyleWord(true); 
		panel.add(txtInfReg);
		
		JTextObject txtNombre = new JTextObject("Nombre", 20);
		txtNombre.setFont(f2);
		txtNombre.setBounds(100,315, 150,20);
		panel.add(txtNombre);
		
		JTextObject txtApellido = new JTextObject("Apellido", 20);
		txtApellido.setFont(f2);
		txtApellido.setBounds(280,315,150,20);
		panel.add(txtApellido);
		
		JTextObject txtCorreo = new JTextObject("Correo electrónico", 20);
		txtCorreo.setFont(f2);
		txtCorreo.setBounds(100,345,280,20);
		panel.add(txtCorreo);
		
		JTextObject txtUsuario = new JTextObject("Nombre usuario", 20);
		txtUsuario.setFont(f2);
		txtUsuario.setBounds(100,375,150,20);
		panel.add(txtUsuario);
		
		JTextObject txtContraseña = new JTextObject("Contraseña", 20);
		txtContraseña.setFont(f2);
		txtContraseña.setBounds(280,375,150,20);
		panel.add(txtContraseña);
		
		JButton btnRegistrar = new JButton ("Registrar"); 
		btnRegistrar.setFont(f5);
		btnRegistrar.setBounds(220, 410, 100, 30);
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
		frame.setSize(500, 520);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		frmInicioSesion(panel);

		frame.setVisible(true);
	}
	
	
	

}
