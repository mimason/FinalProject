package main;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Controls extends JPanel {

	private World world;
	private JTextField numTargetsBox;
	private JButton genTargetsButton;
	private JButton clearTargetsButton;
	private JSlider powerSlider;
	private JSlider angleSlider;
	private JLabel numTargetsLabel;
	private JButton launchButton;
	private JLabel powerBox;
	private JLabel angleBox;

	public Controls(World w) {
		world = w;

		setLayout(new FlowLayout(FlowLayout.LEFT));

		// Setup panel
		JPanel setupPanel = new JPanel();
		setupPanel.setLayout(new GridBagLayout());
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
		setupPanel.add(numTargetsLabel, c);
		c.gridx = 1;
		setupPanel.add(numTargetsBox, c);
		c.insets = new Insets(10,0,0,0);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.ipady = 20;
		setupPanel.add(genTargetsButton, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.ipady = 0;
		c.insets = new Insets(0,0,0,0);
		setupPanel.add(clearTargetsButton, c);
		// Add listeners to elements
		genTargetsButton.addActionListener(new ControlButtonListener());
		clearTargetsButton.addActionListener(new ControlButtonListener());
		setupPanel.setBorder(BorderFactory.createTitledBorder("SETUP"));
		add(setupPanel);


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

		// Launch panel
		JPanel launchPanel = new JPanel();
		launchPanel.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		// Create elements
		JLabel powerLabel = new JLabel("Power ");
		powerBox = new JLabel();
		powerBox.setText("" + world.getLauncher().getPower());
		JLabel angleLabel = new JLabel("Angle ");
		angleBox = new JLabel();
		angleBox.setText("" + (int)world.getLauncher().getAngle());
		launchButton = new JButton("Launch");
		// Add elements to panel
		c1.insets = new Insets(5,0,0,0);
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.gridx = 0;
		c1.gridy = 0;
		launchPanel.add(powerLabel, c1);
		c1.gridx = 1;
		launchPanel.add(powerBox, c1);
		c1.gridx = 0;
		c1.gridy = 1;
		launchPanel.add(angleLabel, c1);
		c1.gridx = 1;
		launchPanel.add(angleBox, c1);
		c1.insets = new Insets(10,0,0,0);
		c1.gridx = 0;
		c1.gridy = 2;
		c1.gridwidth = 2;
		c1.ipady = 20;
		c1.ipadx = 50;
		launchPanel.add(launchButton, c1);
		// Add listeners to elements
		launchButton.addActionListener(new ControlButtonListener());
		launchPanel.setBorder(BorderFactory.createTitledBorder("LAUNCH"));
		add(launchPanel);

	}

	private class ControlButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if( source == genTargetsButton ) {
				String number = numTargetsBox.getText();
				try {
					int n = Integer.parseInt(number);
					world.generateTargets(n);
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error: Please enter a valid number", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else if( source == clearTargetsButton ) {
				world.clearTargets();
			} else if( source == launchButton ) {
				world.launch();				
			}
			world.repaint();
		}
	}

	private class SliderChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			if( source == powerSlider ) {
				world.getLauncher().setPower(source.getValue());
				powerBox.setText(String.valueOf(source.getValue()));
			} else if( source == angleSlider ) {
				world.getLauncher().setAngle(source.getValue());
				angleBox.setText(String.valueOf(source.getValue()));
			}
			world.repaint();
		}		
	}
}
