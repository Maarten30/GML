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
		
		
		
	
		stop = new JButton("Stop");
		pausar = new JButton("Pausar");
		play = new JButton("Play");
		BarraProgreso = new JProgressBar(0, 1000);
		BarraProgreso.setStringPainted(true);
		
		
		play.addActionListener(this);
		stop.addActionListener(this);
		pausar.addActionListener(this);
		
		Botonera.add(play);
		Botonera.add(pausar);
		Botonera.add(stop);
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


