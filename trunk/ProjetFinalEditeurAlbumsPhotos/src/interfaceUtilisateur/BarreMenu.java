package interfaceUtilisateur;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BarreMenu extends JMenuBar
{
	public BarreMenu()
	{
		JMenu mFichier = new JMenu("Fichier");
		mFichier.add(new JMenuItem("Nouveau"));
		mFichier.add(new JMenuItem("Ouvrir…"));
		mFichier.add(new JMenuItem("Enregistrer"));
		mFichier.add(new JMenuItem("Enregistrer sous…"));
		mFichier.add(new JMenuItem("Quitter"));
		this.add(mFichier);
		
		JMenu mImages = new JMenu("Images");
		mFichier.add(new JMenuItem("Importer…"));
		this.add(mFichier);
		
		
	}

}
