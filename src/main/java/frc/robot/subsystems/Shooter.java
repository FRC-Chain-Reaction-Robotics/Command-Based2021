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

import frc.robot.Constants;

public class Shooter {
    TalonFX ShooterMotorL= new TalonFX (Constants.SHOOTERL_MOTOR_ID);
    TalonFX ShooterMotorR= new TalonFX (Constants.SHOOTERR_MOTOR_ID);

    public Shooter (){
        ShooterMotorL.setInverted(true);
        ShooterMotorR.follow(ShooterMotorL);
        setDefaultCommand(new RunCommand(this::off, this));
        register();
    }
    public void on(){
        ShooterMotorL.set(ControlMode.PercentOutput, 0.7);
    }
    
    public void off(){
        ShooterMotorL.set(ControlMode.PercentOutput, 0);
    }

    public void reverse(){
        ShooterMotorL.set(ControlMode.PercentOutput, -0.5)
}


