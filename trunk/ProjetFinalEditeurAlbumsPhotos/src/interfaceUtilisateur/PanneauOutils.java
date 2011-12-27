package interfaceUtilisateur;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.SpinnerNumberModel;

public class PanneauOutils extends JPanel implements ActionListener
{
	JSlider slider1;
	JSpinner spinner;
	
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
		spinner = new JSpinner(new SpinnerNumberModel(0, -100, +1000, 1));
		JLabel label2_pc = new JLabel("%");
		panneauO_2.add(label2);
		panneauO_2.add(spinner);
		panneauO_2.add(label2_pc);

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(panneauO_1);
		this.add(panneauO_2);
		slider1.addChangeListener(new Slider1Listener(slider1,label1_v));
		spinner.addChangeListener(new Spinner1Listener(spinner,label2_pc));
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println(this.slider1);
	}
}

class Slider1Listener implements ChangeListener
{
	JSlider slider;
	JLabel label;
	
	public Slider1Listener(JSlider slider, JLabel label)
	{
		this.slider = slider;
		this.label = label;
	}
	
	@Override
	public void stateChanged(ChangeEvent arg0)
	{
		//System.out.println("slider1 : "+slider.getValue());
		label.setText(slider.getValue()+"°");
		EditeurAlbums.F.P.album.rotationimage(slider.getValue());
		
	}
	
}


class Spinner1Listener implements ChangeListener
{
	JSpinner spinner;
	JLabel label;
	
	public Spinner1Listener(JSpinner spinner, JLabel label)
	{
		this.spinner = spinner;
		this.label = label;
	}
	
	@Override
	public void stateChanged(ChangeEvent arg0)
	{
		//System.out.println("slider1 : "+slider.getValue());
		label.setText(spinner.getValue()+"%");
		EditeurAlbums.F.P.album.redimensionnement((Integer) spinner.getValue());
		
	}
	
}
