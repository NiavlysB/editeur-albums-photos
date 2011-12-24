package structure;

import java.io.Serializable;

public class PhotoJpeg extends Photo  implements Serializable{

	public PhotoJpeg(int x, int y, String chemin) {
		super(x, y, chemin);
	}
	public PhotoJpeg(String chemin){
		super(chemin);
	}

}