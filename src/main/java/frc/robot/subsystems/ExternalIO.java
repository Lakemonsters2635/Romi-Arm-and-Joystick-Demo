// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/*
 * This class represents the External I/O of the Romi reference robot.
 * The five channels can be individually configured as one of DIO, PWM or Analog In (with the exception of EXT0 which
 * does not support Analog In).
 * 
 * This table shows the "roboRIO" channel assigned to each EXT channel based on its configuration.
 * This configuration is performed using the web interface to the Romi (http://wpilib.local)
 * 
 * 
 *       DIO      PWM      AnalogIn
 * EXT0    8        2             -
 * EXT1    9        3             0
 * EXT2   10        4             1
 * EXT4   11        5             2
 * EXT5   12        6             3
 * 
 * In the constructor, you must specify the configuration of each channel.  This MUST match the configuration
 * created using the web interface (see above and also https://docs.wpilib.org/en/stable/docs/romi-robot/web-ui.html)
 * 
 * NOTE: In contrast to the OnBoardIO class (which has member functions for each LED and button), this class simply makes
 * the I/O objects public.  Thus, you can access a servo on channel 2 via something like:
 * 
 *          myExt.m_servo2.set(value);
 */
 

public class ExternalIO extends SubsystemBase {

// Servos - might not exist depending on configuration

  public Servo m_servo2;
  public Servo m_servo3;
  public Servo m_servo4;
  public Servo m_servo5;
  public Servo m_servo6;

// Digital I/O - Need both input and output because we don't know at compile time

  public DigitalInput m_input8;
  public DigitalInput m_input9;
  public DigitalInput m_input10;
  public DigitalInput m_input11;
  public DigitalInput m_input12;
 
  public DigitalOutput m_output8;
  public DigitalOutput m_output9;
  public DigitalOutput m_output10;
  public DigitalOutput m_output11;
  public DigitalOutput m_output12;

// Analog Input

  public AnalogInput m_analogInput0;
  public AnalogInput m_analogInput1;
  public AnalogInput m_analogInput2;
  public AnalogInput m_analogInput3;

  public enum ChannelMode { INPUT, OUTPUT};
  public enum ExternalMode { DIO, PWM, AIN };

  
  /*
   * Creates a new ExternalIO. 
   * 
   * Parameters ext* specify how each channel was configured.  The ext's must be immediately followed by a list
   * of ChannelMode values, one for each ext* that was specified as DIO.  If too few ChannelMode values are
   * given, an exception will be thrown when you run off the end of the list.  If too many are given, the extras
   * are silently ignored.
   * 
   * Examples:
   *    ExternalIO example1 = new ExternalIO(DIO, DIO, DIO, DIO, DIO, INPUT, OUTPUT, INPUT, OUTPUT, OUTPUT);
   *    ExternalIO example2 = new ExternalIO(PWM, PWM, PWM, PWM, PWM);
   *    ExternalIO example3 = new ExternalIO(PWM, AIN, DIO, PWM, DIO, INPUT, OUTPUT)
   */

  public ExternalIO(
    ExternalMode ext0,
    ExternalMode ext1,
    ExternalMode ext2,
    ExternalMode ext3,
    ExternalMode ext4,
    ChannelMode... modes
  ) {
    int n = 0;
  
  // To note: for each ext* set to DIO, there must be a ChannelMode.  If you don't pass in
    switch (ext0) {
      case DIO:
        if (modes[n++] == ChannelMode.INPUT)
          m_input8 = new DigitalInput(8);
        else
          m_output8 = new DigitalOutput(8);
        break;
      case PWM:   m_servo2 = new Servo(2); break;
      case AIN:   DriverStation.reportError("Cannot configure EXT0 as Analog Input", true); break;
    }

    switch (ext1) {
      case DIO:
        if (modes[n++] == ChannelMode.INPUT)
          m_input9 = new DigitalInput(9);
        else
          m_output9 = new DigitalOutput(9);
        break;
      case PWM:   m_servo3 = new Servo(3); break;
      case AIN:   m_analogInput0 = new AnalogInput(0); break;
    }

    switch (ext2) {
      case DIO:
        if (modes[n++] == ChannelMode.INPUT)
          m_input10 = new DigitalInput(10);
        else
          m_output10 = new DigitalOutput(10);
        break;
      case PWM:   m_servo4 = new Servo(4); break;
      case AIN:   m_analogInput1 = new AnalogInput(1); break;
    }

    switch (ext3) {
      case DIO:
        if (modes[n++] == ChannelMode.INPUT)
          m_input11 = new DigitalInput(11);
        else
          m_output11 = new DigitalOutput(11);
        break;
      case PWM:   m_servo5 = new Servo(5); break;
      case AIN:   m_analogInput2 = new AnalogInput(2); break;
    }

    switch (ext4) {
      case DIO:
        if (modes[n++] == ChannelMode.INPUT)
          m_input12 = new DigitalInput(12);
        else
          m_output12 = new DigitalOutput(12);
        break;
      case PWM:   m_servo6 = new Servo(6); break;
      case AIN:   m_analogInput3 = new AnalogInput(3); break;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
