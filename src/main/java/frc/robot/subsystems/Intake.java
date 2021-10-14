package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;


public class Intake extends SubsystemBase
{
    //Create the motors needed to run intake
    TalonSRX intakeMotor = new TalonSRX(Constants.INTAKE_MOTOR_ID);
    
    public Intake(){
        intakeMotor.setInverted(true);
        setDefaultCommand(new RunCommand(this::off, this));
        register();
    }


    public void on(){
        intakeMotor.set(ControlMode.PercentOutput, 0.7);
    }
    
    public void off(){
        intakeMotor.set(ControlMode.PercentOutput, 0);
    }

    public void reverse(){
        intakeMotor.set(ControlMode.PercentOutput, -0.5);
    }

}