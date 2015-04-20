package physics;

import java.awt.Dimension;

public class GravityBall extends Ball{
	
	private static final double GRAVITY = 9.8 * 3780 / 20;
	
	public GravityBall(double radius, double velocity, Dimension position,
			int angle) {
		super(radius, velocity, position, angle);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(long timeElapsed) {
		//printLog();
		checkWallCollision();
		double velocityX;
		double velocityY;
		double timeElapsedInS = timeElapsed/(double)1000000000;
		double angleRad = this.getAngle() * Math.PI/180;
		velocityX = this.getVelocity() * Math.cos(angleRad);
		velocityY = ((-1) * this.getVelocity() * Math.sin(angleRad)) + GRAVITY * timeElapsedInS ;
		
		double distanceXX = velocityX * timeElapsedInS;
		double distanceYY = velocityY * timeElapsedInS;
		this.getPosition().width += distanceXX;
		this.getPosition().height += distanceYY;
		this.setVelocity(velocityX, velocityY);
		int angle = (int)(Math.atan2((-1) * velocityY, velocityX) * 180 / Math.PI);
		if(angle < 0) {
			angle = angle + 360;
		} else if (angle >= 360) {
			angle = angle - 360;
		}
		this.setAngle(angle);
		
	}
}
