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

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drivetrain extends SubsystemBase {
  //Create our motors
  CANSparkMax leftFront = new CANSparkMax(0, MotorType.kBrushed);
  CANSparkMax leftBack = new CANSparkMax(1, MotorType.kBrushed);
  CANSparkMax rightFront = new CANSparkMax(2, MotorType.kBrushed);
  CANSparkMax rightBack = new CANSparkMax(3, MotorType.kBrushed);

  SpeedControllerGroup left = new SpeedControllerGroup(leftFront, leftBack);
  SpeedControllerGroup right = new SpeedControllerGroup(rightFront, rightBack);

  //whats next?!?!??!
  DifferentialDrive dt = new DifferentialDrive(left, right);

  	CANEncoder lfEncoder = leftFront.getEncoder();
	CANEncoder rfEncoder = rightFront.getEncoder();
	
	Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

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
