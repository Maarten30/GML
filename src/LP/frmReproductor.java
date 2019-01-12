package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import LN.clsCancion;
import LN.clsPlayList;
import LN.clsUsuario;




/**
 * Clase que sirve para crear la pantalla principal de la aplicacion
 * @author Gabriela garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class frmReproductor extends JFrame implements LineListener, ActionListener
{ 
	private static Logger logger = Logger.getLogger(frmReproductor.class.getName());
	
	private JFrame ventanita;
	private JPanel panelBajo;
	private JPanel Botonera;
	private JPanel panelSup;
	private JPanel inicio;
	private JSplitPane splitpanel;
	private JScrollPane panelListas;
	private JScrollPane panelCanciones;

	private JLabel inicio1;
	private JLabel usuario1;
	
	
	private JButton play;
	private JButton like;
	private JButton stop;
	private JButton pausar;
//	private JButton anterior;
	private JButton avance;
	private JButton Fin;
	private JButton principio;
	private JButton rebobinar;
	private JButton shuffle;
//	private JButton siguiente;
	private JButton anadir;
	private JList<String> listas= null;
	private JList<String> listaCanciones= null;
	private DefaultListModel<String> model = new DefaultListModel<>();
	private DefaultListModel<String> model2 = new DefaultListModel<>();
	private JProgressBar BarraProgreso;
	private JTextArea display;
	private String newline = "\n";
	private JLabel FotoCan = new JLabel();
	
	boolean playCompleted;
	boolean playing;
	boolean PrimeraVez = true;
	
	private int SongIndex = 0;
	private int aux = 0;
	frmInternalListas intListas;
	
	private File audioFile;
	public Clip audioClip;
	private String audioFilePath = "test/res/Demi Lovato - Stone Cold (Official Video).wav";
	long clipTime = 0;
	
	private ArrayList<clsCancion> Canciones;
	private clsUsuario UsuarioActual;

	/**
	 * Metodo en el que se crean todos los elementos que van a aparecer en la pantalla
	 */
	public void GUI(clsUsuario usuario)
	{
		
		UsuarioActual = usuario;
	//	Canciones = playlist.getCanciones();
		
		ventanita = new JFrame("Music Player");
		ventanita.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventanita.setSize(1350, 720);
		//ventanita.pack();
		ventanita.getContentPane().setBackground(new Color(240, 240, 240));
		
		ImageIcon jungleBackground = new ImageIcon("C:/imagenSpoti.png");
		JLabel backgroundImage = new JLabel(jungleBackground);
		ventanita.add(backgroundImage);
		
		panelSup = new JPanel();
		panelBajo = new JPanel();
		Botonera = new JPanel();
		inicio = new JPanel();
		
		for(clsPlayList a:UsuarioActual.getListas())
		{
			model.addElement(a.getNombre());
		}
	
		listas = new JList<>(model);
		
		Canciones = UsuarioActual.getListas().get(0).getCanciones();
		
		int numero = 0;
		
		CargarCanciones(numero); 

		listaCanciones = new JList<>(model2);
		
		
		Font f1 = new Font("Century Gothic",Font.BOLD,18);
		Font f2 = new Font("Century Gothic",Font.BOLD,25);
		
		listas.setFont(f1);
		listaCanciones.setFont(f1);
		
		inicio1= new JLabel("¡BIENVENIDO " +  frmInicioSesion.txtUsu.getText() +" A GML MUSIC!");
		inicio1.setFont(f2);
		inicio.add(inicio1, BorderLayout.CENTER);
		
		JButton musica = new JButton(new ImageIcon ("src/img/Imagen1.png"));
		ImageIcon icon = new ImageIcon(UsuarioActual.getListas().get(0).getCanciones().get(0).getRutaImg());
		FotoCan.setIcon(icon);
		musica.setOpaque(true);                
    	musica.setBorder(null);           
    	musica.setContentAreaFilled(false); 
    	musica.setBorderPainted(false);     
    	musica.setFocusPainted(false);
    	musica.setContentAreaFilled(false);
    	
    	FotoCan.setOpaque(true);                
    	FotoCan.setBorder(null);           
    	
    	inicio.add(FotoCan);
		inicio.add(musica, FlowLayout.LEFT);
		
		
//		usuario1 = new JLabel(frmInicioSesion.txtUsu.getText());
//		usuario1.setFont(f1);
//		inicio.add(usuario1);
		
    	
		
		panelListas = new JScrollPane(listas);
		panelCanciones = new JScrollPane(listaCanciones);
		
		Botonera.setLayout( new FlowLayout( FlowLayout.CENTER ));
		panelBajo.setLayout( new BorderLayout() );
		
		inicio.setLayout( new FlowLayout( FlowLayout.CENTER ));
		panelSup.setLayout(new BorderLayout());
		
		pausar = new JButton( new ImageIcon( "src/img/pausa.png") );
		play = new JButton( new ImageIcon("src/img/Play.png") );
		stop = new JButton( new ImageIcon("src/img/stop.png") );
		anadir = new JButton (new ImageIcon ("src/img/añadir.png"));
//		anterior = new JButton( new ImageIcon("src/img/anterior.png") );
//		avance = new JButton( new ImageIcon("src/img/avance.png") );
		Fin = new JButton( new ImageIcon("src/img/Fin.png") );
//		rebobinar = new JButton( new ImageIcon("src/img/rebobinar.png") );
		shuffle = new JButton( new ImageIcon("src/img/shuffle.png") );
//		siguiente = new JButton( new ImageIcon("src/img/siguiente.png") );
		principio = new JButton( new ImageIcon("src/img/principio.png") );
		like = new JButton( new ImageIcon("src/img/like.png") );
		
		
	
//		stop = new JButton("Stop");
//		pausar = new JButton("Pausar");
//		play= new JButton("Play");
		BarraProgreso = new JProgressBar(0, 1000);
		BarraProgreso.setStringPainted(true);
		
		pausar.setOpaque(false);            
    	pausar.setContentAreaFilled(false); 
    	pausar.setBorderPainted(false);     
    	pausar.setBorder(null); 
    	pausar.setFocusPainted(false);
    	pausar.setContentAreaFilled(false);
    	pausar.setPressedIcon(new ImageIcon(  "src/img/pausaN.png" ));
    	
    	like.setOpaque(false);            
    	like.setContentAreaFilled(false); 
    	like.setBorderPainted(false);     
    	like.setBorder(null); 
    	like.setFocusPainted(false);
    	like.setContentAreaFilled(false);
    	like.setPressedIcon(new ImageIcon(  "src/img/likeN.png" ));
//    	like.setRolloverIcon(new ImageIcon(  "src/img/likeN.png" ) );

    	stop.setOpaque(false);            
    	stop.setContentAreaFilled(false); 
    	stop.setBorderPainted(false);    
    	stop.setBorder(null); 
    	stop.setFocusPainted(false);
    	stop.setContentAreaFilled(false);
    	stop.setPressedIcon(new ImageIcon(  "src/img/stopN.png" ));

    	play.setOpaque(false);            
    	play.setContentAreaFilled(false); 
    	play.setBorderPainted(false);     
    	play.setBorder(null); 
    	play.setFocusPainted(false);
    	play.setContentAreaFilled(false);
    	play.setPressedIcon(new ImageIcon(  "src/img/PlayN.png" ));
    	
    	anadir.setOpaque(false);            
    	anadir.setContentAreaFilled(false); 
    	anadir.setBorderPainted(false);     
    	anadir.setBorder(null); 
    	anadir.setFocusPainted(false);
    	anadir.setContentAreaFilled(false);
    	anadir.setPressedIcon(new ImageIcon(  "src/img/añadirN.png" ));
    	
//    	anterior.setOpaque(false);            
//    	anterior.setContentAreaFilled(false); 
//    	anterior.setBorderPainted(false);     
//    	anterior.setBorder(null); 
//    	anterior.setFocusPainted(false);
//    	anterior.setContentAreaFilled(false);
//    	anterior.setPressedIcon(new ImageIcon(  "src/img/anteriorN.png" ));
    	
//    	avance.setOpaque(false);            
//    	avance.setContentAreaFilled(false); 
//    	avance.setBorderPainted(false);     
//    	avance.setBorder(null); 
//    	avance.setFocusPainted(false);
//    	avance.setContentAreaFilled(false);
//    	avance.setPressedIcon(new ImageIcon(  "src/img/avanceN.png" ));
    	
    	Fin.setOpaque(false);            
    	Fin.setContentAreaFilled(false); 
    	Fin.setBorderPainted(false);     
    	Fin.setBorder(null); 
    	Fin.setFocusPainted(false);
    	Fin.setContentAreaFilled(false);
    	Fin.setPressedIcon(new ImageIcon(  "src/img/FinN.png" ));
    	
//    	rebobinar.setOpaque(false);            
//    	rebobinar.setContentAreaFilled(false); 
//    	rebobinar.setBorderPainted(false);     
//    	rebobinar.setBorder(null); 
//    	rebobinar.setFocusPainted(false);
//    	rebobinar.setContentAreaFilled(false);
//    	rebobinar.setPressedIcon(new ImageIcon(  "src/img/rebobinarN.png" ));
    	
    	shuffle.setOpaque(false);            
    	shuffle.setContentAreaFilled(false); 
    	shuffle.setBorderPainted(false);     
    	shuffle.setBorder(null); 
    	shuffle.setFocusPainted(false);
    	shuffle.setContentAreaFilled(false);
    	shuffle.setPressedIcon(new ImageIcon(  "src/img/shuffleN.png" ));
    	
//    	siguiente.setOpaque(false);            
//    	siguiente.setContentAreaFilled(false); 
//    	siguiente.setBorderPainted(false);     
//    	siguiente.setBorder(null); 
//    	siguiente.setFocusPainted(false);
//    	siguiente.setContentAreaFilled(false);
//    	siguiente.setPressedIcon(new ImageIcon(  "src/img/SiguienteN.png" ));
    	
    	principio.setOpaque(false);            
    	principio.setContentAreaFilled(false); 
    	principio.setBorderPainted(false);     
    	principio.setBorder(null); 
    	principio.setFocusPainted(false);
    	principio.setContentAreaFilled(false);
    	principio.setPressedIcon(new ImageIcon(  "src/img/principioN.png" ));
		
    	
		play.addActionListener(this);
		stop.addActionListener(this);
		pausar.addActionListener(this);
//		anterior.addActionListener(this);
//		siguiente.addActionListener(this);
		anadir.addActionListener(this);
		shuffle.addActionListener(this);
		principio.addActionListener(this);
//		rebobinar.addActionListener(this);
//		avance.addActionListener(this);
		Fin.addActionListener(this);
		like.addActionListener(this);
		
		
//		Botonera.add(anterior);
//		Botonera.add(siguiente);
		Botonera.add(anadir);
		Botonera.add(shuffle);
		Botonera.add(play);
		Botonera.add(pausar);
		Botonera.add(stop);
		Botonera.add(principio);
//		Botonera.add(rebobinar);
//		Botonera.add(avance);
		Botonera.add(Fin);
		Botonera.add(like);
		Botonera.add(BarraProgreso);
		
		splitpanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelListas, panelCanciones);
		splitpanel.setResizeWeight(0.5);
		
		panelSup.add(inicio, BorderLayout.CENTER);
		
		panelBajo.add(Botonera, BorderLayout.NORTH );
		panelBajo.add(BarraProgreso, BorderLayout.SOUTH );
		
		ventanita.getContentPane().add(panelSup, BorderLayout.NORTH);
		ventanita.getContentPane().add(panelBajo, BorderLayout.SOUTH);
		ventanita.getContentPane().add(splitpanel);
		
		ventanita.setVisible(true);
		Botonera.setVisible(true);
		
	   
	    
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
		listaCanciones.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) 
                {
                	System.out.println(listaCanciones.getSelectedIndex());
                	SongIndex = listaCanciones.getSelectedIndex();
                }
      
            }
        });
		
		listas.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) 
                {
                	
                	CargarCanciones(listas.getSelectedIndex());
            		
                }
            }
        });
		
	
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == play)
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
		else if(arg0.getSource() == pausar)
		{
			clipTime= audioClip.getMicrosecondPosition();
			playing = false;
			audioClip.stop();	
		}
		else if(arg0.getSource() == stop)
		{
			playing = false;
			audioClip.stop();
			clipTime = 0;
			System.out.println(clipTime);
			audioClip.close();
			
		}
		else if(arg0.getSource() == Fin)
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
			FotoCan.setIcon(icon);
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
		else if(arg0.getSource() == principio)
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
			FotoCan.setIcon(icon);
			
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
		else if(arg0.getSource() == anadir)
		{
			intListas =  new frmInternalListas();
			intListas.frmInternalListas(UsuarioActual);

        	
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


