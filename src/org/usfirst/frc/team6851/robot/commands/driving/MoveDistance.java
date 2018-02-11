package org.usfirst.frc.team6851.robot.commands.driving;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.commands.CommandBase;
import org.usfirst.frc.team6851.robot.subsystems.DriveTrain;

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
		this.distanceToFinishRight = driveTrain.rightEncoder.get() + distanceInRotation;
		this.distanceToFinishLeft = driveTrain.leftEncoder.get() + distanceInRotation;
	}
	
	@Override
	protected void execute() {
		driveTrain.drive(speed*Math.signum(distanceInRotation), 0);
		super.execute();
	}
	@Override
	protected boolean isFinished() {
		if(Math.signum(distanceInRotation) == 1) {
			return (driveTrain.leftEncoder.get()>= distanceToFinishLeft
					||driveTrain.rightEncoder.get()>= distanceToFinishRight);
			
		} 
		else {
			return (driveTrain.leftEncoder.get() <= distanceToFinishLeft
					||driveTrain.rightEncoder.get()<= distanceToFinishRight);
		}
		
	}
	@Override
	protected void end() {
		driveTrain.drive(0,0);
		super.end();
	}

}
