package org.usfirst.frc.team6851.robot.commands.driving;

import org.usfirst.frc.team6851.robot.Dashboard;
import org.usfirst.frc.team6851.robot.commands.CommandBase;

/**
 * Turns to the desired angle of the gyro reading
 */
public class TurnToAngleCommand extends CommandBase {

	double startingAngle;
	double wantedAngle;
	double speed;
	boolean turningLeft;

	double ajustableSpeedSpeed;
	boolean isAtAjustableSpeed;
	double direction;
	double gyroAverage = 0;
	double lastGyroValue;

	public TurnToAngleCommand(double wantedAngle, double speed) {
		this.speed = speed;
		this.wantedAngle = wantedAngle;
	}

	@Override
	protected void initialize() {
		if (wantedAngle > 90)
			wantedAngle -= 10;
		else if (wantedAngle > 45)
			wantedAngle -= 5;

		Dashboard.nextAutonomousStep();
		String str = String.format("Turning with gyro to %f degre", wantedAngle);
		Dashboard.updateAutonomousStep(str);
		isAtAjustableSpeed = false;
		lastGyroValue = driveBase.getOrientation();
		startingAngle = lastGyroValue;
		turningLeft = wantedAngle < lastGyroValue;
		ajustableSpeedSpeed = 0;
		direction = Math.signum(wantedAngle - lastGyroValue);
	}

	@Override
	protected void execute() {
		double currentAngle = driveBase.getOrientation();
		double t = (currentAngle - startingAngle) / (wantedAngle - startingAngle);

		t = ease(t);

		double s = speed * t;
		s = Math.max(0.45, s);

		if (Math.abs(gyroAverage) < 0.2) {
			System.out.println(String.format("BCP Faster %.2f - %.2f", ajustableSpeedSpeed, t));
			ajustableSpeedSpeed += 0.04;
		} else if (Math.abs(gyroAverage) < 1) {
			System.out.println(String.format("Faster %.2f - %.2f", ajustableSpeedSpeed, t));
			ajustableSpeedSpeed += 0.005;
		} else if (Math.abs(gyroAverage) > 5) {
			System.out.println(String.format("Faster %.2f - %.2f", ajustableSpeedSpeed, t));
			ajustableSpeedSpeed -= 0.001;
		}
		System.out.println(String.format("avg : %.2f", gyroAverage));
		s += ajustableSpeedSpeed;
		s = Math.min(speed, s);

		s *= direction;

		// f = 1 - Math.abs(currentAngle) / Math.abs(wantedAngle);

		String str = String.format("Turning with gyro to %.2f degre, currently at %.2f degre, at full speed %.2f",
				wantedAngle, currentAngle, s);
		Dashboard.updateAutonomousStep(str);
		// f = easeTurn(f) + speed;
		driveBase.drive(0, s);

		double n = 5;
		gyroAverage = gyroAverage * (n - 1) / n + (currentAngle - lastGyroValue) / n;
		lastGyroValue = currentAngle;

		/*
		 * 
		 * if(Math.abs( currentAngle - wantedAngle) < 40) { AjustableSpeedTurn(); }else
		 * Turn();
		 */
	}

	private double ease(double t) {
		if (t < 0.5) {
			return Math.pow((2 * t), 3);
		} else {
			return Math.pow((2 * (1 - t)), 3);
		}
	}

	private void AjustableSpeedTurn() {
		double currentAngle = driveBase.getOrientation();

		if (ajustableSpeedSpeed == 0) {
			ajustableSpeedSpeed = 0.2 * speed * Math.signum(wantedAngle - currentAngle);
			System.out.println("TROP SLOW");
		}
		// System.out.println("gyroAverage : " + gyroAverage);

		if (Math.abs(gyroAverage) < 0.2) {
			System.out.println("BCP Faster " + ajustableSpeedSpeed);
			ajustableSpeedSpeed *= 1.025;
		} else if (Math.abs(gyroAverage) < 0.4) {
			System.out.println("Faster " + ajustableSpeedSpeed);
			ajustableSpeedSpeed *= 1.02;
		} else {
			System.out.println("Slower " + ajustableSpeedSpeed);
			ajustableSpeedSpeed *= 0.972;
		}

		String str = String.format("Turning (ending) with gyro to %.2f degre, currently at %.2f degre, at speed %.2f",
				wantedAngle, currentAngle, ajustableSpeedSpeed);
		Dashboard.updateAutonomousStep(str);

		driveBase.drive(0, ajustableSpeedSpeed);
	}

	private void Turn() {
		double currentAngle = driveBase.getOrientation();

		double s = speed * Math.signum(wantedAngle - currentAngle);
		double f = 1;

		f = 1 - Math.abs(currentAngle) / Math.abs(wantedAngle);

		String str = String.format("Turning with gyro to %.2f degre, currently at %.2f degre, at full speed %.2f",
				wantedAngle, currentAngle, s);
		Dashboard.updateAutonomousStep(str);
		f = easeTurn(f) + speed;
		driveBase.drive(0, s * f);

		// On garde toujours la derniere vitesse pour commencer smooth sur le
		// ajustableSpeed
		ajustableSpeedSpeed = s * f;
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