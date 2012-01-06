package interfaceUtilisateur;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.*;

public class FenetrePrincipale extends JFrame implements Serializable//implements ActionListener
{
	PanneauPrincipal P;
	BarreMenu menu;
	
	public FenetrePrincipale()
	{
		this.setTitle("Éditeur d’albums de photos");
		this.setDefaultCloseOperation(FenetrePrincipale.EXIT_ON_CLOSE);
		P = new PanneauPrincipal(); // (Tout se passe là-dedans)
		this.add(P);
		
		menu = new BarreMenu(P.pAlbum);
		this.setJMenuBar(menu);
		//JToolBar toolbar = new JToolBar(JToolBar.HORIZONTAL);
		
		/*
        URL imageURL = ToolBarDemo.class.getResource(imgLocation);
        
        //Create and initialize the button.
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);
 
        if (imageURL != null) {                      //image found
            button.setIcon(new ImageIcon(imageURL, altText));
        } else {                                     //no image found
            button.setText(altText);
            System.err.println("Resource not found: "
                               + imgLocation);
        }*/
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/*
	@Override
	public void actionPerformed(ActionEvent e)
	{
		P.album.importImage();
	}*/
}
