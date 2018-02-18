/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6851.robot.subsystems;

import org.usfirst.frc.team6851.robot.RobotMap;
import org.usfirst.frc.team6851.robot.commands.claw.PlayerControledGrabber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Grabber extends SubsystemBase {

	public static double SCREW_HEIGHT_UPPER_LIMIT = 0.666;
	public static double SCREW_HEIGHT_LOWER_LIMIT = 0.42;

	public DigitalInput lowerLimitSwitch = tryInitDigitalInput(RobotMap.lowerLimitSwitch, "LowerLimiteSwitch");
	public DigitalInput upperLimitSwitch = tryInitDigitalInput(RobotMap.upperLimitSwitch, "UpperLimitSwitch");
	public TalonSRX screwMotor = new TalonSRX(RobotMap.screwMotor);
	public TalonSRX grabberMotorLeft = new TalonSRX(RobotMap.grabberMotorLeft);
	public TalonSRX grabberMotorRight = new TalonSRX(RobotMap.grabberMotorRight);
	public AnalogInput screwHeight = tryInitAnalogInput(RobotMap.screwHeightPotentiometer, "ScrewHeightPotentiometer");

	public void initDefaultCommand() {
		setDefaultCommand(new PlayerControledGrabber());
	}
	
	public void FeedPowerCube() {
		grabberMotorLeft.set(ControlMode.Velocity, -0.2);
		grabberMotorRight.set(ControlMode.Velocity, -0.2);
	}

	public void ThrowPowerCube() {
		grabberMotorLeft.set(ControlMode.Velocity, 0.2);
		grabberMotorRight.set(ControlMode.Velocity, 0.2);
	}

	public void Raise(double speed) {
		if (IsAtUpperLimit())
			screwMotor.set(ControlMode.Velocity, 0);
		else
			screwMotor.set(ControlMode.Velocity, speed);
	}

	public void Lower(double speed) {
		if (IsAtLowerLimit())
			screwMotor.set(ControlMode.Velocity, 0);
		else
			screwMotor.set(ControlMode.Velocity, -speed);
	}
	
	public void MoveHeight(double speed) {
		if(speed < 0)
			Lower(-speed);
		else
			Raise(speed);
	}

	public void stopScrewMotor() {
		screwMotor.set(ControlMode.Velocity, 0);
	}

	public boolean IsAtLowerLimit() {
		return (lowerLimitSwitch != null && lowerLimitSwitch.get())
				|| (screwHeight != null && screwHeight.getVoltage() < SCREW_HEIGHT_LOWER_LIMIT);
	}

	public boolean IsAtUpperLimit() {
		return (upperLimitSwitch != null && upperLimitSwitch.get())
				|| (screwHeight != null && screwHeight.getVoltage() > SCREW_HEIGHT_UPPER_LIMIT);
	}

	public void stopWheelMotors() {
		grabberMotorLeft.set(ControlMode.Velocity, 0);
		grabberMotorRight.set(ControlMode.Velocity, 0);
	}

}