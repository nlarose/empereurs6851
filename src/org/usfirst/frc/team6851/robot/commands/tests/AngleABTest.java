package org.usfirst.frc.team6851.robot.commands.tests;

import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class AngleABTest extends CommandBase{

	private double lastAngle;
	
	@Override
	protected boolean isFinished() {
		return !driveTrain.navx.isRotating();
	}

	@Override
	protected void end() {
		double rotation = 0;
		double currentAngle = driveTrain.getOrientation();
		if(lastAngle < currentAngle)
			rotation = currentAngle - lastAngle;
		else
			rotation = -1 * (lastAngle - currentAngle);
		
		System.out.println(String.format("Done a %.2f degre rotation", rotation));
		lastAngle = currentAngle;
	}
}
