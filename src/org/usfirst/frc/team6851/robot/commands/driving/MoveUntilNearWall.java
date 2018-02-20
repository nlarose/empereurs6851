package org.usfirst.frc.team6851.robot.commands.driving;

import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class MoveUntilNearWall extends CommandBase{

	private double targetDistance;
	private double speed;
	
	
	public MoveUntilNearWall(double targetDistanceInInchs, double speed) {
		requires(driveBase);
		this.targetDistance = targetDistanceInInchs;
		this.speed = speed;
	}

	@Override
	protected void execute() {
		driveBase.drive(speed, 0);
	}

	@Override
	protected boolean isFinished() {
		return true;
		//return driveBase.isUnderWallDistanceOf(targetDistance);
	}
	
	@Override
	protected void end() {
		driveBase.stopDriving();
	}

}
