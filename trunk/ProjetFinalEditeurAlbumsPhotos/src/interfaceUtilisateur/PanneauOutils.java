package interfaceUtilisateur;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.SpinnerNumberModel;

import exceptions.ImageNonExistanteException;

import structure.Photo;

public class PanneauOutils extends JPanel
{
	JSlider slider1;
	JSpinner spinner;
	private JList listPhotos;
	
	public PanneauOutils()
	{
		setBorder(BorderFactory.createTitledBorder("Outils"));
		
		JPanel panneauO_1 = new JPanel();
		JLabel label1 = new JLabel("Rotation");
		slider1 = new JSlider(0, -180, 180, 0);
		JLabel label1_v = new JLabel("0°");
		Dimension d = label1_v.getPreferredSize();
		label1_v.setPreferredSize(new Dimension(d.width+60, d.height));
		panneauO_1.add(label1);
		panneauO_1.add(slider1);
		panneauO_1.add(label1_v);

		JPanel panneauO_2 = new JPanel();
		JLabel label2 = new JLabel("Redimensionnement");
		spinner = new JSpinner(new SpinnerNumberModel(100, 0, +1000, 1));
		JLabel label2_pc = new JLabel("%");
		panneauO_2.add(label2);
		panneauO_2.add(spinner);
		panneauO_2.add(label2_pc);

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(panneauO_1);
		this.add(panneauO_2);
		slider1.addChangeListener(new Slider1Listener(slider1,label1_v));
		spinner.addChangeListener(new Spinner1Listener(spinner,label2_pc));
		
		JPanel panneauBoutonsOrdre = new JPanel();
		JButton bVersPremier = new JButton("Vers le premier plan");
		JButton bVersArriere = new JButton("Vers l’arrière-plan");
		JButton bPremier = new JButton("Passer au premier plan");
		JButton bArriere = new JButton("Passer en arrière-plan");
		bVersPremier.addActionListener(new BUpListener());
		bVersArriere.addActionListener(new BDownListener());
		bPremier.addActionListener(new BForeListener());
		bArriere.addActionListener(new BBackListener());
		panneauBoutonsOrdre.add(bVersPremier);
		panneauBoutonsOrdre.add(bVersArriere);
		panneauBoutonsOrdre.add(bPremier);
		panneauBoutonsOrdre.add(bArriere);
		this.add(panneauBoutonsOrdre);
		
		JPanel panneauPhotos = new JPanel();
		listPhotos = new JList();
		listPhotos = new JList(EditeurAlbums.sAlbum.photos);
		panneauPhotos.add(listPhotos);
		this.add(panneauPhotos);
	}

	public void setListPhotos(Vector<Photo> l)
	{
		listPhotos = new JList(l);
	}
	
}

// Slider rotation
class Slider1Listener implements ChangeListener
{
	static JSlider slider;
	static JLabel label;
	
	public Slider1Listener(JSlider slider, JLabel label)
	{
		Slider1Listener.slider = slider;
		Slider1Listener.label = label;
	}
	public static void actualisationslider(Photo p){
		slider.setValue(p.getrotation());
		label.setText(slider.getValue()+"°");
	}
	
	@Override
	public void stateChanged(ChangeEvent arg0)
	{
		label.setText(slider.getValue()+"°");
		EditeurAlbums.F.P.pAlbum.rotationimage(slider.getValue());
		EditeurAlbums.F.P.pAlbum.refreshSelection();
	}
}

// Spinner redimensionnement
class Spinner1Listener implements ChangeListener
{
	static JSpinner spinner;
	static JLabel label;
	
	public Spinner1Listener(JSpinner spinner, JLabel label)
	{
		Spinner1Listener.spinner = spinner;
		Spinner1Listener.label = label;
	}
	public static void actualisationspinner(Photo p){
		spinner.setValue((Integer)Math.round(p.getScale()*100));
		label.setText(spinner.getValue()+"%");
	}
	
	@Override
	public void stateChanged(ChangeEvent arg0)
	{
		label.setText(spinner.getValue()+"%");
		EditeurAlbums.F.P.pAlbum.redimensionnement((float)(Integer)spinner.getValue()/100);
		EditeurAlbums.F.P.pAlbum.refreshSelection();
	}
}

class BUpListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			EditeurAlbums.F.P.pAlbum.upPhoto();
		} catch (ImageNonExistanteException e1) {
			e1.printStackTrace();
		}
	}
}
class BDownListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			EditeurAlbums.F.P.pAlbum.downPhoto();
		} catch (ImageNonExistanteException e1) {
			e1.printStackTrace();
		}
	}
}
class BForeListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			EditeurAlbums.F.P.pAlbum.forePhoto();
		} catch (ImageNonExistanteException e1) {
			e1.printStackTrace();
		}
	}
}
class BBackListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			EditeurAlbums.F.P.pAlbum.backPhoto();
		} catch (ImageNonExistanteException e1) {
			e1.printStackTrace();
		}
	}
}
