package org.usfirst.frc.team6851.robot.commands;

public class SecondDelay extends CommandBase{

	
	public static double Delay;
	
	@Override
	protected void initialize() {
		setTimeout(Delay);
	}
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}

}
