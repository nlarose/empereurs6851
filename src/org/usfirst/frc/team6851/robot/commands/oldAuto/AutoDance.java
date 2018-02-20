package org.usfirst.frc.team6851.robot.commands.oldAuto;

import org.usfirst.frc.team6851.robot.commands.FirstDelay;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoDance extends AutonomousCommand {
	
	public AutoDance(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 

		addSequential(new FirstDelay());
		//addSequential(new MoveDistance(1*12, MoveSpeedFast));
		//addSequential(new WaitCommand(0.5));
		addSequential(new TurnRobotCommand(180 * angleFactor, TurnSpeedFast));
		/*addSequential(new WaitCommand(0.5));
		addSequential(new MoveDistance(3*12,MoveSpeedMiddle));
		addSequential(new WaitCommand(0.5));
		addSequential(new TurnRobotCommand(90*angleFactor, MoveSpeedMiddle));
		addSequential(new WaitCommand(0.5));
		addSequential(new MoveDistance(3*12, MoveSpeedMiddle));
		addSequential(new WaitCommand(0.5));
		addSequential(new TurnRobotCommand(90*angleFactor, MoveSpeedMiddle));
		addSequential(new WaitCommand(0.5));
		addSequential(new MoveDistance(3*12, MoveSpeedMiddle));
		addSequential(new WaitCommand(0.5));
		addSequential(new TurnRobotCommand(90*angleFactor, MoveSpeedMiddle));
		addSequential(new WaitCommand(0.5));
		addSequential(new MoveDistance(3*12, MoveSpeedMiddle));
		addSequential(new WaitCommand(0.5));
		addSequential(new TurnRobotCommand(135*angleFactor, MoveSpeedMiddle));
		addSequential(new WaitCommand(0.5));
		addSequential(new MoveDistance(-1*12, MoveSpeedLow));*/
		
	}
	
}
