package interfaceUtilisateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImportListener implements ActionListener
{
	PanneauAlbum album;
	
	public ImportListener(PanneauAlbum album)
	{
		this.album = album;
	}
	
	@Override
	public void actionPerformed(ActionEvent a)
	{
		album.importImage();
	}

}

class OuvrirListener implements ActionListener
{
	PanneauAlbum pAlbum;
	
	public OuvrirListener(PanneauAlbum pAlbum)
	{
		this.pAlbum = pAlbum;
	}
	
	@Override
	public void actionPerformed(ActionEvent a)
	{
		pAlbum.ouvrirAlbum();
	}
}

class SauvegardeListener implements ActionListener
{
	PanneauAlbum pAlbum;
	
	public SauvegardeListener(PanneauAlbum pAlbum)
	{
		this.pAlbum = pAlbum;
	}
	
	@Override
	public void actionPerformed(ActionEvent a)
	{
		//pAlbum.sauvegardeImage();
		pAlbum.enregistrerAlbum();
	}

}

class ExportListener implements ActionListener
{
	PanneauAlbum pAlbum;

	public ExportListener(PanneauAlbum pAlbum)
	{
		this.pAlbum = pAlbum;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pAlbum.exporterAlbum();
	}
}