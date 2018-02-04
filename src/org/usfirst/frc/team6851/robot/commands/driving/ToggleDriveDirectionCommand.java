package org.usfirst.frc.team6851.robot.commands.driving;

import org.usfirst.frc.team6851.robot.Robot;
import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class ToggleDriveDirectionCommand extends CommandBase {

	@Override
	protected void initialize() {
		Robot.oi.reverseDriveDirection = !Robot.oi.reverseDriveDirection; 
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
