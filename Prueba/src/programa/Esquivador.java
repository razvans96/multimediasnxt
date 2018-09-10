package programa;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.TouchSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.RotateMoveController;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;
import lejos.util.PilotProps;

/**
 * Clase que utiliza Comportamientos para implementar un programa 
 * que esquiva obstáculos
 * 
 * @author Razvan Ioan Stoia
 *
 */
public class Esquivador {
	
	public static void main (String[] aArg)
	throws Exception
	{
     	PilotProps pp = new PilotProps();
    	pp.loadPersistentValues();
    	float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "4.96"));
    	float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "13.0"));
    	RegulatedMotor leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "A"));
    	RegulatedMotor rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "C"));
    	boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE,"false"));
		final RotateMoveController pilot = new DifferentialPilot(wheelDiameter, trackWidth, leftMotor, rightMotor, reverse);
		final UltrasonicSensor dist = new UltrasonicSensor(SensorPort.S3);
		final TouchSensor touch = new TouchSensor(SensorPort.S1);
                pilot.setRotateSpeed(180);
        
		Behavior Avanzar = new Behavior()
		{
			public boolean takeControl() {return dist.getDistance() > 30;}
			
			public void suppress() {
				pilot.stop();
			}
			public void action() {				
				pilot.forward();
			}					
		};
		
		Behavior Obstaculo = new Behavior()
		{
			
			public boolean takeControl() {return dist.getDistance() <= 30;}

			public void suppress() {
				
			}
			
			public void action() {
				int sweep = 30;
				pilot.stop();
				Sound.buzz();
				pilot.travel(-5);
				pilot.rotate(sweep);
				while(dist.getDistance()<=30) {
					Sound.buzz();
					sweep *= -2;
					pilot.rotate(sweep);
				}
				
			}
		};
		
		Behavior Stop = new Behavior()
		{
			
			
			public boolean takeControl() {return touch.isPressed();}

			public void suppress() {
				
			}
			
			public void action() {
				pilot.stop();
			}
		};
		

		Behavior[] bArray = {Obstaculo, Avanzar, Stop};
        LCD.drawString("Preparado para", 0, 1);
        LCD.drawString("avanzar!", 0, 2);
        Button.waitForAnyPress();
	    (new Arbitrator(bArray)).start();
	}
}