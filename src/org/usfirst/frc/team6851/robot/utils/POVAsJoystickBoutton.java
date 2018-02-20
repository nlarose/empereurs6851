package org.usfirst.frc.team6851.robot.utils;

import org.usfirst.frc.team6851.robot.utils.Gamepad.GamepadButton;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class POVAsJoystickBoutton extends JoystickButton{

	GenericHID joystick;
	int povNumber;
	public int angle;
	
	public enum POVDirection{Up,Right,Down,Left}
	
	public POVAsJoystickBoutton(GenericHID joystick, int povNumber, POVDirection direction) {
		super(joystick, GamepadButton.Back.value());
		this.joystick = joystick;
		this.povNumber = povNumber;
		switch (direction) {
			case Up: angle = 0; break;
			case Right: angle = 90; break;
			case Down: angle = 180; break;
			case Left: angle = 270; break;
		}
	}

	@Override
	public boolean get() {
		return joystick.getPOV(povNumber) == angle;
	}
}
