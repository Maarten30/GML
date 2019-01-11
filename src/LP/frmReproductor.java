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
import javax.swing.JSplitPane;

import LN.clsCancion;




/**
 * Clase que sirve para crear la pantalla principal de la aplicacion
 * @author Gabriela garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class frmReproductor implements LineListener, ActionListener
{ 

	private JFrame ventanita;
	private JPanel panelBajo;
	private JPanel Botonera;
	private JSplitPane splitpanel;
	private JScrollPane panelListas;
	private JScrollPane panelCanciones;

	
	private JButton play;
	private JButton stop;
	private JButton pausar;
	private JButton anterior;
	private JButton avance;
	private JButton Fin;
	private JButton principio;
	private JButton rebobinar;
	private JButton shuffle;
	private JButton siguiente;
	private JList<String> listas= null;
	private JList<String> listaCanciones= null;
	private JProgressBar BarraProgreso;
	
	boolean playCompleted;
	boolean playing;
	
	private LN.clsUsuario usuario;
	
	private File audioFile;
	public Clip audioClip;
	//private String audioFilePath = "C:/Demi Lovato - Stone Cold (Official Video).wav";
	private String audioFilePath = "test/res/Demi Lovato - Stone Cold (Official Video).wav";
	long clipTime = 0;

	/**
	 * Metodo en el que se crean todos los elementos que van a aparecer en la pantalla
	 */
	public void GUI()//ArrayList<clsCancion> canciones)
	{
		ventanita = new JFrame("Music Player");
		ventanita.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventanita.setSize(1350, 720);
		//ventanita.pack();
		ventanita.getContentPane().setBackground(new Color(240, 240, 240));
		
		ImageIcon jungleBackground = new ImageIcon("C:/imagenSpoti.png");
		JLabel backgroundImage = new JLabel(jungleBackground);
		ventanita.add(backgroundImage);
		
		panelBajo = new JPanel();
		Botonera = new JPanel();
		
		String nombre = "Maarten";
		String nombre2 = "4";
		String nombre3 = "Lucia";
		String nombre4 = "Gab";
		
		DefaultListModel<String> model = new DefaultListModel<>();
		

		model.addElement(nombre);
		model.addElement(nombre2);
		model.addElement(nombre3);
		model.addElement(nombre4);
		
		listas = new JList<>(model);
		
		DefaultListModel<String> model2 = new DefaultListModel<>();
		

		model2.addElement(nombre);
		model2.addElement(nombre2);
		model2.addElement(nombre3);
		model2.addElement(nombre4);
		
		listaCanciones = new JList<>(model2);
		
		Font f1 = new Font("Century Gothic",Font.BOLD,18);
		
		listas.setFont(f1);
		listaCanciones.setFont(f1);
		
		
		panelListas = new JScrollPane(listas);
		panelCanciones = new JScrollPane(listaCanciones);
		
		Botonera.setLayout( new FlowLayout( FlowLayout.CENTER ));
		panelBajo.setLayout( new BorderLayout() );
		
		pausar = new JButton( new ImageIcon( "src/img/pausa.png") );
		play = new JButton( new ImageIcon("src/img/Play.png") );
		stop = new JButton( new ImageIcon("src/img/stop.png") );
		anterior = new JButton( new ImageIcon("src/img/anterior.png") );
		avance = new JButton( new ImageIcon("src/img/avance.png") );
		Fin = new JButton( new ImageIcon("src/img/Fin.png") );
		rebobinar = new JButton( new ImageIcon("src/img/rebobinar.png") );
		shuffle = new JButton( new ImageIcon("src/img/shuffle.png") );
		siguiente = new JButton( new ImageIcon("src/img/siguiente.png") );
		principio = new JButton( new ImageIcon("src/img/principio.png") );
		
		
	
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
    	
    	anterior.setOpaque(false);            
    	anterior.setContentAreaFilled(false); 
    	anterior.setBorderPainted(false);     
    	anterior.setBorder(null); 
    	anterior.setFocusPainted(false);
    	anterior.setContentAreaFilled(false);
    	anterior.setPressedIcon(new ImageIcon(  "src/img/anteriorN.png" ));
    	
    	avance.setOpaque(false);            
    	avance.setContentAreaFilled(false); 
    	avance.setBorderPainted(false);     
    	avance.setBorder(null); 
    	avance.setFocusPainted(false);
    	avance.setContentAreaFilled(false);
    	avance.setPressedIcon(new ImageIcon(  "src/img/avanceN.png" ));
    	
    	Fin.setOpaque(false);            
    	Fin.setContentAreaFilled(false); 
    	Fin.setBorderPainted(false);     
    	Fin.setBorder(null); 
    	Fin.setFocusPainted(false);
    	Fin.setContentAreaFilled(false);
    	Fin.setPressedIcon(new ImageIcon(  "src/img/FinN.png" ));
    	
    	rebobinar.setOpaque(false);            
    	rebobinar.setContentAreaFilled(false); 
    	rebobinar.setBorderPainted(false);     
    	rebobinar.setBorder(null); 
    	rebobinar.setFocusPainted(false);
    	rebobinar.setContentAreaFilled(false);
    	rebobinar.setPressedIcon(new ImageIcon(  "src/img/rebobinarN.png" ));
    	
    	shuffle.setOpaque(false);            
    	shuffle.setContentAreaFilled(false); 
    	shuffle.setBorderPainted(false);     
    	shuffle.setBorder(null); 
    	shuffle.setFocusPainted(false);
    	shuffle.setContentAreaFilled(false);
    	shuffle.setPressedIcon(new ImageIcon(  "src/img/shuffleN.png" ));
    	
    	siguiente.setOpaque(false);            
    	siguiente.setContentAreaFilled(false); 
    	siguiente.setBorderPainted(false);     
    	siguiente.setBorder(null); 
    	siguiente.setFocusPainted(false);
    	siguiente.setContentAreaFilled(false);
    	siguiente.setPressedIcon(new ImageIcon(  "src/img/SiguienteN.png" ));
    	
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
		anterior.addActionListener(this);
		siguiente.addActionListener(this);
		shuffle.addActionListener(this);
		principio.addActionListener(this);
		rebobinar.addActionListener(this);
		avance.addActionListener(this);
		Fin.addActionListener(this);
		
		
		Botonera.add(anterior);
		Botonera.add(siguiente);
		Botonera.add(shuffle);
		Botonera.add(play);
		Botonera.add(pausar);
		Botonera.add(stop);
		Botonera.add(principio);
		Botonera.add(rebobinar);
		Botonera.add(avance);
		Botonera.add(Fin);
		Botonera.add(BarraProgreso);
		
		splitpanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelListas, panelCanciones);
		splitpanel.setResizeWeight(0.5);
		
		panelBajo.add(Botonera, BorderLayout.NORTH );
		panelBajo.add(BarraProgreso, BorderLayout.SOUTH );
		
		ventanita.getContentPane().add(panelBajo, BorderLayout.SOUTH);
		ventanita.getContentPane().add(splitpanel);
		
		ventanita.setVisible(true);
		Botonera.setVisible(true);
		
	   
	    
		BarraProgreso.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (audioClip.isOpen()) {
//					// Seek en el vídeo
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
	
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == play)
		{
			play();
			playing = true;
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
	}
	
	
	public void AvanceBP()
	{
		BarraProgreso.setValue( (int) (1000.0 * audioClip.getLongFramePosition() / audioClip.getFrameLength()) );
		BarraProgreso.repaint();
		
		//lMensaje2.setText( formatoHora.format( new Date(mediaPlayer.getTime()-3600000L) ) );
	}
	
	
   
	
	public void play()//String audioFilePath
    {
    	
        audioFile = new File(audioFilePath);
 
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
	
	public void CargarPath()
	{
		
		//ArrayList<PlayList> listas = usuario.getListas();
		
		
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
    
    public static void main(String[] args) 
    {
    	
        frmReproductor player = new frmReproductor();
        player.GUI();
        
    }
}


