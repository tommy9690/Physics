package rotation;

import java.awt.Graphics;
import java.util.ArrayList;

public class RotationManager {
	
	private static final ArrayList<RotationObject> rotationObjectList = new ArrayList<RotationObject>();
	private static RotationManager rotationManager;
	
	private RotationManager() {
		
	}
	
	public static RotationManager getInstance() {
		
		if (rotationManager == null) {
			rotationManager = new RotationManager();
		} 
		return rotationManager;
		
	}

	
	public void addRotationObject(RotationObject rotationObject) {
		rotationObjectList.add(rotationObject);
	}
	
	public void removeRotationObject(int index) {
		rotationObjectList.remove(index);
	}
	
	public RotationObject getRotationObject(int index) {
		return rotationObjectList.get(index);
	}
	
	public int getRotationObjectsListSize() {
		return rotationObjectList.size();
	}
	
	public void updateAll(long timeElapsed) {
		for (RotationObject rotationObject : rotationObjectList) {
			rotationObject.update(timeElapsed);
		}
	}
	
	public void drawAll(Graphics g) {
		for (RotationObject rotationObject : rotationObjectList) {
			rotationObject.draw(g);
		}
	}
}
