package main;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class Controls extends JPanel {

	private World world;
	private JTextField numTargetsBox;
	private JButton genTargetsButton;
	private JButton clearTargetsButton;
	private JSlider powerSlider;
	private JSlider angleSlider;
	private JLabel numTargetsLabel;
	public JButton launchButton;
	private JLabel powerBox;
	private JLabel angleBox;
	//private ChangeKeys launchAdjustments;

	public Controls(World w) {
		world = w;

		setLayout(new FlowLayout(FlowLayout.LEFT));

		// Setup panel
		JPanel setupPanel = new JPanel();
		setupPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//setupPanel.addKeyListener(launchAdjustments);
		// Create elements
		numTargetsLabel = new JLabel("Number of targets ");
		numTargetsBox = new NumericTextField("5", 3);
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
		
		// Sliders
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
		JLabel powerLabel = new JLabel("Power:");
		powerBox = new JLabel();
		powerBox.setText("" + world.getLauncher().getPower());
		JLabel angleLabel = new JLabel("Angle:");
		angleBox = new JLabel();
		angleBox.setText("" + (int)world.getLauncher().getAngle());
		launchButton = new JButton("Launch");
		// Add elements to panel
		c1.insets = new Insets(5,0,0,0);
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.gridx = 0;
		c1.gridy = 0;
		c1.weightx = 0.1;
		launchPanel.add(powerLabel, c1);
		
		c1.gridx = 1;
		c1.weightx = 1.0;
		c1.anchor = GridBagConstraints.EAST;
		launchPanel.add(powerBox, c1);
		
		c1.ipadx = 0;
		c1.anchor = GridBagConstraints.CENTER;
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
		
		// Set key bindings
		launchButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "launch");
		launchButton.getActionMap().put("launch", new ControlButtonListener());
		
	}

	// source: http://www.coderanch.com/how-to/java/NumericTextField
	class NumericTextField extends JTextField
	{

	    public NumericTextField(String string, int i) {
			super(string, i);
		}

		@Override
	    protected Document createDefaultModel()
	    {
	        return new NumericDocument();
	    }

	    private class NumericDocument extends PlainDocument
	    {
	        // The regular expression to match input against (zero or more digits)
	        private final Pattern DIGITS = Pattern.compile("\\d*");

	        @Override
	        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
	        {
	            // Only insert the text if it matches the regular expression
	            if (str != null && DIGITS.matcher(str).matches())
	                super.insertString(offs, str, a);
	        }
	    }
	}
	
	private class ControlButtonListener extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton source = (JButton)e.getSource();
			if( source == genTargetsButton ) {
				String number = numTargetsBox.getText();
				try {
					int n = Integer.parseInt(number);
					if( n > 50 || n == 0){
						throw( new NumberFormatException());
					}
					world.generateTargets(n);
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error: Please enter a number between 1 and 50.", "Error",
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
	
//	private class ChangeKeys implements KeyListener{
//
//		@Override
//		public void keyPressed(KeyEvent arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void keyReleased(KeyEvent arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void keyTyped(KeyEvent e) {
//			//up arrow 
//			if(e.getKeyCode() == 38){
//				System.out.println("up");
//				angleSlider.requestFocus(true);
//				world.getLauncher().setAngle(world.getLauncher().getAngle()+1);
//			}
//			//Down Arrow
//			if(e.getKeyCode() == 40){
//				System.out.println("down");
//				angleSlider.requestFocus(true);
//				world.getLauncher().setAngle(world.getLauncher().getAngle()-1);
//			}
//			//Right Arrow
//			if(e.getKeyCode() == 39){
//				System.out.println("right");
//				powerSlider.requestFocus(true);
//				world.getLauncher().setPower(world.getLauncher().getPower()+1);
//			}
//			//Left arrow
//			if(e.getKeyCode() == 37){
//				System.out.println("left");
//				powerSlider.requestFocus(true);
//				world.getLauncher().setPower(world.getLauncher().getPower()+1);
//			}
//			//Space
//			if(e.getKeyCode() == 32){
//				launchButton.requestFocus(true);
//				System.out.println("space");
//				world.launch();
//			}
//		}
//	}
}
