package org.usfirst.frc.team6851.robot.commands.driving;

import org.usfirst.frc.team6851.robot.commands.CommandBase;
import org.usfirst.frc.team6851.robot.utils.MotorEasing;

public class SmashTheWallForJohn extends CommandBase {

	MotorEasing morterEasing = new MotorEasing();
	double AverageLeft;
	double AverageRight;
	double LastEncoderLeft;
	double LastEncoderRight;
	
	public SmashTheWallForJohn() {
		requires(driveBase);
	}
	
	@Override
	protected void initialize() {
		AverageLeft = 10;
		AverageRight = 10;
		LastEncoderLeft = driveBase.getLeftEncoderDistance();
		LastEncoderRight = driveBase.getRightEncoderDistance();
	}
	
	@Override
	protected void execute() {
		driveBase.drive(0.5, 0);
		double n = 20;
		AverageLeft = AverageLeft * (n - 1) / n + (driveBase.getLeftEncoderDistance() - LastEncoderLeft) / n;
		LastEncoderLeft = driveBase.getLeftEncoderDistance();
		AverageRight = AverageRight * (n - 1) / n + (driveBase.getRightEncoderDistance() - LastEncoderRight) / n;
		LastEncoderRight = driveBase.getRightEncoderDistance();
		//System.out.println(String.format("SmashJohn %.2f , %.2f", AverageLeft, AverageRight));
	}
	
	@Override
	protected boolean isFinished() {
		 return AverageLeft < 1.2 && AverageRight < 1.2;
	}
	
	@Override
	protected void end() {
		driveBase.stopDriving();
	}

}
