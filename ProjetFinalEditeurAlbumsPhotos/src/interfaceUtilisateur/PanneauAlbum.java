package interfaceUtilisateur;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import structure.Album;
import structure.Photo;
import exceptions.ImageNonExistanteException;

@SuppressWarnings("serial")
public class PanneauAlbum extends JPanel
{
	ArrayList<Photo> listePhotos;
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
		
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				currentPhoto = EditeurAlbums.sAlbum.emplacementphoto(e.getX(),e.getY());

			}	
			public void mousePressed(MouseEvent e) {
				currentPhoto = EditeurAlbums.sAlbum.emplacementphoto(e.getX(),e.getY());
				if (currentPhoto!=null){
					Slider1Listener.actualisationslider(currentPhoto);
					Spinner1Listener.actualisationspinner(currentPhoto);
					repaint();
					offsetX = e.getX()-currentPhoto.getposx();
					offsetY = e.getY()-currentPhoto.getposy();
					//System.out.println(currentPhoto);
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
						repaint();
					}
				}		
			}
		});
		repaint();
	}
	
	public void upPhoto() throws ImageNonExistanteException
	{
		if(currentPhoto != null)
			EditeurAlbums.sAlbum.upPhoto(currentPhoto);
		repaint();
	}	
	public void downPhoto() throws ImageNonExistanteException
	{
		if(currentPhoto != null)
			EditeurAlbums.sAlbum.downPhoto(currentPhoto);
		repaint();
	}	
	public void forePhoto() throws ImageNonExistanteException
	{
		if(currentPhoto != null)
			EditeurAlbums.sAlbum.forePhoto(currentPhoto);
		repaint();
	}	
	public void backPhoto() throws ImageNonExistanteException
	{
		if(currentPhoto != null)
			EditeurAlbums.sAlbum.backPhoto(currentPhoto);
		repaint();
	}
	
	public void NouvelAlbum(){
		JOptionPane frame = new JOptionPane();
		int n = JOptionPane.showOptionDialog(frame,"Voulez-vous sauvegarder votre projet ?",
		    "Nouvel album", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		if(n != JOptionPane.CANCEL_OPTION)
		{
			if(n == JOptionPane.YES_OPTION){
				enregistrerAlbum();
			}
			
			/*
			for(Photo p: EditeurAlbums.sAlbum.photos)
				try {
					EditeurAlbums.sAlbum.enleve(p.getchemin());
				} catch (ImageNonExistanteException e) {
					e.printStackTrace();
					System.out.println("erreur de suppression");
				}
			*/
			EditeurAlbums.sAlbum = new Album();
			currentPhoto=null;
			repaint();
		}
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
			EditeurAlbums.sAlbum.rajout(new Photo(0, 0, chooser.getSelectedFile().getAbsolutePath()));
		} else {
			System.out.println("Annulation");
		}
		
		System.out.println(EditeurAlbums.sAlbum.listephoto());
		
		this.repaint();
	}
	
	public void exporterAlbum()
	{
		currentPhoto = null;
		repaint();
		
		JFileChooser exporter = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Images JPG", "jpg", "jpeg");
		exporter.setFileFilter(filter);
		int retExporter = exporter.showDialog(EditeurAlbums.F, "Exporter");
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
			for(Photo p: EditeurAlbums.sAlbum.photos){
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
		if (currentPhoto != null){
			currentPhoto.setrotation(rot);
			repaint();
		}
		else System.out.println("Pas de photo sélectionnée");
	}
	
	public void redimensionnement(float scale){
		if(currentPhoto !=null){
			currentPhoto.setScale(scale);
			repaint();
		}
		else System.out.println("Pas de photo sélectionnée");
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g.create();
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(new Color(200, 0, 0));
		
		for(Photo p: EditeurAlbums.sAlbum.photos)
		{
			assert(p != null);
			
			AffineTransform transform = new AffineTransform();
		    transform.setToTranslation(p.getposx(), p.getposy()); 		// position
		    transform.scale(p.getScale(), p.getScale()); 				// taille
		    transform.rotate(p.getrotation()*Math.PI/180, p.gettaillex()/2,p.gettailley()/2 );	// rotation
		    g2d.drawImage(p.bimg, transform, null);						// --
		    
			if(p.equals(currentPhoto)) // dessin de la sélection
		    {
		    	try {
					AffineTransform invTransform = transform.createInverse();
					g2d.transform(transform);
					g2d.drawRect(0, 0, currentPhoto.gettaillex(), currentPhoto.gettailley());
					g2d.transform(invTransform);
				} catch (NoninvertibleTransformException e) {
					e.printStackTrace();
				}
		    }
		}

	}

}
