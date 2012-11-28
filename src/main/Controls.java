package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	private JButton genTargetsButton;
	private JButton clearTargetsButton;
	JSlider powerSlider;
	JSlider angleSlider;
	private JLabel numTargetsLabel;

	public Controls(World w) {
		world = w;

		// Setup panel
		JPanel targetPanel = new JPanel();
		targetPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Create elements
		numTargetsLabel = new JLabel("Number of targets ");
		numTargetsBox = new JTextField(3);
		genTargetsButton = new JButton("Generate Targets");
		clearTargetsButton = new JButton("Clear Targets");
		// Add elements to panel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		targetPanel.add(numTargetsLabel, c);
		c.gridx = 1;
		targetPanel.add(numTargetsBox, c);
		c.insets = new Insets(10,0,0,0);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.ipady = 20;
		targetPanel.add(genTargetsButton, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.ipady = 0;
		c.insets = new Insets(0,0,0,0);
		targetPanel.add(clearTargetsButton, c);
		// Add listeners to elements
		genTargetsButton.addActionListener(new GenerateTargetsButtonListener());
		clearTargetsButton.addActionListener(new GenerateTargetsButtonListener());
		targetPanel.setBorder(BorderFactory.createTitledBorder("SET UP"));
		add(targetPanel);


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
			JButton source = (JButton)e.getSource();
			if( source == genTargetsButton ) {
				String number = numTargetsBox.getText();
				try {
					int n = Integer.parseInt(number);
					world.generateTargets(n);
				} catch(NumberFormatException ex) {
					System.out.println("Please enter a valid number");
				}
			} else if( source == clearTargetsButton ) {
				world.clearTargets();
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
