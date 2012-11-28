package main;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class World extends JPanel {

	private final int HEIGHT = 400;
	private final int WIDTH = 800;

	private Image background;
	
	private Launcher launcher;
//	private Projectile projectile;  // it would be better to just use this, don't think we
	                                // need to save all projectiles
	private ArrayList<Target> targets;
	
	private ArrayList<Projectile> projectiles; // for testing

	public World() {
		projectiles = new ArrayList<Projectile>();
		launcher = new Launcher();
		targets = new ArrayList<Target>();

		// Get the background image
		URL url = getClass().getResource("/doubtful_asian_girl.jpg");
		MediaTracker tracker = new MediaTracker(this);
		background = Toolkit.getDefaultToolkit().getImage(url);
		tracker.addImage(background, 0);
		try {
			tracker.waitForID(0);
		} catch(InterruptedException e) { 
			return;
		}
	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;

		// Draw the background image before moving the origin
		g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);

		// Change origin to bottom left
		g.scale(1.0, -1.0);
		g.translate(0, -HEIGHT);

		// Draw background
		//g.setColor(Color.WHITE);
		//g.fillRect(0, 0, WIDTH, HEIGHT);

		// Draw launcher
		launcher.draw(g);

		// Draw projectiles
		for( Projectile p : projectiles ) {
			p.draw(g);
		}

		// Draw targets
		for( Target t : targets ) {
			t.draw(g);
		}
	}

	// Randomly generates non-overlapping targets to the right of the launcher
	public void generateTargets(int numTargets) {	

		Random rand = new Random();

		ArrayList<Integer> locations = new ArrayList<Integer>();

		while( targets.size() < numTargets ) {

			int location = rand.nextInt(3*WIDTH/4)+WIDTH/4;
			int size = rand.nextInt(HEIGHT/3)+50;

			if( !locations.contains(location) ) {
				locations.add(location);
				makeTarget(location, size);
			}
		}
	}

	// makes target at desired location with desired size
	public void makeTarget(int location, int size){
		targets.add(new Target(location, size));
	}

	// checks to see if the projectile collided with any targets in this frame, returns null if it did not hit anything
	// projectiles should be destroyed if its height is less than 0;
	// should probably calculate collisions via the bottom left corner as it will lead
	// utilizes insideofme fn in target
	public void checkCollisions() {
		for( Target t : targets ) {
			if( t.insideOfMe(projectiles.get(0)) ) {
				t.setHit();
				projectiles.remove(0);
			}
		}

		if( projectiles.size() > 0 && projectiles.get(0).getyPos() <= 0 ) {
			projectiles.remove(0);
		}
	}

	// not exactly elegant but it seems the best way to get the projectile into the world
	public void launch() {
		Projectile temp = launcher.launch();
		if(temp != null){
			projectiles.add(temp);
		}
	}

	// iterates through projectiles and moves them
	public void moveProjectiles(double deltaTime){
		for( Projectile p : projectiles ) {
			p.move(deltaTime);
		}
	}

	public Launcher getLauncher() {
		return launcher;
	}
	public ArrayList<Target> getTargets() {
		return targets;
	}
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}

	public void clearTargets() {
		targets.clear();
	}

}
