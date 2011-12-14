package structure;

public class PhotoJpeg extends Photo{

	public PhotoJpeg(int x, int y, int tx, int ty, String chemin) {
		super(x, y, tx, ty, chemin);
	}
	public PhotoJpeg(String chemin){
		super(chemin);
	}

}