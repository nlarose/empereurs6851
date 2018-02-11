package org.usfirst.frc.team6851.robot.commands.driving;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.commands.CommandBase;
import org.usfirst.frc.team6851.robot.subsystems.DriveBase;

public class MoveDistance extends CommandBase {
	private double distanceInRotation;
	private double distanceToFinishRight;
	private double distanceToFinishLeft;
	private double speed;
	
	public MoveDistance(double distanceInInch, double speed) {
		this.speed = speed;
		this.distanceInRotation = distanceInInch * Constant.ENCODER_ROTATION_PER_INCH;
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		this.distanceToFinishRight = driveBase.getRightEncoderDistance() + distanceInRotation;
		this.distanceToFinishLeft = driveBase.getLeftEncoderDistance() + distanceInRotation;
	}
	
	@Override
	protected void execute() {
		driveBase.drive(speed*Math.signum(distanceInRotation), 0);
		super.execute();
	}
	@Override
	protected boolean isFinished() {
		if(Math.signum(distanceInRotation) == 1) {
			return (driveBase.getRightEncoderDistance()>= distanceToFinishLeft
					||driveBase.getLeftEncoderDistance()>= distanceToFinishRight);
			
		} 
		else {
			return (driveBase.getRightEncoderDistance() <= distanceToFinishLeft
					||driveBase.getLeftEncoderDistance()<= distanceToFinishRight);
		}
		
	}
	@Override
	protected void end() {
		driveBase.drive(0,0);
		super.end();
	}

}
