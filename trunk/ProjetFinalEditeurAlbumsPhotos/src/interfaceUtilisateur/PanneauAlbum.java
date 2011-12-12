package interfaceUtilisateur;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
//import java.awt.image.ImageObserver;
//import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;

import structure.PhotoJpeg;
import structure.PhotoPng;

public class PanneauAlbum extends JPanel
{
	Image img1;
	
	public PanneauAlbum()
	{
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.setPreferredSize(new Dimension(500, 400));
		this.setVisible(true);
				
		try
		{
			img1 = ImageIO.read(new File("images/logo.gif"));
		}
		catch (IOException e) {
			System.out.println("Fichier inexistant");
			System.exit(1);
		}
		this.repaint();				
	}
	
	public void importImage()
	{
		JFileChooser chooser = new JFileChooser();
		//FileFilter filtre = ;
		int retChooser = chooser.showOpenDialog(EditeurAlbums.F);
		System.out.println("Retour : "+retChooser);
		if(retChooser == JFileChooser.APPROVE_OPTION)
		{
			System.out.println("Ouverture du fichier "+chooser.getSelectedFile().getName());
		} else {
			System.out.println("Annulation");
		}
		
		EditeurAlbums.sAlbum.rajout(new PhotoJpeg(0, 0, 160, 120, chooser.getSelectedFile().getAbsolutePath()));
		System.out.println(EditeurAlbums.sAlbum.listephoto());
		
		
		
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img1, 0, 0, 300, 400, Color.WHITE, this);
	}
}
