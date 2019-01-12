package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import LN.clsPlayList;
import LN.clsUsuario;

/**
 * Clase en la que se crea el internal frame que muestra las listas de usuario disponibles
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class frmInternalListas extends JFrame implements ActionListener
{
	private static Logger logger = Logger.getLogger(frmInternalListas.class.getName());
	
	private JList<String> listas= null;
	private DefaultListModel<String> model = new DefaultListModel<>();
	
	private JButton btnA�adir; 
	private JButton btnCancelar;
	
	private clsUsuario UsuarioActual;
	
	private JPanel panelBajo;
	private JPanel botonera;
	private JFrame frame;
	private JScrollPane panelListas;
	
	/**
	 * M�todo en el que se meten los componentes que van a aparecer en el internal frame
	 */
	public void frmInternalListas(clsUsuario usuario)
	{
		UsuarioActual = usuario;
		

		frame = new JFrame("Tus listas de reproduccion:");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 300);
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		

		
		botonera = new JPanel();
		panelBajo = new JPanel();
		
		botonera.setLayout( new FlowLayout( FlowLayout.CENTER ));
		panelBajo.setLayout( new BorderLayout() );
		
		
//		for(clsPlayList a:UsuarioActual.getListas())
//		{
//			model.addElement(a.getNombre());
//		}
//		
//		listas = new JList<>(model);
//		
//		Font f1 = new Font("Century Gothic",Font.BOLD,18);
//		listas.setFont(f1);
//		
//		panelListas = new JScrollPane(listas);
//		panel.add(panelListas);
		
		btnA�adir = new JButton("A�adir");
		btnA�adir.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		
		botonera.add(btnA�adir);
		botonera.add(btnCancelar);
		
		panelBajo.add(botonera, BorderLayout.NORTH );
		frame.add(panelBajo, BorderLayout.SOUTH);
		
		frame.setVisible(true);
		botonera.setVisible(true);
	
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
