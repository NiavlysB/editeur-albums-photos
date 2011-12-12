package interfaceUtilisateur;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
//import java.awt.image.ImageObserver;
//import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PanneauAlbum extends JPanel
{
	
	Image img1;
	public PanneauAlbum()
	{
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.setPreferredSize(new Dimension(500, 400));
		this.setVisible(true);
				
		try
		{
			img1 = ImageIO.read(new File("images/logo.gif"));
		}
		catch (IOException e) {
			System.out.println("Fichier inexistant");
			System.exit(1);
		}
		this.repaint();

					
	}
	public void paintComponent(Graphics g) {
		g.drawImage(img1, 0, 0, 300, 400, Color.WHITE, this);
	}
}
