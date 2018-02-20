package org.usfirst.frc.team6851.robot.commands;

public class WaitForRobotNotRotating extends CommandBase{

	public WaitForRobotNotRotating() {
		setTimeout(0.6);
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
		//return !driveBase.isRotating();
	}

}
