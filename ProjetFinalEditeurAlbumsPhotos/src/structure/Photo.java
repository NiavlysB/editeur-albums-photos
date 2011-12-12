package structure;

interface IPhoto {
	int getposx();
	int getposy();
	void setposx(int pos);
	void setposy(int pos);
	int gettaillex();
	int gettailley();
	void settaillex(int taillex);
	void settailley(int tailley);
	void resize();
	void deplace(int x, int y);
	String getchemin();
	void setchemin( String chemin);
	int getrotation();
	void setrotaion(int degree);
	
}

class PhotoJpeg implements IPhoto{
	private int posx;
	private int posy;
	private int taillex;
	private int tailley;
	private String cheminimage;
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
	
	public PhotoJpeg(String chemin){
		posx=0;
		posy=0;
		taillex=100;
		tailley=100;
		cheminimage=chemin;
	}
	
	public PhotoJpeg(int x, int y, int tx, int ty, String chemin){
		posx=x;
		posy=y;
		taillex=tx;
		tailley=ty;
		cheminimage=chemin;
	}
	
	public int getrotation(){
		return rotation;
	}
	public void setrotaion(int degree){
		this.rotation=degree;
	}

}



class PhotoPNG implements IPhoto{
	private int posx;
	private int posy;
	private int taillex;
	private int tailley;
	private String cheminimage;
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
	
	public PhotoPNG(String chemin){
		posx=0;
		posy=0;
		taillex=100;
		tailley=100;
		cheminimage=chemin;
	}
	
	public PhotoPNG(int x, int y, int tx, int ty, String chemin){
		posx=x;
		posy=y;
		taillex=tx;
		tailley=ty;
		cheminimage=chemin;
	}
	
	public int getrotation(){
		return rotation;
	}
	public void setrotaion(int degree){
		this.rotation=degree;
	}
}
