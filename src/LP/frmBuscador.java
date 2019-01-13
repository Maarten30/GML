package LP;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import LN.clsBD;
import LN.clsCancion;
import LN.clsUsuario;


public class frmBuscador extends JFrame implements ActionListener
{
		private static final long serialVersionUID = 1L;

		private static Logger logger = Logger.getLogger(frmInternalListas.class.getName());
		
		private JButton btnBuscar; 
		private JButton btnAnadir;
		private JTextField txtBuscar;
		private JFrame frame;
		private frmInternalListas intListas;
		private JPanel panel;
		private JScrollPane panelCanciones;
		private JSplitPane splitPanel;
		private JLabel lblCancion;
		
		private DefaultListModel<String> model = new DefaultListModel<>();
		private JList<String> listaCanciones= null;
		
		private ArrayList<clsCancion> canciones = new ArrayList<clsCancion>();
		
		private int SongIndex = 0;
		
		private clsUsuario UsuarioActual;
		
		private Connection conection = null;
		private Statement statement = null;
		private ResultSet rs = null;
	
		public void frmBuscador(clsUsuario usuario)
		{	
			conection = clsBD.getConnection();
			statement = clsBD.getStatement();
			
			Font f2 = new Font("Century Gothic",Font.BOLD,14);
			
			frame = new JFrame("Buscador");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setSize(500, 400);
			frame.setVisible(true);
			
			panel = new JPanel();
			panel.setLayout(null);
			frame.add(panel);
			
			listaCanciones = new JList<>(model);

			panelCanciones = new JScrollPane(listaCanciones);
			
			splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel, panelCanciones);
			splitPanel.setResizeWeight(0.5);
			frame.add(splitPanel);
			
			txtBuscar = new JTextField(); 
			txtBuscar.setBounds(20, 35, 300, 30);
			txtBuscar.setFont(f2);
			panel.add(txtBuscar);
			
			lblCancion = new JLabel("Introduzca el nombre de la canción o del artista que desee buscar: ");
			lblCancion.setBounds(20, 5, 550, 30);
			lblCancion.setFont(f2);
			panel.add(lblCancion);
		
			btnBuscar = new JButton("Buscar");
			btnBuscar.setBounds(340, 35, 80, 30);
			panel.add(btnBuscar);
			btnBuscar.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					buscarCancion();
				}
			});
			
			UsuarioActual = usuario;
			
			btnAnadir = new JButton("Añadir");
			btnAnadir.setBounds(340, 70, 80, 30);
			panel.add(btnAnadir);
			btnAnadir.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					SongIndex = listaCanciones.getSelectedIndex();
					intListas =  new frmInternalListas();
					
					for(clsCancion c : UsuarioActual.getListas().get(0).getCanciones())
					{
						if(canciones.get(SongIndex).getNombre().equalsIgnoreCase(c.getNombre()))
						{
							intListas.frmInternalListas(UsuarioActual, c);
						}
					}
				}
			});
			
			listaCanciones.setSelectedIndex(0);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			// TODO Auto-generated method stub
		}
		
	    public void buscarCancion()
	    {   
	    	String texto = txtBuscar.getText().toUpperCase();
	    	try 
	    	{
				rs = statement.executeQuery("SELECT nombre from canciones WHERE nombre like'" +texto+"' or autor like '"+texto+"'");
				
				while(rs.next())
				{			
					model.removeAllElements();

					for(clsCancion c : UsuarioActual.getListas().get(0).getCanciones())
					{
						if(c.getNombre().equalsIgnoreCase(texto)|| c.getAutor().equalsIgnoreCase(texto))
						{
							canciones.add(c);
							model.addElement(c.getNombre() + " - " + c.getAutor());
						}
						else
						{
							continue;

						}
					}
				}
				
			} 
	    	catch (SQLException e) 
	    	{
				e.printStackTrace();
			}
	    }

}
