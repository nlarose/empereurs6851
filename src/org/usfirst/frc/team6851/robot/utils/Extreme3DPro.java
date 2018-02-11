package org.usfirst.frc.team6851.robot.utils;

import org.usfirst.frc.team6851.robot.utils.Gamepad.GamepadButton;

import edu.wpi.first.wpilibj.DriverStation;

public class Extreme3DPro {
	public enum Extreme3DProButton {
		_1(1),
		_2(2),
		_3(3),
		_4(4),
		_5(5),
		_6(6),
		_7(7),
		_8(8),
		_9(9),
		_10(10),
		_11(11),
		_12(12);
		
		private byte value;
		Extreme3DProButton(int value) {
			this.value = (byte) value;
		}
		
		public byte value() {
			return this.value;
		}
	}
	
	public enum GamepadAxis {
		LeftX(0),
		LeftY(1),
		LeftTrigger(2),
		RightTrigger(3),
		RightX(4),
		RightY(5);
		
		private byte value;
		GamepadAxis(int value) {
			this.value = (byte) value;
		}
		
		public byte value() {
			return this.value;
		}
	}
	
	private int port;
	private DriverStation driverStation;
	
	Extreme3DPro(int port) {
		this.port = port;
		driverStation = DriverStation.getInstance();
	}
	
	public double getAxis(GamepadAxis axis) {
		return driverStation.getStickAxis(port, axis.value());
	}

	public boolean getButton(GamepadButton button) {
		return driverStation.getStickButton(port,  button.value());
	}
}
