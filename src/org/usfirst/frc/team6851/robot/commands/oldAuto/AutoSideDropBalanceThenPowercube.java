package org.usfirst.frc.team6851.robot.commands.oldAuto;

import org.usfirst.frc.team6851.robot.commands.claw.MonterPelle;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.MoveUntilNearWall;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;

public class AutoSideDropBalanceThenPowercube extends AutonomousCommand {

	public AutoSideDropBalanceThenPowercube(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 
		
		addSequential(new MoveDistance(10*12,MoveSpeedFast));
		
	}
}
