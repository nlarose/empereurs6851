package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.commands.claw.ThrowPowerCube;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;

public class AutoSideSwitchSide extends AutonomousCommand {
	
	public AutoSideSwitchSide(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 
		
		addSequential(new MoveDistance(11*12, MoveSpeedFast));
		addSequential(new TurnRobotCommand(90 * angleFactor, MoveSpeedFast));
		addSequential(new MoveDistance(1*12, MoveSpeedFast));
		
		addSequential(new ThrowPowerCube());
	}
	
}
