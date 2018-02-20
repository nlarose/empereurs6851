package org.usfirst.frc.team6851.robot.commands.claw;

import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class GrabPowerCube extends CommandBase {

	public GrabPowerCube() {
		setTimeout(10);
	}
	
	@Override
	protected void execute() {
		grabber.FeedPowerCube();
	}
	
	@Override
	protected void end() {
		grabber.stopWheelMotors();
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut() || grabber.hasACube();
	}

}
