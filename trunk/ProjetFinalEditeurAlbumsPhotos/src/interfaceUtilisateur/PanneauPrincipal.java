package interfaceUtilisateur;

import java.awt.*;
import javax.swing.*;

public class PanneauPrincipal extends JPanel
{
	PanneauAlbum pAlbum;
	PanneauOutils outils;
	
	public PanneauPrincipal()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JLabel label1 = new JLabel("Votre album :\n");
		this.add(label1);
		
		pAlbum = new PanneauAlbum();
		this.add(pAlbum);
		
		outils = new PanneauOutils();
		this.add(outils);
		
		
		
	}
}
