package interfaceUtilisateur;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanneauPrincipal extends JPanel
{
	PanneauAlbum pAlbum;
	PanneauOutils pOutils;
	
	public PanneauPrincipal()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JLabel label1 = new JLabel("Votre album :\n");
		this.add(label1);
		
		pAlbum = new PanneauAlbum();
		this.add(pAlbum);
		
		pOutils = new PanneauOutils();
		this.add(pOutils);
		
		
		
	}
}
