package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.commands.FirstDelay;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.oldAuto.AutonomousCommand;

public class AutoJustLine extends AutonomousCommand {

	public AutoJustLine(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 
		
		addSequential(new FirstDelay());
		addSequential(new MoveDistance(8*12,0.6));
	}
	

}
