package FinalProjectPackage;
import java.util.ArrayList;


public class World {
	private Launcher launcher;
	//private Projectile projectile;
	private ArrayList<Target> targets;
	private ArrayList<Projectile> projectiles;
	public void paintComponent() {
		
	}
	public World() {
		projectiles = new ArrayList<Projectile>();
		launcher = new Launcher();
		targets = new ArrayList<Target>();
	}
	public void generateTargets(int numTargets) {	//Randomly generates non-overlapping targets to the right of the launcher
		
	}
	public void makeTarget(int where,int size){//makes target at desired location with desired size
		
	}
	public void checkCollisions(){//checks to see if the projectile collided with any targets in this frame, returns null if it did not hit anything
		//projectiles should be destroyed if it is's height is less than 0;
		//should probably calculate collisions via the botom left corner as it will lead
		//utilizes insideofme fn in target
	}
	public void launch(){//not exactly elegant but it seems the best way to get the projectile into the world 
		Projectile temp = launcher.launch();
		if(temp != null){
			projectiles.add(temp);
		}
	}
	public void moveProjectiles(double deltaTime){//iterates through projectiles and moves them
		
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
	public static void main(String[] args) {
		
	}

}
