package rotation;

public class RotationUtility {
	
	private static RotationUtility rotationUtility;
	private double[] doubleArray = new double[2];
	private RotationUtility() {
		
	}
	
	public static RotationUtility getInstance(){
		if (rotationUtility == null) {
			rotationUtility = new RotationUtility();
		}
		return rotationUtility;
	}
	
	public double[] doRotate(double iniX, double iniY, double originX, double originY, double angleInRad) {
		
		clearDoubleArray();
		//System.out.println(originX);
		doubleArray[0] = (iniX-originX) * Math.cos(angleInRad) - (iniY - originY) * Math.sin(angleInRad) + originX;
		//System.out.println("(iniX-originX) * Math.cos(angleInRad):" +(iniX-originX) * Math.cos(angleInRad));
		//System.out.println("(iniY - originY) * Math.sin(angleInRad):" + (iniY - originY) * Math.sin(angleInRad));
		//System.out.println("iniX:" + iniX);
		doubleArray[1] = (iniX-originX) * Math.sin(angleInRad) + (iniY - originY) * Math.cos(angleInRad) + originY;
		return doubleArray;
	}
	
	private void clearDoubleArray() {	
		doubleArray[0] = 0;
		doubleArray[1] = 0;
	}
}
