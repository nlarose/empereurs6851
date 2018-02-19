/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6851.robot;

import org.usfirst.frc.team6851.robot.commands.CommandBase;
import org.usfirst.frc.team6851.robot.commands.claw.PlayerControledGrabber;
import org.usfirst.frc.team6851.robot.subsystems.DriveBase;
import org.usfirst.frc.team6851.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

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
	
	public static DriveBase driveBase = new DriveBase();
	public static Grabber grabber = new Grabber();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		Dashboard.init();
		SystemCheckUp.init();
		oi = new OI();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
		// TODO EFACER MOI !!!!
		CommandBase.driveBase().leftEncoder.reset();
		CommandBase.driveBase().rightEncoder.reset();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override

	public void autonomousInit() {
		/// TODO A CHECKER TEND QUE C NULL, attendre, ca va evnetuellement arriver.
		String gameData = DriverStation.getInstance().getGameSpecificMessage().toUpperCase();
		if (gameData.length() > 0) {
			if (gameData.equals("LLL")) {
				autonomousCommand = Dashboard.LLLChooser.getSelected();			
			} else if(gameData.equals("LRL")) {
				autonomousCommand = Dashboard.LRLChooser.getSelected();			
			} else if(gameData.equals("RRR")) {
				autonomousCommand = Dashboard.RRRChooser.getSelected();			
			} else if(gameData.equals("RLR")) {
				autonomousCommand = Dashboard.RLRChooser.getSelected();			
			} else {
				System.out.println("ERROR!!unknown gameData:" + gameData );
			} 
		} else {
			System.out.println("ERROR!!missing gameData!");
		}
		
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		new PlayerControledGrabber().start();
		CommandBase.driveBase().drive.setSafetyEnabled(true);

		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
	public void robotPeriodic() {
		Dashboard.update();
		SystemCheckUp.update();
		oi.update();
		System.out.println(CommandBase.grabber().lowerLimitSwitch.get());
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		//double motorOutput = CommandBase.grabber.grabberMotorLeft.getMotorOutputPercent();
		//System.out.println("motor : " + CommandBase.grabber.lowerLimitSwitch.get());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
