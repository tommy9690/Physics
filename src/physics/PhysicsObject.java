package physics;

import java.awt.Dimension;
import java.awt.Graphics;

public abstract class PhysicsObject {
	
	public double sizeX = 0;
	public double sizeY = 0;	

	public abstract void draw(Graphics g);
	public abstract void update(long timeElapsed);
	public abstract void setPosition(Dimension position);
	public abstract Dimension getPosition();
	//public checkCollision;
	
	public double getSizeX() {
		return sizeX;
	}
	public void setSizeX(double sizeX) {
		this.sizeX = sizeX;
	}
	public double getSizeY() {
		return sizeY;
	}
	public void setSizeY(double sizeY) {
		this.sizeY = sizeY;
	}
}
