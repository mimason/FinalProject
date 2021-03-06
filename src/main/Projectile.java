package main;
import java.awt.Color;
import java.awt.Graphics;


public class Projectile {
	
	private final int SIZE = 10;
	
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
		/*System.out.println("Creating new projectile:");
		System.out.println("   P_x: " + xPos);
		System.out.println("   P_y: " + yPos);
		System.out.println("   V_x: " + xVel);
		System.out.println("   V_y: " + yVel);*/
	}
	
	public double calcSpeed() {
		return Math.sqrt(xVel*xVel + yVel*yVel);
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
	
	// delta time is the inverse frame rate, used to keep motion smooth on machines where frame rates will be different
	public void move(double deltaTime){
		xPos = xPos + xVel*deltaTime;
		yPos = yPos + yVel*deltaTime;
		yVel = yVel - 10*deltaTime;
	}

	
	
	
}
