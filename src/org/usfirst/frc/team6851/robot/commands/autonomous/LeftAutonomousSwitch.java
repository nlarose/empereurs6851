package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.commands.driving.MoveDistance;
import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftAutonomousSwitch extends AutonomousCommand {
	public LeftAutonomousSwitch() {
		addSequential(new MoveDistance(11*12,MoveSpeedFast));
		addSequential(new TurnRobotCommand(90,MoveSpeedFast));
		addSequential(new MoveDistance(1*12,MoveSpeedFast));
		
	}
}
