package org.usfirst.frc.team6851.robot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmarterDashboard {

	public static double GetOrCreate(String key, double defaultValue) {
		if(!SmartDashboard.containsKey(key)) {
			SmartDashboard.putNumber(key, defaultValue);
		}
		return SmartDashboard.getNumber(key, defaultValue);
	}
	
	public static double multiplyNumber(String key, double factor) {
		double v = SmartDashboard.getNumber(key, 0);
		SmartDashboard.putNumber(key, v * factor);

		return v * factor;
	}

	public static double addNumber(String key, double defaultValue, double factor) {
		double v = SmartDashboard.getNumber(key, defaultValue);
		SmartDashboard.putNumber(key, v + factor);

		return v + factor;
	}

	public static double clampNumber(String key, double defaultValue, double lower, double upper) {
		double v = SmartDashboard.getNumber(key, defaultValue);
		if (v > upper)
			v = upper;
		else if (v < lower)
			v = lower;
		SmartDashboard.putNumber(key, v);

		return v;
	}

	public static void putNumber(String key, double number, String format) {
		SmartDashboard.putString(key, String.format(format, number));
	}
}
