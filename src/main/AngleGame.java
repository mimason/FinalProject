package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AngleGame extends JFrame {
	
	public AngleGame() {
		setTitle("Angles");
		setSize(new Dimension(800, 600));
				
		// Centers the window
		setLocationRelativeTo(null);
		
		// Create the world
		World w = new World();
		add(w, BorderLayout.CENTER);
		
		
		// Creates the controls
		UserInterface ui = new UserInterface(w);
		add(ui, BorderLayout.SOUTH);
	}

	
	public static void main(String[] args) {
		
		AngleGame ag = new AngleGame();
		ag.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ag.setVisible(true);
	}
}
