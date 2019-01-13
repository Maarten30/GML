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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import LN.clsBD;


public class frmBuscador extends JFrame implements ActionListener
{
		private static final long serialVersionUID = 1L;

		private static Logger logger = Logger.getLogger(frmInternalListas.class.getName());
		
		private JButton btnBuscar; 
		private JTextField txtBuscar;
		private JFrame frame;
		private JPanel panel;
		private JLabel lblCancion;
		
		private Connection conection = null;
		private Statement statement = null;
		private ResultSet rs = null;
	
		public void frmBuscador()
		{	
			conection = clsBD.getConnection();
			statement = clsBD.getStatement();
			
			Font f2 = new Font("Century Gothic",Font.BOLD,14);
			
			frame = new JFrame("Buscador");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setSize(450, 150);
			frame.setVisible(true);
			
			panel = new JPanel();
			panel.setLayout(null);
			frame.add(panel);
			
			txtBuscar = new JTextField(); 
			txtBuscar.setBounds(10, 50, 240, 30);
			txtBuscar.setFont(f2);
			panel.add(txtBuscar);
			
			lblCancion = new JLabel("Introduzca el nombre de la canción o del artista que desee: ");
			lblCancion.setBounds(5, 15, 500, 30);
			lblCancion.setFont(f2);
			panel.add(lblCancion);
		
			btnBuscar = new JButton("Buscar");
			btnBuscar.setBounds(270, 50, 80, 30);
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
					System.out.println(rs.getString("nombre"));
				}
				
			} 
	    	catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

}
