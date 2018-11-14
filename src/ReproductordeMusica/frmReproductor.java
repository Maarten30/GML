package ReproductordeMusica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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


public class frmReproductor implements LineListener, ActionListener
{ 

	private JFrame ventanita;
	private JPanel Botonera;
	private JPanel panelLista;
	
	private JButton play;
	private JButton stop;
	private JButton seguir;
	private JButton pausar;
	private JList<String> listas= null;
	
	boolean playCompleted;
	
	private File audioFile;
	private Clip audioClip;
	private String audioFilePath = "C:/Demi Lovato - Stone Cold (Official Video).wav";
	long clipTime = 0;
//	
//	public static void main(String[] args)
//	{
//		new frmReproductor().GUI();
//	}
//	
	public void GUI()
	{
		ventanita = new JFrame("Music Player");
		ventanita.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanita.setSize(1350, 720);
		ventanita.getContentPane().setBackground(new Color(240, 240, 240));
		
		ImageIcon jungleBackground = new ImageIcon("C:/imagenSpoti.png");
		JLabel backgroundImage = new JLabel(jungleBackground);
		ventanita.add(backgroundImage);
		
		Botonera = new JPanel();
		panelLista = new JPanel();
		
	
		stop = new JButton("Stop");
		pausar = new JButton("Pausar");
		play = new JButton("Play");
		seguir = new JButton("Seguir");
		play.addActionListener(this);
		stop.addActionListener(this);
		seguir.addActionListener(this);
		
		Botonera.add(play);
		Botonera.add(pausar);
		Botonera.add(stop);
		Botonera.add(seguir);
		
		ventanita.getContentPane().add(Botonera, BorderLayout.SOUTH);
		ventanita.getContentPane().add(panelLista, BorderLayout.WEST);
		
		ventanita.setVisible(true);
		Botonera.setVisible(true);
	
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == play)
		{
			play();
		}
		else if(arg0.getSource() == pausar)
		{
			clipTime= audioClip.getMicrosecondPosition();
			audioClip.stop();	
		}
		else if(arg0.getSource() == seguir)
		{
			audioClip.setMicrosecondPosition(clipTime);
			audioClip.start();
		}
		else if(arg0.getSource() == stop)
		{
			audioClip.stop();
			audioClip.close();
			audioClip.setMicrosecondPosition(0);
		}
	}
	
	
    void play()// 
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
            System.out.println("Song started.");
             
        } else if (type == LineEvent.Type.STOP) 
        {
            playCompleted = true;
            System.out.println("Song completed.");
        }
 
    }
    
    public static void main(String[] args) 
    {
    	
        frmReproductor player = new frmReproductor();
        player.GUI();
        
    }
}


