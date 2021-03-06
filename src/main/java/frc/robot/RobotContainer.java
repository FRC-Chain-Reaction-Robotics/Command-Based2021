/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutonomousCommand;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import static edu.wpi.first.wpilibj.XboxController.Button.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_Drivetrain = new Drivetrain();

//  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  boolean slowMode = false;
  XboxController richard = new XboxController(0);

  
 // s
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    //slowMode = richard.getBumper(Hand.kLeft);
    var slowButton = new JoystickButton(richard, kBumperLeft.value);
    
    slowButton.whenHeld(new RunCommand(() -> m_Drivetrain.setOutput(0.5))).whenReleased(new RunCommand(() -> m_Drivetrain.setOutput(1)));
    m_Drivetrain.setDefaultCommand(new RunCommand(()->m_Drivetrain.driveBoy(-richard.getY(Hand.kLeft), -richard.getX(Hand.kRight)), m_Drivetrain));
    
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new AutonomousCommand(m_Drivetrain);
  }
  
}
