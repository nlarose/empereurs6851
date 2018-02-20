package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.commands.claw.ThrowPowerCube;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.MoveUntilNearWall;
import org.usfirst.frc.team6851.robot.commands.driving.SmashTheWallForJohn;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoSideSwitchNear extends AutonomousCommand {
	
	public AutoSideSwitchNear(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 

		addSequential(new FirstDelay());
		addSequential(new MoveDistance(1*12, MoveSpeedMiddle));
		addSequential(new WaitCommand(0.5));
		addSequential(new TurnRobotCommand(-10 * angleFactor, MoveSpeedFast));
		addSequential(new MoveDistance(10*12, MoveSpeedFast));
		addSequential(new WaitCommand(0.5));
		addSequential(new TurnRobotCommand(100 * angleFactor, MoveSpeedFast));
		
		addSequential(new SmashTheWallForJohn());
		addSequential(new ThrowPowerCube());
		addSequential(new SecondDelay());
		
		
	}
	
}
