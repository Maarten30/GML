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
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
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
	
	private int SongIndex = 0;
	private int aux = 0;
	private int ListIndex = 0;
	
	private File audioFile;
	public Clip audioClip;
	private String audioFilePath = "test/res/Demi Lovato - Stone Cold (Official Video).wav";
	long clipTime = 0;
	
	private ArrayList<clsCancion> Canciones;
	private clsUsuario UsuarioActual;
	
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
		
		listas.setFont(f1);
		listaCanciones.setFont(f1);
		
		lblInicio1= new JLabel("¡BIENVENIDO " +  frmInicioSesion.txtUsu.getText() +" A GML MUSIC!");
		lblInicio1.setFont(f2);
		panelInicio.add(lblInicio1, BorderLayout.CENTER);
		
		//IMAGEN IZQUIERDA
		
		JButton musica = new JButton(new ImageIcon ("src/img/Imagen1.png"));
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
		btnRefrescar = new JButton("Refrescar");
		
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
		panelBotonera.add(btnRefrescar);
		
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
		
		//BUSCADOR

//	    txtBuscar = new JTextField();
//	    txtBuscar1 = new JTextField();
//	    txtBuscar1.setBounds(100, 20, 100, 25);
//		//txtBuscar.setVisible(true);
//		btnBuscar = new JButton("Buscar");
//		btnBuscar.setBounds(getBounds());
//		panelBuscador.setBackground(Color.BLACK); //Lo pongo asi para distinguir el panel
//		System.out.println(panelBuscador.getLocation());
//		panelBuscador.add(btnBuscar);
//		panelBuscador.add(txtBuscar);
//		panelBuscador.setVisible(true);
////		inicio.add(btnBuscar, FlowLayout.LEFT);
////		inicio.add(txtBuscar, FlowLayout.LEFT);
//		panelInicio.add(txtBuscar1);
//		panelInicio.add(panelBuscador, FlowLayout.LEFT);
//		btnBuscar.addActionListener(new ActionListener()
//		{
//			
//			public void actionPerformed(ActionEvent arg0) 
//			{
//				buscarCancion();
//			}
//			
//		});
		//txtBuscar.setSize(300, 20);
		//inicio.add(txtBuscar, BorderLayout.EAST);
	    
	    //VOLUMEN
		
		g = new GridBagConstraints();
		panelBotonera.setLayout(new GridBagLayout());
		g.gridx = 1;
		g.gridy = 0;
		
		lblVol = new JLabel("Volumen 20 %");
		g.gridx=0;
		g.gridy=1;
		panelBotonera.add(lblVol, g.gridx);
		
		slide = new JSlider(JSlider.HORIZONTAL,0,100,30);
		slide.setMajorTickSpacing(50);
		slide.setPaintTicks(true);
		panelBotonera.add(slide,g.gridy);
		
		slide.addChangeListener(new ChangeListener()
				{
					public void stateChanged(ChangeEvent event)
					{
						lblVol.setText("Volumen "+slide.getValue()+" %");
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
		
		//SELECTION LISTENER LISTAS
		
		listas.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) 
                {
                	ListIndex = listas.getSelectedIndex();
                	Canciones = UsuarioActual.getListas().get(ListIndex).getCanciones();
                	CargarCanciones(ListIndex);
                }
            }
        });
	}
	
	//MÉTODOS PARA EL FUNCIONAMIENTO DE LA MÚSICA
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnPlay)
		{
			System.out.println("PEooooooo" + SongIndex);
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
			playing = true;
			
			aux = SongIndex;
			System.out.println(audioClip.getLongFramePosition());
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
			if(model2.size()==SongIndex+1)
			{
				SongIndex = 0;
			}
			else
			{
				SongIndex = SongIndex+1;
			}
			ImageIcon icon = new ImageIcon(UsuarioActual.getListas().get(0).getCanciones().get(SongIndex).getRutaImg());
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
			
			if(SongIndex!=0)
			{
				SongIndex = SongIndex -1;
			}
			else
			{
				primeraPos = true;
			}
			
			ImageIcon icon = new ImageIcon(UsuarioActual.getListas().get(0).getCanciones().get(SongIndex).getRutaImg());
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
		else if(arg0.getSource() == btnRefrescar)
		{
			CargarListas();
		}
//		else if (arg0.getSource() == shuffle)
//		{
//			
//		 int r = (int) Math.random()*41; //Porque hay 40 canciones, asique por uno mas
//		 SongIndex = r;
//			
//		}
//		else if (arg0.getSource() == like)
//		{
//			
//		 
//			
//		}
		
		
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
    	for(clsCancion a:Canciones)
    	{
    		System.out.println(a.getNombre());
    	}
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


