package interfaceUtilisateur;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
//import java.awt.image.ImageObserver;
//import java.util.Observer;
import structure.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import structure.PhotoJpeg;
import structure.PhotoPng;

public class PanneauAlbum extends JPanel
{
	ArrayList<Photo> listePhotos;
	
	public PanneauAlbum()
	{
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.setPreferredSize(new Dimension(500, 400));
		this.setVisible(true);
		
		this.addMouseListener(new MouseAdapter(){
			Photo photo;
			public void mouseClicked(MouseEvent e) {
				photo = EditeurAlbums.sAlbum.emplacementphoto(e.getX(),e.getY());
				if (photo!=null){//dessiner carre collore
					System.out.println(photo);
				}
			}	
			public void mousePressed(MouseEvent e) {
				photo = EditeurAlbums.sAlbum.emplacementphoto(e.getX(),e.getY());				
			}
			
			public void mouseReleased(MouseEvent e) {
				if(photo != null) {
					if(e.getX() != photo.getposx()) {
						photo.setposx(e.getX());
						photo.setposy(e.getY());
						repaint();
					}
				}
			
			}
		});
		
		/*		
		try
		{
			img1 = ImageIO.read(new File("images/logo.gif"));
		}
		catch (IOException e) {
			System.out.println("Fichier inexistant");
			System.exit(1);
		}*/
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
	
	public void drawImage(Photo p, int x, int y)
	{
		
		this.repaint();
	}
	
	public void drawImage(Photo p)
	{
		drawImage(p, 0, 0);
	}
	
	public void paintComponent(Graphics g) {
		//g.drawImage(img1, 0, 0, 300, 400, Color.WHITE, this);
		for(Photo p: listePhotos)
		{
			try
			{
				g.drawImage(ImageIO.read(new File(p.getchemin())), p.getposx(), p.getposy(), p.gettaillex(), p.gettailley(), null);
			} catch (IOException e)
			{
				System.out.println("Fichier "+p.getchemin()+" non trouvé");
				e.printStackTrace();
			}
		}
	}
}
