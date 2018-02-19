package org.usfirst.frc.team6851.robot.systemCheckUp;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DigitalInputChecker extends ItemCheckUp{

	public DigitalInput di;
	public String DashboardKey;
	
	boolean falseHappen = false;
	boolean trueHappen = false;
	
	public DigitalInputChecker(DigitalInput di, String dashboardKey) {
		this.di = di;
		DashboardKey = dashboardKey;
	}


	public void Update() {
		if(di != null && di.get())
			trueHappen = true;
		if(di != null && !di.get())
			falseHappen = true;
	}
	
	public void Show(){
		SmartDashboard.putBoolean(DashboardKey, IsDone());
	}


	@Override
	public boolean IsDone() {
		return trueHappen && falseHappen;
	}

}
