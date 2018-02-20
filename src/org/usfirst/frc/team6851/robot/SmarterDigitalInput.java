package org.usfirst.frc.team6851.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class SmarterDigitalInput extends DigitalInput{

	public SmarterDigitalInput(int channel) {
		super(channel);
	}
	
	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		return !super.get();
	}

}
