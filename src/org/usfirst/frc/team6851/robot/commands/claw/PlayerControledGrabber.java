package org.usfirst.frc.team6851.robot.commands.claw;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.Robot;
import org.usfirst.frc.team6851.robot.commands.CommandBase;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class PlayerControledGrabber extends CommandBase {

	public PlayerControledGrabber() {
		requires(grabber);
	}

	@Override
	protected void execute() {
		double hInput = Robot.oi.screwHeightInput.getInput();
		grabber.MoveHeight(hInput);

		if (Robot.oi.grabberLeftMotor.get())
			grabber.grabberMotorLeft.set(ControlMode.Velocity, Constant.GRABBER_WHEEL_GRAB_SPEED);

		grabber.grabberMotorLeft.set(ControlMode.Velocity, 0);

		if (Robot.oi.grabberRightMotor.get())
			grabber.grabberMotorRight.set(ControlMode.Velocity, Constant.GRABBER_WHEEL_GRAB_SPEED);
		else
			grabber.grabberMotorLeft.set(ControlMode.Velocity, 0);
		
		if(Robot.oi.grabberThrow.get()) {
			grabber.grabberMotorRight.set(ControlMode.Velocity, Constant.GRABBER_WHEEL_THROW_SPEED);
			grabber.grabberMotorLeft.set(ControlMode.Velocity, Constant.GRABBER_WHEEL_THROW_SPEED);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
