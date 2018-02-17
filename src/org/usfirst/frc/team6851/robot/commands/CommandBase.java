package org.usfirst.frc.team6851.robot.commands;

import org.usfirst.frc.team6851.robot.subsystems.Autonomous;
import org.usfirst.frc.team6851.robot.subsystems.DriveBase;
import org.usfirst.frc.team6851.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command{

	public static DriveBase driveBase = new DriveBase();
	public static Autonomous auto = new Autonomous();
	//public static CameraSubsystem cameras = new CameraSubsystem();
	public static Grabber grabber = new Grabber();
}
