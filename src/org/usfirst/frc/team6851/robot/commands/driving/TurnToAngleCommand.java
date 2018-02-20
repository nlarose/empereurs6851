package org.usfirst.frc.team6851.robot.commands.driving;

import org.usfirst.frc.team6851.robot.Dashboard;
import org.usfirst.frc.team6851.robot.commands.CommandBase;
import org.usfirst.frc.team6851.robot.utils.MotorEasing;

/**
 * Turns to the desired angle of the gyro reading
 */
public class TurnToAngleCommand extends CommandBase {

	double startingAngle;
	double wantedAngle;
	double speed;
	boolean turningLeft;

	double direction;
	double gyroAverage = 0;
	double lastGyroValue;
	
	MotorEasing motorEasing = new MotorEasing();

	public TurnToAngleCommand(double wantedAngle, double speed) {
		requires(driveBase);
		this.speed = speed;
		this.wantedAngle = wantedAngle;
	}

	@Override
	protected void initialize() {

		lastGyroValue = driveBase.getOrientation();
		startingAngle = lastGyroValue;
		turningLeft = wantedAngle < lastGyroValue;
		direction = Math.signum(wantedAngle - lastGyroValue	);
		if (Math.abs(wantedAngle) > 90) {
			wantedAngle -= 10 * direction;
		} else if (Math.abs(wantedAngle) > 45) {
			wantedAngle -= 5 * direction;
		}
		motorEasing.reset();
		
		Dashboard.nextAutonomousStep();
		String str = String.format("Turning with gyro From %.1fto %.1f degre",lastGyroValue, wantedAngle);
		Dashboard.updateAutonomousStep(str);
	}

	@Override
	protected void execute() {
		double currentAngle = driveBase.getOrientation();
		double t = (currentAngle - startingAngle) / (wantedAngle - startingAngle);

		double s = motorEasing.Ease(t, speed);
		
		s *= direction;

		String str = String.format("Turning with gyro to %.2f degre, currently at %.2f degre, at full speed %.2f",
				wantedAngle, currentAngle, s);
		Dashboard.updateAutonomousStep(str);
		driveBase.drive(0, s);
	}

	

	public static double easeTurn(double t) {
		return t * t * 0.25;
	}

	protected boolean isFinished() {
		if (turningLeft)
			return driveBase.getOrientation() < wantedAngle;
		else
			return driveBase.getOrientation() > wantedAngle;
	}

	@Override
	protected void end() {
		driveBase.drive(0, 0);
		String message = String.format("Turned to %f degre! :)", wantedAngle);
		Dashboard.updateAutonomousStep(message);
		System.out.println(message);
	}

	@Override
	protected void interrupted() {
		driveBase.drive(0, 0);
		String message = String.format("Turning to %f degre interrupted! :(", wantedAngle);
		Dashboard.updateAutonomousStep(message);
		System.out.println(message);
	}
}