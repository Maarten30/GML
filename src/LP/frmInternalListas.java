package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import LN.clsBD;
import LN.clsCancion;
import LN.clsPlayList;
import LN.clsUsuario;

/**
 * Clase en la que se crea el internal frame que muestra las listas de usuario disponibles
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class frmInternalListas extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(frmInternalListas.class.getName());
	
	private JList<String> listas= null;
	private DefaultListModel<String> model = new DefaultListModel<>();
	
	private JButton btnAñadir; 
	private JButton btnCancelar;
	private JButton btnCreayAñade;
	
	private JLabel lblAñadir;
	private JTextField txtLista;
	
	private clsUsuario UsuarioActual;
	
	private JPanel panelBajo;
	private JPanel botonera;
	private JFrame frame;
	private JScrollPane panelListas;
	private clsCancion Cancion;
	private int ListIndex = 0; 
	
	private boolean actividad = true;
	
	private Connection conection = null;
	private Statement statement = null;
	
	/**
	 * Método en el que se meten los componentes que van a aparecer en el internal frame
	 */
	public void frmInternalListas(clsUsuario usuario, clsCancion cancion)
	{
		UsuarioActual = usuario;
		Cancion = cancion;
		
		conection = clsBD.getConnection();
		statement = clsBD.getStatement();
		
		Font f1 = new Font("Century Gothic",Font.BOLD,18);
		Font f2 = new Font("Century Gothic",Font.BOLD,14);
		
		frame = new JFrame("Tus listas de reproduccion:");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 300);
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		frame.setVisible(true);
		
		lblAñadir = new JLabel("Añadir a nueva lista:"); 
		lblAñadir.setFont(f2);
		lblAñadir.setBounds(10, 170, 150, 20);
		lblAñadir.setForeground(Color.black);
		frame.add(lblAñadir);
		
		txtLista = new JTextField(); 
		txtLista.setBounds(20, 190, 300, 30);
		txtLista.setFont(f2);
		frame.add(txtLista);
		
		botonera = new JPanel();
		panelBajo = new JPanel();
		
		botonera.setLayout( new FlowLayout( FlowLayout.CENTER ));
		panelBajo.setLayout( new BorderLayout() );
		
		
		for(clsPlayList a:UsuarioActual.getListas())
		{
			model.addElement(a.getNombre());
		}
		
		listas = new JList<>(model);
		
		listas.setFont(f1);
		
		listas.setSelectedIndex(ListIndex);
		
		btnAñadir = new JButton("Añadir");
		btnAñadir.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				boolean repetido = false;
				
				for(clsCancion a:UsuarioActual.getListas().get(ListIndex).getCanciones())
				{
					if(Cancion.getNombre().equalsIgnoreCase(a.getNombre()))
					{
						repetido = true;
					}
				}
				
				if(repetido == true)
				{
					JOptionPane.showMessageDialog(null,"Esta canción ya se encuentra en la lista seleccionada","AÑADIR CANCION",JOptionPane.INFORMATION_MESSAGE);

				}else
				{
					UsuarioActual.getListas().get(ListIndex).añadirCancion(Cancion);
					String nombre = UsuarioActual.getListas().get(ListIndex).getNombre();
					clsBD.añadirCanPlaylist(nombre, Cancion);
					
					frame.dispose();			
				}
			}
		});
		
		btnCreayAñade = new JButton("Crear y añadir");
		btnCreayAñade.setBounds(325, 190, 150, 30);
		
		btnCreayAñade.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				boolean repetido = false;
				
				if(txtLista.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Debes insertar un nombre para la nueva Lista","LISTA NUEVA",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{	
					String nombre = txtLista.getText();
					
					for(clsPlayList a:UsuarioActual.getListas())
					{
						if(nombre.equalsIgnoreCase(a.getNombre()))
						{
							repetido = true;
						}
					}
					
					if(repetido == true)
					{
						JOptionPane.showMessageDialog(null,"Ya existe una PlayList con este nombre","AÑADIR CANCION",JOptionPane.INFORMATION_MESSAGE);

					}
					else
					{
						clsPlayList NewLista = new clsPlayList(nombre);
						NewLista.añadirCancion(Cancion);
						UsuarioActual.añadirPlaylist(NewLista);
						clsBD.añadirUsuario(UsuarioActual.getNombre(), UsuarioActual.getApellido(), UsuarioActual.getEmail(),
								UsuarioActual.getNombreUs(), UsuarioActual.getContrasena(), nombre);
						clsBD.añadirCanPlaylist(nombre, Cancion);
						
						frame.dispose();
					}
				}	
			}
		});
		
		frame.add(btnCreayAñade);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
				frame.dispose();
			}
			
		});
		
		botonera.add(btnAñadir);
		botonera.add(btnCancelar);
		
		panelListas = new JScrollPane(listas);
		
		panelBajo.add(botonera, BorderLayout.NORTH );
		frame.add(panelBajo, BorderLayout.SOUTH);
		frame.getContentPane().add(panelListas);
		
		frame.setVisible(true);
		botonera.setVisible(true);
		
		listas.addListSelectionListener(new ListSelectionListener() 
		{
	        @Override
	        public void valueChanged(ListSelectionEvent arg0) 
	        {
	            if (!arg0.getValueIsAdjusting()) 
	            {
	            	ListIndex = listas.getSelectedIndex();
	            }
	        }
	    });
	}
	
	public boolean Actividad()
	{
		return actividad;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
}
