package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.commands.claw.DropPowerCube;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.MoveUntilNearWall;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;

public class AutoCenterSwitchSideTight extends AutonomousCommand {
	
	public AutoCenterSwitchSideTight(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 

		addSequential(new TurnRobotCommand(-20 * angleFactor, MoveSpeedFast));
		addSequential(new MoveDistance(10*12, MoveSpeedFast));
		addSequential(new TurnRobotCommand(20 * angleFactor, MoveSpeedFast));
		addSequential(new MoveUntilNearWall(Constant.BUMPER_PROFONDEUR, MoveSpeedFast));

		addSequential(new DropPowerCube());
		
		addSequential(new TurnRobotCommand(90 * angleFactor, MoveSpeedFast));
		addSequential(new MoveDistance(1*12, MoveSpeedFast));
		addSequential(new MoveUntilNearWall(12, MoveSpeedLow));
	}
	
}
