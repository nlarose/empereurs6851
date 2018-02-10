package org.usfirst.frc.team6851.robot.subsystems;

import org.usfirst.frc.team6851.robot.RobotMap;
import org.usfirst.frc.team6851.robot.commands.driving.JoystickDriveCommand;
import org.usfirst.frc.team6851.robot.utils.MathUtils;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {
	public final DifferentialDrive drive = new DifferentialDrive(new Spark(RobotMap.leftMotor), new Spark(RobotMap.rightMotor));
	public final AHRS navx = new AHRS(SPI.Port.kMXP);
	
	public Encoder leftEncoder = tryInitEncoder(RobotMap.leftMotorEncoderA, RobotMap.leftMotorEncoderB);
	public Encoder rightEncoder = tryInitEncoder(RobotMap.rightMotorEncoderA, RobotMap.rightMotorEncoderB);
	
	// HeadingKeeping
	public boolean correctOrientationWithNavx;
	public final double CORRECTION_FACTOR = 0.35;
	public final double MAX_CORRECTION = 0.6;
	public final double MIN_CORRECTION = 0.05;
	
	double orientationheading = 0;
	
	@Override
	protected void initDefaultCommand() {
		navx.reset();
		// TODO Auto-generated method stub
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
			orientationheading = navx.getAngle();
		}else {
			if(rotateValue != 0) {
				// We don't apply correction when the inputed rotation wants to
				// turn. ( not zero )
				orientationheading = navx.getAngle();
			}else {
				double correction = (orientationheading - navx.getAngle() ) * CORRECTION_FACTOR; 
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
		return navx.getAngle();
	}
	public static Encoder tryInitEncoder(int sourceA, int sourceB) {
        try {
            Encoder encoder = new Encoder(sourceA, sourceB);
            return encoder;

        } catch (RuntimeException re) {
            if (re.getMessage().contains("Code: -1029")) {
                System.err.println(
                        "ERRROR! Encoder at source A:" + sourceA + " and source B:" + sourceB + " is not pluged-in.");
            } else {
                System.err.println(re.getMessage());
            }
        }
        return null;
    }
	
}
