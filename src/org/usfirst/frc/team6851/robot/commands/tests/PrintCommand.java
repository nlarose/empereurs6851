package org.usfirst.frc.team6851.robot.commands.tests;

import org.usfirst.frc.team6851.robot.commands.OneShotCommandBase;

public class PrintCommand extends OneShotCommandBase{

	String message;
	
	public PrintCommand(String message) {
		this.message = message;
	}
	
	@Override
	protected void initialize() {
		System.out.println(message);
	}
}
