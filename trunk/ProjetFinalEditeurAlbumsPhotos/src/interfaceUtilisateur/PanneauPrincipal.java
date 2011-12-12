package interfaceUtilisateur;

import java.awt.*;
import javax.swing.*;

public class PanneauPrincipal extends JPanel
{
	PanneauAlbum album;
	PanneauOutils outils;
	
	public PanneauPrincipal()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JLabel label1 = new JLabel("Votre album :\n");
		this.add(label1);
		
		album = new PanneauAlbum();
		this.add(album);
		
		outils = new PanneauOutils();
		this.add(outils);
		
		
		
	}
}
