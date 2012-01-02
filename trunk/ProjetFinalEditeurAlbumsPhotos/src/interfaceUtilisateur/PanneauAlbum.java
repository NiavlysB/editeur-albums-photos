package interfaceUtilisateur;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
//import java.awt.image.ImageObserver;
//import java.util.Observer;
import structure.*;
import java.util.*;
import java.lang.Object;

import javax.imageio.ImageIO;
import javax.swing.*;


import structure.PhotoJpeg;
import structure.PhotoPng;

public class PanneauAlbum extends JPanel
{
	ArrayList<Photo> listePhotos;
	Rectangle selection;
	Photo currentPhoto;
	int indexCurrentPhoto;
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
				currentPhoto = EditeurAlbums.sAlbum.photos.get(EditeurAlbums.sAlbum.emplacementphoto2(e.getX(),e.getY()));

			}	
			public void mousePressed(MouseEvent e) {
				currentPhoto = EditeurAlbums.sAlbum.photos.get(EditeurAlbums.sAlbum.emplacementphoto2(e.getX(),e.getY()));
				if (currentPhoto!=null){//dessiner carre collore
					selection.setRect(currentPhoto.getposx()-1, currentPhoto.getposy()-1, currentPhoto.gettaillex()+1, currentPhoto.gettailley()+1);
					repaint();
					System.out.println(currentPhoto);
				}
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
						selection.setRect(currentPhoto.getposx()-1, currentPhoto.getposy()-1, currentPhoto.gettaillex()+1, currentPhoto.gettailley()+1);
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
	public void sauvegardeImage()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		File fichier = null;
		int retChooser = chooser.showSaveDialog(EditeurAlbums.F);
		if(retChooser == JFileChooser.APPROVE_OPTION)
		{
			System.out.println("Ouverture du fichier "+chooser.getSelectedFile().getName());
			fichier = chooser.getSelectedFile();

			fichier.mkdir();
			int i=0;
			for(Photo p: EditeurAlbums.sAlbum.photos){
				i++;
				try {
					ImageIO.write((RenderedImage) p, "jpg",new File(fichier.getAbsolutePath() + System.getProperty("file.separator")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} 
		else {
			System.out.println("Annulation");
		}

	}
	
	
	public void rotationimage(int rot){
		if (currentPhoto != null){
			currentPhoto.setrotation(rot);
			repaint();
		}
		else System.out.println("Pas de photo sélectionnée");
	}
	
	public void redimensionnement(float scale){
		// TODO: passer redimensionnement et rotation dans Photo (appeler ces méthodes ici)
		if(currentPhoto !=null){
			double w=currentPhoto.bimg.getWidth();
			double h=currentPhoto.bimg.getHeight();
			//currentPhoto.settaillex((int) (w+(w/100)*pourcentage));
			//currentPhoto.settailley((int) (h+(h/100)*pourcentage));
			//selection.setRect(currentPhoto.getposx()-1, currentPhoto.getposy()-1, currentPhoto.gettaillex()+1, currentPhoto.gettailley()+1);
			currentPhoto.setScale(scale);
			repaint();
		}
		else System.out.println("Pas de photo sélectionnée");
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g.create();
		for(Photo p: EditeurAlbums.sAlbum.photos)
		{
			assert(p != null);
			System.out.println("repaint "+p.gettaillex()+","+p.gettailley());
			//g2d.drawImage(p.bimg, p.getposx(), p.getposy(), p.gettaillex(), p.gettailley(), null);
			
		    System.out.println("posotion x : "+p.getposx()+" position y :"+ p.getposy()+" taille x "+ p.gettaillex()+" taille y "+ p.gettailley()+" x "+(p.getposx()+p.gettaillex()/2)+" y "+(p.getposy()+p.gettailley())/2);
		    //g2d.drawImage(p.bimg, p.getposx(), p.getposy(), p.gettaillex(), p.gettailley(), null);
		    //g2d.draw(selection);
		    double rot = p.getrotation()*Math.PI/180;
		    AffineTransform transform = new AffineTransform();
		    transform.setToTranslation(p.getposx(), p.getposy());
		    //transform.scale(p.gettaillex()/p.bimg.getWidth(), p.gettailley()/p.bimg.getHeight());
		    transform.scale(p.getScale(), p.getScale());
		    transform.rotate(rot);
		    g2d.drawImage(p.bimg, transform, null);
		
		}
	}
}
