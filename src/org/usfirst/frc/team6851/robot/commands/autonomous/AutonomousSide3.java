package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousSide3 extends AutonomousCommand {

	public AutonomousSide3() {
		addSequential(new MoveDistance(10*12,MoveSpeedFast));
		addSequential(new TurnRobotCommand(135,MoveSpeedFast));
		addSequential(new MoveDistance(2*12,MoveSpeedLow));
		addSequential(new TurnRobotCommand(-45,MoveSpeedFast));
		addSequential(new MoveDistance(6*12,MoveSpeedFast));
	}
	

}
