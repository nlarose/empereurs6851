package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.commands.FirstDelay;
import org.usfirst.frc.team6851.robot.commands.WaitForRobotNotRotating;
import org.usfirst.frc.team6851.robot.commands.claw.MonterPelle;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.SmashTheWallForJohn;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;
import org.usfirst.frc.team6851.robot.commands.oldAuto.AutonomousCommand;

public class AutoASwitchCenter extends AutonomousCommand {
	
	public AutoASwitchCenter(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 

		addSequential(new FirstDelay());
		addSequential(new MoveDistance(1*12, MoveSpeedMiddle));
		addSequential(new WaitForRobotNotRotating());
		addSequential(new TurnRobotCommand(-20 * angleFactor, MoveSpeedFast));
		addSequential(new MoveDistance(11*12, MoveSpeedFast));
		addSequential(new WaitForRobotNotRotating());
		addSequential(new TurnRobotCommand(110 * angleFactor, MoveSpeedFast));
		
		addSequential(new SmashTheWallForJohn());
		addSequential(new MonterPelle());
	}
	
}
