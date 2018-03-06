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

	private static final double TOO_MUCH_ERROR_DIFF = 0.1;
	public final DifferentialDrive drive = new DifferentialDrive(new Spark(RobotMap.leftMotor),
			new Spark(RobotMap.rightMotor));
	private final AHRS navx = new AHRS(SPI.Port.kMXP);

	public final Encoder leftEncoder = tryInitEncoder(RobotMap.leftMotorEncoderA, RobotMap.leftMotorEncoderB, true, "Left  Encoder");
	public final Encoder rightEncoder = tryInitEncoder(RobotMap.rightMotorEncoderA, RobotMap.rightMotorEncoderB, false, "Right Encoder");

	public final Ultrasonic leftSensor = null;//tryInitSensor(RobotMap.frontLeftSensorEcho, RobotMap.frontLeftSensorTrigger, "Left  Ultrasonic");
	public final Ultrasonic rightSensor  = null;//= tryInitSensor(RobotMap.frontRightSensorEcho, RobotMap.frontRightSensorTrigger, "Right Ultrasonic");
	
	// HeadingKeeping
	public boolean correctOrientationWithNavx = true;
	public final double CORRECTION_FACTOR_FORWARD = 0.3;
	public final double CORRECTION_FACTOR_BACKWARD = 0.35;
	public final double MAX_CORRECTION_FORWARD = 0.4;
	public final double MAX_CORRECTION_BACKWARD = 0.45;
	public final double MIN_CORRECTION = 0.02;

	double orientationheading = 0;
	
	double lastLeftEncoder;
	double lastRightEncoder;
	double avgLeftEncoder;
	double avgRightEncoder;
	
	double currentRotationCorrection = -0.12;

	@Override
	protected void initDefaultCommand() {
		if (navx != null)
			navx.reset();
		setDefaultCommand(new JoystickDriveCommand());
	}

	public void stopDriving() {
		drive.arcadeDrive(0, 0);
	}

	public void drive(double moveValue, double rotateValue) {
		SmartDashboard.putBoolean("correctOrientationWithNavx", correctOrientationWithNavx);
		//System.out.println("Je resoir de " + moveValue + ", " + rotateValue);
		if (correctOrientationWithNavx)
			rotateValue = correctRotationWithEncoders(moveValue, rotateValue);
		drive.arcadeDrive(moveValue, rotateValue);
	}

	private double correctRotationWithEncoders(double moveValue, double rotateValue) {
		if (moveValue == 0) {
			// Move as normal
			lastLeftEncoder = leftEncoder.get();
			lastRightEncoder = rightEncoder.get();
			//System.out.println("arrete");
		} else {
			if (rotateValue != 0) {
				// We don't apply correction when the inputed rotation wants to
				// turn. ( not zero )
				lastLeftEncoder = leftEncoder.get();
				lastRightEncoder = rightEncoder.get();
				//System.out.println("mais on tourne pas");
			} else {
				double n = 5;
				avgLeftEncoder = avgLeftEncoder * (n - 1) / n + (leftEncoder.get() - lastLeftEncoder) / n;
				avgRightEncoder = avgRightEncoder * (n - 1) / n + (rightEncoder.get() - lastRightEncoder) / n;
				
				lastLeftEncoder = leftEncoder.get();
				lastRightEncoder = rightEncoder.get();
				double diff = avgLeftEncoder - avgRightEncoder;
				
				System.out.println("DriveBase la diff est :" +  currentRotationCorrection);
				if( diff < -TOO_MUCH_ERROR_DIFF) {
					currentRotationCorrection += 0.01;
				}else if( diff > TOO_MUCH_ERROR_DIFF) {
					currentRotationCorrection -= 0.01;
				}
				
				rotateValue += currentRotationCorrection;
				SmartDashboard.putNumber("correction", currentRotationCorrection);
			}
		}
		return rotateValue;
	}
	
	private double correctRotationWithNavX(double moveValue, double rotateValue) {
		if (moveValue == 0) {
			// Move as normal
			orientationheading = getOrientation();
			//System.out.println("arrete");
		} else {
			if (rotateValue != 0) {
				// We don't apply correction when the inputed rotation wants to
				// turn. ( not zero )
				orientationheading = getOrientation();
				//System.out.println("mais on tourne pas");
			} else {
				double factor = (moveValue > 0) ? CORRECTION_FACTOR_FORWARD : CORRECTION_FACTOR_BACKWARD;
				double maxCorrection = (moveValue > 0) ? MAX_CORRECTION_FORWARD : MAX_CORRECTION_BACKWARD;
				double correction = (orientationheading - getOrientation()) * factor;
				//System.out.println("On corrige + " + (orientationheading - getOrientation()));
				correction = MathUtils.clamp(correction, -maxCorrection, maxCorrection);
				correction = Math.pow(correction, 2);
				if (Math.abs(correction) < MIN_CORRECTION)
					correction = 0;
				rotateValue += correction;
				SmartDashboard.putNumber("correction", correction);
			}
		}
		return rotateValue;
	}

	public double getOrientation() {
		if (navx != null)
			return -1 * navx.getAngle();
		else
			return 0;
	}

	public double getLeftEncoderDistance() {
		if (leftEncoder != null)
			return leftEncoder.get();
		else
			return 0;
	}

	public double getRightEncoderDistance() {
		if (rightEncoder != null)
			return rightEncoder.get();
		else
			return 0;
	}

	public double getLeftSensorDistance() {
		if (leftSensor != null)
			return leftSensor.pidGet();
		else
			return 0; // @rebustness If there is no sensor, what do we wan? Infinit distance, negatif?
	}

	public double getRightSensorDistance() {
		if (rightSensor != null)
			return rightSensor.pidGet();
		else
			return 0;
	}

	public boolean isUnderWallDistanceOf(double wallNearness) {
		if (rightSensor != null && rightSensor.pidGet() < wallNearness)
			return true;
		else if (leftSensor != null && leftSensor.pidGet() < wallNearness)
			return true;
		else
			return false;
	}

	public boolean isCalibrating() {
		if (navx != null)
			return navx.isCalibrating();
		else
			return false;
	}

	public boolean isRotating() {
		if (navx != null)
			return navx.isRotating();
		else
			return false;
	}

}
