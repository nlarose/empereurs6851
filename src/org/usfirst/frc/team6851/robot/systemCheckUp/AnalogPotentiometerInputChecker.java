package org.usfirst.frc.team6851.robot.systemCheckUp;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AnalogPotentiometerInputChecker extends ItemCheckUp{

	public AnalogPotentiometer pot;
	public String DashboardKey;
	
	double baseValue;
	boolean Changed;
	
	public AnalogPotentiometerInputChecker(AnalogPotentiometer pot, String key) {
		super();
		this.pot = pot;
		this.DashboardKey = key;
		if(pot!= null)
			baseValue  = pot.get();
	}

	@Override
	public void Update() {
		if(pot != null && baseValue != pot.get())
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
