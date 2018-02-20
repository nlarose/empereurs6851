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
		if(grabber.getScrewHeight() < TargetPosition)
			grabber.Raise(Constant.SCREW_AUTO_SPEED);
		else if(grabber.getScrewHeight() > TargetPosition)
			grabber.Lower(Constant.SCREW_AUTO_SPEED);
	}
	
	@Override
	protected boolean isFinished() {
		return Math.abs(grabber.getScrewHeight() - TargetPosition) < TargetEpsilon;
	}

}
