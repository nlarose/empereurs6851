package org.usfirst.frc.team6851.robot.utils;

public class MotorEasing {

	double ajustableSpeedSpeed;
	double average;
	double lastT;
	
	public void reset() {
		ajustableSpeedSpeed = 0;
		average = 0;
		lastT = 0;
	}
	
	public double Ease(double t, double speed) {
		double easedT = ease(t);

		double s = speed * easedT;
		s = Math.max(0.45, s);

		if (Math.abs(average) < 0.003 && ajustableSpeedSpeed < speed) {
			//System.out.println(String.format("BCP Faster %.2f - %.2f", ajustableSpeedSpeed, easedT));
			ajustableSpeedSpeed += 0.04;
		} else if (Math.abs(average) < 0.012 && ajustableSpeedSpeed < speed) {
			//System.out.println(String.format("Faster %.2f - %.2f", ajustableSpeedSpeed, easedT));
			ajustableSpeedSpeed += 0.002;
		} else if (ajustableSpeedSpeed > 0) {
			//System.out.println(String.format("SLOWER %.2f - %.2f", ajustableSpeedSpeed, easedT));
			ajustableSpeedSpeed -= 0.01;
		}
		s += ajustableSpeedSpeed;
		s = Math.min(speed, s);

		//System.out.println(String.format("avg: %.4f , t: %.2f, ajust: %.2f, s: %.2f", average,t,ajustableSpeedSpeed,s));

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
