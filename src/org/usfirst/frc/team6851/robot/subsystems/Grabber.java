package org.usfirst.frc.team6851.robot.subsystems;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.RobotMap;
import org.usfirst.frc.team6851.robot.commands.claw.PlayerControledGrabber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

public class Grabber extends SubsystemBase {

	public static double SCREW_HEIGHT_UPPER_LIMIT = 3360; // 2750
	public static double SCREW_HEIGHT_LOWER_LIMIT = 2220; // 1610

	public final DigitalInput lowerLimitSwitch = tryInitDigitalInput(RobotMap.lowerLimitSwitch, "LowerLimiteSwitch");
	public final DigitalInput upperLimitSwitch = tryInitDigitalInput(RobotMap.upperLimitSwitch, "UpperLimitSwitch");
	public final DigitalInput powerCubeInSwitch = null; //tryInitDigitalInput(RobotMap.powerCubeInSwitch, "PowerCubeInSwitch");
	
	public final TalonSRX screwMotor = tryInitTalonSRX(RobotMap.screwMotor, true, "Screw Motor");
	
	public final TalonSRX grabberMotorLeft = tryInitTalonSRX(RobotMap.grabberMotorLeft, false, "Grabber Left");
	public final TalonSRX grabberMotorRight  = tryInitTalonSRX(RobotMap.grabberMotorRight, false, "Grabber Right");
	public final AnalogInput screwHeight = tryInitAnalogInput(RobotMap.screwHeightPotentiometer, "ScrewHeightPotentiometer");

	
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new PlayerControledGrabber());
	}

	public void FeedPowerCube() {
		grabberMotorLeft.set(ControlMode.PercentOutput, Constant.GRABBER_WHEEL_THROW_SPEED);
		grabberMotorRight.set(ControlMode.PercentOutput, -Constant.GRABBER_WHEEL_THROW_SPEED);
	}

	public void ThrowPowerCube() {
		grabberMotorRight.set(ControlMode.PercentOutput, Constant.GRABBER_WHEEL_THROW_SPEED);
		grabberMotorLeft.set(ControlMode.PercentOutput, -Constant.GRABBER_WHEEL_THROW_SPEED);
	}

	public void Raise(double speed) {
		if (IsAtUpperLimit()) {
			screwMotor.set(ControlMode.PercentOutput, 0);
		}
		else
			screwMotor.set(ControlMode.PercentOutput, speed);
	}

	public void Lower(double speed) {
		if (IsAtLowerLimit())
			screwMotor.set(ControlMode.PercentOutput, 0);
		else
			screwMotor.set(ControlMode.PercentOutput, -speed);
	}
	
	public void MoveHeight(double speed) {
		if(speed < 0)
			Lower(-speed);
		else
			Raise(speed);
	}
	
	public double getScrewHeight() {
		if(screwHeight == null)
			return -1;
		else 
			return screwHeight.getValue();
	}
	
	public boolean getLowerLimitSwitch() {
		return lowerLimitSwitch != null && lowerLimitSwitch.get();
	}
	
	public boolean getUpperLimitSwitch() {
		return upperLimitSwitch != null && upperLimitSwitch.get();
	}
	
	public boolean getPowerCube() {
		return powerCubeInSwitch != null && powerCubeInSwitch.get();
	}

	public void stopScrewMotor() {
		screwMotor.set(ControlMode.PercentOutput, 0);
	}

	public boolean IsAtLowerLimit() {
		return (lowerLimitSwitch != null && lowerLimitSwitch.get())
				|| (screwHeight != null && screwHeight.getValue() < SCREW_HEIGHT_LOWER_LIMIT);
	}

	public boolean IsAtUpperLimit() {
		return (upperLimitSwitch != null && upperLimitSwitch.get())
				|| (screwHeight != null && screwHeight.getValue() > SCREW_HEIGHT_UPPER_LIMIT);
	}

	public void stopWheelMotors() {
		grabberMotorLeft.set(ControlMode.PercentOutput, 0);
		grabberMotorRight.set(ControlMode.PercentOutput, 0);
	}

	public boolean hasACube() {
		return powerCubeInSwitch != null && powerCubeInSwitch.get();
	}

}
