package org.usfirst.frc.team6851.robot.commands.claw;

import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class ThrowPowerCube extends CommandBase {

	public ThrowPowerCube() {
		setTimeout(2);
	}
	
	@Override
	protected void execute() {
		grabber.ThrowPowerCube();
	}
	
	@Override
	protected void end() {
		grabber.stopWheelMotors();
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

}
