// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.OnBoardIO;

public class YellowOnOff extends CommandBase {
  private final OnBoardIO m_onboardIO;
  private final boolean m_state;
  
  /** Creates a new GreenOn. */
  public YellowOnOff(boolean state, OnBoardIO onboardIO) {
    m_state = state;
    m_onboardIO = onboardIO;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_onboardIO.setYellowLed(m_state);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
