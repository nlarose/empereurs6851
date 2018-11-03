package org.usfirst.frc.team6851.robot.subsystems;

import org.usfirst.frc.team6851.robot.SmarterDigitalInput;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public abstract class SubsystemBase extends Subsystem {

	protected Compressor tryInitCompressor(int chanel, String name) {
		try {
			Compressor ObjetCompresseur= new Compressor(chanel);
			// Definir le canal PCM par defaut (pas teste, enlever si ca ne marche pas)
			SensorBase.setDefaultSolenoidModule(chanel);
			return ObjetCompresseur;
		}catch(Exception e) {
			if (e.getMessage().contains("Code: -1029")) {
				System.err.println("ERRROR! Compressor " + name + " at " + chanel + " is not pluged-in.");
			} else {
				System.err.println(e.getMessage());
			}
			return null;
		}
	}

	protected Solenoid tryInitSolenoid(int chanel, String name) {
		try {
			Solenoid ObjetSolenoid= new Solenoid(chanel);
			
			return ObjetSolenoid;
		}catch(Exception e) {
			if (e.getMessage().contains("Code: -1029")) {
				System.err.println("ERRROR! Solenoid " + name + " at " + chanel + " is not pluged-in.");
			} else {
				System.err.println(e.getMessage());
			}
			return null;
		}
	}

	protected DoubleSolenoid tryInitDoubleSolenoid(int forwardChannel, int backwardChannel,String name) {
		try {
			DoubleSolenoid ObjetSolenoid = new DoubleSolenoid(forwardChannel, backwardChannel);
			
			return ObjetSolenoid;
		}catch(Exception e) {
			if (e.getMessage().contains("Code: -1029")) {
				System.err.println("ERRROR! DoubleSolenoid " + name + " at " + forwardChannel + " is not pluged-in.");
			} else {
				System.err.println(e.getMessage());
			}
			return null;
		}
	}
	
	protected TalonSRX tryInitTalonSRX(int chanel, boolean inverted, String name) {
		try {
			TalonSRX talon = new TalonSRX(chanel);
			talon.setInverted(inverted);
			return talon;
		}catch(Exception e) {
			if (e.getMessage().contains("Code: -1029")) {
				System.err.println("ERRROR! TalonSRX " + name + " at " + chanel + " is not pluged-in.");
			} else {
				System.err.println(e.getMessage());
			}
			return null;
		}
	}
	
	protected SmarterDigitalInput tryInitDigitalInput(int chanel, String name) {
		try {
			SmarterDigitalInput di = new SmarterDigitalInput(chanel);
			return di;
		} catch (Exception e) {
			if (e.getMessage().contains("Code: -1029")) {
				System.err.println("ERRROR! DigitalInput " + name + " at " + chanel + " is not pluged-in.");
			} else {
				System.err.println(e.getMessage());
			}
			return null;
		}
	}

	protected AnalogInput tryInitAnalogInput(int chanel, String name) {
		try {
			AnalogInput ai = new AnalogInput(chanel);
			AnalogInput.setGlobalSampleRate(30);

			return ai;
		} catch (Exception e) {
			if (e.getMessage().contains("Code: -1029")) {
				System.err.println("ERRROR! AnalogPotentiometer " + name + " at " + chanel + " is not pluged-in.");
			} else {
				System.err.println(e.getMessage());
			}
			return null;
		}
	}

	protected Encoder tryInitEncoder(int sourceA, int sourceB, boolean reversed, String name) {
		try {
			Encoder encoder = new Encoder(sourceA, sourceB, reversed, EncodingType.k1X);
			LiveWindow.add(encoder);
			return encoder;

		} catch (Exception e) {
			if (e.getMessage().contains("Code: -1029")) {
				System.err.println("ERRROR! Encoder " + name + "at source A:" + sourceA + " and source B:" + sourceB
						+ " is not pluged-in.");
			} else {
				System.err.println(e.getMessage());
			}
		}
		return null;
	}

	protected Ultrasonic tryInitSensor(int ping, int echo, String name) {
		try {
			Ultrasonic sensor = new Ultrasonic(ping, echo);
			sensor.setAutomaticMode(true);
			sensor.setDistanceUnits(Unit.kInches);
			LiveWindow.add(sensor);
			return sensor;
		} catch (Exception e) {
			System.err.println(
					"ERRROR! Sensor " + name + "of ping:" + ping + " and echo :" + echo + " is not pluged-in.");
			if (e.getMessage() != null)
				System.err.println(e.getMessage());
		}
		return null;
	}
}
