/**
 * Bug 1, move too slow, graphic stuck at boundary, precision issue
 * Bug 2, object went inside each other intermittent
 * Bug 3, Square root of velocity produce NaN
 */

package physics;

import static physics.PhysicsConstant.FRAME;
import static physics.PhysicsConstant.SCREEN_HEIGHT;
import static physics.PhysicsConstant.SCREEN_WIDTH;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class TestPhysics extends JPanel implements MouseListener{
	
	private ArrayList<PhysicsObject> objectList = new ArrayList<PhysicsObject>();
	private HashMap<Integer, Double> collideXlist = new HashMap<Integer, Double>();
	private HashMap<Integer, Double> collideYlist = new HashMap<Integer, Double>();
	private ArrayList<Integer> collisionMark = new ArrayList<Integer>();
	private HashMap<Integer, ArrayList<Integer>> collidetotallistX = new HashMap<Integer, ArrayList<Integer>>();
	private HashMap<Integer, ArrayList<Integer>> collidetotallistY = new HashMap<Integer, ArrayList<Integer>>();
	private HashMap<Integer, ArrayList<Integer>> collidetotallist = new HashMap<Integer, ArrayList<Integer>>();
	
   public Comparator<Map.Entry<Integer, Double>> byMapValues = new Comparator<Map.Entry<Integer, Double>>() {
        @Override
        public int compare(Map.Entry<Integer, Double> left, Map.Entry<Integer, Double> right) {
            return left.getValue().compareTo(right.getValue());
        }
    };
	
	private Timer timer; 
	
	
	public TestPhysics() {
		this.setBackground(Color.BLUE);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));                                   
		Ball ball = new Ball(55,0,new Dimension(450,450), 0);
		Ball ball2 = new Ball(55,400*2,new Dimension(450,0),230);
		Ball ball3 = new Ball(55,480*2,new Dimension(0,450), 20);
		Ball ball4 = new Ball(55,400*2,new Dimension(450,700), 70);
		Ball ball5 = new Ball(55,400*2,new Dimension(700,450), 130);
		ball.setNumber(1);
		ball2.setNumber(2);
		ball3.setNumber(3);
		ball4.setNumber(4);
		ball5.setNumber(5);
		ball2.setColor(Color.lightGray);
		ball3.setColor(Color.GREEN);
		GravityBall gball = new GravityBall(15,500,new Dimension(40,500), 80);
		GravityBall gball1 = new GravityBall(15,600,new Dimension(40,500), 20);
		GravityBall gball2 = new GravityBall(15,700,new Dimension(40,500), 50);
		GravityBall gball3 = new GravityBall(15,800,new Dimension(40,500), 155);
		objectList.add(ball);
		objectList.add(ball2);
		objectList.add(ball3);
		objectList.add(ball4);
		objectList.add(ball5);
		objectList.add(gball1);
		objectList.add(gball2);
		objectList.add(gball3);
		addMouseListener(this);
		timer = new Timer();
		Physics task = new Physics();
		timer.scheduleAtFixedRate(task, 0, 1000/FRAME);
	}
	
	private class Physics extends TimerTask {
		
		private long lastFrameTime = 0;
		private long nextFrameTime = 0;
		private long firstFrameTime;
		private long timeElapsed;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(nextFrameTime != 0){
				lastFrameTime = nextFrameTime;
				
			} else {
				lastFrameTime = System.nanoTime();
				firstFrameTime = lastFrameTime;
			}
			nextFrameTime = System.nanoTime();
			timeElapsed = nextFrameTime - lastFrameTime;
/*			System.out.println("lastFrameTime: " + lastFrameTime);
			System.out.println("nextFrameTime: " + nextFrameTime);
			System.out.println("timeElapsed: " + (lastFrameTime - firstFrameTime)/(double)1000000000);
			System.out.println("timeBetweenFrame: " + timeElapsed);*/
			update(timeElapsed);
			//System.out.println("Got Repaint!!");
			repaint();
		}
		
		public void update(long timeElapsed){
			
			for (PhysicsObject object : objectList){
				object.update(timeElapsed);				
			}
			
			
			//if(
			if (objectList.size() >= 2) {
				checkBoundingBox();
				//if (collidetotallistX.size() > 0 && collidetotallistY.size() > 0) {
				//if (collidetotallistX.size() > 0) {
					//combinedCollsionList();
				if(collidetotallist.size() > 0 ) {
					processBallCollision();
//					if(checkBallCollsion((Ball) objectList.get(0), (Ball)objectList.get(1))) {
//						doBallCollision((Ball) objectList.get(0), (Ball)objectList.get(1));
//						//System.out.println("Collide!!");
//					}
				}
				collidetotallist.clear();
				//}
			}
			
		}
		
		public void processBallCollision(){
			Iterator<Entry<Integer, ArrayList<Integer>>> itr = collidetotallist.entrySet().iterator();
			CollisionChecker cc = new CollisionChecker();
			while (itr.hasNext()) {
				Map.Entry<Integer, ArrayList<Integer>> element = itr.next();
				int mainObject = element.getKey();
				ArrayList<Integer> collideObject = element.getValue();

				if (collideObject.size() == 1) {
					
						if(cc.checkBallCollsion((Ball) objectList.get(mainObject), (Ball)objectList.get(collideObject.get(0)))) {
							System.out.println("**************************************");
							System.out.println("Collide between 'Ball " + mainObject +"' and 'Ball " + collideObject.get(0) +"'");
							System.out.println("**************************************");
							
							cc.doBallCollision((Ball) objectList.get(mainObject), (Ball)objectList.get(collideObject.get(0)));
							
						}
				
				} else {
					System.out.println("**************************************");
					System.out.println("   Error:Three object collide !!!!    ");
					System.out.println("**************************************");
				} 
			}
			
			
		}
		
/*		public void combinedCollsionList() {
			for (int i = 0; i < objectList.size(); i ++){
				ArrayList<Integer> collideListX = collidetotallistX.get(i);
				ArrayList<Integer> collideListY = collidetotallistY.get(i);
				
				//System.out.println("collidetotallistX: " + collidetotallistX);
				//System.out.println("collidetotallistY: " + collidetotallistY);
				//System.out.println("collideListX: " + collideListX);
				//System.out.println("collideListY: " + collideListY);
				
				ArrayList<Integer> combinedList = new ArrayList<Integer>();
				if(collideListX != null && collideListY != null){
					for (int element : collideListX) {
						if (collideListY.contains(element)) {
							combinedList.add(element);
						}
					}
					
					if (combinedList.size() > 0) {
						collidetotallist.put(i, combinedList);
					}
				}
			}
			collidetotallistX.clear();
			collidetotallistY.clear();
			System.out.println("collidetotallist: " + collidetotallist);
		}
		*/
		public void checkBoundingBox() {
			for (int i = 0; i < objectList.size(); i ++){
				collideXlist.put(2 * i, (double) objectList.get(i).getPosition().width);	
				collideXlist.put(2 * i + 1, (double) objectList.get(i).getPosition().width 
											+ objectList.get(i).getSizeX());	
				collideYlist.put(2 * i, (double) objectList.get(i).getPosition().height);
				collideYlist.put(2 * i + 1, (double) objectList.get(i).getPosition().height 
											+ objectList.get(i).getSizeY());	
			}
			
			List<Map.Entry<Integer, Double>> tempListX = new LinkedList<Map.Entry<Integer, Double>>(collideXlist.entrySet());
			List<Map.Entry<Integer, Double>> tempListY = new LinkedList<Map.Entry<Integer, Double>>(collideYlist.entrySet());
			

			
			Collections.sort(tempListX, byMapValues);
			Collections.sort(tempListY, byMapValues);		
			System.out.println("Sort X points: " + tempListX.toString());
			System.out.println("Sort Y points: " + tempListY.toString());
			System.out.println("Consolidation of X points: " + collideXlist);
			System.out.println("Consolidation of Y points: " + collideYlist);			
			
			
			//For x direction
			for (int i = 0; i < objectList.size(); i ++){
				for (int counter = 0; counter < collideXlist.size(); counter ++) {
					//System.out.println("CollsionMarkX: " + collisionMark.toString());
					int position = tempListX.get(counter).getKey();
					if (position % 2 == 0) {
						if (collisionMark.size() > 0) {
							collidetotallistX.put(position/2, new ArrayList<Integer>(collisionMark));
						}
						collisionMark.add(position/2);
						
					} else if (position % 2 == 1) {
						collisionMark.remove((Integer) ((position - 1)/2));
					}
					
				}
			}
			
			collisionMark.clear();
			
			
			if(collidetotallistX.size() > 0) {
				Iterator<Entry<Integer, ArrayList<Integer>>> itr = collidetotallistX.entrySet().iterator();
				System.out.println("**************check Collision Y start ***************");
				while (itr.hasNext()) {
					Map.Entry<Integer, ArrayList<Integer>> element = itr.next();
					int referenceXStart = 2 * element.getKey();
					int referenceXEnd = 2 * element.getKey() + 1;
					
					System.out.println("referenceXStart: " + referenceXStart);
					System.out.println("referenceXEnd: " + referenceXEnd);
					
					double compareYStart = collideYlist.get(referenceXStart);
					double compareYEnd = collideYlist.get(referenceXEnd);
					
					System.out.println("compareYStart: " + compareYStart);
					System.out.println("compareYEnd: " + compareYEnd);
					
					ArrayList<Integer> tobeComparedList = element.getValue();
					ArrayList<Integer> combinedList = new ArrayList<Integer>();
					
					for (int tobeComparedListEle : tobeComparedList) {
						int tobeComparedRefYStart = tobeComparedListEle * 2;
						int tobeComparedRefYEnd = tobeComparedListEle * 2 + 1;

						double tobeComparedYStart = collideYlist.get(tobeComparedRefYStart);
						double tobeComparedYEnd = collideYlist.get(tobeComparedRefYEnd);
						
						System.out.println("tobeComparedYStart: " + tobeComparedYStart);
						System.out.println("tobeComparedYEnd: " + tobeComparedYEnd);
						
						if((compareYStart <= tobeComparedYStart && 
								tobeComparedYStart  <= compareYEnd) ||
							(compareYStart <= tobeComparedYEnd && 
									tobeComparedYEnd  <= compareYEnd)){
							combinedList.add(tobeComparedListEle);
						}
					}
					
					if(combinedList.size() > 0) {
						collidetotallist.put(element.getKey(), combinedList);
					}
				}
				System.out.println("**************check Collision Y end   ***************");
			}
			System.out.println("collidetotallistX Map: " + collidetotallistX);
			System.out.println("collidetotallist Map: " + collidetotallist);
			
			//For y direction
			for (int i = 0; i < objectList.size(); i ++){
				for (int counter = 0; counter < collideYlist.size(); counter ++) {
					int position = tempListY.get(counter).getKey();
					//System.out.println("CollsionMarkY: " + collisionMark.toString());
					if (position % 2 == 0) {
						if (collisionMark.size() > 0) {
							collidetotallistY.put(position/2, new ArrayList<Integer>(collisionMark));
						}
						collisionMark.add(position/2);
						
					} else {
						collisionMark.remove((Object) ((position - 1)/2));
					}
					
				}
			}
			System.out.println("collidetotallistY Map: " + collidetotallistY);
			collidetotallistY.clear();
			collisionMark.clear();
			
			collidetotallistX.clear();
			collideXlist.clear();
			collideYlist.clear();
			

			
		}
	}
		
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				JFrame jrame = new JFrame("Physics");
				//jrame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
				TestPhysics tp = new TestPhysics();
				jrame.setContentPane(tp);
				jrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//jrame.setLocationRelativeTo(null);
				jrame.pack();
				jrame.setVisible(true);
			}
		});
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		//g2d.drawString(arg0, arg1, arg2)
		for( PhysicsObject obj :objectList) {
			obj.draw(g);
		}
		//g.drawString(str, x, y)
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

		
		if(timer == null) {
			this.timer = new Timer();	
			Physics task = new Physics();
			this.timer.scheduleAtFixedRate(task, 0, 1000/FRAME);
		} else {
			timer.cancel();
			timer = null;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
