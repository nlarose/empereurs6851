package org.usfirst.frc.team6851.robot.commands;

import org.usfirst.frc.team6851.robot.subsystems.DriveBase;
import org.usfirst.frc.team6851.robot.subsystems.Pelle;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command{

	public static final DriveBase driveBase = new DriveBase();
	public static final Pelle pelle = new Pelle();
}
