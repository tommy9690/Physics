package rotation;

import java.util.TimerTask;

public class PhysicsTask extends TimerTask{
	
	private long lastFrameTime = 0;
	private long nextFrameTime = 0;
	//private long firstFrameTime = 0;
	private long timeElapsed;
	private RotationManager rotationManager;
	private RotationPanel panel;
	
	public PhysicsTask(RotationPanel panel) {
		this.panel = panel;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(nextFrameTime != 0){
			lastFrameTime = nextFrameTime;
			
		} else {
			lastFrameTime = System.nanoTime();
			//firstFrameTime = lastFrameTime;
		}
		nextFrameTime = System.nanoTime();
		timeElapsed = nextFrameTime - lastFrameTime;
/*			System.out.println("lastFrameTime: " + lastFrameTime);
		System.out.println("nextFrameTime: " + nextFrameTime);
		System.out.println("timeElapsed: " + (lastFrameTime - firstFrameTime)/(double)1000000000);
		System.out.println("timeBetweenFrame: " + timeElapsed);*/
		rotationManager = RotationManager.getInstance();
		rotationManager.updateAll(timeElapsed);
		panel.repaint();
		//update(timeElapsed);
		//System.out.println("Got Repaint!!");
		//repaint();
	}
}
