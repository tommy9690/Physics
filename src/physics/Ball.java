package physics;

import static physics.PhysicsConstant.SCREEN_HEIGHT;
import static physics.PhysicsConstant.SCREEN_WIDTH;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Ball extends PhysicsObject {
	
	
	private double radius;
	private Color color = Color.PINK;
	private double velocity;
	private static final double MAX_VELOCITY = 2000;

	private Dimension position;
	private int angle;
	
	private double collisionAngle = 0;
	 
	private int number;



	/**
	 * This method create a ball with radius, velocity, position and angle
	 * 
	 * Arguements:
	 * 1-radius
	 * 2-velocity
	 * 3-position (left upper corner)
	 * 4-angle (start from pointing right, increase by counterclockwise rotation)
	 * 
	 * @param radius
	 * @param velocity
	 * @param position
	 * @param angle
	 */
	public Ball(double radius, double velocity, Dimension position, int angle){
		this.radius = radius;
		this.velocity = velocity;
		this.position = position;
		this.angle = angle;
		super.setSizeX(2 * radius);
		super.setSizeY(2 * radius);
	}
	
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		//System.out.println("draw x: " + position.width);
		//System.out.println("draw y: " + position.height);
		g2d.fill(new Ellipse2D.Double(position.width, position.height,2 * radius, 2 * radius ));
		//g.fillOval(position.width, position.height, (int) (2 * radius),  (int) (2 * radius));
/*		g2d.setColor(Color.yellow);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine((int)(position.width + radius), 
					 (int)(position.height + radius), 
					 (int) (Math.cos(this.angle*Math.PI/180) * radius + (position.width + radius)) , 
					 (int) (Math.sin(this.angle*Math.PI/180) * radius * (-1) + (position.height + radius)));
		
		g2d.setColor(Color.BLACK);
		g2d.drawLine((int)(position.width + radius), 
				 (int)(position.height + radius), 
				 (int) (Math.cos(this.collisionAngle) * radius + (position.width + radius)) , 
				 (int) (Math.sin(this.collisionAngle) * radius * (-1) + (position.height + radius)));
		
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(2));
		g2d.drawLine((int)(position.width + radius), 
				 (int)(position.height + radius), 
				 (int) (Math.cos(this.collisionAngle + Math.PI/2) * radius + (position.width + radius)) , 
				 (int) (Math.sin(this.collisionAngle + Math.PI/2) * radius * (-1) + (position.height + radius)));
		g2d.drawLine((int)(position.width + radius), 
				 (int)(position.height + radius), 
				 (int) (Math.cos(this.collisionAngle - Math.PI/2) * radius + (position.width + radius)) , 
				 (int) (Math.sin(this.collisionAngle - Math.PI/2) * radius * (-1) + (position.height + radius)));
		
		g2d.setColor(Color.BLUE);
		g2d.setStroke(new BasicStroke(1));
		g2d.drawLine((int)(position.width + radius), 
				 (int)(position.height + radius), 
				 (int) (Math.cos(0) * radius + (position.width + radius)) , 
				 (int) (Math.sin(0) * radius * (-1) + (position.height + radius)));*/
		
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		g2d.setFont(new Font("Arial", 23, 45));
		g2d.drawString(""+this.number,position.width+(int)radius,position.height+(int)radius);
	
	}

	@Override
	public void update(long timeElapsed) {
		// TODO Auto-generated method stub
		//printLog();
		checkWallCollision();		
		double velocityX;
		double velocityY;
		double angleRad = angle * Math.PI/180;
		
		velocityX = Math.cos(angleRad);
		velocityY = Math.sin(angleRad);
		System.out.println("update velocityX: " + velocityX);
		System.out.println("update velocityY: " + velocityY);
		
		double distanceXX = velocity * velocityX * timeElapsed/(double)1000000000;
		double distanceYY = (-1) * velocity * velocityY * timeElapsed/(double)1000000000l;
		
		System.out.println("update distanceXX: " + distanceXX);
		System.out.println("update distanceYY: " + distanceYY);
		
		this.position.width += distanceXX;
		this.position.height += distanceYY;
/*		accumDistanceX += distanceXX;
		accumDistanceY += distanceYY;
		if (accumDistanceX >= 1 || accumDistanceX <= -1) {
			this.position.width += (int) accumDistanceX;
			accumDistanceX -= (int)accumDistanceX;
		}
		if (accumDistanceY >= 1 || accumDistanceY <= -1) {
			this.position.height += (int) accumDistanceY;
			accumDistanceY -= (int)accumDistanceY;
		}	*/
		
/*		System.out.println("angleRad: " + angleRad);
		System.out.println("velocityX: " + velocityX);
		System.out.println("velocityY: " + velocityY);
		System.out.println("distanceXX: " + distanceXX);
		System.out.println("distanceYY: " + distanceYY);*/
		//System.out.println("accumDistanceX: " + accumDistanceX);
		//System.out.println("accumDistanceY: " + accumDistanceY);
		
	}

	public int getAngle() {
		return angle;
	}


	public void setAngle(int angle) {
		this.angle = angle;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public void setPosition(Dimension position) {
		// TODO Auto-generated method stub
		this.position = position;
	}

	@Override
	public Dimension getPosition() {
		// TODO Auto-generated method stub
		return this.position;
	}
	
	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	
	public void setVelocity(double velocityX, double velocityY) {
		double newVelocity = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
		this.velocity = newVelocity <= MAX_VELOCITY ?  newVelocity : MAX_VELOCITY;
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}
	
	public double getCollisionAngle() {
		return collisionAngle;
	}


	public void setCollisionAngle(double collisionAngle) {
		this.collisionAngle = collisionAngle;
	}

	public void checkWallCollision(){
		//this.printLog();
		
		if(this.angle > 360) {
			this.angle -= 360;
		}
		if(this.angle < 0) {
			this.angle += 360;
		}
		
		//System.out.println("************** Check Wall Collision Start  ************** ");
		if((this.position.width <= 0 && (this.angle > 90 && this.angle < 270))
				|| (this.position.width + 2 * radius >= SCREEN_WIDTH
						&& ((this.angle < 90 && this.angle >= 0) 
								|| (this.angle > 270 && this.angle < 360)))){
			//angle = 180 - 2 * angle;
			System.out.println("************** Bounce Start  ************** ");
			System.out.println("CoordinateX: " + (this.position.width));
			System.out.println("Screen Width: " + (SCREEN_WIDTH - 60));
			System.out.println("Right Side Boundary: " + (this.position.width - 2 * radius));
			System.out.println("Angle before: " + angle);
			angle = angle * (-1) + 180;
			if(angle < 0) {
				angle = angle + 360;
			} else if (angle >= 360) {
				angle = angle - 360;
			}
			if (this.position.width <= 0) {
				this.position.width = 1;
			}
			if(this.position.width + 2 * radius >= SCREEN_WIDTH) {
				this.position.width = SCREEN_WIDTH - 2 * (int)radius - 1;
			}
			System.out.println("Angle after: " + angle);
			System.out.println("************** Bounce End    ************** ");
			//System.exit(0);
		}
		
		if((this.position.height <= 0 && (this.angle > 0 && this.angle < 180))
				|| (this.position.height + 2 * radius >= SCREEN_HEIGHT &&
						(this.angle < 360 && this.angle > 180))){
			//angle *= (-1);
			System.out.println("************** Bounce Start  ************** ");
			System.out.println("CoordinateY: " + (this.position.height));
			System.out.println("Screen Height: " + (SCREEN_HEIGHT - 60));
			System.out.println("Bottom Side Boundary: " + (this.position.height - 2 * radius));
			System.out.println("Angle before: " + angle);
			angle = angle * (-1);
			if(angle < 0) {
				angle = angle + 360;
			} else if (angle >= 360) {
				angle = angle - 360;
			}
			if(this.position.height <= 0) {
				this.position.height = 1;
			}
			if(this.position.height + 2 * radius >= SCREEN_HEIGHT) {
				this.position.height = SCREEN_HEIGHT - 2 * (int)radius - 1;
			}
			System.out.println("Angle after: " + angle);
			System.out.println("************** Bounce End    ************** ");
			//System.exit(0);
		}
		//System.out.println("************** Check Wall Collision End  ************** ");
		//this.printLog();
	}
	
	

	
	public void printLog() {
		System.out.println("****************Ball Start ******************");
		System.out.println("Position X: " + position.width);
		System.out.println("Position Y: " + position.width);
		System.out.println("Angel: " + angle);
		System.out.println("Velocity: " + velocity);
		System.out.println("****************  Ball End ******************");
	}

}
