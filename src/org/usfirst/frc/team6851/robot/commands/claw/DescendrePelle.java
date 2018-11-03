package org.usfirst.frc.team6851.robot.commands.claw;

import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class DescendrePelle extends CommandBase {

	public DescendrePelle() {
		//setTimeout(10);
	}
	
	@Override
	protected void execute() {
		pelle.Descendre();;
	}
	
	@Override
	protected void end() {
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
