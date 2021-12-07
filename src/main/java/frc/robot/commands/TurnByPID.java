// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

public class TurnByPID extends PIDCommand {
  private final Drivetrain m_drive;
  private static final double m_kTurnP = 0.020;
  private static final double m_kTurnI = 0.02;// .0267;//0.08;
  private static final double m_kTurnD = 0.0080;// 0.00045;
  private static final double m_maxSpeed = 0.5;

  /**
   * Creates a new TurnDegrees. This command will turn your robot for a desired
   * rotation (in degrees) and rotational speed.
   *
   * @param speed   The speed which the robot will drive. Negative is in reverse.
   *                Unused?
   * @param degrees Degrees to turn. Uses gyro and PID to control angle
   * @param drive   The drive subsystem on which this command will run
   */
  public TurnByPID(double speed, double degrees, Drivetrain drive) {
    super(new PIDController(m_kTurnP, m_kTurnI, m_kTurnD),
        // Close loop on heading
        drive::getGyroAngleZ,
        // Set reference to target
        speed < 0 ? -degrees : degrees,
        // Pipe output to turn robot
        output -> drive.arcadeDrive(0, output),
        // Require the drive
        drive);

    m_drive = drive;

    // Set the controller to be continuous (because it is an angle controller)
    getController().enableContinuousInput(-180, 180);
    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController().setTolerance(1.0);

    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Set motors to stop, read encoder values for starting point
    m_drive.arcadeDrive(0, 0);
    m_drive.resetEncoders();
    m_drive.resetGyro();
    super.initialize();
  }

  @Override
  public void execute() {
    double control = m_controller.calculate(m_measurement.getAsDouble(), m_setpoint.getAsDouble());
    if (control > m_maxSpeed)
        control = m_maxSpeed;
    if (control < -m_maxSpeed)
        control = -m_maxSpeed;

    m_useOutput.accept(control);
  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.printf("turned: %f\n", m_drive.getGyroAngleZ());

    return getController().atSetpoint();
//    return getAverageTurningDistance() >= (inchPerDegree * m_degrees);
  }

}
