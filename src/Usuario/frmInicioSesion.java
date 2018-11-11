package Usuario;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class frmInicioSesion extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	JTextField txt1,txt2;
	JButton boton1, boton2;
	JPanel panel;
	JLabel usuario,contra;

	public frmInicioSesion() 
	{	
		setBounds(200, 200, 500, 500);
	    setVisible(true);
		
	    panel = new JPanel();
		panel.setBackground(Color.gray); 
		setContentPane(panel);
		panel.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		txt1 = new JTextField();
		txt2 = new JTextField();
	
		boton1 = new JButton();
		boton2 = new JButton();
		
		usuario = new JLabel("Usuario:");
		contra = new JLabel("Contraseña:");
		
		txt1.setBounds(190, 150, 160, 25);
		panel.add(txt1);
		txt1.setColumns(10);
		
		usuario.setBounds(190, 120, 160, 25);
		panel.add(usuario);
		
		txt2.setBounds(190, 210, 160, 25);
		panel.add(txt2);
		txt2.setColumns(10);
		
		contra.setBounds(190, 180, 160, 25);
		panel.add(contra);
		
		boton1.setBounds(190, 250, 160, 25);
		panel.add(boton1);
		boton1.setText("Entrar");
	 
	 
		}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args)
	{
		new frmInicioSesion();
	}
	

}
