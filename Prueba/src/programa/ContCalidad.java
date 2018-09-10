package programa;

import lejos.nxt.*;

public class ContCalidad {
	
	private static TouchSensor ts = new TouchSensor(SensorPort.S1);
	private static ColorSensor cs = new ColorSensor(SensorPort.S2);
	
	public static void main(String[] args) {
		
		int id;
		
		while(!ts.isPressed()) {
			id = cs.getColorID();
			switch(id) {
			case 3:
				
				LCD.clear();
				LCD.drawString("Limon", 4, 3);
				LCD.drawString(":)", 7, 5);
				Button.waitForAnyPress();
				break;
			case 0:
				LCD.clear();
				LCD.drawString("Manzana", 4, 3);
				LCD.drawString(":)", 7, 5);
				Button.waitForAnyPress();
				break;
				
			case 1:
				LCD.clear();
				LCD.drawString("Pera", 4, 3);
				LCD.drawString(":)", 7, 5);
				Button.waitForAnyPress();
				break;
			
			case 2:
				LCD.clear();
				LCD.drawString("Mal estado", 0, 3);
				LCD.drawString(":(", 7, 5);
				Button.waitForAnyPress();
				break;
				
			default:
				LCD.clear();
				LCD.drawString("Leyendo...", 4, 3);
				
				
			}
		}
		LCD.clear();
		Button.waitForAnyPress();
		
	}
}
