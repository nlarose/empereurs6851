package org.usfirst.frc.team6851.robot;

import org.usfirst.frc.team6851.robot.commands.CommandBase;
import org.usfirst.frc.team6851.robot.commands.autonomous.*;
import org.usfirst.frc.team6851.robot.commands.claw.ForceRaiseGrabber;
import org.usfirst.frc.team6851.robot.commands.oldAuto.AutoDance;
import org.usfirst.frc.team6851.robot.subsystems.DriveBase;
import org.usfirst.frc.team6851.robot.utils.SmarterDashboard;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {

	public static final SendableChooser<Command> LRLChooser = new SendableChooser<>();
	public static final SendableChooser<Command> RLRChooser = new SendableChooser<>();
	public static final SendableChooser<Command> LLLChooser = new SendableChooser<>();
	public static final SendableChooser<Command> RRRChooser = new SendableChooser<>();
	public static double LRLFirstDelay;
	public static double RLRFirstDelay;
	public static double LLLFirstDelay;
	public static double RRRFirstDelay;
	public static double LRLSecondDelay;
	public static double RLRSecondDelay;
	public static double LLLSecondDelay;
	public static double RRRSecondDelay;

	
	private static int autonomousStepIndex = 0;
	
	public static void init() {
		SmartDashboard.putData(new ForceRaiseGrabber());
		
		SmartDashboard.putData("Auto LRL mode", LRLChooser);
		SmartDashboard.putData("Auto RLR mode", RLRChooser);
		SmartDashboard.putData("Auto LLL mode", LLLChooser);
		SmartDashboard.putData("Auto RRR mode", RRRChooser);
		

		AddToAutos("An-Side go to Switch throw near", new AutoASwitchNearThenPCZone(false));
		AddToAutos("Am-Side go to Switch throw in middle", new AutoASwitchCenter(false));
		AddToAutos("Af-Side go to Switch throw far", new AutoASwitchFarThenPlatZone(false));
		
		AddToAutos("Be-Line then Exchange", new AutoBExchange(false));
		AddToAutos("Bs-Front side then PCZone", new AutoBSwitchSideThenPCZone(false));

		AddToAutos("Ce-Line then Exchange", new AutoCExchange(false));
		AddToAutos("Cl-Side of Left switch", new AutoCLeftSwitch(false));
		AddToAutos("Cr-Side of Right switch", new AutoCLeftSwitch(true));
		
		AddDefaultToAutos("Just do the line", new AutoJustLine(false));

		AddToAutos("Dn-Side go to Switch throw near", new AutoASwitchNearThenPCZone(true));
		AddToAutos("Dm-Side go to Switch throw in middle", new AutoASwitchCenter(true));
		AddToAutos("Df-Side go to Switch throw far", new AutoASwitchFarThenPlatZone(true));
		

		AddToAutos("Do nothing", new AutoJustLine(false));
		
		//AddToAutos("DANCE", new AutoDance(false));

		
		resetAutonomousSteps();
	}
	
	private static void AddToAutos(String name, Command autoCommand) {
		LRLChooser.addObject(name, autoCommand);
		RLRChooser.addObject(name, autoCommand);
		LLLChooser.addObject(name, autoCommand);
		RRRChooser.addObject(name, autoCommand);
	}
	
	private static void AddDefaultToAutos(String name, Command autoCommand) {
		LRLChooser.addDefault(name, autoCommand);
		RLRChooser.addDefault(name, autoCommand);
		LLLChooser.addDefault(name, autoCommand);
		RRRChooser.addDefault(name, autoCommand);
	}
	
	public static void update() {
		DriveBase drivebase = CommandBase.driveBase;
		SmartDashboard.putNumber("Gyro angle", drivebase.getOrientation());
		SmartDashboard.putBoolean("navx isCalibrating", drivebase.isCalibrating());
		SmartDashboard.putBoolean("Navx Angle Correction", drivebase.correctOrientationWithNavx);
		
/*
		SmartDashboard.putNumber("Screw Height", CommandBase.grabber.getScrewHeight());

		SmartDashboard.putBoolean("Limite switch lower", CommandBase.grabber.getLowerLimitSwitch());
		SmartDashboard.putBoolean("Limite switch upper", CommandBase.grabber.getUpperLimitSwitch());
		SmartDashboard.putBoolean("Limite switch powercube", CommandBase.grabber.getPowerCube());
	*/	
		
		
		SmartDashboard.putNumber("Left Motor Encoder", drivebase.getLeftEncoderDistance() / Constant.ENCODER_ROTATION_PER_INCH);
		SmartDashboard.putNumber("Right Motor Encoder", drivebase.getRightEncoderDistance() / Constant.ENCODER_ROTATION_PER_INCH);

		
		
		LRLFirstDelay = SmarterDashboard.GetOrCreate("LRL First Delay", 0);
		RLRFirstDelay = SmarterDashboard.GetOrCreate("RLR First Delay", 0);
		LLLFirstDelay = SmarterDashboard.GetOrCreate("LLL First Delay", 0);
		RRRFirstDelay = SmarterDashboard.GetOrCreate("RRR First Delay", 0);
		LRLSecondDelay = SmarterDashboard.GetOrCreate("LRL Second Delay", 0);
		RLRSecondDelay = SmarterDashboard.GetOrCreate("RLR Second Delay", 0);
		LLLSecondDelay = SmarterDashboard.GetOrCreate("LLL Second Delay", 0);
		RRRSecondDelay = SmarterDashboard.GetOrCreate("RRR Second Delay", 0);
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
