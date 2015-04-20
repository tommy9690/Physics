package rotation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.awt.geom.Path2D;

public class Rectangle extends RotationObject{
	
	double x1; 
	double y1;
	double x2; 
	double y2;
	double x3;
	double y3;
	double x4;
	double y4;
	
	
	public Rectangle(double sizeX, double sizeY, Dimension2D pivotPoint, Dimension2D position) {
		super(sizeX, sizeY, pivotPoint, position);
		setObjectPositions(sizeX, sizeY, position);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.CYAN);
		
		

		Graphics2D g2d = (Graphics2D) g;
		//g2d.fill(new Rectangle2D.Double(getInitialPosition().getWidth(), getInitialPosition().getHeight(), getSizeX(), getSizeY()));
		Path2D p = new Path2D.Double();
		p.moveTo(x1, y1);
		p.lineTo(x2, y2);
		p.lineTo(x3, y3);
		p.lineTo(x4, y4);
		p.closePath();
		g2d.fill(p);
	}

	@Override
	public void update(long timeElapsed) {
		// TODO Auto-generated method stub
		double mass = getMass();
		double width = getSizeX();
		double height = getSizeY();
		double iniX = getInitialPosition().getWidth();
		double timeElapsedInS = timeElapsed/(double)1000000000;
		//iniX += timeElapsedInS;
		setInitialPositionX(iniX);
		//setObjectPositions(width, height, getInitialPosition());
		
		rotateObject(getAngle(),getPivotPoint());
		//printLog();
		//double momentOfInertiaCenter = mass * (width * width + height * height)/12;
		//double centre = 
		//double distanceCenterPivot = Math.sqrt()
	}
	
	private void setObjectPositions(double sizeX, double sizeY, Dimension2D position) {
		x1 = position.getWidth();
		y1 = position.getHeight();
		x2 = x1 + sizeX;
		y2 = y1;
		x3 = x1 + sizeX;
		y3 = y1 + sizeY;
		x4 = x1;
		y4 = y1 + sizeY;
	}
	
	private void rotateObject(double angle, Dimension2D pivotPoint) {
	
		double pivotX = pivotPoint.getWidth();
		double pivotY = pivotPoint.getHeight();
		double angleInRad = angle/180 * Math.PI;
		
		RotationUtility rotationUtility = RotationUtility.getInstance();
		printLog();
		
		double[] points;
		points = rotationUtility.doRotate(x1, y1, pivotX, pivotY, angleInRad);
		
		x1 = points[0];
		y1 = points[1];
		
		points = rotationUtility.doRotate(x2, y2, pivotX, pivotY, angleInRad);
		x2 = points[0];
		y2 = points[1];
		
		points = rotationUtility.doRotate(x3, y3, pivotX, pivotY, angleInRad);
		x3 = points[0];
		y3 = points[1];
		
		points = rotationUtility.doRotate(x4, y4, pivotX, pivotY, angleInRad);
		x4 = points[0];
		y4 = points[1];
		//printLog();
	}
	
	private void printLog(){
/*		System.out.println("*********************");
		System.out.println("x1: "+x1);
		System.out.println("y1: "+y1);
		System.out.println("x2: "+x2);
		System.out.println("y2: "+y2);
		System.out.println("x3: "+x3);
		System.out.println("y3: "+y3);
		System.out.println("x4: "+x4);
		System.out.println("y4: "+y4);
		System.out.println("Angle: " + getAngle());
		System.out.println("####################");
		System.out.println("");
		*/
	
	}
}
