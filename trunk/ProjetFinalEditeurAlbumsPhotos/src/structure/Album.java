package structure;
import java.util.*;

public class Album<A extends IPhoto> {
	Vector<A> photos;
	
	public void rajout(A a){
		photos.add(a);
	}
	
	public void enleve(String chemin) throws ImageNonExistanteError{
	int b = -1;
		for(A a : photos){
			if(a.getchemin()==chemin){
				System.out.println(photos.indexOf(a));
				b=photos.indexOf(a);
			}
		}
		if(b==-1)
			throw new ImageNonExistanteError();
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
	
	
public static void main(String args[]){
	Album a = new Album();
	PhotoPNG pn = new PhotoPNG("chalhkh");
	PhotoJpeg jp = new PhotoJpeg("miu_opjçjh");
	PhotoPNG p = new PhotoPNG(50,50,100,100,"blub");
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
	catch(ImageNonExistanteError e) {System.out.println("erreur de suppression d'image");}
	try{	
		a.enleve("blap");
	}
	catch(ImageNonExistanteError e) {System.out.println("erreur de suppression d'image");}
	System.out.println(" ");
	System.out.println(a.listephoto());
	
	
}

}
