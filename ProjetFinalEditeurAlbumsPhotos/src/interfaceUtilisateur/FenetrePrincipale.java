package interfaceUtilisateur;

import java.awt.BorderLayout;

import javax.swing.*;

public class FenetrePrincipale extends JFrame
{
	PanneauPrincipal P;
	BarreMenu menu;
	
	public FenetrePrincipale()
	{
		this.setTitle("Éditeur d’albums de photos");
		this.setDefaultCloseOperation(FenetrePrincipale.EXIT_ON_CLOSE);
		P = new PanneauPrincipal(); // (Tout se passe là-dedans)
		this.add(P);
		
		menu = new BarreMenu();
		this.setJMenuBar(menu);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
