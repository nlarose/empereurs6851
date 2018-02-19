package org.usfirst.frc.team6851.robot.commands.claw;

import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class LowerGrabber extends CommandBase {

	public LowerGrabber() {
		requires(grabber);
	}
	
	@Override
	protected void execute() {
		grabber.Lower(0.2);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		grabber.stopScrewMotor();
	}
}
