package physics;

public class CollisionChecker {
	//Simple 2 ball checking, more ball need to implements another one
	public boolean checkBallCollsion(Ball object1, Ball object2){
		double center1X;
		double center1Y;
		double center2X;
		double center2Y;
		double radius1 = object1.getRadius();
		double raidus2 = object2.getRadius();
		center1X = object1.getPosition().width + radius1;
		center1Y = object1.getPosition().height + radius1;
		center2X = object2.getPosition().width + raidus2;
		center2Y = object2.getPosition().height + raidus2;
		System.out.println("******** checkBallCollision Start **********");
		System.out.println("Object 1: X: " + object1.getPosition().width);
		System.out.println("Object 1: Y: " + object1.getPosition().height);
		System.out.println("Object 2: X: " + object2.getPosition().width);
		System.out.println("Object 2: Y: " + object2.getPosition().height);
		System.out.println();
		System.out.println("Distance: " + Math.sqrt(
						(center2X-center1X) * (center2X-center1X) +
						(center2Y-center1Y) * (center2Y-center1Y)));
		System.out.println("Both radius: " + (radius1+raidus2));
		boolean check = radius1+raidus2 >= Math.sqrt(
				(center2X-center1X) * (center2X-center1X) +
				(center2Y-center1Y) * (center2Y-center1Y)) ;
		System.out.println("Two ball Reached: " + check);
		System.out.println("******** checkBallCollision End  **********");
		return check;
	}

	public void doBallCollision(Ball object1, Ball object2) {
		double center1X;
		double center1Y;
		double center2X;
		double center2Y;
		double radius1 = object1.getRadius();
		double raidus2 = object2.getRadius();
		double velocity1 = object1.getVelocity();
		double velocity2 = object2.getVelocity();

		double subVelocityY1;
		double subVelocityX1;
		double subVelocityY2;
		double subVelocityX2;

		int angle1 = object1.getAngle();
		int angle2 = object2.getAngle();
		center1X = object1.getPosition().width + radius1;
		center1Y = object1.getPosition().height + radius1;
		center2X = object2.getPosition().width + raidus2;
		center2Y = object2.getPosition().height + raidus2;			

		System.out.println("************ doBallCollision Start  ************" );
		//First object calculation
		double collisionAngleRad1 = Math.atan2((-1) * (center2Y- center1Y),center2X-center1X);
		if(collisionAngleRad1 < 0) {
			collisionAngleRad1 += Math.PI * 2;
		}
		if(collisionAngleRad1 >= 2 * Math.PI) {
			collisionAngleRad1 -= Math.PI * 2;
		}
		
		double collisionAngleRad2 = collisionAngleRad1 - Math.PI;
		if (collisionAngleRad2 < 0){
			collisionAngleRad1 += Math.PI * 2;
		}
		if(collisionAngleRad1 >= 2 * Math.PI) {
			collisionAngleRad1 -= Math.PI * 2;
		}
		
		System.out.println("collisionAngle1 in degree: " + (collisionAngleRad1 *180 /Math.PI) );
		System.out.println("old angle1: " + angle1 );
		System.out.println("collisionAngleRad2 in degree: " + (collisionAngleRad2 *180 /Math.PI) );
		System.out.println("old angle2: " + angle2 );
		
		object1.setCollisionAngle(collisionAngleRad1);
		object2.setCollisionAngle(collisionAngleRad2);
		
		if (checkCollisionHappening(collisionAngleRad1, collisionAngleRad2, angle1, angle2)){			
			
			double calAngle1 = getCalulateAngle(collisionAngleRad1, angle1);
			//double calAngle1 = collisionAngleRad1 - angle1 * Math.PI / 180;
/*			if (calAngle1 < 0) {
				calAngle1 *= -1;
			} */
	

			//System.out.println("ball1 color" + object1.getColor());
			System.out.println("calAngle1 in degree: " + (calAngle1 * 180 / Math.PI) );
	
			subVelocityY1 = Math.sin(calAngle1) * velocity1;
			subVelocityX1 = Math.cos(calAngle1) * velocity1;
	
			//Second object calculation
			/*			double collisionAngleRad2 = Math.atan2((-1) * (center1Y- center2Y),center1X-center2X);
					if(collisionAngleRad2 < 0) {
						collisionAngleRad2 += Math.PI * 2;
					}
					if(collisionAngleRad2 >= 2 * Math.PI) {
						collisionAngleRad2 -= Math.PI * 2;
					}*/

			double calAngle2 = getCalulateAngle(collisionAngleRad2, angle2);
/*			double calAngle2 = collisionAngleRad2 - angle2  * Math.PI / 180;
			if (calAngle2 < 0) {
				calAngle2 *= -1;
			} */
	

			System.out.println("calAngle2 in degree: " + (calAngle2 * 180 / Math.PI)  );
	
			subVelocityY2 = Math.sin(calAngle2) * velocity2;
			subVelocityX2 = Math.cos(calAngle2) * velocity2 * (-1);
	
			double energy = subVelocityX1 * subVelocityX1 * 1 /* mass */ 
					+ subVelocityX2 * subVelocityX2 * 1 /* mass */;
	
			double momentum = subVelocityX1 * 1 /* mass */ + subVelocityX2 * 1 /* mass */;
	
			double newVelocityX1 = momentum * 0.5 - Math.sqrt(2 * energy -  momentum * momentum) * 0.5;
			double newVelocityX2 = momentum * 0.5 + Math.sqrt(2 * energy -  momentum * momentum) * 0.5;
			System.out.println("Root1: " + newVelocityX1);
			System.out.println("Root2: " + newVelocityX2);
			
			if (newVelocityX1 == subVelocityX1) {
				newVelocityX2 = newVelocityX1;
				newVelocityX1 = momentum * 0.5 + Math.sqrt(2 * energy -  momentum * momentum) * 0.5;				
			}
			newVelocityX1 *= -1;
			//double newVelocityX2 = momentum * 0.5 + Math.sqrt(2 * energy -  momentum * momentum) * 0.5;
			System.out.println("old Velocity1: " + velocity1 );
			System.out.println("old Velocity2: " + velocity2 );
			System.out.println("old subVelocityY1: " + subVelocityY1 );
			System.out.println("old subVelocityY2: " + subVelocityY2 );
			//System.out.println("old subVelocityX2: " + subVelocityX2 );
			System.out.println("old subVelocityX1: " + subVelocityX1 );
			System.out.println("old subVelocityX2: " + subVelocityX2 );
			System.out.println("new subVelocityX1: " + newVelocityX1 );
			System.out.println("new subVelocityX2: " + newVelocityX2 );			
	
			//Post calcuation of object 1
			double newCalAngle1 = Math.atan2(subVelocityY1, newVelocityX1);
			double newVelocity1 = Math.sqrt(newVelocityX1 * newVelocityX1 + subVelocityY1 * subVelocityY1);
			
			System.out.println("newCalAngle 1:"  + (newCalAngle1 * 180 /Math.PI));
			
	/*		if (collisionAngleRad1 - angle1 * Math.PI / 180 < 0) {
				newCalAngle1 *= -1; 
			} */
	
			
			//int newAngle1 = (int)((collisionAngleRad1 - newCalAngle1)*180/Math.PI);
	
	
	
			//Post calcuation of object 2;
			double newCalAngle2 = Math.atan2(subVelocityY2, newVelocityX2);
			double newVelocity2 = Math.sqrt(newVelocityX2 * newVelocityX2 + subVelocityY2 * subVelocityY2);
	/*
			if (collisionAngleRad2 - angle2 * Math.PI / 180 < 0) {
				newCalAngle2 *= -1; 
			}*/
			double newCollisionAngleRad1 = collisionAngleRad2;
			double newCollisionAngleRad2 = collisionAngleRad1;
			double newAngle1 = getOriginalAngle(newCollisionAngleRad1, newCalAngle1, angle1);
			double newAngle2 = getOriginalAngle(newCollisionAngleRad2, newCalAngle2, angle2);
/*			if (collisionAngleRad1 > collisionAngleRad2) {
				newAngle1 = (int)((newCollisionAngleRad1 + newCalAngle1)*180/Math.PI);
				newAngle2 = (int)((newCalAngle2 - (2 * Math.PI - newCollisionAngleRad2))*180/Math.PI);
			}*/
			
			//int newAngle2 = (int)((collisionAngleRad2 - newCalAngle2)*180/Math.PI);
			object1.setAngle((int)(newAngle1 * 180 / Math.PI));
			object1.setVelocity(newVelocity1);
			
			System.out.println("new Angle1: " + (newAngle1 * 180 / Math.PI));
			System.out.println("new Velocity1: " + newVelocity1);
			
			
			System.out.println("newCalAngle 2:"  + (newCalAngle2 *180 /Math.PI));
			System.out.println("new Angle2: " + (newAngle2 * 180 / Math.PI));
			System.out.println("new Velocity2: " + newVelocity2);
			object2.setAngle((int)(newAngle2 * 180 / Math.PI));
			object2.setVelocity(newVelocity2);
			
	
			System.out.println("************ doBallCollision End    ************" );
		}
		//object1.setVelocity(0);
		//object2.setVelocity(0);
	
	}
	
	private double  getCalulateAngle(double collisionAngleRad, int angle) {
		double difference = collisionAngleRad - angle * Math.PI / 180;
		if(difference < 0) {
			difference *= -1;
		}
		if (difference >= Math.PI/2) {
			difference = 2 * Math.PI - difference;
		}
		
		return difference;
			
			
	}
	
	private double getOriginalAngle(double collisionAngleRad, double newAngle, int oldAngle) {
		
		double originalAngle = 0;
		System.out.println("*************** Inside getOriginalAngle **************");
		System.out.println("collisionAngleRad: " + (collisionAngleRad *180/Math.PI));
		System.out.println("newAngle: " + (newAngle *180/Math.PI));
		System.out.println("oldAngle: " + oldAngle);
		double collisionAngleHighEnd = collisionAngleRad + Math.PI;
		double collisionAngleLowEnd = collisionAngleRad - Math.PI;
		double collisionAngleExtraHighEnd = 0;
		double collisionAngleExtraLowEnd = 2 * Math.PI;
		

		
		
		if (collisionAngleLowEnd < 0) {
			collisionAngleExtraLowEnd = collisionAngleLowEnd + 2 * Math.PI;
			collisionAngleLowEnd = 0;
		}
		if (collisionAngleHighEnd > 2 * Math.PI) {
			collisionAngleExtraHighEnd = collisionAngleExtraHighEnd - 2 * Math.PI;
			collisionAngleHighEnd = 2 * Math.PI;
		}
		
		System.out.println("collisionAngleHighEnd: " + (collisionAngleHighEnd *180/Math.PI));
		System.out.println("collisionAngleLowEnd: " + (collisionAngleLowEnd *180/Math.PI));
		System.out.println("collisionAngleExtraHighEnd: " + (collisionAngleHighEnd *180/Math.PI));
		System.out.println("collisionAngleExtraLowEnd: " + (collisionAngleExtraLowEnd *180/Math.PI));
		
		
		if((collisionAngleLowEnd < oldAngle * Math.PI / 180 && collisionAngleRad > oldAngle * Math.PI / 180) 
				|| (collisionAngleExtraLowEnd <  oldAngle * Math.PI / 180 && oldAngle <= 360)) {
			originalAngle = collisionAngleRad - newAngle;
			System.out.println("Substraction occur");
			
		}
		
		if((collisionAngleHighEnd > oldAngle * Math.PI / 180 && collisionAngleRad < oldAngle * Math.PI / 180)
				|| (collisionAngleExtraHighEnd >  oldAngle * Math.PI / 180 && oldAngle >= 0)) {
			originalAngle = collisionAngleRad + newAngle;
			System.out.println("Addition occur");
		}
		System.out.println("originalAngle: " + originalAngle * 180 / Math.PI);
		System.out.println("*************** Exit getOriginalAngle **************");
		return originalAngle;
	}
	
	private boolean checkCollisionHappening(double collisionAngle1, double collisionAngle2, int angle1, int angle2) {
		
		double collisionLowRange1 = collisionAngle1 - Math.PI /2;
		double collisionHighRange1 = collisionAngle1 + Math.PI /2;;
		double collisionLowRange2 = collisionAngle2 - Math.PI /2;
		double collisionHighRange2 = collisionAngle2 + Math.PI /2;
		
		double collisionExtraLowRange1 = 2 * Math.PI;
		double collisionExtraHighRange1 = 0;
		double collisionExtraLowRange2 = 2 * Math.PI;
		double collisionExtraHighRange2 = 0;
		
		if (collisionLowRange1 < 0) {
			collisionExtraLowRange1 = collisionLowRange1 + 2 * Math.PI;
			collisionLowRange1 = 0;
		} 
		
		if (collisionHighRange1 > 2 * Math.PI) {
			collisionExtraHighRange1 = collisionHighRange1 - 2 * Math.PI;
			collisionHighRange1 = 2 * Math.PI;
		}
		
		if (collisionLowRange2 < 0) {
			collisionExtraLowRange2 = collisionLowRange2 + 2 * Math.PI;
			collisionLowRange2 = 0;
		} 
		
		if (collisionHighRange2 > 2 * Math.PI) {
			collisionExtraHighRange2 = collisionHighRange2 - 2 * Math.PI;
			collisionHighRange2 = 2 * Math.PI;
		}
		
		System.out.println("********** checkCollisonHappening START ******************");
		System.out.println("collisionLowRange1: " + collisionLowRange1 * 180/Math.PI);
		System.out.println("collisionHighRange1: " + collisionHighRange1 * 180/Math.PI);
		System.out.println("collisionLowRange2: " + collisionLowRange2 * 180/Math.PI);
		System.out.println("collisionHighRange2: " + collisionHighRange2 * 180/Math.PI);
		System.out.println("collisionExtraLowRange1: " + collisionExtraLowRange1 * 180/Math.PI);
		System.out.println("collisionExtraHighRange1: " + collisionExtraHighRange1 * 180/Math.PI);
		System.out.println("collisionExtraLowRange2: " + collisionExtraLowRange2 * 180/Math.PI);
		System.out.println("collisionExtraHighRange2: " + collisionExtraHighRange2 * 180/Math.PI);
		System.out.println("angle1: " + angle1 );
		System.out.println("angle2: " + angle2 );
		double angle1Rad = angle1 * Math.PI / 180;
		double angle2Rad = angle2 * Math.PI / 180;
		boolean check = (collisionLowRange1 < angle1Rad && collisionHighRange1 > angle1Rad) ||
				(collisionLowRange2 < angle2Rad && collisionHighRange2 > angle2Rad) ||
				( angle1Rad > collisionExtraLowRange1 && angle1Rad <= 2 * Math.PI) ||
				( angle1Rad >= 0 && angle1Rad <= collisionExtraHighRange1) ||
				( angle2Rad > collisionExtraLowRange2 && angle2Rad <= 2 * Math.PI) ||
				( angle2Rad >= 0 && angle2Rad <= collisionExtraHighRange2);
		System.out.println("check: " + check);
		System.out.println("**********   checkCollisonHappening END ******************");
		return check;
	}

}
