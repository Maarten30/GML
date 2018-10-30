package ReproductordeMusica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class frmReproductor extends JFrame implements ActionListener
{
	 
	private JButton play;
	private JButton pausa;
	
	
	
	public static void main(String[] args)
	{
		new frmReproductor().GUI();
	}
	
	public void GUI()
	{
		JFrame ventanita = new JFrame("Music Player");
		ventanita.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//ventanita.setSize(300, 200);
		ventanita.setLayout(new GridLayout(3,1,5,5));
		
		pausa = new JButton("Pausa");
		play = new JButton("Play");
		play.addActionListener(this);
		pausa.addActionListener(this);
		
		ventanita.add(play);
		ventanita.add(pausa);
		ventanita.setVisible(true);
	
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == play)
		{
			System.out.println("Holaaaa");
		}
		else if(arg0.getSource() == pausa)
		{
			System.out.println("Ueeeeee");
		}
	}


}
