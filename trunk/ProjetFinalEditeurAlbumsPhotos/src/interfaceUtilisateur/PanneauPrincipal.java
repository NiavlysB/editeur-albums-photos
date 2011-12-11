package interfaceUtilisateur;

import java.awt.*;
import javax.swing.*;

public class PanneauPrincipal extends JPanel
{
	PanneauAlbum album;
	JPanel outils;
	
	public PanneauPrincipal()
	{
		//this.setLayout(FlowLayout);
		JLabel label1 = new JLabel("Votre album :\n");
		this.add(label1);
		
		album = new PanneauAlbum();
		this.add(album);
		
		
	}
}
