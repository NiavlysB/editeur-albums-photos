package interfaceUtilisateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class BarreMenu extends JMenuBar
{
	PanneauAlbum pAlbum;
	
	public BarreMenu(PanneauAlbum pAlbum)
	{
		this.pAlbum = pAlbum;
		
		// Fichier
		JMenu mFichier = new JMenu("Fichier");
		mFichier.setMnemonic('f');
		
		JMenuItem miNouveau = new JMenuItem("Nouveau", 'n');
		NouveauListener lNouveau = new NouveauListener(this.pAlbum);
		miNouveau.addActionListener(lNouveau);
		miNouveau.setAccelerator(KeyStroke.getKeyStroke('N', java.awt.event.InputEvent.CTRL_DOWN_MASK));
		mFichier.add(miNouveau);
		
		
		JMenuItem miOuvrir = new JMenuItem("Ouvrir…", 'o');
		OuvrirListener lOuvrir = new OuvrirListener(this.pAlbum);
		miOuvrir.addActionListener(lOuvrir);
		miOuvrir.setAccelerator(KeyStroke.getKeyStroke('O', java.awt.event.InputEvent.CTRL_DOWN_MASK));
		mFichier.add(miOuvrir);
		
		
		JMenuItem miSauvegardersous = new JMenuItem("Enregistrer sous…", 's');
		SauvegardeListener lsauvegarder = new SauvegardeListener(this.pAlbum);
		miSauvegardersous.addActionListener(lsauvegarder);
		miSauvegardersous.setAccelerator(KeyStroke.getKeyStroke('S', java.awt.event.InputEvent.CTRL_DOWN_MASK));
		mFichier.add(miSauvegardersous);	
		
		JMenuItem miExporter = new JMenuItem("Exporter…", 'e');
		ExportListener lExporter = new ExportListener(this.pAlbum);
		miExporter.addActionListener(lExporter);
		miExporter.setAccelerator(KeyStroke.getKeyStroke('E', java.awt.event.InputEvent.CTRL_DOWN_MASK));
		mFichier.add(miExporter);	
		
		JMenuItem miQuitter = new JMenuItem("Quitter", 'q');
		miQuitter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EditeurAlbums.F.dispose();
			}
		});
		miQuitter.setAccelerator(KeyStroke.getKeyStroke('Q', java.awt.event.InputEvent.CTRL_DOWN_MASK));
		mFichier.add(miQuitter);
		
		this.add(mFichier);
		
		// Images
		JMenu mImages = new JMenu("Images");
		mImages.setMnemonic('i');
		JMenuItem miImporter = new JMenuItem("Importer…", 'i');
		
		ImportListener lImporter = new ImportListener(this.pAlbum);
		miImporter.addActionListener(lImporter);
		mImages.add(miImporter);
		this.add(mImages);
		
		
		
		
	}

}
