package interfaceUtilisateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BarreMenu extends JMenuBar
{
	ImportListener lImporter;
	sauvegardelistener lsauvegarder;
	PanneauAlbum album;
	
	public BarreMenu(PanneauAlbum album)
	{
		this.album = album;
		
		JMenu mFichier = new JMenu("Fichier");
		mFichier.add(new JMenuItem("Nouveau"));
		mFichier.add(new JMenuItem("Ouvrir…"));
		mFichier.add(new JMenuItem("Enregistrer"));

		JMenuItem misauvegardersous = new JMenuItem("Enregistrer sous…");
		lsauvegarder = new sauvegardelistener(this.album);
		misauvegardersous.addActionListener(lsauvegarder);
		mFichier.add(misauvegardersous);	
		
		JMenuItem miQuitter = new JMenuItem("Quitter");
		miQuitter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EditeurAlbums.F.dispose();
			}
		});
		mFichier.add(miQuitter);
		
		this.add(mFichier);
		
		
		
		
		JMenu mImages = new JMenu("Images");
		JMenuItem miImporter = new JMenuItem("Importer…");
		
		lImporter = new ImportListener(this.album);
		miImporter.addActionListener(lImporter);
		mImages.add(miImporter);
		this.add(mImages);
		
		
		
		
	}

}
