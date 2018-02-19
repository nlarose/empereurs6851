package org.usfirst.frc.team6851.robot;

import org.usfirst.frc.team6851.robot.commands.CommandBase;
import org.usfirst.frc.team6851.robot.commands.autonomous.*;
import org.usfirst.frc.team6851.robot.subsystems.DriveBase;
import org.usfirst.frc.team6851.robot.subsystems.DriveType;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {

	public static final SendableChooser<Command> LRLChooser = new SendableChooser<>();
	public static final SendableChooser<Command> RLRChooser = new SendableChooser<>();
	public static final SendableChooser<Command> LLLChooser = new SendableChooser<>();
	public static final SendableChooser<Command> RRRChooser = new SendableChooser<>();
	public static final SendableChooser<DriveType> DrivingStyle = new SendableChooser<>();
	
	private static int autonomousStepIndex = 0;
	
	public static void init() {
		SmartDashboard.putData("Auto LRL mode", LRLChooser);
		SmartDashboard.putData("Auto RLR mode", RLRChooser);
		SmartDashboard.putData("Auto LLL mode", LLLChooser);
		SmartDashboard.putData("Auto RRR mode", RRRChooser);

		AddToAutos("An-Side go to Switch throw near", new AutoSideSwitchNear(false), new AutoSideSwitchNear(true));
		AddToAutos("Am-Side go to Switch throw in middle", new AutoSideSwitchCenter(false), new AutoSideSwitchCenter(true));
		AddToAutos("Af-Side go to Switch throw far", new AutoSideSwitchFar(false), new AutoSideSwitchFar(true));
		AddToAutos("Bf-Side exchange fast", new AutoSideExchangeFast(false), new AutoCenterExchange(true));
		AddToAutos("Bf-Side exchange tight", new AutoSideExchangeTight(false), new AutoSideExchangeTight(true));
		AddToAutos("C -Just do the line", new AutoSideJustLine(false), new AutoSideJustLine(true));
		AddToAutos("D -Switch to the side", new AutoSideSwitchSide(false), new AutoSideSwitchSide(true));
		AddToAutos("DANCE", new AutoDance(false), new AutoDance(true));
		AddToAutos("A", new PrintCommand("A Left"), new PrintCommand("A Right"));
		AddToAutos("B", new PrintCommand("B Left"), new PrintCommand("B Right"));
		AddToAutos("C", new PrintCommand("C Left"), new PrintCommand("C Right"));
		AddToAutos("D", new PrintCommand("D Left"), new PrintCommand("D Right"));
		
		
		DrivingStyle.addDefault("Dual analog Gamepad", DriveType.DrivingGame);
		DrivingStyle.addObject("Extreme 3D Pro", DriveType.Joystick);
		SmartDashboard.putData("Input device", DrivingStyle);
		
		resetAutonomousSteps();
	}
	
	private static void AddToAutos(String name, Command autoCommandLeft, Command autoCommandRight) {
		LRLChooser.addObject(name, autoCommandLeft);
		RLRChooser.addObject(name, autoCommandRight);
		LLLChooser.addObject(name, autoCommandLeft);
		RRRChooser.addObject(name, autoCommandRight);
		
		
	}
	
	public static void update() {
		DriveBase drivebase = CommandBase.driveBase();
		SmartDashboard.putNumber("Gyro angle", drivebase.getOrientation());
		SmartDashboard.putBoolean("navx isCalibrating", drivebase.isCalibrating());
		SmartDashboard.putBoolean("Navx Angle Correction", drivebase.correctOrientationWithNavx);
		

		SmartDashboard.putNumber("Screw Height", CommandBase.grabber().getScrewHeight());

		SmartDashboard.putBoolean("Limite switch lower", CommandBase.grabber().getLowerLimitSwitch());
		SmartDashboard.putBoolean("Limite switch upper", CommandBase.grabber().getUpperLimitSwitch());
		SmartDashboard.putBoolean("Limite switch powercube", CommandBase.grabber().getPowerCube());
		
		
		SmartDashboard.putNumber("LeftSensor", drivebase.getLeftSensorDistance());
		SmartDashboard.putNumber("RightSensor", drivebase.getRightSensorDistance());
		
		SmartDashboard.putNumber("Left Motor Encoder", drivebase.getLeftEncoderDistance() / Constant.ENCODER_ROTATION_PER_INCH);
		SmartDashboard.putNumber("Right Motor Encoder", drivebase.getRightEncoderDistance() / Constant.ENCODER_ROTATION_PER_INCH);
		//System.out.println(drivebase.leftEncoder.getRaw() + " , " + drivebase.rightEncoder.getRaw());
		/*System.out.print(" - Ultrasensor -");
		if(drivebase.leftSensor != null) 
			System.out.print(" left:" + drivebase.leftSensor.getRangeInches());
		if(drivebase.rightSensor != null) 
			System.out.print(" right:" + drivebase.rightSensor.getRangeInches());
		System.out.println("");*/
		
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
