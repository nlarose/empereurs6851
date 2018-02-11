package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.commands.claw.DropPowerCube;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.MoveUntilNearWall;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;

public class AutoSideExchangeFast extends AutonomousCommand {

	public AutoSideExchangeFast(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 

		//Assume im facing toward my team
		addSequential(new MoveDistance(-10*12,MoveSpeedFast));
		addSequential(new TurnRobotCommand(-20 * angleFactor, MoveSpeedFast));
		addSequential(new MoveDistance(9*12,MoveSpeedFast));
		
		addSequential(new TurnRobotCommand(20 * angleFactor, MoveSpeedFast));
	
		addSequential(new DropPowerCube());
		addSequential(new MoveUntilNearWall(Constant.BUMPER_PROFONDEUR, MoveSpeedLow));
	}
	

}
