package org.usfirst.frc.team6851.robot;

import java.util.ArrayList;

import org.usfirst.frc.team6851.robot.commands.CommandBase;
import org.usfirst.frc.team6851.robot.systemCheckUp.AnalogInputChecker;
import org.usfirst.frc.team6851.robot.systemCheckUp.DigitalInputChecker;
import org.usfirst.frc.team6851.robot.systemCheckUp.EncoderChecker;
import org.usfirst.frc.team6851.robot.systemCheckUp.ItemCheckUp;
import org.usfirst.frc.team6851.robot.systemCheckUp.UltrasonicChecker;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SystemCheckUp {

	static ArrayList<ItemCheckUp> checkup = new ArrayList<>();
	
	static boolean AllGo = false;
	public static void init() {
		AllGo = false;
		SmartDashboard.putBoolean("Checkup All Go", false);
		
		checkup.add(new DigitalInputChecker(CommandBase.grabber.lowerLimitSwitch, "LowerLimitSitchCheck"));
		checkup.add(new DigitalInputChecker(CommandBase.grabber.upperLimitSwitch, "UpperimitSitchCheck"));
		checkup.add(new AnalogInputChecker(CommandBase.grabber.screwHeight, "Scew Height Checker"));
		
		checkup.add(new EncoderChecker(CommandBase.driveBase.leftEncoder, "Left Encoder Checker"));
		checkup.add(new EncoderChecker(CommandBase.driveBase.rightEncoder, "Right Encoder Checker"));
		checkup.add(new UltrasonicChecker(CommandBase.driveBase.leftSensor, "Left Sensor Checker"));
		checkup.add(new UltrasonicChecker(CommandBase.driveBase.rightSensor, "Right Sensor Checker"));
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
