package interfaceUtilisateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BarreMenu extends JMenuBar
{
	ImportListener lImporter;
	SauvegardeListener lsauvegarder;
	PanneauAlbum pAlbum;
	
	public BarreMenu(PanneauAlbum pAlbum)
	{
		this.pAlbum = pAlbum;
		
		JMenu mFichier = new JMenu("Fichier");
		
		JMenuItem miNouveau = new JMenuItem("Nouveau");
		NouveauListener lNouveau = new NouveauListener(this.pAlbum);
		miNouveau.addActionListener(lNouveau);
		mFichier.add(miNouveau);
		
		
		JMenuItem miOuvrir = new JMenuItem("Ouvrir…");
		OuvrirListener lOuvrir = new OuvrirListener(this.pAlbum);
		miOuvrir.addActionListener(lOuvrir);
		mFichier.add(miOuvrir);	
		
		JMenuItem misauvegardersous = new JMenuItem("Enregistrer sous…");
		lsauvegarder = new SauvegardeListener(this.pAlbum);
		misauvegardersous.addActionListener(lsauvegarder);
		mFichier.add(misauvegardersous);	
		
		JMenuItem miExporter = new JMenuItem("Exporter…");
		ExportListener lExporter = new ExportListener(this.pAlbum);
		miExporter.addActionListener(lExporter);
		mFichier.add(miExporter);	
		
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
		
		lImporter = new ImportListener(this.pAlbum);
		miImporter.addActionListener(lImporter);
		mImages.add(miImporter);
		this.add(mImages);
		
		
		
		
	}

}
