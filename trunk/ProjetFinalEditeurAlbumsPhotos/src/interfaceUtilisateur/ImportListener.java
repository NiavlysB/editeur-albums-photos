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
