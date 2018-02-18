package org.usfirst.frc.team6851.robot.systemCheckUp;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AnalogInputChecker extends ItemCheckUp{

	public AnalogInput ai;
	public String DashboardKey;
	
	double baseValue;
	boolean Changed;
	
	public AnalogInputChecker(AnalogInput ai, String key) {
		super();
		this.ai = ai;
		this.DashboardKey = key;
		if(ai!= null)
			baseValue  = ai.getVoltage();
	}

	@Override
	public void Update() {
		if(ai != null && baseValue != ai.getVoltage())
			Changed = true;
	}
	
	@Override
	public void Show() {
		SmartDashboard.putBoolean(DashboardKey, IsDone());
	}

	@Override
	public boolean IsDone() {
		return Changed;
	}

	

}
