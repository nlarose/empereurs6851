package org.usfirst.frc.team6851.robot.commands.autonomous;

import org.usfirst.frc.team6851.robot.commands.driving.TurnRobotCommand;
import org.usfirst.frc.team6851.robot.commands.tests.AngleABTest;
import org.usfirst.frc.team6851.robot.commands.tests.PrintGyroWhenStable;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

public class TestAutonomous extends CommandGroup{

	public TestAutonomous() {
		addSequential(new PrintGyroWhenStable());
		addSequential(new AngleABTest());
		addSequential(new TurnRobotCommand(-90, 0.6));
		addSequential(new PrintGyroWhenStable());
		addSequential(new AngleABTest());
		addSequential(new PrintCommand("Done"));
	}
}
