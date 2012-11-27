package main;
import java.awt.Graphics;
import java.util.ArrayList;


public class Projectile {
	//private World world;//used for collision detection, not sure if it's more appropriate here or in word itself
	private double xPos;
	private double yPos;
	private double xVel;
	private double yVel;
	public Projectile(double xPos, double yPos, double xVel, double yVel) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVel = xVel;
		this.yVel = yVel;
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
		
	}
	public void move(double deltaTime){//delta time is the inverse frame rate, used to keep motion smooth on machines where frame rates will be different
		xPos = xPos + xVel*deltaTime;
		yPos = yPos + xVel*deltaTime;
		yVel = yVel - 9.8*deltaTime;
	}
	
	
}
