package programa;

import lejos.nxt.*;

public class CalibradorColor {
	private static TouchSensor ts = new TouchSensor(SensorPort.S1);
	private static ColorSensor cs = new ColorSensor(SensorPort.S2);
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(!ts.isPressed()) {
			LCD.drawInt(cs.getColorID(), 0, 0);
		}
		Button.waitForAnyPress();
	}
	
	
}
