package structure;

import java.io.Serializable;

public class PhotoPng extends Photo  implements Serializable{
	public PhotoPng(int x, int y, String chemin){
		super(x, y, chemin);
	}
}
