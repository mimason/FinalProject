package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Launcher {
	private World world;
	private ArrayList<Integer> projPath;
	private double angle;
	private int power;
	private final int SIZE = 5;
	
	public Launcher(World world) {
		this.world = world;
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
		Projectile p = new Projectile(Math.cos(Math.toRadians(angle))*104, Math.sin(Math.toRadians(angle))*104, Math.cos(Math.toRadians(angle))*power, Math.sin(Math.toRadians(angle))*power);
		return p;
	}
	
	public void draw(Graphics g) {
//		g.setColor(Color.GREEN);
//		g.drawLine(0, 0, (int) (Math.cos(Math.toRadians(angle))*50), (int) (Math.sin(Math.toRadians(angle))*50)); 
//		g.setColor(Color.WHITE);
		
		// Draw cannon
		BufferedImage cannon = World.cannon;
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(angle), 0, 0);
        //g2d.drawImage(cannon, 0, -14, 104, 14, 0, 0, cannon.getWidth(), cannon.getHeight(), null);
        g2d.drawImage(cannon, 0, -cannon.getHeight()/2, null);
        //g2d.fillRect(0, -5, 60, 10);
        g2d.rotate(-Math.toRadians(angle), 0, 0);
        
        // Draw base
        g2d.setColor(Color.BLACK);
        g2d.fillOval(-25, -25, 50, 50);
        g2d.clearRect(0, -25, 200, 25);
        
		drawProjPath(this,g);
	}
	
	public void drawProjPath(Launcher launcher,Graphics g) {
		
		Projectile temp = launcher.launch();
		int time = 1;
		while(temp.getyPos()>=0) {
			if(time%(1/world.getDTime()) == 0){
				projPath.add((int)temp.getxPos());
				projPath.add((int)temp.getyPos());
			}
			temp.move(world.getDTime());
			time++;
		}
		for(int i=0; i<projPath.size(); i+=2) {
			g.drawOval(projPath.get(i) - SIZE/2, projPath.get(i+1) - SIZE/2, SIZE, SIZE);
		}
		projPath.clear();
	}
	
	public void reset() {
		power = 50;
		angle = 45;
	}
}
