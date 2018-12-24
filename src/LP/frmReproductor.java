package ReproductordeMusica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;



public class frmReproductor implements LineListener, ActionListener
{ 

	private JFrame ventanita;
	private JPanel panelBajo;
	private JPanel Botonera;
	private JPanel panelLista;
	
	private JButton play;
	private JButton stop;
	private JButton pausar;
	private JList<String> listas= null;
	private JProgressBar BarraProgreso;
	
	boolean playCompleted;
	boolean playing;
	
	private File audioFile;
	public Clip audioClip;
	private String audioFilePath = "C:/Demi Lovato - Stone Cold (Official Video).wav";
	long clipTime = 0;


	public void GUI()
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
		panelLista = new JPanel();
		listas = new JList();
		
		Botonera.setLayout( new FlowLayout( FlowLayout.CENTER ));
		panelBajo.setLayout( new BorderLayout() );
		
		
		
	
		stop = new JButton("Stop");
		pausar = new JButton("Pausar");
		play = new JButton("Play");
		BarraProgreso = new JProgressBar(0, 1000);
		
		
		play.addActionListener(this);
		stop.addActionListener(this);
		pausar.addActionListener(this);
		
		Botonera.add(play);
		Botonera.add(pausar);
		Botonera.add(stop);
		Botonera.add(BarraProgreso);
		
		panelLista.add(listas);
		
		panelBajo.add(Botonera, BorderLayout.NORTH );
		panelBajo.add(BarraProgreso, BorderLayout.SOUTH );
		
		ventanita.getContentPane().add(panelBajo, BorderLayout.SOUTH);
		ventanita.getContentPane().add(panelLista, BorderLayout.WEST);
		
		ventanita.setVisible(true);
		Botonera.setVisible(true);
	
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == play)
		{
			play();
			playing = true;
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
			audioClip.close();
			audioClip.setMicrosecondPosition(0);
		}
	}
	
	
	
	public void mouseClicked(MouseEvent e) {
		if (audioClip.isOpen()) 
		{
			// Seek en el vídeo
			long porcentajeSalto = (long)e.getX() / BarraProgreso.getWidth();
			audioClip.setMicrosecondPosition( porcentajeSalto );
		    AvanceBP();
			// Otra manera de hacerlo con los milisegundos:
			// long milisegsSalto = mediaPlayer.getLength();
			// milisegsSalto = Math.round( milisegsSalto * porcentajeSalto );
			// mediaPlayer.setTime( milisegsSalto );
		}
	}
	
	public void AvanceBP()
	{
		BarraProgreso.setValue( (int) (10000.0 * audioClip.getLongFramePosition() / audioClip.getFrameLength()) );
		BarraProgreso.repaint();
		
		//lMensaje2.setText( formatoHora.format( new Date(mediaPlayer.getTime()-3600000L) ) );
	}
	
	
   
	
	void play()//String audioFilePath
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
             
//            while (!playCompleted) {
//                // wait for the playback completes
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }
            
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
 
    }
    
    public static void main(String[] args) 
    {
    	
        frmReproductor player = new frmReproductor();
        player.GUI();
        
    }
}


