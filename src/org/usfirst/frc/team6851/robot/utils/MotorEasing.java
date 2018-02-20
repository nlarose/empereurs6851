package org.usfirst.frc.team6851.robot.utils;

public class MotorEasing {

	double ajustableSpeedSpeed;
	double average;
	double lastT;
	
	public double Ease(double t, double speed) {
		t = ease(t);

		double s = speed * t;
		s = Math.max(0.45, s);

		if (Math.abs(average) < 0.2) {
			//System.out.println(String.format("BCP Faster %.2f - %.2f", ajustableSpeedSpeed, t));
			ajustableSpeedSpeed += 0.04;
		} else if (Math.abs(average) < 1) {
			//System.out.println(String.format("Faster %.2f - %.2f", ajustableSpeedSpeed, t));
			ajustableSpeedSpeed += 0.005;
		} else if (Math.abs(average) > 5) {
			//System.out.println(String.format("SLOWER %.2f - %.2f", ajustableSpeedSpeed, t));
			ajustableSpeedSpeed -= 0.001;
		}
		System.out.println(String.format("avg : %.2f", average));
		s += ajustableSpeedSpeed;
		s = Math.min(speed, s);
		

		double n = 5;
		average = average * (n - 1) / n + (t - lastT) / n;
		lastT = t;
		
		return s;
	}

	private static double ease(double t) {
		if (t < 0.5) {
			return Math.pow((2 * t), 3);
		} else {
			return Math.pow((2 * (1 - t)), 3);
		}
	}
}
