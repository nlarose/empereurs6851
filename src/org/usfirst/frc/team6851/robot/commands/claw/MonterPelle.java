package org.usfirst.frc.team6851.robot.commands.claw;

import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class MonterPelle extends CommandBase {

	public MonterPelle() {
		//setTimeout(0.75);
	}
	
	@Override
	protected void execute() {
		pelle.Monter();
	}
	
	@Override
	protected void end() {

	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
