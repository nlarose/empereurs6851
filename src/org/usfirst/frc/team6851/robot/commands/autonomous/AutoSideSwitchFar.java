package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.commands.claw.ThrowPowerCube;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.MoveUntilNearWall;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;

public class AutoSideSwitchFar extends AutonomousCommand {
	
	public AutoSideSwitchFar(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 

		addSequential(new MoveDistance(1*12, MoveSpeedFast));
		addSequential(new TurnRobotCommand(10 * angleFactor, MoveSpeedFast));
		addSequential(new MoveDistance(9*12, MoveSpeedFast));
		addSequential(new TurnRobotCommand(-10 * angleFactor, MoveSpeedFast));
		
		addSequential(new MoveUntilNearWall(Constant.BUMPER_PROFONDEUR, MoveSpeedLow));
		addSequential(new ThrowPowerCube());
	}
	
}
