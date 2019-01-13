package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import LN.clsBD;
import LN.clsCancion;
import LN.clsPlayList;
import LN.clsUsuario;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;




/**
 * Clase que sirve para crear la pantalla principal de la aplicacion
 * @author Gabriela garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class frmReproductor extends JFrame implements LineListener, ActionListener
{ 
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(frmReproductor.class.getName());
	
	private JFrame frmVentanita;
	private frmInternalListas intListas;
	private frmBuscador buscador;
	
	private JPanel panelBajo;
	private JPanel panelBotonera;
	private JPanel panelSup;
	private JPanel panelInicio;
	private JPanel panelBuscador;
	
	private JSplitPane splitPanel;
	private JScrollPane panelListas;
	private JScrollPane panelCanciones;

	private JLabel lblInicio1;
	private JLabel lblUsuario1;
	private JLabel lblBuscar;
	private JLabel lblFotoCan = new JLabel();
	private JLabel lblVol;
	private JLabel Refrescar;
	
	private JButton btnPlay;
	private JButton btnLike;
	private JButton btnStop;
	private JButton btnPausar;
//	private JButton anterior;
	private JButton btnAvance;
	private JButton btnFin;
	private JButton btnPrincipio;
	private JButton btnRebobinar;
	private JButton btnShuffle;
//	private JButton siguiente;
	private JButton btnAnadir;
	private JButton btnBuscar;
	private JButton btnRefrescar;
	
	private JTextField txtBuscar;
	private JTextField txtBuscar1;
	private JTextArea display;
	
	private JSlider barraSlid;
	private JSlider slide;
	private JProgressBar BarraProgreso;
	
	private JList<String> listas= null;
	private JList<String> listaCanciones= null;
	
	private DefaultListModel<String> model = new DefaultListModel<>();
	private DefaultListModel<String> model2 = new DefaultListModel<>();
	
	private String newline = "\n";
	
	private GridBagConstraints g;
	
	boolean playCompleted;
	boolean playing;
	boolean PrimeraVez = true;
	boolean aleatorio = false;
	boolean refrescado = false;
	
	private int SongIndex = 0;
	private int aux = 0;
	private int ListIndex = 0;
	private int CancionAnterior = 0;
	private ImageIcon ImageIcon;
	private ImageIcon ImageIconP;
	
	private File audioFile;
	public Clip audioClip;
	private FloatControl gainControl;
	long clipTime = 0;
	
	private ArrayList<clsCancion> Canciones;
	private clsUsuario UsuarioActual;
	private clsCancion cancion;
	
	private ResultSet rs = null;
	private Statement st = null;
	private Connection con = null;

	/**
	 * Metodo en el que se crean todos los elementos que van a aparecer en la pantalla
	 */
	public void GUI(clsUsuario usuario)
	{
		
		UsuarioActual = usuario;
	//	Canciones = playlist.getCanciones();
		
		frmVentanita = new JFrame("Music Player");
		frmVentanita.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVentanita.setSize(1350, 720);
		//ventanita.pack();
		frmVentanita.getContentPane().setBackground(new Color(240, 240, 240));
		
//		ImageIcon jungleBackground = new ImageIcon("C:/imagenSpoti.png");
//		JLabel backgroundImage = new JLabel(jungleBackground);
//		frmVentanita.add(backgroundImage);
		
		panelSup = new JPanel();
		panelBajo = new JPanel();
		panelBotonera = new JPanel();
		panelInicio = new JPanel();
		panelBuscador = new JPanel();
		panelInicio.setLayout(null);
		
		for(clsPlayList a:UsuarioActual.getListas())
		{
			model.addElement(a.getNombre());
		}
	
		listas = new JList<>(model);
		
		Canciones = UsuarioActual.getListas().get(0).getCanciones();
		
		int numero = 0;
		
		CargarCanciones(numero); 

		listaCanciones = new JList<>(model2);
		
		listaCanciones.setSelectedIndex(0);
		
		Font f1 = new Font("Century Gothic",Font.BOLD,18);
		Font f2 = new Font("Century Gothic",Font.BOLD,25);
		Font f3 = new Font("Century Gothic",Font.BOLD,14);
		
		listas.setFont(f1);
		listaCanciones.setFont(f1);
		
		lblInicio1= new JLabel("¡BIENVENIDO " +  frmInicioSesion.txtUsu.getText() +" A GML MUSIC!");
		lblInicio1.setFont(f2);
		panelInicio.add(lblInicio1, BorderLayout.CENTER);
		
		//IMAGEN IZQUIERDA
		
		JButton musica = new JButton(new ImageIcon ("src/img/Imagen1G.png"));
		for(clsCancion a:UsuarioActual.getListas().get(0).getCanciones())
		{
			System.out.println(a.getNombre());
		}
			
		ImageIcon icon = new ImageIcon(UsuarioActual.getListas().get(0).getCanciones().get(0).getRutaImg());
		lblFotoCan.setIcon(icon);
		musica.setOpaque(true);                
    	musica.setBorder(null);           
    	musica.setContentAreaFilled(false); 
    	musica.setBorderPainted(false);     
    	musica.setFocusPainted(false);
    	musica.setContentAreaFilled(false);
    	
    	lblFotoCan.setOpaque(true);                
    	lblFotoCan.setBorder(null);           
    	
    	//PANELES
    	
    	panelInicio.add(lblFotoCan);
		panelInicio.add(musica, FlowLayout.LEFT);
		
		panelListas = new JScrollPane(listas);
		panelCanciones = new JScrollPane(listaCanciones);
		
		panelBotonera.setLayout( new FlowLayout( FlowLayout.CENTER ));
		panelBajo.setLayout( new BorderLayout() );
		
		panelInicio.setLayout( new FlowLayout( FlowLayout.CENTER ));
		panelSup.setLayout(new BorderLayout());
		
		//INSERCIÓN IMÁGENES BOTONES
		
		btnPausar = new JButton( new ImageIcon( "src/img/pausa.png") );
		btnPlay = new JButton( new ImageIcon("src/img/Play.png") );
		btnStop = new JButton( new ImageIcon("src/img/stop.png") );
		btnAnadir = new JButton (new ImageIcon ("src/img/añadir.png"));
		btnFin = new JButton( new ImageIcon("src/img/Fin.png") );
		btnShuffle = new JButton( new ImageIcon("src/img/shuffle.png") );
		btnPrincipio = new JButton( new ImageIcon("src/img/principio.png") );
		btnLike = new JButton( new ImageIcon("src/img/like.png") );
		btnRefrescar = new JButton(new ImageIcon("src/img/Refrescar.png"));
		btnBuscar = new JButton(new ImageIcon("src/img/buscar.png"));
		
		
		//BARRA DE PROGRESO CANCION
		
		BarraProgreso = new JProgressBar(0, 1000);
		BarraProgreso.setStringPainted(true);
		
		
		//BOTONES 
		
		btnPausar.setOpaque(false);            
    	btnPausar.setContentAreaFilled(false); 
    	btnPausar.setBorderPainted(false);     
    	btnPausar.setBorder(null); 
    	btnPausar.setFocusPainted(false);
    	btnPausar.setContentAreaFilled(false);
    	btnPausar.setPressedIcon(new ImageIcon(  "src/img/pausaN.png" ));
    	
    	btnLike.setOpaque(false);            
    	btnLike.setContentAreaFilled(false); 
    	btnLike.setBorderPainted(false);     
    	btnLike.setBorder(null); 
    	btnLike.setFocusPainted(false);
    	btnLike.setContentAreaFilled(false);
    	btnLike.setPressedIcon(new ImageIcon(  "src/img/likeN.png" ));
//    	like.setRolloverIcon(new ImageIcon(  "src/img/likeN.png" ) );

    	btnStop.setOpaque(false);            
    	btnStop.setContentAreaFilled(false); 
    	btnStop.setBorderPainted(false);    
    	btnStop.setBorder(null); 
    	btnStop.setFocusPainted(false);
    	btnStop.setContentAreaFilled(false);
    	btnStop.setPressedIcon(new ImageIcon(  "src/img/stopN.png" ));

    	btnPlay.setOpaque(false);            
    	btnPlay.setContentAreaFilled(false); 
    	btnPlay.setBorderPainted(false);     
    	btnPlay.setBorder(null); 
    	btnPlay.setFocusPainted(false);
    	btnPlay.setContentAreaFilled(false);
    	btnPlay.setPressedIcon(new ImageIcon(  "src/img/PlayN.png" ));
    	
    	btnAnadir.setOpaque(false);            
    	btnAnadir.setContentAreaFilled(false); 
    	btnAnadir.setBorderPainted(false);     
    	btnAnadir.setBorder(null); 
    	btnAnadir.setFocusPainted(false);
    	btnAnadir.setContentAreaFilled(false);
    	btnAnadir.setPressedIcon(new ImageIcon(  "src/img/añadirN.png" ));
    	
    	btnFin.setOpaque(false);            
    	btnFin.setContentAreaFilled(false); 
    	btnFin.setBorderPainted(false);     
    	btnFin.setBorder(null); 
    	btnFin.setFocusPainted(false);
    	btnFin.setContentAreaFilled(false);
    	btnFin.setPressedIcon(new ImageIcon(  "src/img/FinN.png" ));
    	
    	btnShuffle.setOpaque(false);            
    	btnShuffle.setContentAreaFilled(false); 
    	btnShuffle.setBorderPainted(false);     
    	btnShuffle.setBorder(null); 
    	btnShuffle.setFocusPainted(false);
    	btnShuffle.setContentAreaFilled(false);
    	btnShuffle.setPressedIcon(new ImageIcon(  "src/img/shuffleN.png" ));
    	
    	btnPrincipio.setOpaque(false);            
    	btnPrincipio.setContentAreaFilled(false); 
    	btnPrincipio.setBorderPainted(false);     
    	btnPrincipio.setBorder(null); 
    	btnPrincipio.setFocusPainted(false);
    	btnPrincipio.setContentAreaFilled(false);
    	btnPrincipio.setPressedIcon(new ImageIcon(  "src/img/principioN.png" ));
    	
    	btnRefrescar.setOpaque(false);            
    	btnRefrescar.setContentAreaFilled(false); 
    	btnRefrescar.setBorderPainted(false);     
    	btnRefrescar.setBorder(null); 
    	btnRefrescar.setFocusPainted(false);
    	btnRefrescar.setContentAreaFilled(false);
    	btnRefrescar.setPressedIcon(new ImageIcon(  "src/img/RefrescarN.png" ));
    	
    	btnBuscar.setOpaque(false);            
    	btnBuscar.setContentAreaFilled(false); 
    	btnBuscar.setBorderPainted(false);     
    	btnBuscar.setBorder(null); 
    	btnBuscar.setFocusPainted(false);
    	btnBuscar.setContentAreaFilled(false);
    	btnBuscar.setPressedIcon(new ImageIcon(  "src/img/buscarN.png" ));
		
    	// LISTENERS BOTONES
    	
		btnPlay.addActionListener(this);
		btnStop.addActionListener(this);
		btnPausar.addActionListener(this);
		btnAnadir.addActionListener(this);
		btnShuffle.addActionListener(this);
		btnPrincipio.addActionListener(this);
		btnFin.addActionListener(this);
		btnLike.addActionListener(this);
		btnRefrescar.addActionListener(this);
		btnBuscar.addActionListener(this);

		//BOTONERA
		
		
		panelBotonera.add(btnAnadir);
		panelBotonera.add(btnShuffle);
		panelBotonera.add(btnPlay);
		panelBotonera.add(btnPausar);
		panelBotonera.add(btnStop);
		panelBotonera.add(btnPrincipio);
		panelBotonera.add(btnFin);
		panelBotonera.add(btnLike);
		panelBotonera.add(BarraProgreso);
		panelBotonera.add(btnBuscar);
		
		
		
	//REFRESCAR
		
		Refrescar = new JLabel ("Refrescar listas de reproduccion:");
		Refrescar.setFont(f3);
		panelInicio.add(btnRefrescar, FlowLayout.LEFT);
		panelInicio.add(Refrescar, FlowLayout.LEFT);
		
		
		//IMAGENES SHUFFLE
		
		ImageIcon = new ImageIcon("src/img/shuffle.png");
		ImageIconP = new ImageIcon("src/img/shuffleN.png");
		
		
		//SPLITPANE
		
		splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelListas, panelCanciones);
		splitPanel.setResizeWeight(0.5);
		
		panelSup.add(panelInicio, BorderLayout.CENTER);
		
		panelBajo.add(panelBotonera, BorderLayout.NORTH );
		panelBajo.add(BarraProgreso, BorderLayout.SOUTH );
		
		frmVentanita.getContentPane().add(panelSup, BorderLayout.NORTH);
		frmVentanita.getContentPane().add(panelBajo, BorderLayout.SOUTH);
		frmVentanita.getContentPane().add(splitPanel);
		
		frmVentanita.setVisible(true);
		panelBotonera.setVisible(true);
		
	    //VOLUMEN
		
		g = new GridBagConstraints();
		panelBotonera.setLayout(new GridBagLayout());
		g.gridx = 1;
		g.gridy = 0;
		
		lblVol = new JLabel("Volumen 100%");
		g.gridx=0;
		g.gridy=1;
		panelBotonera.add(lblVol, g.gridx);
		
		slide = new JSlider(JSlider.HORIZONTAL,0,100,30);
		slide.setMajorTickSpacing(50);
		slide.setPaintTicks(true);
		slide.setValue(100);
		panelBotonera.add(slide,g.gridy);
		
		
		
		slide.addChangeListener(new ChangeListener()
				{
					public void stateChanged(ChangeEvent event)
					{
						
						lblVol.setText("Volumen " + slide.getValue() + "%");
						try{
						if(slide.getValue() == 0 || slide.getValue()<5)
						{
							gainControl.setValue((float)-(80-((slide.getValue()/100)*80)));
						}
						else if(slide.getValue() == 5 || slide.getValue()<10)
						{
							gainControl.setValue((float)-(65-((slide.getValue()/100)*65)));
						}
						else if(slide.getValue() == 10 || slide.getValue()<20)
						{
							gainControl.setValue((float)-(50-((slide.getValue()/100)*50)));
						}
						else if(slide.getValue() == 20 || slide.getValue()<40)
						{
							gainControl.setValue((float)-(30-((slide.getValue()/100)*30)));
						}
						else if(slide.getValue() == 40 || slide.getValue()<50)
						{
							gainControl.setValue((float)-(20-((slide.getValue()/100)*20)));
						}
						else if(slide.getValue() == 50 || slide.getValue()<75)
						{
							gainControl.setValue((float)-(10-((slide.getValue()/100)*10)));
						}
						else if(slide.getValue() == 75 || slide.getValue()<100)
						{
							gainControl.setValue((float)-(2.5-((slide.getValue()/100)*2.5)));
						}
						}catch(Exception e)
	                	{
							
	                	}
						
						

						//gainControl.setValue(-20.0f);
						//lblVol.setText("Volumen "+slide.getValue()+" %");
//						slide.setValue(auidoClip);
					}
				});

		//MOUSE LISTENER BARRA PROGRESO
		
		BarraProgreso.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (audioClip.isOpen()) {
//					// Seek en el vÃ­deo
//					float porcentajeSalto = (float)e.getX() / BarraProgreso.getWidth();
//					mediaPlayer.setPosition( porcentajeSalto );
//			    	visualizaTiempoRep();
//					// Otra manera de hacerlo con los milisegundos:
//					// long milisegsSalto = mediaPlayer.getLength();
//					// milisegsSalto = Math.round( milisegsSalto * porcentajeSalto );
//					// mediaPlayer.setTime( milisegsSalto );
					
					float porcentajeSalto = (float)e.getX() / BarraProgreso.getWidth();
					long Salto = Math.round(audioClip.getMicrosecondLength()*porcentajeSalto);
					audioClip.setMicrosecondPosition( Salto );
					//BarraProgreso.setValue( (int) (10000.0 * audioClip.getLongFramePosition() / audioClip.getFrameLength()) );
					//BarraProgreso.repaint();
				    AvanceBP();
					// Otra manera de hacerlo con los milisegundos:
					// long milisegsSalto = mediaPlayer.getLength();
					// milisegsSalto = Math.round( milisegsSalto * porcentajeSalto );
					// mediaPlayer.setTime( milisegsSalto );
				}
			}
		});
		
		//SELECTION LISTENER CANCIONES
		
		listaCanciones.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) 
                {
                	SongIndex = listaCanciones.getSelectedIndex();
                }
      
            }
        });
		
		listaCanciones.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) 
		        {
					if(SongIndex!=aux)
					{
						if(PrimeraVez==false)
						{
							playing = false;
							audioClip.stop();
							clipTime = 0;
							audioClip.close();
						}
					}
					
					PrimeraVez = false;
					play(SongIndex);
					ImageIcon icon = new ImageIcon(UsuarioActual.getListas().get(ListIndex).getCanciones().get(SongIndex).getRutaImg());
					lblFotoCan.setIcon(icon);
					playing = true;
					
					aux = SongIndex;
					AvanceBP();
		        } 
		    }
		});
		
		//SELECTION LISTENER LISTAS
		
		listas.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) 
                {
                	ListIndex = listas.getSelectedIndex();
                	
                	try
                	{
                		Canciones = UsuarioActual.getListas().get(ListIndex).getCanciones();
                	}catch(Exception e)
                	{
                	
                		Canciones = UsuarioActual.getListas().get(0).getCanciones();
                		ListIndex = 0;
                		listas.setSelectedIndex(0);
                	}
                	
                	
                	CargarCanciones(ListIndex);
                }
            }
        });
	}
	
	//MÉTODOS PARA EL FUNCIONAMIENTO DE LA MÚSICA
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnPlay)
		{
			
			if(SongIndex!=aux)
			{
				if(PrimeraVez==false)
				{
					playing = false;
					audioClip.stop();
					clipTime = 0;
					audioClip.close();
				}
			}
			
			PrimeraVez = false;
			play(SongIndex);
			ImageIcon icon = new ImageIcon(UsuarioActual.getListas().get(ListIndex).getCanciones().get(SongIndex).getRutaImg());
			lblFotoCan.setIcon(icon);
			playing = true;
			
			aux = SongIndex;
			AvanceBP();
			
		}
		else if(arg0.getSource() == btnPausar)
		{
			clipTime= audioClip.getMicrosecondPosition();
			playing = false;
			audioClip.stop();	
		}
		else if(arg0.getSource() == btnStop)
		{
			playing = false;
			audioClip.stop();
			clipTime = 0;
			System.out.println(clipTime);
			audioClip.close();
			
		}
		else if(arg0.getSource() == btnFin)
		{
			if(aleatorio==true)
			{
				
				int randomNum = ThreadLocalRandom.current().nextInt(0, model2.getSize() + 1);
				CancionAnterior = SongIndex;
				SongIndex = randomNum;
			}else
			{
				if(model2.size()==SongIndex+1)
				{
					SongIndex = 0;
				}
				else
				{
					SongIndex = SongIndex+1;
				}	
			}
			
			ImageIcon icon = new ImageIcon(UsuarioActual.getListas().get(ListIndex).getCanciones().get(SongIndex).getRutaImg());
			lblFotoCan.setIcon(icon);
			
			System.out.println("EL PRIMER NUMERO ES " + SongIndex);
			System.out.println("EL SEGUNDO NUMERO ES " + aux);
			if(SongIndex!=aux)
			{
				playing = false;
				audioClip.stop();
				clipTime = 0;
				audioClip.close();
			}
			play(SongIndex);
			playing = true;
			aux = SongIndex;
			System.out.println(audioClip.getLongFramePosition());
			AvanceBP();
		}
		else if(arg0.getSource() == btnPrincipio)
		{
			boolean primeraPos = false;
			
			if(aleatorio == true)
			{
				SongIndex = CancionAnterior;
			}
			else
			{
				if(SongIndex!=0)
				{
					SongIndex = SongIndex -1;
				}
				else
				{
					primeraPos = true;
				}
			}
			
			
			ImageIcon icon = new ImageIcon(UsuarioActual.getListas().get(ListIndex).getCanciones().get(SongIndex).getRutaImg());
			lblFotoCan.setIcon(icon);
			
			System.out.println("EL PRIMER NUMERO ES " + SongIndex);
			System.out.println("EL SEGUNDO NUMERO ES " + aux);
			if(SongIndex!=aux || primeraPos == true)
			{
				playing = false;
				audioClip.stop();
				clipTime = 0;
				audioClip.close();
			}
			
			play(SongIndex);
			playing = true;
			aux = SongIndex;
			System.out.println(audioClip.getLongFramePosition());
			AvanceBP();
		}
		else if(arg0.getSource() == btnAnadir)
		{
			intListas =  new frmInternalListas();
			clsCancion cancion = UsuarioActual.getListas().get(ListIndex).getCanciones().get(SongIndex);
			intListas.frmInternalListas(UsuarioActual, cancion);
			
		}
		else if(arg0.getSource() == btnBuscar)
		{
			buscador =  new frmBuscador();
			buscador.frmBuscador(UsuarioActual);		
		}
		else if(arg0.getSource() == btnRefrescar)
		{
			CargarListas();
			
		}
		else if(arg0.getSource() == btnShuffle)
		{
			
			
			if(aleatorio == false)
			{
				aleatorio = true;
				System.out.println("Se ha puesto en aleatorio");
				btnShuffle.setIcon(new ImageIcon("src/img/shuffleN.png"));
			}
			else
			{
				aleatorio = false;
				System.out.println("Se ha puesto en orden normal");
				btnShuffle.setIcon(new ImageIcon("src/img/shuffle.png"));
			}
			
		}
		else if(arg0.getSource() == btnLike)
		{
			boolean existe = false;
			boolean cancionRep = false;
			String favoritos = "FAVORITOS";
			
			for(clsPlayList a:UsuarioActual.getListas())
			{
				if(a.getNombre().equals(favoritos))
				{
					existe = true;
				}
			}
			
			if(existe == true)
			{
				for(clsPlayList a:UsuarioActual.getListas())
				{
					if(a.getNombre().equals(favoritos))
					{
						for(clsCancion d:a.getCanciones())
						{
							if(d.getNombre().equalsIgnoreCase(UsuarioActual.getListas().get(ListIndex).getCanciones().get(SongIndex).getNombre()))
							{
								cancionRep = true;
							}
						}
						if(cancionRep==false)
						{
							a.añadirCancion(UsuarioActual.getListas().get(ListIndex).getCanciones().get(SongIndex));
							clsBD.añadirCanPlaylist(favoritos, UsuarioActual.getListas().get(ListIndex).getCanciones().get(SongIndex));

						}
						else
						{
							JOptionPane.showMessageDialog(null,"Esta canción ya se encuentra en la lista de FAVORITOS","AÑADIR A FAVORITOS",JOptionPane.INFORMATION_MESSAGE);

						}
					}
					
				}
			}else
			{
				clsPlayList fav = new clsPlayList(favoritos);
				fav.añadirCancion(UsuarioActual.getListas().get(ListIndex).getCanciones().get(SongIndex));
				UsuarioActual.añadirPlaylist(fav);
				clsBD.añadirUsuario(UsuarioActual.getNombre(), UsuarioActual.getApellido(), UsuarioActual.getEmail(),
						UsuarioActual.getNombreUs(), UsuarioActual.getContrasena(), fav.getNombre());
				clsBD.añadirCanPlaylist(fav.getNombre(), UsuarioActual.getListas().get(ListIndex).getCanciones().get(SongIndex));
				
				
			}
			
		}

	}
	
	
	public void AvanceBP()
	{
		BarraProgreso.setValue( (int) (1000.0 * audioClip.getLongFramePosition() / audioClip.getFrameLength()) );
		BarraProgreso.repaint();
		
		//lMensaje2.setText( formatoHora.format( new Date(mediaPlayer.getTime()-3600000L) ) );
	}
	
	public void CargarCanciones(int numero)
	{
		
		model2.removeAllElements();
		
		for(clsCancion a:UsuarioActual.getListas().get(numero).getCanciones())
		{
			model2.addElement(a.getNombre() + " - " + a.getAutor());
		}
		
	}
	
	public void CargarListas()
	{
		model.removeAllElements();
		for(clsPlayList a:UsuarioActual.getListas())
		{
			model.addElement(a.getNombre());
		}
		
	}
	
	
   
	
	public void play(int numero)//String audioFilePath
    {
		
		System.out.println("Empieza cancion");
		
        audioFile = new File(Canciones.get(numero).getFile().getPath());
 
        try 
        {
        	AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
        	AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(Clip.class, format);
 
            audioClip = (Clip) AudioSystem.getLine(info);
 
            audioClip.addLineListener(this);
 
            audioClip.open(audioStream);
            
            audioClip.setMicrosecondPosition(clipTime);
			
            audioClip.start();
            
            gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            
            
             

            
            if(audioClip.getMicrosecondPosition()==audioClip.getMicrosecondLength())
			{
				clipTime=0;
			}
             
//            audioClip.close();
             
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
        
        audioClip.addLineListener(new LineListener() {
	        public void update(LineEvent event) {
	            if (audioClip.isRunning()) {
	                AvanceBP();
	            }
	        }
	    });
         
    }
     
    @Override
    public void update(LineEvent event) 
    {
    	
        LineEvent.Type type = event.getType();
         
        if (type == LineEvent.Type.START) 
        {
            System.out.println("Song playing.");
             
        } else if (type == LineEvent.Type.STOP) 
        {
            playCompleted = true;
            System.out.println("Song paused.");
            
            Long numero = audioClip.getMicrosecondLength();
            Long numero2 = audioClip.getMicrosecondPosition();
  
            
            if(numero.equals(numero2))
            {
            	
            	System.out.println("Ha llegado al final");
            	playing = false;
    			audioClip.stop();
    			clipTime = 0;
    			System.out.println(clipTime);
    			audioClip.close();
    			
    			
    			if(aleatorio==true)
    			{
    				System.out.println("Se ha metido en siguiente aleatorio");
    				int randomNum = ThreadLocalRandom.current().nextInt(0, model2.getSize() + 1);
    				System.out.println(randomNum);
    				SongIndex = randomNum;
    			}else
    			{
    				if(model2.size()==SongIndex+1)
    				{
    					SongIndex = 0;
    				}
    				else
    				{
    					SongIndex = SongIndex+1;
    				}	
    				
    			}
    			
    			
    			
    			play(SongIndex);
    			ImageIcon icon = new ImageIcon(UsuarioActual.getListas().get(ListIndex).getCanciones().get(SongIndex).getRutaImg());
    			lblFotoCan.setIcon(icon);
    			playing = true;
    			AvanceBP();
            }
        }
        
        
        while(audioClip.isRunning())
        {
        	AvanceBP();
        }
    }
    
	public void InicializarBD()
	{
		clsBD.initBD(); 
	    clsBD.crearTablaUsuarios();
	    clsBD.crearTablaCanciones();
	    clsBD.crearTablaPlaylist();
	    
	    con = clsBD.getConnection();
		st = clsBD.getStatement();
	}
    
    public void buscarCancion()
    {   
    	String texto = txtBuscar.getText();
    	try 
    	{
			rs = st.executeQuery("SELECT nombre, autor from canciones WHERE nombre,autor like" + "'"+texto+"' _%");
			System.out.println(rs);
		} 
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	private static final boolean AÑADIR_FIC_LOG = false;
	
	static
	{
		try
		{
			logger.addHandler(new FileHandler(frmReproductor.class.getName() + ".log.xml", AÑADIR_FIC_LOG));
		}catch(SecurityException | IOException e)
		{
			logger.log(Level.SEVERE, "Error en la creación del fichero log");
		}
	}

	
}


