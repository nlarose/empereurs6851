package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.commands.FirstDelay;
import org.usfirst.frc.team6851.robot.commands.WaitForRobotNotRotating;
import org.usfirst.frc.team6851.robot.commands.claw.SetGrabberDown;
import org.usfirst.frc.team6851.robot.commands.claw.ThrowPowerCube;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.MoveUntilNearWall;
import org.usfirst.frc.team6851.robot.commands.driving.SmashTheWallForJohn;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;
import org.usfirst.frc.team6851.robot.commands.oldAuto.AutonomousCommand;

public class AutoASwitchFarThenPlatZone extends AutonomousCommand {
	
	public AutoASwitchFarThenPlatZone(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 

		addSequential(new FirstDelay());
		addSequential(new MoveDistance(1*12, MoveSpeedMiddle));
		addSequential(new WaitForRobotNotRotating());
		addSequential(new TurnRobotCommand(-10 * angleFactor, MoveSpeedFast));
		addSequential(new MoveDistance(13*12, MoveSpeedFast));
		addSequential(new WaitForRobotNotRotating());
		addSequential(new TurnRobotCommand(100 * angleFactor, MoveSpeedFast));
		
		addSequential(new SmashTheWallForJohn());
		addSequential(new ThrowPowerCube());
		
		addSequential(new MoveDistance(-1*12, MoveSpeedMiddle));
		addSequential(new WaitForRobotNotRotating());
		addSequential(new TurnRobotCommand(-90 * angleFactor, MoveSpeedFast));
		
		addParallel(new SetGrabberDown(Constant.SCREW_AUTO_SPEED));
		addParallel(new MoveDistance(5*12, MoveSpeedMiddle));
		
		addSequential(new TurnRobotCommand(90 * angleFactor, MoveSpeedFast));
		addParallel(new MoveDistance(4*12, MoveSpeedMiddle));

		addSequential(new TurnRobotCommand(90 * angleFactor, MoveSpeedFast));
	}
	
}
