package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.commands.FirstDelay;
import org.usfirst.frc.team6851.robot.commands.SecondDelay;
import org.usfirst.frc.team6851.robot.commands.WaitForRobotNotRotating;
import org.usfirst.frc.team6851.robot.commands.claw.MonterPelle;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.SmashTheWallForJohn;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;
import org.usfirst.frc.team6851.robot.commands.oldAuto.AutonomousCommand;

public class AutoCLeftSwitch extends AutonomousCommand {

	public AutoCLeftSwitch(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 

		addSequential(new FirstDelay());
		addSequential(new MoveDistance(3*12,MoveSpeedFast));
		addSequential(new TurnRobotCommand(-90 * angleFactor, MoveSpeedFast));
		addSequential(new MoveDistance(4*12,MoveSpeedMiddle));
		addSequential(new WaitForRobotNotRotating());
		addSequential(new TurnRobotCommand(90 * angleFactor, TurnSpeedSlow));
		addSequential(new MoveDistance(3*12,MoveSpeedMiddle));

		addSequential(new SmashTheWallForJohn());
		addSequential(new MonterPelle());
		addSequential(new SecondDelay());

		addSequential(new MoveDistance(-1*12,MoveSpeedMiddle));
		addSequential(new TurnRobotCommand(90 * angleFactor, TurnSpeedSlow));
		addSequential(new WaitForRobotNotRotating());
		
	
	}
	

}
