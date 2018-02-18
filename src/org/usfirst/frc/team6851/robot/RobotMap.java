/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6851.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static int leftMotor = 1;
	public static int rightMotor = 0;

	public static int leftMotorEncoderA = 2;
	public static int leftMotorEncoderB = 3;
	public static int rightMotorEncoderA = 0;
	public static int rightMotorEncoderB = 1;
	
	public static int frontRightSensorEcho = 4;	
	public static int frontRightSensorTrigger = 5;
	public static int frontLeftSensorEcho = 6;
	public static int frontLeftSensorTrigger = 7;

	//grabber 
	public static int lowerLimitSwitch = 8;
	public static int upperLimitSwitch = 9;
	
	public static int screwMotor = 0;
	public static int grabberMotorLeft = 1;
	public static int grabberMotorRight = 2;
	public static int screwHeightPotentiometer = 1;
	//
}
