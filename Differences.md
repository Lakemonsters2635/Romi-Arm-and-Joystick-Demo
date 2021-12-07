Changes for Yellow Light/XBox Button A
=================================

* Added Command: YellowOnOff
* In RobotContainer:
    *   import YellowOnOff
    *   import JoystickButton
    *   in configureButtonBindings:
<pre><code>
    JoystickButton buttonA = new JoystickButton(m_controller, 1);  
    buttonA  
        .whenActive(new YellowOnOff(true, m_onboardIO))  
        .whenInactive(new YellowOnOff(false, m_onboardIO));
</code></pre>


Changes for Robot Arm
=====================

* Added Command: ArmCommand
* Added subsystem: ExternalIO
* In RobotContainer:
    * import ExternalIO
    * import ExternalIO.ExternalMode
    * added class member variable:
<pre><code>
    private final ExternalIO m_externalIO = new ExternalIO(  
        ExternalMode.PWM, ExternalMode.PWM, ExternalMode.PWM,  
        ExternalMode.AIN, ExternalMode.AIN);
</code></pre>

* in configureButtonBindings:
<pre><code>
    // Default command for External I/O runs the arm  
    m_externalIO.setDefaultCommand(getArmCommand());
</code></pre>
* at end, added:
<pre><code>
  /**
   * Use this to pass the arm command to the main {@link Robot} class.
   */

   public Command getArmCommand() {
     return new ArmCommand(
       m_externalIO, () -> m_controller.getRawAxis(2),  
       () -> m_controller.getRawAxis(3),  
       () -> m_controller.getRawButton(2));
   }
</code></pre>

Changes for PID
===============
* Added Command: TurnByPID
    * This replaces TurnDegrees
* Added Command: DriveDistanceSteeringByPID
* Created CommandGroup: AutonomousDistance to use TurnByPID and DriveDistanceSteeringByPID
* Added Chooser entry for PID version 