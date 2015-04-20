package rotation;

import static rotation.RotationConstant.FRAME;
import static rotation.RotationConstant.SCREEN_HEIGHT;
import static rotation.RotationConstant.SCREEN_WIDTH;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JPanel;

public class RotationPanel extends JPanel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer timer; 
	private RotationManager rotationManager;
	
	public RotationPanel() {
		this.setBackground(Color.BLUE);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));  
		timer = new Timer();
		PhysicsTask ptask = new PhysicsTask(this);
		timer.scheduleAtFixedRate(ptask, 0, 1000/FRAME);
		rotationManager = RotationManager.getInstance();
		intializeObject(rotationManager);
		this.setFocusable(true);
		this.addKeyListener(this);
		
	}
	
	private void intializeObject(RotationManager rotationManager) {
		Dimension position = new Dimension();
		position.setSize(400.0,250.0);
		Dimension pivot = new Dimension();
		pivot.setSize(450.0,275.0);
		Rectangle rect1 = new Rectangle(100, 50, pivot, position);
		rect1.setAngle(1);
		rotationManager.addRotationObject(rect1);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		for (int i=0; i < rotationManager.getRotationObjectsListSize(); i++) {
			rotationManager.getRotationObject(i).draw(g);
		}
	}
	
	public void doFasterCCRotation() {
		RotationObject  rotationObject;
		for (int i=0; i < rotationManager.getRotationObjectsListSize(); i++) {
			rotationObject = rotationManager.getRotationObject(i);
			rotationObject.setAngle(rotationObject.getAngle() + 1);
		}
	}
	
	public void doSlowerCRotation() {
		RotationObject  rotationObject;
		for (int i=0; i < rotationManager.getRotationObjectsListSize(); i++) {
			rotationObject = rotationManager.getRotationObject(i);			
			rotationObject.setAngle(rotationObject.getAngle() - 1);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Key Pressed:" + e.getKeyCode());
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			
			doFasterCCRotation();
			
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			
			doSlowerCRotation();
			
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
