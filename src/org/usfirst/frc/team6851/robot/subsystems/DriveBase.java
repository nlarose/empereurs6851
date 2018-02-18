package org.usfirst.frc.team6851.robot.subsystems;

import org.usfirst.frc.team6851.robot.RobotMap;
import org.usfirst.frc.team6851.robot.commands.driving.JoystickDriveCommand;
import org.usfirst.frc.team6851.robot.utils.MathUtils;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase extends SubsystemBase {
	public final DifferentialDrive drive = new DifferentialDrive(new Spark(RobotMap.leftMotor), new Spark(RobotMap.rightMotor));
	private final AHRS navx = new AHRS(SPI.Port.kMXP);
	
	public final Encoder leftEncoder 		= tryInitEncoder(RobotMap.leftMotorEncoderA, RobotMap.leftMotorEncoderB 	, true, "Left  Encoder");
	public final Encoder rightEncoder 		= tryInitEncoder(RobotMap.rightMotorEncoderA, RobotMap.rightMotorEncoderB 	, false ,"Right Encoder");
	
	public final Ultrasonic leftSensor  	= tryInitSensor(RobotMap.frontLeftSensorEcho, RobotMap.frontLeftSensorTrigger	, "Left  Ultrasonic");
	public final Ultrasonic rightSensor 	= tryInitSensor(RobotMap.frontRightSensorEcho, RobotMap.frontRightSensorTrigger , "Right Ultrasonic");
	
	// HeadingKeeping
	public boolean correctOrientationWithNavx;
	public final double CORRECTION_FACTOR = 0.35;
	public final double MAX_CORRECTION = 0.6;
	public final double MIN_CORRECTION = 0.05;
	
	double orientationheading = 0;
	
	@Override
	protected void initDefaultCommand() {
		navx.reset();
		
		setDefaultCommand(new JoystickDriveCommand());
	}

	public void stopDriving() {
		drive.arcadeDrive(0, 0);
	}
	
	public void drive(double moveValue, double rotateValue) {
		if(correctOrientationWithNavx)
			rotateValue = correctRotation(moveValue, rotateValue);
		drive.arcadeDrive(moveValue, rotateValue);
	}

	private double correctRotation(double moveValue, double rotateValue) {
		if(moveValue == 0) {
			//Move as normal
			orientationheading = getOrientation() ;
		}else {
			if(rotateValue != 0) {
				// We don't apply correction when the inputed rotation wants to
				// turn. ( not zero )
				orientationheading = getOrientation() ;
			}else {
				double correction = (orientationheading - getOrientation()  ) * CORRECTION_FACTOR; 
				correction = MathUtils.clamp(correction, -MAX_CORRECTION, MAX_CORRECTION);
				correction = Math.pow(correction,2);
				if(Math.abs(correction) < MIN_CORRECTION)
					correction = 0;
				rotateValue += correction;
				SmartDashboard.putNumber("correction", correction);
			}
		}
		return rotateValue;
	}

	
	public double getOrientation() {
		return -1 * navx.getAngle();
	}
	
	public double getLeftEncoderDistance() {
		if(leftEncoder != null)
			return leftEncoder.get();
		else
			return 0;
	}
	
	public double getRightEncoderDistance() {
		if(rightEncoder != null)
			return rightEncoder.get();
		else
			return 0;
	}
	
	public double getLeftSensorDistance() {
		if(leftSensor != null)
			return leftSensor.pidGet();
		else
			return 0; //@rebustness If there is no sensor, what do we wan? Infinit distance, negatif?
	}
	
	public double getRightSensorDistance() {
		if(rightSensor != null)
			return rightSensor.pidGet();
		else
			return 0;
	}
	
	public boolean isUnderWallDistanceOf(double wallNearness) {
		if(rightSensor != null && rightSensor.pidGet() < wallNearness)
			return true;
		else if(leftSensor != null && leftSensor.pidGet() < wallNearness)
			return true;
		else
			return false;
	}

	public boolean isCalibrating() {
		return navx.isCalibrating();
	}

	public boolean isRotating() {
		return navx.isRotating();
	}	
	
}
