package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;

public class AutoSideExchange extends AutonomousCommand {

	//Todo c quoi ca ?
	public AutoSideExchange(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 
		
		addSequential(new MoveDistance(10*12,MoveSpeedFast));
		addSequential(new TurnRobotCommand(135 * angleFactor, MoveSpeedFast));
		addSequential(new MoveDistance(2*12, MoveSpeedLow));
		addSequential(new TurnRobotCommand(-45 * angleFactor, MoveSpeedFast));
		addSequential(new MoveDistance(6*12, MoveSpeedFast));
	}
	

}
