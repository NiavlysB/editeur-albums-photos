package interfaceUtilisateur;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
//import java.awt.image.ImageObserver;
//import java.util.Observer;
import structure.*;
import java.util.*;
import java.lang.Object;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import exceptions.ImageNonExistanteException;


import structure.Album;
import structure.Photo;
import structure.PhotoJpeg;
import structure.PhotoPng;

public class PanneauAlbum extends JPanel
{
	ArrayList<Photo> listePhotos;
	Polygon selection;
	int angleSelection;
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

		selection = new Polygon();
		
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				currentPhoto = EditeurAlbums.sAlbum.emplacementphoto(e.getX(),e.getY());

			}	
			public void mousePressed(MouseEvent e) {
				currentPhoto = EditeurAlbums.sAlbum.emplacementphoto(e.getX(),e.getY());
				if (currentPhoto!=null){//dessiner carre collore
					refreshSelection();
					angleSelection = currentPhoto.getrotation();
					interfaceUtilisateur.Slider1Listener.actualisationslider(currentPhoto);
					interfaceUtilisateur.Spinner1Listener.actualisationspinner(currentPhoto);
					repaint();
					offsetX = e.getX()-currentPhoto.getposx();
					offsetY = e.getY()-currentPhoto.getposy();
					System.out.println(currentPhoto);
				}
			}
			
			public void mouseReleased(MouseEvent e) {
				
			}
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e){
				if(currentPhoto != null) {
					if(e.getX() != currentPhoto.getposx()) {
						currentPhoto.deplace(e.getX()-offsetX, e.getY()-offsetY);
						refreshSelection();
						repaint();
						
					}
				}		
			}
		});
		
		this.repaint();				
	}
	
	private double angleSelectionRad()
	{
		return (angleSelection*Math.PI)/180;
	}
	
	public void refreshSelection()
	{
		//selection.setRect(currentPhoto.getposx()-1, currentPhoto.getposy()-1,
		//		(currentPhoto.gettaillex()+1)*currentPhoto.getScale(), (currentPhoto.gettailley()+1)*currentPhoto.getScale());
		
		// Photo : coordonnées à décaler 
		selection.reset();
		selection.addPoint(currentPhoto.getposx(), currentPhoto.getposy()); // point d’origine de la photo
		int x1 = (int)(currentPhoto.getposx()+Math.cos(angleSelectionRad())*currentPhoto.getWidth());
		int x2 = (int)(currentPhoto.getposx()-Math.sin(angleSelectionRad())*currentPhoto.getHeight());
		int y1 = (int)(currentPhoto.getposy()+Math.sin(angleSelectionRad())*currentPhoto.getWidth());
		int y2 = (int)(currentPhoto.getposy()+Math.cos(angleSelectionRad())*currentPhoto.getHeight());
		selection.addPoint(x1, y1);
		selection.addPoint(x1-(currentPhoto.getposx()-x2), y1-(currentPhoto.getposy()-y2));
		selection.addPoint(x2, y2);
	}
	
	public void NouvelAlbum(){
		Object[] options = {"Oui","No",};
		JOptionPane frame = new JOptionPane();
		int n = JOptionPane.showOptionDialog(frame,"voulez vous sauvegarder votre projet",
		    "sauvegarde?", JOptionPane.YES_NO_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    null,
		    options,
		    options[0]);

		if(n==0){
			enregistrerAlbum();
		}
		for(Photo p: EditeurAlbums.sAlbum.photos)
			try {
				EditeurAlbums.sAlbum.enleve(p.getchemin());
			} catch (ImageNonExistanteException e) {
				e.printStackTrace();
				System.out.println("erreur de suppression");
			}
		selection=null;
		currentPhoto=null;
		repaint();
	}
	
	public void importImage()
	{
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Images JPG, PNG et GIF", "jpg", "jpeg", "png", "gif");
		chooser.setFileFilter(filter);
		int retChooser = chooser.showOpenDialog(EditeurAlbums.F);
		System.out.println("Retour : "+retChooser);
		if(retChooser == JFileChooser.APPROVE_OPTION)
		{
			System.out.println("Ouverture du fichier "+chooser.getSelectedFile().getName());
		} else {
			System.out.println("Annulation");
		}
		
		EditeurAlbums.sAlbum.rajout(new Photo(0, 0, chooser.getSelectedFile().getAbsolutePath()));
		System.out.println(EditeurAlbums.sAlbum.listephoto());
		
		this.repaint();
	}
	
	public void exporterAlbum()
	{
		JFileChooser exporter = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Images JPG", "jpg", "jpeg");
		exporter.setFileFilter(filter);
		int retExporter = exporter.showSaveDialog(EditeurAlbums.F);
		if(retExporter == JFileChooser.APPROVE_OPTION)
		{
			System.out.println("Exportation de l’album vers "+exporter.getSelectedFile().getAbsolutePath());
			int w = this.getWidth();
			int h = this.getHeight();
			BufferedImage bimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bimg.createGraphics();
			this.paint(g);
			
			/* J’abandonne la détection de l’extension, on a qu’à imposer le JPG pour l’exportation
			System.out.println("getName : "+exporter.getSelectedFile().getName());
			System.out.println("split :   "+exporter.getSelectedFile().getName().split("."));
			String[] fileNameSplit = exporter.getSelectedFile().getName().split(".");
			String fileExtension;
			if(fileNameSplit.length == 0)
			{
				System.out.println("fileNameSplit.length == 0");
				fileExtension = "jpg";
			} else {
				fileExtension = fileNameSplit[fileNameSplit.length-1];
				System.out.println("Extension is now "+fileExtension);
			}
			if(!fileExtension.matches("^jpg|jpeg|png|gif$"))
			{
				System.out.println("Unknown extension « "+fileExtension+" », defaulting to jpg");
				fileExtension = "jpg";
			}
			*/
			String fileExtension = "jpg";
			File fileExport = new File(exporter.getSelectedFile().getAbsolutePath()+"."+fileExtension);
			
			try {
				ImageIO.write(bimg, fileExtension, fileExport);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void ouvrirAlbum() {
		JFileChooser opener = new JFileChooser();
		opener.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Albums photo", "alb");
		opener.setFileFilter(filter);
		int retOpener = opener.showOpenDialog(EditeurAlbums.F);
		if(retOpener == JFileChooser.APPROVE_OPTION)
		{
			System.out.println("Ouverture de l’album "+opener.getSelectedFile().getName());
			try
			{
				FileInputStream file = new FileInputStream(opener.getSelectedFile());
				ObjectInputStream ois = new ObjectInputStream(file);
				EditeurAlbums.sAlbum = (Album) ois.readObject();
				for(Photo p: EditeurAlbums.sAlbum.photos)
				{
					p.ByteFromBuf();
					repaint();
				}
				EditeurAlbums.F.P.pOutils.setListPhotos(EditeurAlbums.sAlbum.photos);
			}
			catch (FileNotFoundException e){
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void enregistrerAlbum() {
		JFileChooser saver = new JFileChooser();
		saver.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Albums photo", "alb");
		saver.setFileFilter(filter);
		int retOpener = saver.showSaveDialog(EditeurAlbums.F);
		if(retOpener == JFileChooser.APPROVE_OPTION)
		{
			System.out.println("Enregistrement de l’album vers "+saver.getSelectedFile().getAbsolutePath());
			EditeurAlbums.sAlbum.serialize(saver.getSelectedFile());
		}
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
					e.printStackTrace();
				}
			}
		} 
		else {
			System.out.println("Annulation");
		}
	}
	
	
	public void rotationimage(int rot){
		// TODO: voir ci-dessous
		if (currentPhoto != null){
			currentPhoto.setrotation(rot);
			refreshSelection();
			repaint();
		}
		else System.out.println("Pas de photo sélectionnée");
	}
	
	public void redimensionnement(float scale){
		// TODO: passer redimensionnement et rotation dans Photo (appeler ces méthodes ici)
		if(currentPhoto !=null){
			double w=currentPhoto.bimg.getWidth();
			double h=currentPhoto.bimg.getHeight();
			currentPhoto.redimensionnement((int) w, (int) h);
			refreshSelection();
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
			System.out.println("position x : "+p.getposx()+" position y :"+ p.getposy()+" taille x "+ p.gettaillex()+" taille y "+ p.gettailley()+" x "+(p.getposx()+p.gettaillex()/2)+" y "+(p.getposy()+p.gettailley())/2);
		    //g2d.draw(selection);
		    g2d.drawPolygon(selection);
		    
		    AffineTransform transform = new AffineTransform();
		    transform.setToTranslation(p.getposx(), p.getposy());
		    //transform.scale(p.gettaillex()/p.bimg.getWidth(), p.gettailley()/p.bimg.getHeight());
		    transform.scale(p.getScale(), p.getScale());
		    double rot = p.getrotation()*Math.PI/180;
		    transform.rotate(rot, p.gettaillex()/2,p.gettailley()/2 );
		    g2d.drawImage(p.bimg, transform, null);
		
		}

	}

}
