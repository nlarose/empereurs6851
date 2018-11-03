package org.usfirst.frc.team6851.robot.commands.oldAuto;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.commands.claw.MonterPelle;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.MoveUntilNearWall;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;

public class AutoCenterDropThenPowerCube extends AutonomousCommand {
	
	public AutoCenterDropThenPowerCube(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 

		addSequential(new MoveDistance(5*12, MoveSpeedFast));
		addSequential(new TurnRobotCommand(-90 * angleFactor, MoveSpeedFast));
		addSequential(new MoveDistance(3*12, MoveSpeedFast));
		addSequential(new TurnRobotCommand(90 * angleFactor, MoveSpeedFast));

		addSequential(new MoveUntilNearWall(Constant.BUMPER_PROFONDEUR, MoveSpeedFast));
		addSequential(new MonterPelle());
		

		addSequential(new MoveUntilNearWall(-5, MoveSpeedFast));
		addSequential(new TurnRobotCommand(90 * angleFactor, MoveSpeedFast));
		addSequential(new MoveUntilNearWall(12, MoveSpeedLow));
	}
	
}
