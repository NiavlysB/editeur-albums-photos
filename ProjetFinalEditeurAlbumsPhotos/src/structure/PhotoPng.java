package structure;

public class PhotoPng extends Photo{
	public PhotoPng(int x, int y, int tx, int ty, String chemin){
		super(x, y, tx, ty, chemin);
	}
	public PhotoPng(String chemin){
		super( chemin);
	}
}
