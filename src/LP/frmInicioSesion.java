package LP;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
//import java.util.logging.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import LN.clsBD;
import LN.clsPlayList;

public class frmInicioSesion extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
//	private static Logger logger = Logger.getLogger(frmInicioSesion.class.getName());

	private JPanel panel;
	
	private JLabel lblInicio;
	private JLabel lblUsu; 
	private JLabel lblRegistro;
	private JLabel lblContra; 
	private JLabel lblNombre;
	private JLabel lblApe;
	private JLabel lblCorreo;
	private JLabel lblUsu2;
	private JLabel lblContra2;
	
	private JTextField txtUsu;
	private JTextField txtNombre;
	private JTextField txtApe;
	private JTextField txtCorreo;
	public static JTextField txtUsu2;
	
	private JPasswordField contraField; 
	private JPasswordField contraField2; 
	
	private JButton btnEntrar;
	private JButton btnRegistrar; 
	
	private String nombre; 
	private String apellido;
	private String email;
	private String nombreUsu;
	private String contrasenya;
//	ArrayList<clsPlayList> listas = null;
	
	
	
	private InputMap map; 

	public frmInicioSesion() 
	{	
		setTitle("Inicio de Sesión");
		setSize(460,550);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	    panel = new JPanel();
	    add(panel);
	    
	    panel.setVisible(true);
	    panel.setLayout(null);
	    panel.setBackground(Color.WHITE);
	     
	    clsBD.initBD(); 
	    clsBD.crearTablaUsuarios();
	    clsBD.crearTablaCanciones();
	
		//Inserción de imagen
		ImageIcon img = new ImageIcon("src/LN/logo.png");
		JLabel fondo = new JLabel(img);	
		JLabel fondo2 = new JLabel(img); 
		JLabel fondo3 = new JLabel (img);
		JLabel fondo4 = new JLabel (img);
		fondo.setBounds(-10,-20,100,100);
		fondo2.setBounds(-10, 440, 100, 100);
		fondo3.setBounds(360, -20, 100, 100);
		fondo4.setBounds(360,440,100,100);
		panel.add(fondo);
		panel.add(fondo2); 
		panel.add(fondo3);
		panel.add(fondo4);
		fondo.setVisible(true);
		
		//Fuentes de letra para la pantalla 
		Font f1 = new Font("Century Gothic",Font.BOLD,20);
		Font f2 = new Font("Century Gothic",1,13); 
		Font f3 = new Font("Century Gothic",1,13); 
		
		//Creación de la pantalla 
		lblInicio = new JLabel("INICIAR SESIÓN"); 
		lblInicio.setFont(f1);
		lblInicio.setBounds(160,10,220,40);
		panel.add(lblInicio);
				
		lblUsu = new JLabel("Usuario");
		lblUsu.setFont(f2);
		lblUsu.setBounds(20,50,100,50);
		panel.add(lblUsu); 
		
		txtUsu = new JTextField(); 
		txtUsu.setBounds(110, 65, 300, 25);
		txtUsu.setFont(f3);
		panel.add(txtUsu);
		
		lblContra = new JLabel ("Contraseña"); 
		lblContra.setFont(f2);
		lblContra.setBounds(20,100,300,30);
		panel.add(lblContra);
		
		contraField = new JPasswordField();
		contraField.setBounds(110, 105, 300, 25);
		contraField.setFont(f2);
		contraField.requestFocus();
		panel.add(contraField);
		
		map = new InputMap(); 
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0,false),"pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
		btnEntrar = new JButton ("Entrar"); 
		btnEntrar.setToolTipText("Pulse este botón si desea entrar al reproductor de música");
		btnEntrar.setInputMap(0, map);
		btnEntrar.setFont(f2);
		btnEntrar.setBounds(200, 150, 100, 30);
		btnEntrar.setBackground(Color.white);
		panel.add(btnEntrar);
//		btnEntrar.addActionListener(new ActionListener()
//				{
//					public void actionPerformed(ActionEvent arg0)
//					{				
//						logger.log(Level.INFO, "Comienzo inicio sesión");
//						String nombreUsu; 
//						String contrasena; 
//						
//						char[] clave = contraField.getPassword();
//						String claveFinal = new String(clave); 
//						clsUsuario usu = new clsUsuario(); 
//											
//						if(txtUsu.getText().equals(usu.getNombre())&& claveFinal.equals(usu.getContrasena()))
//						{
//							JOptionPane.showMessageDialog(null, "Bienvenido a GML music","INICIO SESIÓN",JOptionPane.INFORMATION_MESSAGE);
//							frmReproductor pagina = new frmReproductor(); //LLEVAR A LA PANTALLA PRINCIPAL
//						}
//						else
//						{
//							JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos","ERROR",JOptionPane.ERROR_MESSAGE);
//							txtUsu.setText("");
//							contraField.setText("");
//							txtUsu.requestFocus(); 
//						}
//					}
//				});	

		lblRegistro = new JLabel("REGISTRARSE"); 
		lblRegistro.setFont(f1);
		lblRegistro.setBounds(185,200,180,40);
		panel.add(lblRegistro);
		
		lblNombre = new JLabel ("Nombre");
		lblNombre.setFont(f2);
		lblNombre.setBounds(20,240,100,50);
		panel.add(lblNombre); 
		
		txtNombre = new JTextField(); 
		txtNombre.setBounds(110, 255, 300, 25);
		txtNombre.setFont(f3);
		panel.add(txtNombre);
		
		lblApe = new JLabel ("Apellido");
		lblApe.setFont(f2);
		lblApe.setBounds(20,280,100,50);
		panel.add(lblApe);
		
		txtApe = new JTextField(); 
		txtApe.setBounds(110, 295, 300, 25);
		txtApe.setFont(f3);
		panel.add(txtApe);
		
		lblCorreo = new JLabel ("Correo");
		lblCorreo.setFont(f2);
		lblCorreo.setBounds(20,320,100,50);
		panel.add(lblCorreo);
		
		txtCorreo = new JTextField(); 
		txtCorreo.setBounds(110, 335, 300, 25);
		txtCorreo.setFont(f3);
		panel.add(txtCorreo);
		
		lblUsu2 = new JLabel ("Usuario");
		lblUsu2.setFont(f2);
		lblUsu2.setBounds(20,360,100,50);
		panel.add(lblUsu2);
		
		txtUsu2 = new JTextField(); 
		txtUsu2.setBounds(110, 375, 300, 25);
		txtUsu2.setFont(f3);
		panel.add(txtUsu2);
		
		lblContra2 = new JLabel ("Contraseña");
		lblContra2.setFont(f2);
		lblContra2.setBounds(20,400,100,50);
		panel.add(lblContra2);
		
		contraField2 = new JPasswordField();
		contraField2.setBounds(110,415,300,25);
		contraField2.setFont(f2);
		contraField2.requestFocus();
		panel.add(contraField2);
		
		btnRegistrar = new JButton ("Registrar"); 
		btnRegistrar.setFont(f2);
		btnEntrar.setToolTipText("Pulse este botón si desea crearse una cuenta");
		btnRegistrar.setBounds(200, 460, 100, 30);
		btnRegistrar.setBackground(Color.WHITE);
		panel.add(btnRegistrar);
		btnRegistrar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
				if(txtNombre.getText().isEmpty() || txtApe.getText().isEmpty() || txtCorreo.getText().isEmpty() || txtUsu2.getText().isEmpty() || contraField2.getPassword()==null )
				{
					JOptionPane.showMessageDialog(null,"Te faltan campos de información por rellenar","INICIO SESIÓN",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					nombre = txtNombre.getText();
					apellido = txtApe.getText();
					
					if (clsBD.comprUsuario(txtUsu2.getText().toUpperCase()) == false)
					{
						nombreUsu = txtUsu2.getText();
						contrasenya = new String(contraField2.getPassword());
						
						Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
						 
						email = txtCorreo.getText();
						 
						Matcher mather = pattern.matcher(email);
					    if (mather.find() == true) 
					    {
					    	//FALTARÍA AÑADIR EL USUARIO
				
					    		clsBD.añadirUsuario(nombre, apellido, email, nombreUsu, contrasenya);
					 
					    		JOptionPane.showMessageDialog(null,"Su registro se ha realizado satisfactoriamente","INICIO SESIÓN",JOptionPane.INFORMATION_MESSAGE);
					    		dispose();
					    	
					    	//Llama a la pantalla frmReproductor
					        frmReproductor player = new frmReproductor();
					        player.GUI();
					    } 
					    else 
					    {
							JOptionPane.showMessageDialog(null,"El correo ingresado no es válido","INICIO SESIÓN",JOptionPane.ERROR_MESSAGE);
								
							txtCorreo.setText(null);
							txtCorreo.requestFocus();
					    }
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Su Nombre de usuario ya está ocupado","INICIO SESIÓN",JOptionPane.INFORMATION_MESSAGE);
					}
					
				
				}
			}
		});
	}
		
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
}