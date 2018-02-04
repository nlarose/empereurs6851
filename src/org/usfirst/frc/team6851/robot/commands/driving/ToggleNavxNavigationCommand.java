package org.usfirst.frc.team6851.robot.commands.driving;

import org.usfirst.frc.team6851.robot.commands.OneShotCommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ToggleNavxNavigationCommand extends OneShotCommandBase{

	@Override
	protected void initialize() {
		drivetrain.correctOrientationWithNavx = !drivetrain.correctOrientationWithNavx;
		SmartDashboard.putBoolean("NavX", drivetrain.correctOrientationWithNavx);
	}
}
