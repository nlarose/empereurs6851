package org.usfirst.frc.team6851.robot.subsystems;

import org.usfirst.frc.team6851.robot.commands.autonomous.DoNothing;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Autonomous extends Subsystem{

	public Autonomous() {
		super();
		
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DoNothing());
	}

}
