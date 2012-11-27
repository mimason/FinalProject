package main;
import java.awt.Color;
import java.awt.Graphics;


public class Projectile {
	
	private final int SIZE = 10;
	
	//private World world;//used for collision detection, not sure if it's more appropriate here or in word itself
	private double xPos;
	private double yPos;
	private double xVel;
	private double yVel;
	
	/**
	 * 
	 * @param xPos
	 * @param yPos
	 * @param xVel
	 * @param yVel
	 */
	public Projectile(double xPos, double yPos, double xVel, double yVel) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVel = Math.round(xVel);
		this.yVel = Math.round(yVel);
		System.out.println("Creating new projectile:");
		System.out.println("   P_x: " + xPos);
		System.out.println("   P_y: " + yPos);
		System.out.println("   V_x: " + xVel);
		System.out.println("   V_y: " + yVel);
	}
	
	public double calcSpeed() {
		return 0;
	}
	public double getxPos() {
		return xPos;
	}
	public double getyPos() {
		return yPos;
	}
	public double getxVel() {
		return xVel;
	}
	public double getyVel() {
		return yVel;
	}
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval((int)xPos - SIZE/2, (int)yPos - SIZE/2, SIZE, SIZE);
	}
	
	public void move(double deltaTime){//delta time is the inverse frame rate, used to keep motion smooth on machines where frame rates will be different
		xPos = xPos + xVel*deltaTime;
		yPos = yPos + xVel*deltaTime;
		yVel = yVel - 9.8*deltaTime;
	}
	
	
}
