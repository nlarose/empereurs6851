package org.usfirst.frc.team6851.robot.systemCheckUp;

import java.util.ArrayList;

import org.usfirst.frc.team6851.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SystemCheckUp {

	static ArrayList<ItemCheckUp> checkup = new ArrayList<>();
	
	static boolean AllGo = false;
	public static void init() {
		AllGo = false;
		SmartDashboard.putBoolean("Checkup All Go", false);
		
		
		checkup.add(new DigitalInputChecker(CommandBase.pelle.lowerLimitSwitch, "Lower Limit Switch Check"));
//!!!		checkup.add(new DigitalInputChecker(CommandBase.grabber.upperLimitSwitch, "Upper Limit Switch Check"));
//!!!		checkup.add(new DigitalInputChecker(CommandBase.grabber.powerCubeInSwitch, "Power Cube In Check"));
		//checkup.add(new AnalogPotentiometerInputChecker(CommandBase.grabber.screwHeight, "Scew Height Checker"));
		
		checkup.add(new EncoderChecker(CommandBase.driveBase.leftEncoder, "Left Encoder Checker"));
		checkup.add(new EncoderChecker(CommandBase.driveBase.rightEncoder, "Right Encoder Checker"));
	}

	public static void update() {
		if(AllGo) return;
		
		SmartDashboard.putBoolean("Checkup", false);
		for (ItemCheckUp itemCheckUp : checkup) {
			itemCheckUp.Update();
			itemCheckUp.Show();
		}
		
		for (ItemCheckUp itemCheckUp : checkup) {
			if(!itemCheckUp.IsDone())
				return;
		}
		AllGo = true;
		SmartDashboard.putBoolean("Checkup All Go", true);
	}

}
