package programa;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class CalibradorLuz {

	private static TouchSensor ts = new TouchSensor(SensorPort.S1);
	private static ColorSensor cs = new ColorSensor(SensorPort.S2);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(!ts.isPressed()) {
			LCD.drawInt(cs.getLightValue(), 0, 0);
		}
		Button.waitForAnyPress();
	}

}
