/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6851.robot;

import java.util.ArrayList;
import java.util.Timer;

import javax.management.monitor.Monitor;

import org.usfirst.frc.team6851.robot.commands.driving.ToggleDriveDirectionCommand;
import org.usfirst.frc.team6851.robot.commands.driving.ToggleNavxNavigationCommand;
import org.usfirst.frc.team6851.robot.commands.driving.ToggleSlowerMoveCommand;
import org.usfirst.frc.team6851.robot.subsystems.DriveType;
import org.usfirst.frc.team6851.robot.utils.Extreme3DPro.Extreme3DProButton;
import org.usfirst.frc.team6851.robot.utils.Gamepad.GamepadAxis;
import org.usfirst.frc.team6851.robot.utils.Gamepad.GamepadButton;
import org.usfirst.frc.team6851.robot.utils.input.AxisInputBase;
import org.usfirst.frc.team6851.robot.utils.input.DualInputInput;
import org.usfirst.frc.team6851.robot.utils.input.JoystickInput;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private DriveType currentDriveType = DriveType.None;
	public Joystick joystick1;
	
	public AxisInputBase moveInput;
	public AxisInputBase rotateInput;
	
	public boolean reverseDriveDirection = false;
	public double driveSpeedFactor = 0.4;
	
	private double t;
	private long lastUpdate;
	
	private ArrayList<JoystickButton> buttons = new ArrayList<>();
	
	public OI() {
		//initTestJoystick();
		joystick1 = new Joystick(0);
		// Drive Speed and reverse controls
		getButton(GamepadButton.A).toggleWhenActive(new ToggleSlowerMoveCommand());
		getButton(GamepadButton.B).toggleWhenActive(new ToggleDriveDirectionCommand());
		getButton(GamepadButton.Start).toggleWhenActive(new ToggleNavxNavigationCommand());
		
		changeIoTo(Dashboard.DrivingStyle.getSelected());
	}

	private void changeIoTo(DriveType driveType) {
		//@ warning for nothing, ca free pas comme il faut!
		for (JoystickButton joystickButton : buttons) {
			joystickButton.free();
		}
		buttons.clear();
		
		if(driveType.equals(DriveType.Joystick)) {
			initJoystick();
		}else {
			initGamePad();
		}
	}

	private void initJoystick() {
		rotateInput = new JoystickInput(joystick1, GamepadAxis.LeftX, 0.1);
		moveInput = new JoystickInput(joystick1, GamepadAxis.LeftY, 0.1, -1);
		
		getButton(Extreme3DProButton._11).toggleWhenActive(new ToggleSlowerMoveCommand());
		getButton(Extreme3DProButton._12).toggleWhenActive(new ToggleDriveDirectionCommand());
		//getButton(GamepadButton.Start).toggleWhenActive(new ToggleNavxNavigationCommand());
		
		currentDriveType = DriveType.Joystick;
		System.out.println("Switching to Joystick");
	}

	private void initGamePad() {
		rotateInput = new JoystickInput(joystick1, GamepadAxis.LeftX, 0.03);
		moveInput   = new DualInputInput( joystick1, GamepadAxis.LeftTrigger, GamepadAxis.RightTrigger, 0.08 );
		
		getButton(GamepadButton.A).toggleWhenActive(new ToggleSlowerMoveCommand());
		getButton(GamepadButton.B).toggleWhenActive(new ToggleDriveDirectionCommand());
		getButton(GamepadButton.Start).toggleWhenActive(new ToggleNavxNavigationCommand());
		
		currentDriveType = DriveType.DrivingGame;
		System.out.println("Switching to Gamepad");
	}
	
	private void initTestJoystick() {
		/*testJoystick = new Joystick(1);
		getButton(testJoystick,GamepadButton.RB).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.moveIndex(1); }
		});
		getButton(testJoystick,GamepadButton.LB).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.moveIndex(-1); }
		});
		getButton(testJoystick,GamepadButton.Start).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.startTest(); }
		});
		getButton(testJoystick,GamepadButton.Back).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.stopTest(); }
		});
		getButton(testJoystick,GamepadButton.A).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.addValue1(-1); }
		});
		getButton(testJoystick,GamepadButton.B).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.addValue1(1); }
		});
		getButton(testJoystick,GamepadButton.X).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.addValue2(-1); }
		});
		getButton(testJoystick,GamepadButton.Y).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.addValue2(1); }
		});
		*/
	}
	
	JoystickButton getButton(Joystick joystick, int buttonValue) {
		JoystickButton b = new JoystickButton(joystick, buttonValue);
		buttons.add(b);
		return b;
	}

	JoystickButton getButton(GamepadButton button) {
		return getButton(joystick1, button.value());
	}
	JoystickButton getButton(Joystick joystick, GamepadButton button) {
		return getButton(joystick, button.value());
	}

	JoystickButton getButton(Extreme3DProButton button) {
		return getButton(joystick1, button.value());
	}
	JoystickButton getButton(Joystick joystick, Extreme3DProButton button) {
		return getButton(joystick, button.value());
	}
	
	private void UpdateTime() {
		t += System.nanoTime()-lastUpdate;
		if(t>1) t=1;
		lastUpdate = System.nanoTime();
	}
	
	public double getMoveSpeed() {
		double speed = moveInput.getInput();
		if(reverseDriveDirection)
			speed *=-1;
		return speed*driveSpeedFactor;
		
	}
	
	public double getTurnSpeed() {
		
		double speed = rotateInput.getInput();
		speed = Math.signum(speed) * speed * speed; // Control of the speed from a linear to a exponential curve.
		
		return speed*driveSpeedFactor;
	}

	public void update() {
		SmartDashboard.putNumber("joyX", joystick1.getX());
		SmartDashboard.putNumber("joyY", joystick1.getY());
		SmartDashboard.putNumber("moveX", moveInput.getInput());
		SmartDashboard.putNumber("moveY", rotateInput.getInput());
		
		if( ! currentDriveType.equals(Dashboard.DrivingStyle.getSelected()) ) {
			changeIoTo(Dashboard.DrivingStyle.getSelected());
		}
	}
}
