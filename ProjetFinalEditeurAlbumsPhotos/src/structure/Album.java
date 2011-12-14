package structure;
import interfaceUtilisateur.*;

import java.util.*;

import exceptions.ImageNonExistanteException;

public class Album<A extends IPhoto> {
	Vector<A> photos;
	
	public void rajout(A a){
		photos.add(a);
	}
	
	public void enleve(String chemin) throws ImageNonExistanteException{
	int b = -1;
		for(A a : photos){
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
		photos = new Vector<A>();
	}
	
	public String listephoto(){
		String liste="";
		for(A a : photos){
			liste+=a.getchemin();
		}
		return liste;
	}
	
	public Photo emplacementphoto(int x, int y){
		for(A a : photos){
			int posx=a.getposx();
			int posy=a.getposy();
			if ((x>=posx &&x<=posx+a.gettaillex())&&(x>=posy &&x<=posy+a.gettailley()))
				return (Photo)a;
		}
		return null;
	}
	
public static void main(String args[]){
	Album a = new Album();
	PhotoPng pn = new PhotoPng("chalhkh");
	PhotoJpeg jp = new PhotoJpeg("miu_opjçjh");
	PhotoPng p = new PhotoPng(50,50,100,100,"blub");
	PhotoJpeg j = new PhotoJpeg(50,50,100,100,"blap");
	
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
