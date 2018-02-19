package org.usfirst.frc.team6851.robot.commands.claw;

import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class SetGrabberUp extends CommandBase {

	double Speed;
	
	public SetGrabberUp(double speed) {
		Speed = speed;
		requires(grabber);
	}

	@Override
	protected void execute() {
		grabber.Raise(Speed);
	}
	
	@Override
	protected boolean isFinished() {
		return grabber.IsAtUpperLimit();
	}
	
	@Override
	protected void end() {
		grabber.stopScrewMotor();
	}
}
