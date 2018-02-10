package org.usfirst.frc.team6851.robot;

import org.usfirst.frc.team6851.robot.commands.CommandBase;
import org.usfirst.frc.team6851.robot.commands.autonomous.DoNothing;
import org.usfirst.frc.team6851.robot.commands.autonomous.TestAutonomous;
import org.usfirst.frc.team6851.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6851.robot.subsystems.DriveType;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {

	public static final SendableChooser<Command> chooser = new SendableChooser<>();
	public static final SendableChooser<DriveType> DrivingStyle = new SendableChooser<>();
	
	private static int autonomousStepIndex = 0;
	
	public static void init() {
		chooser.addObject("Do nothing", new DoNothing());
		chooser.addDefault("Test", new TestAutonomous());
		SmartDashboard.putData("Auto mode", chooser);
		
		DrivingStyle.addDefault("Dual analog Gamepad", DriveType.DrivingGame);
		DrivingStyle.addObject("Extreme 3D Pro", DriveType.Joystick);
		SmartDashboard.putData("Input device", DrivingStyle);
		
		resetAutonomousSteps();
	}
	
	public static void update() {
		DriveTrain drivetrain = CommandBase.drivetrain;
		SmartDashboard.putNumber("Gyro angle", drivetrain.getOrientation());
		SmartDashboard.putBoolean("navx isCalibrating", drivetrain.navx.isCalibrating());
		
		
	}
	
	public static void resetAutonomousSteps() {
		autonomousStepIndex = 0;
		SmartDashboard.putString("Autonomous Steps", "");
	}
	
	public static void nextAutonomousStep() {
		autonomousStepIndex++;
	}
	
	public static void updateAutonomousStep(String message) {
		String s = String.format("%d - %s", autonomousStepIndex, message);
		SmartDashboard.putString("Autonomous Steps", s);
	}
}
