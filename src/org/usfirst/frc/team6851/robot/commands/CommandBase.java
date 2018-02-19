package org.usfirst.frc.team6851.robot.commands;

import org.usfirst.frc.team6851.robot.subsystems.DriveBase;
import org.usfirst.frc.team6851.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command{

	public static final DriveBase driveBase = new DriveBase();
	public static final Grabber grabber = new Grabber();
}
