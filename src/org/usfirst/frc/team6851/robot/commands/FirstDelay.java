package org.usfirst.frc.team6851.robot.commands;

public class FirstDelay extends CommandBase{

	
	public static double Delay;
	
	
	@Override
	protected void initialize() {
		setTimeout(Delay);
	}
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}

}
