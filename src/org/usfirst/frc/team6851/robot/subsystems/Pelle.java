package org.usfirst.frc.team6851.robot.subsystems;

import org.usfirst.frc.team6851.robot.Constant;
import org.usfirst.frc.team6851.robot.RobotMap;
//import org.usfirst.frc.team6851.robot.commands.claw.PlayerControledGrabber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;

public class Pelle extends SubsystemBase {

	public static double SCREW_HEIGHT_UPPER_LIMIT = -66; // 2750
	public static double SCREW_HEIGHT_LOWER_LIMIT = -1830; // 1610
	/*
	 * 	public static int canalCanPCM;

	public static int canalMouvementMonterPelle = 1;
	public static int canalMouvementDecendrePelle = 0;
	public static int canalMouvementAvancerPelle = 2;
	public static int canalMouvementReculerPelle =3;
	 * */
	
	public final Compressor Compresseur = tryInitCompressor(RobotMap.canalCanPCM, "Compresseur");
	public final DoubleSolenoid MouvementHorizontal = 
			tryInitDoubleSolenoid(
					RobotMap.canalMouvementAvancerPelle, 
					RobotMap.canalMouvementReculerPelle,
					"MouvementHorizontal");
	public final DoubleSolenoid MouvementVertical = 
			tryInitDoubleSolenoid(
					RobotMap.canalMouvementMonterPelle , 
					RobotMap.canalMouvementDecendrePelle,
					"MouvementVertical");

	
	public final DigitalInput lowerLimitSwitch = tryInitDigitalInput(RobotMap.lowerLimitSwitch, "LowerLimiteSwitch");

	
	
	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new PlayerControledGrabber());
	}

	public void Monter()
	{
		MouvementVertical.set(DoubleSolenoid.Value.kForward);
	}
	
	public void Descendre()
	{
		MouvementVertical.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void Avancer()
	{
		MouvementHorizontal.set(DoubleSolenoid.Value.kForward);
	}

	public void Reculer()
	{
		MouvementHorizontal.set(DoubleSolenoid.Value.kReverse);
	}

	public void FeedPowerCube() {
	}

	public void ThrowPowerCube() {
	}

	public void Raise(double speed) {
	}

	public void Lower(double speed) {
	}
	
	public void MoveHeight(double speed) {
	}
	
	public double getScrewHeight() {
		return 0.0;
	}
	
	public boolean getLowerLimitSwitch() {
		return true;
	}
	
	public boolean getUpperLimitSwitch() {
		return true;
	}
	
	public boolean getPowerCube() {
		return true;
	}

	public void stopScrewMotor() {
	}

	public boolean IsAtLowerLimit() {
		return true;
	}

	public boolean IsAtUpperLimit() {
		return true;
	}

	public void stopWheelMotors() {

	}

	public boolean hasACube() {
		return true;
	}


}
