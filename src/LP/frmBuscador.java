package LP;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
		private JPanel panel;
		private JScrollPane panelCanciones;
		private JSplitPane splitPanel;
		private JLabel lblCancion;
		
		private DefaultListModel<String> model = new DefaultListModel<>();
		private JList<String> listaCanciones= null;
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
			frame.setSize(600, 500);
			frame.setVisible(true);
			
			panel = new JPanel();
			panel.setLayout(null);
			frame.add(panel);
			
			panelCanciones = new JScrollPane(listaCanciones);
			
			splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel, panelCanciones);
			splitPanel.setResizeWeight(0.5);
			frame.add(splitPanel);
			
			txtBuscar = new JTextField(); 
			txtBuscar.setBounds(20, 85, 300, 30);
			txtBuscar.setFont(f2);
			panel.add(txtBuscar);
			
			lblCancion = new JLabel("Introduzca el nombre de la canción o del artista que desee buscar: ");
			lblCancion.setBounds(20, 50, 550, 30);
			lblCancion.setFont(f2);
			panel.add(lblCancion);
		
			btnBuscar = new JButton("Buscar");
			btnBuscar.setBounds(340, 85, 80, 30);
			panel.add(btnBuscar);
			btnBuscar.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					buscarCancion();
					frame.dispose();
				}
				
			});
			
			UsuarioActual = usuario;
			
			btnAnadir = new JButton("Añadir");
			btnAnadir.setBounds(340, 130, 80, 30);
			panel.add(btnAnadir);
			
			listaCanciones = new JList<>(model);
//			listaCanciones.setSelectedIndex(0);
//			
//			listaCanciones.addListSelectionListener(new ListSelectionListener() {
//
//	            @Override
//	            public void valueChanged(ListSelectionEvent arg0) {
//	                if (!arg0.getValueIsAdjusting()) 
//	                {
//	                	SongIndex = listaCanciones.getSelectedIndex();
//	                }
//	      
//	            }
//	        });

		}
	
		@Override
		public void actionPerformed(ActionEvent arg0) {
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
//					System.out.println(rs.getString("nombre"));

				}
				
			} 
	    	catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

}
