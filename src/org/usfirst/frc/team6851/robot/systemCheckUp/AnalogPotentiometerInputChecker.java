package org.usfirst.frc.team6851.robot.systemCheckUp;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AnalogPotentiometerInputChecker extends ItemCheckUp{

	public AnalogInput pot;
	public String DashboardKey;
	
	double baseValue;
	boolean Changed;
	
	public AnalogPotentiometerInputChecker(AnalogInput pot, String key) {
		super();
		this.pot = pot;
		this.DashboardKey = key;
		if(pot!= null)
			baseValue  = pot.getVoltage();
	}

	@Override
	public void Update() {
		if(pot != null && baseValue != pot.getVoltage())
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
