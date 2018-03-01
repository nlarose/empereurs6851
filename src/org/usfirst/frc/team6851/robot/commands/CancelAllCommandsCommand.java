package org.usfirst.frc.team6851.robot.commands;

import edu.wpi.first.wpilibj.command.Scheduler;

public class CancelAllCommandsCommand extends OneShotCommandBase {

	@Override
	protected void initialize() {
		Scheduler.getInstance().removeAll();
	}
}
