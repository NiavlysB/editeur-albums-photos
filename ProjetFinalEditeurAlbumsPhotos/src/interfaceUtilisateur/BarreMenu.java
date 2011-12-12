package interfaceUtilisateur;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BarreMenu extends JMenuBar
{
	ImportListener lImporter;
	PanneauAlbum album;
	
	public BarreMenu(PanneauAlbum album)
	{
		this.album = album;
		
		JMenu mFichier = new JMenu("Fichier");
		mFichier.add(new JMenuItem("Nouveau"));
		mFichier.add(new JMenuItem("Ouvrir…"));
		mFichier.add(new JMenuItem("Enregistrer"));
		mFichier.add(new JMenuItem("Enregistrer sous…"));
		mFichier.add(new JMenuItem("Quitter"));
		this.add(mFichier);
		
		JMenu mImages = new JMenu("Images");
		JMenuItem miImporter = new JMenuItem("Importer…");
		
		lImporter = new ImportListener(this.album);
		miImporter.addActionListener(lImporter);
		mImages.add(miImporter);
		this.add(mImages);
		
		
	}

}
