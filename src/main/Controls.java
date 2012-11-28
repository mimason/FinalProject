package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Controls extends JPanel {
	
	World world;
	JTextField numTargetsBox;
	JSlider powerSlider;
	JSlider angleSlider;
	
	public Controls(World w) {
		world = w;
		
		// Generate targets
		numTargetsBox = new JTextField(5);
		JButton genTargetsButton = new JButton("Generate Targets");
		add(numTargetsBox);
		add(genTargetsButton);
		genTargetsButton.addActionListener(new GenerateTargetsButtonListener());
		
		JPanel powerPanel = new JPanel();
		JPanel anglePanel = new JPanel();
		powerSlider = new JSlider(JSlider.HORIZONTAL,0,100,world.getLauncher().getPower());
		angleSlider = new JSlider(JSlider.HORIZONTAL,0,90,(int)world.getLauncher().getAngle());
		powerSlider.setMajorTickSpacing(20);
		powerSlider.setMinorTickSpacing(2);
		powerSlider.setPaintLabels(true);
		powerSlider.setPaintTicks(true);
		angleSlider.setMajorTickSpacing(15);
		angleSlider.setMinorTickSpacing(1);
		angleSlider.setPaintLabels(true);
		angleSlider.setPaintTicks(true);
		powerSlider.add(new JLabel("Power"));
		powerPanel.add(powerSlider);
		anglePanel.add(angleSlider);
		powerPanel.setBorder(BorderFactory.createTitledBorder("POWER"));
		anglePanel.setBorder(BorderFactory.createTitledBorder("ANGLE"));
		powerSlider.addChangeListener(new SliderChangeListener());
		angleSlider.addChangeListener(new SliderChangeListener());
		add(powerPanel);
		add(anglePanel);
	}
	
	private class GenerateTargetsButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String number = numTargetsBox.getText();
			try {
				int n = Integer.parseInt(number);
				world.generateTargets(n);
			} catch(NumberFormatException ex) {
				System.out.println("Please enter a valid number");
			}
			world.repaint();
		}
		
	}
	private class SliderChangeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			if(source==powerSlider) {
				world.getLauncher().setPower(source.getValue());
			} else if(source==angleSlider) {
				world.getLauncher().setAngle(source.getValue());
			}
			world.repaint();
		}		
	}
}
