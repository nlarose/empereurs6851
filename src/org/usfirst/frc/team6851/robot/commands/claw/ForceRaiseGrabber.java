package org.usfirst.frc.team6851.robot.commands.claw;

import org.usfirst.frc.team6851.robot.commands.CommandBase;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class ForceRaiseGrabber extends CommandBase{

	@Override
	protected void execute() {
		grabber.screwMotor.set(ControlMode.PercentOutput, 0.6);
	}
	
	@Override
	protected boolean isFinished() {
		return grabber.getUpperLimitSwitch();
	}

}
