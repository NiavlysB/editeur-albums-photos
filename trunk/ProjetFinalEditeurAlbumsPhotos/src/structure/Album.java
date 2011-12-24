package structure;

import java.util.*;

import exceptions.ImageNonExistanteException;

public class Album {
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
