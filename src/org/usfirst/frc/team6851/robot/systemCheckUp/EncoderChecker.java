package org.usfirst.frc.team6851.robot.systemCheckUp;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EncoderChecker extends ItemCheckUp{

	public Encoder encoder;
	public String DashboardKey;
	
	double BaseValue;
	boolean Changed;
	
	public EncoderChecker(Encoder encoder, String dashboardKey) {
		this.encoder = encoder;
		DashboardKey = dashboardKey;
		if(encoder != null)
		BaseValue = encoder.get();
	}

	@Override
	public void Update() {
		if(encoder!= null && BaseValue != encoder.get())
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
