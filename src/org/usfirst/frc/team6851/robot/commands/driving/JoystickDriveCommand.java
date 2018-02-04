package org.usfirst.frc.team6851.robot.commands.driving;

import org.usfirst.frc.team6851.robot.Robot;
import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class JoystickDriveCommand extends CommandBase {

	public JoystickDriveCommand() {
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
		// To avoid the first : Robot Drive... Output not updated often enough.
		drivetrain.stopDriving();
	}

	@Override
	protected void execute() {
		double move = Robot.oi.getMoveSpeed();
		double rotation = Robot.oi.getTurnSpeed();
		drivetrain.drive(move, rotation);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
