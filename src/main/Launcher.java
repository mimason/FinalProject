package main;

public class Launcher {
	private double angle;
	private int power;
	public Launcher() {
		
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public Projectile launch() {//returned projectile is  added to the world's projectile list so that it can iterate through the list and the projectile can move itself
		return null;
	}
}
