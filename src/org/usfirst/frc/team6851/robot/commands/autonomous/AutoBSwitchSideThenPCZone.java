package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.commands.FirstDelay;
import org.usfirst.frc.team6851.robot.commands.SecondDelay;
import org.usfirst.frc.team6851.robot.commands.claw.MonterPelle;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.SmashTheWallForJohn;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;
import org.usfirst.frc.team6851.robot.commands.oldAuto.AutonomousCommand;

public class AutoBSwitchSideThenPCZone extends AutonomousCommand {
	
	public AutoBSwitchSideThenPCZone(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 

		addSequential(new FirstDelay());
		addSequential(new MoveDistance(10*12 - Constant.ROBOT_SIZE_Y, MoveSpeedFast));

		addSequential(new SmashTheWallForJohn());
		addSequential(new MonterPelle());
		addSequential(new SecondDelay());
		
		addSequential(new MoveDistance(-1*12, MoveSpeedMiddle));
		addSequential(new TurnRobotCommand(90 * angleFactor, TurnSpeedSlow));
		addSequential(new MoveDistance(6, MoveSpeedMiddle));
	}
	
}
