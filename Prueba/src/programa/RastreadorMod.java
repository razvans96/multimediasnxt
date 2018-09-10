package programa;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.TouchSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.ColorSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.RotateMoveController;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.PilotProps;

/**
 * Clase que utiliza Comportamientos para implementar un programa 
 * que siga una línea a modo de rastreador.
 * 
 * @author Razvan Ioan Stoia
 *
 */
public class RastreadorMod {
	
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
		final ColorSensor light = new ColorSensor(SensorPort.S2);
		final TouchSensor touch = new TouchSensor(SensorPort.S1);
                pilot.setRotateSpeed(180);
        
		Behavior EnLinea = new Behavior()
		{
			public boolean takeControl() {return light.getColorID() == 7;}
			
			public void suppress() {
				pilot.stop();
			}
			public void action() {
				Sound.beep();
				pilot.forward();
                while(light.getColorID()==7 ) {
                	Sound.beep();
                	Thread.yield();
                }
			}					
		};
		
		Behavior FueraLinea = new Behavior()
		{
			private boolean suppress = false;
			
			public boolean takeControl() {return light.getColorID() != 7;}

			public void suppress() {
				suppress = true;
			}
			
			public void action() {
				int sweep = 10;
				while (!suppress) {
					pilot.rotate(sweep,true);
					while (!suppress && pilot.isMoving()) Thread.yield();
					sweep *= -2;
				}
				pilot.stop();
				suppress = false;
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
		

		Behavior[] bArray = {FueraLinea, EnLinea, Stop};
        LCD.drawString("Preparado para", 0, 1);
        LCD.drawString("rastrear!", 0, 2);
        Button.waitForAnyPress();
	    (new Arbitrator(bArray)).start();
	}
}