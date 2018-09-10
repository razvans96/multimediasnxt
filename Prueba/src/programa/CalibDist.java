package programa;

import lejos.nxt.*;

public class CalibDist {
	
	private static TouchSensor ts = new TouchSensor(SensorPort.S1);
	private static UltrasonicSensor us = new UltrasonicSensor(SensorPort.S3);
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(!ts.isPressed()) {
			LCD.drawInt(us.getDistance(), 0, 0);
		}
		Button.waitForAnyPress();
	}

}
