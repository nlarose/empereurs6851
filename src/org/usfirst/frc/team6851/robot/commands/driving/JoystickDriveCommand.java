package org.usfirst.frc.team6851.robot.commands.driving;

import org.usfirst.frc.team6851.robot.Robot;
import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class JoystickDriveCommand extends CommandBase {

	public JoystickDriveCommand() {
		requires(driveBase);
	}

	@Override
	protected void initialize() {
		// To avoid the first : Robot Drive... Output not updated often enough.
		driveBase.stopDriving();
	}

	@Override
	protected void execute() {
		double move = Robot.oi.getMoveSpeed();
		double rotation = Robot.oi.getTurnSpeed();
		driveBase.drive(move, rotation);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
