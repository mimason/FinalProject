package main;

import java.awt.Color;
import java.awt.Graphics;

public class Launcher {
	private double angle;
	private int power;
	public Launcher() {
		angle = 0;
		power = 0;
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
	
	// returned projectile is  added to the world's projectile list so that 
	// it can iterate through the list and the projectile can move itself
	public Projectile launch() {
		Projectile p = new Projectile(0, 0, Math.cos(Math.toRadians(angle))*power, Math.sin(Math.toRadians(angle))*power);
		return p;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawLine(0, 0, (int) (Math.cos(Math.toRadians(angle))*50), (int) (Math.sin(Math.toRadians(angle))*50)); 
	}
}
