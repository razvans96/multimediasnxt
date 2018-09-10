package programa;

import lejos.nxt.*;

public class Rastreador {
	
	private static ColorSensor cs = new ColorSensor(SensorPort.S2);
	private static TouchSensor ts = new TouchSensor(SensorPort.S1);
	private static NXTRegulatedMotor mA = Motor.A;
	private static NXTRegulatedMotor mC = Motor.C;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mA.setSpeed(200);
		mC.setSpeed(200);
		while(!ts.isPressed()) {
			while(cs.getLightValue()<300) {
				mA.rotate(20, true);
				mC.rotate(20, true);
			}
			while(cs.getLightValue()>300) {
				Motor.A.rotate(20, true);
				Motor.C.rotate(20, true);
			}
			while(cs.getLightValue()<300) {
				
			}
			
		
		
	}

}}
