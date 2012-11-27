package main;

public class Target implements Comparable<Target>{
	
	private int location;
	private int size;
	private boolean hit;
	
	public Target(int location, int size) {
		this.location = location;
		this.size = size;
		System.out.println("Creating new target:");
		System.out.println("   L: " + location);
		System.out.println("   S: " + size);
	}
	public void setHit() {
		hit = true;
	}
	public boolean isHit() {
		return hit;
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
	public boolean insideOfMe(Projectile p) {
		return( p.getxPos() == location && p.getyPos() <= size );
	}
}
