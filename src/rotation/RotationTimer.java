package rotation;
import java.util.Timer;

public class RotationTimer {

	private static Timer timer;
	
	
	private RotationTimer() {
		
	}
	
	public static Timer getInstance() {
		
		if (timer == null) {
			timer = new Timer();
		} 
		return timer;
		
	}


}
