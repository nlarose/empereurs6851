package org.usfirst.frc.team6851.robot.commands.driving;

import org.usfirst.frc.team6851.robot.Dashboard;

/**
 * Turns the robot <i>angleOffset</i> degre from the command's start current robot angle.
 * If the robot is at 55 degre when started, and you want to add 40. It will go to 95degre. 
 * */

public class TurnRobotCommand extends TurnToAngleCommand{

	double	angleOffset;

	public TurnRobotCommand(double angleOffset, double speed) {
		super(0,speed);
		this.angleOffset = angleOffset;
	}

	@Override
	protected void initialize() {
		wantedAngle = driveTrain.navx.getAngle() + angleOffset;
		Dashboard.nextAutonomousStep();
	}

}