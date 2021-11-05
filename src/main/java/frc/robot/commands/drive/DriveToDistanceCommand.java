package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;

public class DriveToDistanceCommand extends PIDCommand
{
    Drivetrain dt;
    
    public DriveToDistanceCommand(double distMeters, Drivetrain dt)
    {
        super(new PIDController(2, 0, 0),
            dt::getDistance,    //  PID's process variable getter method
            distMeters, //  PID setpoint
            output -> dt.driveBoy(output, 0),
            dt);    //  PID output method as a lambda, this w>?>,
        this.dt = dt;
        getController().setTolerance(2);  //  the tolerance with which the isFinished() method checks if the PV is within the setpoint
        addRequirements(dt);
        
    }

    @Override
    public void initialize()
    {
        dt.resetEncoders(); //  upon initialization, reset the encoders
    }

    @Override
    public boolean isFinished()
    {
        return getController().atSetpoint();    //  This command will terminate once the desired distance has been reached.
    }
}
