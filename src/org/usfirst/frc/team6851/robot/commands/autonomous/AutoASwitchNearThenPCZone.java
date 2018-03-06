package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.commands.FirstDelay;
import org.usfirst.frc.team6851.robot.commands.SecondDelay;
import org.usfirst.frc.team6851.robot.commands.WaitForRobotNotRotating;
import org.usfirst.frc.team6851.robot.commands.claw.SetGrabberDown;
import org.usfirst.frc.team6851.robot.commands.claw.ThrowPowerCube;
import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.SmashTheWallForJohn;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;
import org.usfirst.frc.team6851.robot.commands.oldAuto.AutonomousCommand;

public class AutoASwitchNearThenPCZone extends AutonomousCommand {
	
	public AutoASwitchNearThenPCZone(boolean angleReversed) {
		double angleFactor = angleReversed ? -1 : 1; 

		addSequential(new FirstDelay());
		addSequential(new MoveDistance(1*12, MoveSpeedMiddle));
		addSequential(new WaitForRobotNotRotating());
		addSequential(new TurnRobotCommand(-20 * angleFactor, MoveSpeedFast));
		addSequential(new MoveDistance(10*12, MoveSpeedFast));
		addSequential(new WaitForRobotNotRotating());
		addSequential(new TurnRobotCommand(110 * angleFactor, TurnSpeedSlow));
		
		addSequential(new SmashTheWallForJohn());
		addSequential(new ThrowPowerCube());
		addSequential(new SecondDelay());
		
		addSequential(new MoveDistance(-1*12, MoveSpeedMiddle));
		addSequential(new WaitForRobotNotRotating());
		addSequential(new TurnRobotCommand(90 * angleFactor, TurnSpeedSlow));
		
		addParallel(new SetGrabberDown(Constant.SCREW_AUTO_SPEED));
		addParallel(new MoveDistance(3*12, MoveSpeedMiddle));
		
		addSequential(new TurnRobotCommand(-90 * angleFactor, TurnSpeedSlow));
		addSequential(new MoveDistance(4*12, MoveSpeedMiddle));
	}
	
}
