package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.commands.FirstDelay;
import org.usfirst.frc.team6851.robot.commands.WaitForRobotNotRotating;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;
import org.usfirst.frc.team6851.robot.commands.oldAuto.AutonomousCommand;

public class AutoBExchange extends AutonomousCommand {

	public AutoBExchange(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 

		addSequential(new FirstDelay());
		addSequential(new MoveDistance(7*12,MoveSpeedFast));
		//addSequential(new TurnRobotCommand(-20 * angleFactor, MoveSpeedFast));
		addSequential(new MoveDistance(-3*12,MoveSpeedMiddle));

		addSequential(new WaitForRobotNotRotating());
		addSequential(new TurnRobotCommand(90 * angleFactor, TurnSpeedSlow));
		addSequential(new MoveDistance(3*12,MoveSpeedMiddle));
		addSequential(new WaitForRobotNotRotating());
		addSequential(new TurnRobotCommand(90 * angleFactor, TurnSpeedSlow));
		addSequential(new MoveDistance(1*12,MoveSpeedMiddle));
		
	
	}
	

}
