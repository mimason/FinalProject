package FinalProjectPackage;

public class Target implements Comparable<Target>{
	private int location;
	private int size;
	private boolean hit;
	public Target(int location) {
		this.location=location;
	}
	public void setHit() {
		
	}
	public boolean isHIt() {
		return false;
	}
	@Override
	public int compareTo(Target o) {
		// TODO Auto-generated method stub
		return o.location - location;
	}
	public int getLocation(){
		return location;
	}
	public int getSize(){
		return size;
	}
	public Projectile insideOfMe() {
		//checks if there are any projectiles inside of target
		return null;
	}
}
