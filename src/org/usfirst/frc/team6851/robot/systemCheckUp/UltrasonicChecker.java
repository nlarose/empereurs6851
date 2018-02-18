package org.usfirst.frc.team6851.robot.systemCheckUp;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UltrasonicChecker extends ItemCheckUp {

	public Ultrasonic sonic;
	public String DashboardKey;

	double baseValue;
	boolean changedFromBaseValue;

	public UltrasonicChecker(Ultrasonic sonic, String key) {
		this.sonic = sonic;
		this.DashboardKey = key;
		if (sonic != null)
			baseValue = sonic.getRangeInches();
	}

	@Override
	public void Update() {
		if (sonic != null && baseValue != sonic.getRangeInches())
			changedFromBaseValue = true;
	}

	@Override
	public void Show() {
		SmartDashboard.putBoolean(DashboardKey, IsDone());
	}

	@Override
	public boolean IsDone() {
		return changedFromBaseValue;
	}

}
