package structure;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;

import exceptions.ImageNonExistanteException;

public class Album implements Serializable {
	public Vector<Photo> photos;
	
	public void rajout(Photo a){
		photos.add(a);
	}
	
	public void enleve(String chemin) throws ImageNonExistanteException{
		int b = -1;
		for(Photo a : photos){
			if(a.getchemin()==chemin){
				System.out.println(photos.indexOf(a));
				b=photos.indexOf(a);
			}
		}
		if(b==-1)
			throw new ImageNonExistanteException();
		else
			photos.remove(b);
	}
	public Album(){
		photos = new Vector<Photo>();
	}
	
	public Album(Album a){
		this.photos = (Vector<Photo>) a.photos.clone();
	}
	
	public Album(ObjectInputStream ois)
	{
		Album a;
		try {
			a = (Album)ois.readObject();
			this.photos = (Vector<Photo>) a.photos.clone();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public String listephoto(){
		String liste="";
		for(Photo a : photos){
			liste+=a.getchemin();
		}
		return liste;
	}
	
	public Photo emplacementphoto(int x, int y){
		ListIterator<Photo> lit = photos.listIterator(photos.size());
		Photo a;
		while(lit.hasPrevious()){
			a = lit.previous();
			int posx=a.getposx();
			int posy=a.getposy();
			if ((x>=posx &&x<=posx+a.gettaillex())&&(y>=posy &&y<=posy+a.gettailley()))
				return a;
		}
		return null;
	}	
	public int emplacementphoto2(int x, int y){
		int a = photos.size();
		while(a>=0){
			a--;
			int posx=photos.get(a).getposx();
			int posy=photos.get(a).getposy();
			if ((x>=posx &&x<=posx+photos.get(a).gettaillex())&&(y>=posy &&y<=posy+photos.get(a).gettailley()))
				return a;
		}
		return -1;
	}
	
	public void serialize()
	{
		
	}
	
public static void main(String args[]){
	Album a = new Album();
	PhotoPng pn = new PhotoPng("chalhkh");
	PhotoJpeg jp = new PhotoJpeg("miu_opjçjh");
	PhotoPng p = new PhotoPng(50,50,"blub");
	PhotoJpeg j = new PhotoJpeg(50,50,"blap");
	
	a.rajout(pn);
	a.rajout(j);
	System.out.println(" ");
	System.out.println(a.listephoto());
	a.rajout(jp);
	a.rajout(p);
	System.out.println(" ");
	System.out.println(a.listephoto());
	try{
		a.enleve("blub");
	}
	catch(ImageNonExistanteException e) {System.out.println("erreur de suppression d'image");}
	try{	
		a.enleve("blap");
	}
	catch(ImageNonExistanteException e) {System.out.println("erreur de suppression d'image");}
	System.out.println(" ");
	System.out.println(a.listephoto());
	
	
}



}
