/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6851.robot.subsystems;

import org.usfirst.frc.team6851.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Grabber extends Subsystem {
	
	public static double SCREW_HEIGHT_UPPER_LIMIT = 0.666;
	public static double SCREW_HEIGHT_LOWER_LIMIT = 0.42;
	
	public DigitalInput lowerLimitSwitch = new DigitalInput(RobotMap.lowerLimitSwitch);
	public DigitalInput upperLimitSwitch = new DigitalInput (RobotMap.upperLimitSwitch);
	public TalonSRX screwMotor = new TalonSRX (RobotMap.screwMotor);
	public TalonSRX grabberMotorLeft = new TalonSRX (RobotMap.grabberMotorLeft);
	public TalonSRX grabberMotorRight = new TalonSRX (RobotMap.grabberMotorRight);
	public AnalogInput screwHeight = new AnalogInput (RobotMap.screwHeightPotentiometer);
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void Raise(double speed) {
		screwMotor.set(ControlMode.Velocity, 0);
		if (upperLimitSwitch.get()) return;
		if (screwHeight.getVoltage()> SCREW_HEIGHT_UPPER_LIMIT) return;
		screwMotor.set(ControlMode.Velocity, speed);
	}
	
	public void Lower (double speed) {
		screwMotor.set(ControlMode.Velocity, 0);
		if (lowerLimitSwitch.get()) return;
		if (screwHeight.getVoltage()< SCREW_HEIGHT_LOWER_LIMIT) return;
		screwMotor.set(ControlMode.Velocity, -speed);
	}
	public void stopScrewMotor() {
		screwMotor.set(ControlMode.Velocity, 0);
	}
	
}