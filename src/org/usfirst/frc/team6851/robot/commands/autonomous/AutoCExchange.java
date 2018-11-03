package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.commands.FirstDelay;
import org.usfirst.frc.team6851.robot.commands.SecondDelay;
import org.usfirst.frc.team6851.robot.commands.WaitForRobotNotRotating;
import org.usfirst.frc.team6851.robot.commands.claw.MonterPelle;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.MoveUntilNearWall;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;
import org.usfirst.frc.team6851.robot.commands.oldAuto.AutonomousCommand;

public class AutoCExchange extends AutonomousCommand {
	
	public AutoCExchange(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 

		addSequential(new FirstDelay());
		addSequential(new MoveDistance(3*12, MoveSpeedFast));
		addSequential(new TurnRobotCommand(-90 * angleFactor, TurnSpeedSlow));
		addSequential(new MoveDistance(3*12, MoveSpeedFast));
		addSequential(new TurnRobotCommand(90 * angleFactor, TurnSpeedSlow));

		addSequential(new MoveDistance(3*12, MoveSpeedFast));
		addSequential(new SecondDelay());
		addSequential(new MoveDistance(-3*12, MoveSpeedFast));

		addSequential(new WaitForRobotNotRotating());
		addSequential(new TurnRobotCommand(180 + 20 * angleFactor, TurnSpeedFast));
		

		addSequential(new MoveDistance(1*12, MoveSpeedFast));
		addSequential(new WaitForRobotNotRotating());
		addSequential(new TurnRobotCommand(-20 * angleFactor, TurnSpeedFast));
		addSequential(new MoveDistance(1*12, MoveSpeedFast));
	}
	
}
