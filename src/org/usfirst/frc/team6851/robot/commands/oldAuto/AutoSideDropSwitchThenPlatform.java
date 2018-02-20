package org.usfirst.frc.team6851.robot.commands.oldAuto;

import org.usfirst.frc.team6851.robot.commands.claw.ThrowPowerCube;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.MoveUntilNearWall;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;

public class AutoSideDropSwitchThenPlatform extends AutonomousCommand {

	public AutoSideDropSwitchThenPlatform(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 
		
		addSequential(new MoveDistance(10*12,MoveSpeedFast));
		addSequential(new ThrowPowerCube());
		
		addSequential(new TurnRobotCommand(135 * angleFactor, MoveSpeedFast));
		
		addSequential(new MoveDistance(1*12, MoveSpeedLow));
		addSequential(new TurnRobotCommand(-45 * angleFactor, MoveSpeedFast));
		addSequential(new MoveDistance(4*12, MoveSpeedFast));
		
		addSequential(new MoveUntilNearWall(12, MoveSpeedLow));
	}
	

}
