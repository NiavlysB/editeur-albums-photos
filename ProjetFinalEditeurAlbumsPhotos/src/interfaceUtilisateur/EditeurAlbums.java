package interfaceUtilisateur;

import structure.Album;

public class EditeurAlbums
{
	public static FenetrePrincipale F;
	public static Album sAlbum;
	
	public static void main(String[] args)
	{
		// Point d’entrée
		sAlbum = new Album();
		F = new FenetrePrincipale();
	}

}
