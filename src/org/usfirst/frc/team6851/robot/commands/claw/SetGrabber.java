package org.usfirst.frc.team6851.robot.commands.claw;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class SetGrabber extends CommandBase{

	double TargetPosition;
	double TargetEpsilon;
	

	public SetGrabber(double targetPosition, double targetEpsilon) {
		TargetPosition = targetPosition;
		TargetEpsilon = targetEpsilon;
	}

	@Override
	protected void execute() {
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
