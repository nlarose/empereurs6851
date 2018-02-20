package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;

public class AutoSideJustLine extends AutonomousCommand {

	public AutoSideJustLine(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 
		
		addSequential(new FirstDelay());
		addSequential(new MoveDistance(1*12,MoveSpeedLow));
	}
	

}
