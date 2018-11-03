package org.usfirst.frc.team6851.robot.commands.claw;

import org.usfirst.frc.team6851.robot.commands.CommandBase;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class CalibrateClaw extends CommandBase{

	public CalibrateClaw() {
		requires(grabber);
	}
	
	@Override
	protected void execute() {
		grabber.screwMotor.set(ControlMode.PercentOutput, 0.4);
	}
	
	@Override
	protected boolean isFinished() {
		return grabber.upperLimitSwitch.get();
	}
	
	@Override
	protected void end() {
		grabber.screwHeightEncoder.reset();
	}

}
