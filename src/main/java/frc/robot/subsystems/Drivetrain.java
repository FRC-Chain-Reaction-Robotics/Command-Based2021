/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drivetrain extends SubsystemBase {
	//commented out sections because talons were used instead of cansparks for now

	//Create our motors
	/*
	WPI_TalonSRX leftFront = new WPI_TalonSRX(1);
	WPI_TalonSRX leftBack = new WPI_TalonSRX(2);
	WPI_TalonSRX rightFront = new WPI_TalonSRX(3);
	WPI_TalonSRX rightBack = new WPI_TalonSRX(4);
	*/
	
	CANSparkMax leftFront = new CANSparkMax(1, MotorType.kBrushless);
	CANSparkMax leftBack = new CANSparkMax(5, MotorType.kBrushless);
	CANSparkMax rightFront = new CANSparkMax(7, MotorType.kBrushless);
	CANSparkMax rightBack = new CANSparkMax(8, MotorType.kBrushless);
	
	CANEncoder lfEncoder = leftFront.getEncoder();
	CANEncoder lbEncoder = leftBack.getEncoder();
	CANEncoder rfEncoder = rightFront.getEncoder();
	CANEncoder rbEncoder = rightBack.getEncoder();

	SpeedControllerGroup left;
	SpeedControllerGroup right;

  	DifferentialDrive dt = new DifferentialDrive(left, right);
	
	Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

	public Drivetrain()
	{
		// leftFront.setNeutralMode(NeutralMode.Coast);
		// leftBack.setNeutralMode(NeutralMode.Coast);
		// rightFront.setNeutralMode(NeutralMode.Coast);
		// rightBack.setNeutralMode(NeutralMode.Coast);

		leftFront.setInverted(true);
		left = new SpeedControllerGroup(leftFront, leftBack);
  		right = new SpeedControllerGroup(rightFront, rightBack);
	}
  public void driveBoy(double xspeed, double zrotation){
    dt.arcadeDrive(xspeed, zrotation);
  }

	public double getDistance()
	{
		return (lfEncoder.getPosition() + rfEncoder.getPosition()) / 2;
	}

	public double getAngle()
	{
		return gyro.getAngle();
	}

	public void resetSensors()
	{
		resetEncoders();
		resetGyro();
	}

	public void resetEncoders()
	{
		lfEncoder.setPosition(0);
		rfEncoder.setPosition(0);
	}

	public void resetGyro()
	{
		gyro.reset();
		gyro.calibrate();
	}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
}
