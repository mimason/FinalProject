package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Launcher {
	ArrayList<Integer> projPath;
	private double angle;
	private int power;
	public Launcher() {
		angle = 45;
		power = 50;
		projPath = new ArrayList<Integer>();
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
		g.setColor(Color.WHITE);
		drawProjPath(this,g);
	}
	public void drawProjPath(Launcher launcher,Graphics g) {
		Projectile temp = launcher.launch();
		while(temp.getyPos()>=0) {
			projPath.add((int)temp.getxPos());
			projPath.add((int)temp.getyPos());
			temp.move(1);
		}
		for(int i=0; i<projPath.size(); i+=2) {
			g.drawOval(projPath.get(i), projPath.get(i+1), 5, 5);
		}
		projPath.clear();
	}
}
