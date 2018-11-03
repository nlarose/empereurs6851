/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6851.robot;

import org.usfirst.frc.team6851.robot.commands.CommandBase;
import org.usfirst.frc.team6851.robot.commands.FirstDelay;
import org.usfirst.frc.team6851.robot.commands.SecondDelay;
import org.usfirst.frc.team6851.robot.commands.claw.CalibrateClaw;
import org.usfirst.frc.team6851.robot.systemCheckUp.SystemCheckUp;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI oi;

	Command autonomousCommand;
		boolean GotGameData;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		Dashboard.init();
		SystemCheckUp.init();
		CameraServer.getInstance().startAutomaticCapture();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		GotGameData = false;
	}

	private void CheckDriverData() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage().toUpperCase();
		if (gameData.length() > 0) {
			GotGameData = true;
			Colorize();
			if (gameData.equals("LLL")) {
				autonomousCommand = Dashboard.LLLChooser.getSelected();		
				FirstDelay.Delay = Dashboard.LLLFirstDelay;
				SecondDelay.Delay = Dashboard.LLLSecondDelay;
				
			} else if(gameData.equals("LRL")) {
				autonomousCommand = Dashboard.LRLChooser.getSelected();	
				FirstDelay.Delay = Dashboard.LRLFirstDelay;
				SecondDelay.Delay = Dashboard.LRLSecondDelay;
			} else if(gameData.equals("RRR")) {
				autonomousCommand = Dashboard.RRRChooser.getSelected();
				FirstDelay.Delay = Dashboard.RRRFirstDelay;
				SecondDelay.Delay = Dashboard.RRRSecondDelay;
			} else if(gameData.equals("RLR")) {
				autonomousCommand = Dashboard.RLRChooser.getSelected();	
				FirstDelay.Delay = Dashboard.RLRFirstDelay;
				SecondDelay.Delay = Dashboard.RLRSecondDelay;
			} else {
				System.out.println("ERROR!!unknown gameData:" + gameData );
			} 
		} 
		
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	private void Colorize() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage().toUpperCase();
		boolean isRed = DriverStation.getInstance().getAlliance().equals(Alliance.Red);
		
		if (gameData.equals("LLL")) {
			SmartDashboard.putBoolean("colorBox1", isRed);
			SmartDashboard.putBoolean("colorBox2", !isRed);
			SmartDashboard.putBoolean("colorBox3", isRed);
			SmartDashboard.putBoolean("colorBox4", !isRed);
			SmartDashboard.putBoolean("colorBox5", isRed);
			SmartDashboard.putBoolean("colorBox6", !isRed);
			
		}  else if (gameData.equals("LRL")) {
			SmartDashboard.putBoolean("colorBox1", isRed);
			SmartDashboard.putBoolean("colorBox2", !isRed);
			SmartDashboard.putBoolean("colorBox3", !isRed);
			SmartDashboard.putBoolean("colorBox4", isRed);
			SmartDashboard.putBoolean("colorBox5", isRed);
			SmartDashboard.putBoolean("colorBox6", !isRed);
		} else if (gameData.equals("RRR")) {
			SmartDashboard.putBoolean("colorBox1", !isRed);
			SmartDashboard.putBoolean("colorBox2", isRed);
			SmartDashboard.putBoolean("colorBox3", !isRed);
			SmartDashboard.putBoolean("colorBox4", isRed);
			SmartDashboard.putBoolean("colorBox5", !isRed);
			SmartDashboard.putBoolean("colorBox6", isRed);
		} else if (gameData.equals("RLR")) {
			SmartDashboard.putBoolean("colorBox1", !isRed);
			SmartDashboard.putBoolean("colorBox2", isRed);
			SmartDashboard.putBoolean("colorBox3", isRed);
			SmartDashboard.putBoolean("colorBox4", !isRed);
			SmartDashboard.putBoolean("colorBox5", !isRed);
			SmartDashboard.putBoolean("colorBox6", isRed);
		}
		
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		if (!GotGameData) {
			CheckDriverData();
		}
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		new CalibrateClaw().start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	@Override
	public void robotPeriodic() {
		Dashboard.update();
		SystemCheckUp.update();
		oi.update();
		//System.out.println(CommandBase.grabber.getScrewHeight());
		//System.out.println(CommandBase.grabber().lowerLimitSwitch.get());
	}
}
