package org.usfirst.frc.team6851.robot.commands.driving;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.Dashboard;
import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class MoveDistance extends CommandBase {
	private double distanceInRotation;
	private double distanceToFinishRight;
	private double distanceToFinishLeft;
	private double speed;
	
	public MoveDistance(double distanceInInch, double speed) {
		requires(driveBase);
		this.speed = speed;
		this.distanceInRotation = distanceInInch * Constant.ENCODER_ROTATION_PER_INCH;
	}
	
	@Override
	protected void initialize() {
		Dashboard.nextAutonomousStep();
		super.initialize();
		this.distanceToFinishRight = driveBase.getRightEncoderDistance() + distanceInRotation;
		this.distanceToFinishLeft = driveBase.getLeftEncoderDistance() + distanceInRotation;
	}
	
	@Override
	protected void execute() {
		double s = speed*Math.signum(distanceInRotation);
		driveBase.drive(s, 0);
		Dashboard.updateAutonomousStep("Moving at " + s);
	}
	@Override
	protected boolean isFinished() {
		if(Math.signum(distanceInRotation) == 1) {
			return (driveBase.getRightEncoderDistance() >= distanceToFinishRight
					||driveBase.getLeftEncoderDistance() >= distanceToFinishLeft);
			
		} 
		else {
			return (driveBase.getRightEncoderDistance() <= distanceToFinishRight
					||driveBase.getLeftEncoderDistance() <= distanceToFinishLeft);
		}
		
	}
	@Override
	protected void end() {
		driveBase.drive(0,0);
	}

}
