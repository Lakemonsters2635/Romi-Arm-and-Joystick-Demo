// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ExternalIO;

public class ArmCommand extends CommandBase {
  private final ExternalIO m_externalIO;
  private final Supplier<Double> m_liftSupplier;
  private final Supplier<Double> m_tiltSupplier;
  private final Supplier<Boolean> m_jawsSupplier;

  /** Creates a new ArmCommand. */
  public ArmCommand(
    ExternalIO externalIO,
    Supplier<Double> liftSupplier,
    Supplier<Double> tiltSupplier,
    Supplier<Boolean> jawsSupplier) {

    m_externalIO = externalIO;
    m_liftSupplier = liftSupplier;
    m_tiltSupplier = tiltSupplier;
    m_jawsSupplier = jawsSupplier;

    addRequirements(externalIO);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_externalIO.m_servo2.set(m_jawsSupplier.get() ? 1.0 : 0.0);
    m_externalIO.m_servo3.set(m_liftSupplier.get());
    m_externalIO.m_servo4.set(m_tiltSupplier.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
