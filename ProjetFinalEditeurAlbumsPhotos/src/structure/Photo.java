package structure;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;


public abstract class Photo implements IPhoto {
	private int posx;
	private int posy;
	private int taillex;
	private int tailley;
	private float scale;
	private String cheminimage;
	public BufferedImage bimg;
	private int rotation;
	public int getposx(){
		return posx;
	}
	public int getposy(){
		return posy;
	}
	public void setposx(int pos){
		this.posx=pos;
	}
	public void setposy(int pos){
		this.posy=pos;
	}
	public int gettaillex(){
		return taillex;
	}
	public int gettailley(){
		return tailley;
	}
	public void settaillex(int taille){
		this.taillex=taille;
	}
	public void settailley(int taille){
		this.tailley=taille;
	}
	public float getScale(){
		return scale;
	}
	public void setScale(float scale){
		this.scale = scale;
	}
	
//methode resize a voir 
//*********************
	public void resize(){} 
//*********************

	
	public void deplace(int x, int y){
		this.setposx(x);
		this.setposy(y);
	}
	
	
	public String getchemin(){
		return cheminimage;
	}
	public void setchemin( String chemin){
		this.cheminimage=chemin;
	}

	public Photo(String chemin){
		// TODO: Arguments doublons avec BufferedImage
		posx=0;
		posy=0;
		taillex=42;
		tailley=42;
		cheminimage=chemin;
	}
	
	public Photo(int x, int y, String chemin){
		posx=x;
		posy=y;
		scale= 1.0f;
		cheminimage=chemin;
		try
		{
			File f = new File(chemin);
			taillex =  ImageIO.read(f).getWidth();
			tailley = ImageIO.read(f).getHeight();
			bimg = new BufferedImage(taillex, tailley, BufferedImage.TYPE_INT_RGB);
			bimg.setData((ImageIO.read(new File(chemin))).getData());
			
			System.out.println("width="+bimg.getWidth()+",height="+bimg.getHeight());
			
		} catch (IOException e)
		{
			System.out.println("Fichier "+chemin+" non trouvé");
			e.printStackTrace();
		}
	}
	
	public int getrotation(){
		return rotation;
	}
	public void setrotation(int degree){
		this.rotation=degree;
	}

}

