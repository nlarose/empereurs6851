package org.usfirst.frc.team6851.robot.commands.claw;

import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class RaiseGrabber extends CommandBase {

	@Override
	protected void execute() {
		grabber.Raise (0.2);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	private void End() {
		grabber.stopScrewMotor();
		// TODO Auto-generated method stub

	}
}
