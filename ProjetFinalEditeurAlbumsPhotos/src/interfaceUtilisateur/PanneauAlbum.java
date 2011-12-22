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
	Rectangle selection;
	Photo currentPhoto;
	int offsetX;
	int offsetY;
	
	public PanneauAlbum()
	{
		this.setBackground(Color.WHITE);
		repaint();
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.setPreferredSize(new Dimension(500, 400));
		this.setVisible(true);

		selection = new Rectangle();
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				currentPhoto = EditeurAlbums.sAlbum.emplacementphoto(e.getX(),e.getY());
				if (currentPhoto!=null){//dessiner carre collore
					selection.setRect(currentPhoto.getposx()-1, currentPhoto.getposy()-1, currentPhoto.gettaillex()+1, currentPhoto.gettailley()+1);
					repaint();
					System.out.println(currentPhoto);
				}
			}	
			public void mousePressed(MouseEvent e) {
				currentPhoto = EditeurAlbums.sAlbum.emplacementphoto(e.getX(),e.getY());
				offsetX = e.getX()-currentPhoto.getposx();
				offsetY = e.getY()-currentPhoto.getposy();
			}
			
			public void mouseReleased(MouseEvent e) {
				/*if(e.getButton()==MouseEvent.BUTTON3)
				{
					System.out.println("3");
					
				}*/

			}
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e){
				if(currentPhoto != null) {
					if(e.getX() != currentPhoto.getposx()) {
						currentPhoto.deplace(e.getX()-offsetX, e.getY()-offsetY);
						repaint();
					}
				}		
			}
		});
		
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
		
		EditeurAlbums.sAlbum.rajout(new PhotoJpeg(0, 0, chooser.getSelectedFile().getAbsolutePath()));
		System.out.println(EditeurAlbums.sAlbum.listephoto());
		
		this.repaint();
		
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(!selection.isEmpty())
		{
			g.drawRect(selection.x, selection.y, selection.width, selection.height);
		}
		for(Photo p: EditeurAlbums.sAlbum.photos)
		{
			assert(p != null);
			System.out.println("repaint "+p.gettaillex()+","+p.gettailley());
			g.drawImage(p, p.getposx(), p.getposy(), p.gettaillex(), p.gettailley(), null);
		}
	}
}
