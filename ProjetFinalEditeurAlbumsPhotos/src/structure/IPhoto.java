package structure;

public interface IPhoto {
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