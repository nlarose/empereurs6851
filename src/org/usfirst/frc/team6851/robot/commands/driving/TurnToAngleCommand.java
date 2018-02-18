package org.usfirst.frc.team6851.robot.commands.driving;

import org.usfirst.frc.team6851.robot.Dashboard;
import org.usfirst.frc.team6851.robot.commands.CommandBase;

/**
 * Turns to the desired angle of the gyro reading
 * */
public class TurnToAngleCommand extends CommandBase{

	double	wantedAngle;
	double	speed;
	boolean turningLeft;
	
	double ajustableSpeedSpeed;
	boolean isAtAjustableSpeed;
	double gyroAverage = 0;
	double lastGyroValue;

	public TurnToAngleCommand(double wantedAngle, double speed) {
		this.speed = speed;
		this.wantedAngle = wantedAngle;
	}

	@Override
	protected void initialize() {
		Dashboard.nextAutonomousStep();
		String str = String.format("Turning with gyro to %f degre", wantedAngle);
		Dashboard.updateAutonomousStep(str);
		System.out.println(str);
		isAtAjustableSpeed = false;
		lastGyroValue = driveBase.getOrientation();
		turningLeft = wantedAngle < lastGyroValue;
	}

	@Override
	protected void execute() {
		double currentAngle = driveBase.getOrientation();
		double n = 5;
		gyroAverage = gyroAverage *(n-1)/n + (currentAngle - lastGyroValue)/n;
		lastGyroValue = currentAngle;
		
		if(Math.abs( currentAngle - wantedAngle) < 40) {
			AjustableSpeedTurn();
		}else
			Turn();
	}

	private void AjustableSpeedTurn() {
		double currentAngle = driveBase.getOrientation();

		if(ajustableSpeedSpeed == 0) {
			ajustableSpeedSpeed = 0.2 * speed * Math.signum(wantedAngle - currentAngle);
			System.out.println("TROP SLOW");
		}	
		//System.out.println("gyroAverage : " + gyroAverage);
		
		if(Math.abs(gyroAverage) < 0.15) {
			System.out.println("Faster " + ajustableSpeedSpeed);
			ajustableSpeedSpeed *= 1.009;
		}else {
			System.out.println("Slower " + ajustableSpeedSpeed);
			ajustableSpeedSpeed *= 0.972;
		}
				
		String str = String.format("Turning (ending) with gyro to %.2f degre, currently at %.2f degre, at speed %.2f", wantedAngle, currentAngle,ajustableSpeedSpeed);
		Dashboard.updateAutonomousStep(str);
		
		driveBase.drive(0, ajustableSpeedSpeed);
	}

	private void Turn() {
		double currentAngle = driveBase.getOrientation();
		
		double s = speed * Math.signum(wantedAngle - currentAngle);
		double f = 1;

		f = 1 - Math.abs(currentAngle) / Math.abs(wantedAngle);
		
		String str = String.format("Turning with gyro to %.2f degre, currently at %.2f degre, at full speed %.2f", wantedAngle, currentAngle, s);
		Dashboard.updateAutonomousStep(str);
		//f = easeTurn(f);
		f = 1;
		driveBase.drive(0, s * f);
		
		//On garde toujours la derniere vitesse pour commencer smooth sur le ajustableSpeed
		ajustableSpeedSpeed = s*f;
	}
	
	public static double easeTurn(double t) {
		return t * t * 0.25 + t/2;
	}

	protected boolean isFinished() {
		if(turningLeft)
			return driveBase.getOrientation() < wantedAngle;
		else 
			return driveBase.getOrientation() > wantedAngle;
	}

	@Override
	protected void end() {
		driveBase.drive(0, 0);
		String message =  String.format("Turned to %f degre! :)", wantedAngle);
		Dashboard.updateAutonomousStep(message);
		System.out.println(message);
	}

	@Override
	protected void interrupted() {
		driveBase.drive(0, 0);
		String message =  String.format("Turning to %f degre interrupted! :(", wantedAngle);
		Dashboard.updateAutonomousStep(message);
		System.out.println(message);
	}
}