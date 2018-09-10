package programa;

import lejos.nxt.*;


public class ContadorObjetos {
	
	private static int con = 0;
	private static UltrasonicSensor us = new UltrasonicSensor(SensorPort.S3);
	private static TouchSensor ts = new TouchSensor(SensorPort.S1);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean pressed = ts.isPressed();
		while(!pressed) {
			pressed = ts.isPressed();
		}
		System.out.println("Comienzo.");
		vuelta();
		System.out.println("Objetos detectados: " + con);
		Button.waitForAnyPress();
	}

	private static void vuelta() {
		boolean evitarDuplicados = false;
		for(int i=0 ; i< 150; i++) {
			Motor.A.setSpeed(200);
			Motor.C.setSpeed(200);
			Motor.A.rotate(30, true);
			Motor.C.rotate(-30, true);
			int distancia = us.getDistance();
			if(distancia >=0 && distancia <=20) {
				if(!evitarDuplicados) {
					Sound.systemSound(false, 0);
					con++;
					evitarDuplicados = true;
				}
			}else {
				evitarDuplicados=false;
			}
		}
	}

}
