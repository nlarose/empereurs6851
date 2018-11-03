package org.usfirst.frc.team6851.robot.commands.oldAuto;

import org.usfirst.frc.team6851.robot.commands.claw.MonterPelle;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.MoveUntilNearWall;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;

public class AutoSideDropBalanceThenPlatform extends AutonomousCommand {

	public AutoSideDropBalanceThenPlatform(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 
		
		addSequential(new MoveDistance(20*12,MoveSpeedFast));
		addSequential(new MonterPelle());
		
		addSequential(new MoveDistance(-10*12,MoveSpeedFast));
		
		addSequential(new TurnRobotCommand(90 * angleFactor, MoveSpeedFast));
		
		
		addSequential(new MoveDistance(2*12, MoveSpeedLow));
		addSequential(new TurnRobotCommand(90 * angleFactor, MoveSpeedFast));
	}
	

}
