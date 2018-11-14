package Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class frmInicioSesion extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	JFrame frame; 
	
	JTextField txtUsuario,txtContra;
	JButton btnEntrar, btnSalir;
	JPanel panel1, panel2;
	JLabel lblUsuario,lblContra;
	ImageIcon img;
	JLabel fondo; 

	public frmInicioSesion() 
	{	
		frame = new JFrame(); 
		frame.setSize(400,500);
		frame.setTitle("Inicio de sesión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon img = new ImageIcon("C:/imagenSpoti.png");
		JLabel fondo = new JLabel(img); 
		frame.add(fondo); 
		frame.setVisible(true);
		frame.setResizable(false); //impedimos que se pueda ampliar la ventana, solo se puede mover de sitio
		
	    panel1 = new JPanel();
	    BoxLayout disenyo = new BoxLayout(panel1,BoxLayout.X_AXIS); 
	    panel1.setLayout(disenyo);
//		frame.getContentPane().add(panel1, BorderLayout.SOUTH);; 
		
//		panel2 = new JPanel(); 
//		frame.getContentPane().add(panel2, BorderLayout.CENTER);
		
		btnEntrar = new JButton("INICIAR SESIÓN");
		panel1.add(btnEntrar);
		panel1.add(btnEntrar);
		
		btnSalir = new JButton("SALIR");
		panel1.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
					public void actionExit(ActionEvent salir){
						System.exit(0);
					}

					@Override
					public void actionPerformed(ActionEvent arg0){
						// TODO Auto-generated method stub
						
					}
					}
					);

		txtUsuario = new JTextField();
//		txt1.setBackground(Color.WHITE);
//		panel2.add(txt1,BorderLayout.CENTER);
//		txt1.setColumns(10);
		
		txtContra = new JTextField();
//		txt2.setBounds(190, 210, 160, 25);
//		txt1.setBackground(Color.WHITE);
//		panel.add(txt2);
//		txt2.setVisible(true);
//		txt2.setColumns(10);
		
		lblUsuario = new JLabel("Usuario:");
//		usuario.setBounds(190, 120, 160, 25);
//		usuario.setHorizontalAlignment(JLabel.CENTER);
//		panel.add(usuario);
		
		lblContra = new JLabel("Contraseña:");
//		contra.setBounds(190, 180, 160, 25);
//		panel.add(contra);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args)
	{
		new frmInicioSesion();
	}
	

}
