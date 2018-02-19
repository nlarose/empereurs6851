package org.usfirst.frc.team6851.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class DigitalInputRICHARD extends DigitalInput{

	public DigitalInputRICHARD(int channel) {
		super(channel);
	}
	
	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		return !super.get();
	}

}
