package org.usfirst.frc.team6851.robot.commands;

import org.usfirst.frc.team6851.robot.Robot;
import org.usfirst.frc.team6851.robot.subsystems.DriveBase;
import org.usfirst.frc.team6851.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command{

	public static DriveBase driveBase() {
		return Robot.driveBase;
	}
	public static Grabber grabber() {
		return Robot.grabber;
	}
}
