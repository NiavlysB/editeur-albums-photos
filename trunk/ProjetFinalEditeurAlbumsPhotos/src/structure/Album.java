package structure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import exceptions.ImageNonExistanteException;

public class Album implements Serializable {
	private static final long serialVersionUID = -7953193962925472688L;
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
	
	public void upPhoto(Photo p) throws ImageNonExistanteException
	{
		if(!photos.contains(p))
			throw new ImageNonExistanteException();
		else
		{
			int index = photos.indexOf(p);
			if(index == photos.size()-1)
			{
				System.out.println("Image déjà au premier plan");
			} else {
				photos.removeElement(p);
				photos.insertElementAt(p, index+1);
				System.out.println(listephoto());
			}
		}
	}
	public void downPhoto(Photo p) throws ImageNonExistanteException
	{
		if(!photos.contains(p))
			throw new ImageNonExistanteException();
		else
		{
			int index = photos.indexOf(p);
			if(index == 0)
			{
				System.out.println("Image déjà en arrière-plan");
			} else {
				photos.removeElement(p);
				photos.insertElementAt(p, index-1);
				System.out.println(listephoto());
			}
		}
	}
	public void forePhoto(Photo p) throws ImageNonExistanteException
	{
		if(!photos.contains(p))
			throw new ImageNonExistanteException();
		else
		{ // mettre à size()-1
			int index = photos.indexOf(p);
			if(index == photos.size()-1)
			{
				System.out.println("Image déjà au premier plan");
			} else {
				photos.removeElement(p);
				photos.insertElementAt(p, photos.size()-1);
				System.out.println(listephoto());
			}
		}
	}
	public void backPhoto(Photo p) throws ImageNonExistanteException
	{
		if(!photos.contains(p))
			throw new ImageNonExistanteException();
		else
		{ // mettre à size()-1
			int index = photos.indexOf(p);
			if(index == 0)
			{
				System.out.println("Image déjà en arrière-plan");
			} else {
				photos.removeElement(p);
				photos.insertElementAt(p, 0);
				System.out.println(listephoto());
			}
		}
	}
	
	public Photo emplacementphoto(int x, int y){
		ListIterator<Photo> lit = photos.listIterator(photos.size());
		Photo a;
		while(lit.hasPrevious()){
			a = lit.previous();
			int posx=a.getposx();
			int posy=a.getposy();
			if ((x>=posx &&x<=posx+a.getWidth())&&(y>=posy &&y<=posy+a.getHeight()))
				return a;
		}
		return null;
	}
	/*
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
	}*/
	
	public void serialize(File file)
	{
		try {
			FileOutputStream of = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(of);
			oos.writeObject(this);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
