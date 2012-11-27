package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class UserInterface extends JPanel {
	
	World world;
	JTextField numTargetsBox;
	
	public UserInterface(World w) {
		world = w;
		
		// Generate targets
		numTargetsBox = new JTextField(5);
		JButton genTargetsButton = new JButton("Generate Targets");
		add(numTargetsBox);
		add(genTargetsButton);
		genTargetsButton.addActionListener(new GenerateTargetsButtonListener());
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

		}
		
	}
}
