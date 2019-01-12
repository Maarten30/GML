package LP;

import java.awt.BorderLayout;
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
	
	private JButton btnAñadir; 
	private JButton btnCancelar;
	
	private clsUsuario UsuarioActual;
	
	private JPanel panelBajo;
	private JPanel botonera;
	private JPanel panel;
	private JScrollPane panelListas;
	
	/**
	 * Método en el que se meten los componentes que van a aparecer en el internal frame
	 */
	public frmInternalListas(clsUsuario usuario)
	{
		UsuarioActual = usuario;
		
		setTitle("Tus listas de reproduccion:"); 
		setBounds(5, 5, 500, 300);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 400, 200);
		setContentPane(panel);
		panel.setLayout(null);
		
		botonera = new JPanel();
		panelBajo = new JPanel();
		
		botonera.setLayout( new FlowLayout( FlowLayout.CENTER ));
		panelBajo.setLayout( new BorderLayout() );
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
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
		
		btnAñadir = new JButton("Añadir");
//		btnAñadir.setBounds(330, 237, 89, 23);
//		panel.add(btnAñadir);
//		btnAñadir.setActionCommand("Añadir");
		btnAñadir.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
//		btnCancelar.setBounds(330, 237, 89, 23);
//		panel.add(btnCancelar);
//		btnCancelar.setActionCommand("Cancelar");
		btnCancelar.addActionListener(this);
		
		botonera.add(btnAñadir);
		botonera.add(btnCancelar);
		
		panelBajo.add(botonera, BorderLayout.NORTH );
		panel.add(panelBajo, BorderLayout.SOUTH);
		
		panel.setVisible(true);
		botonera.setVisible(true);
		
//		panel.repaint();
		
		
	
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
