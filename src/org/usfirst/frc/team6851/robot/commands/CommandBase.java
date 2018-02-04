package org.usfirst.frc.team6851.robot.commands;

import org.usfirst.frc.team6851.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command{

	public static DriveTrain drivetrain = new DriveTrain();
	//public static CameraSubsystem cameras = new CameraSubsystem();
}
