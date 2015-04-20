package rotation;

import java.awt.Graphics;
import java.awt.geom.Dimension2D;

public abstract class RotationObject {
	
	private double sizeX;
	private double sizeY;
	
	private double mass = 0;	
	private double velocity = 0;


	//+ for point toward you, - for point away from you, right hand thumb rule
	private double torque = 0;
	
	private double angle = 0;
	private Dimension2D pivotPoint;	
	private Dimension2D intialPosition;	

	public RotationObject(double sizeX, double sizeY, Dimension2D pivotPoint, Dimension2D position) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.pivotPoint = pivotPoint;
		this.intialPosition = position;
	}
	
	public abstract void draw(Graphics g);
	public abstract void update(long timeElapsed);

	//public checkCollision;

	public double getTorque() {
		return torque;
	}
	public void setTorque(double torque) {
		this.torque = torque;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public Dimension2D getPivotPoint() {
		return pivotPoint;
	}
	public void setPivotPoint(Dimension2D pivotPoint) {
		this.pivotPoint = pivotPoint;
	}
	public double getMass() {
		return mass;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	public double getVelocity() {
		return velocity;
	}
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	public Dimension2D getInitialPosition() {
		return intialPosition;
	}

	public void setInitialPosition(Dimension2D intialPosition) {
		this.intialPosition = intialPosition;
	}
	
	public void setInitialPositionX(double corX) {
		this.intialPosition.setSize(corX, getInitialPosition().getHeight());
	}
	
	public void setInitialPositionY(double corY) {
		this.intialPosition.setSize(getInitialPosition().getWidth(), corY);
	}
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
