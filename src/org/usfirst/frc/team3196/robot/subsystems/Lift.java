package org.usfirst.frc.team3196.robot.subsystems;

import org.usfirst.frc.team3196.robot.commands.LiftWithJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public WPI_TalonSRX liftLeft = new WPI_TalonSRX(0);
	public WPI_TalonSRX liftRight = new WPI_TalonSRX(0);
	public SpeedControllerGroup lift = new SpeedControllerGroup(liftLeft, liftRight);
	
	public double liftThrustLimit = 0.8;

    public void initDefaultCommand() {
    	setDefaultCommand(new LiftWithJoystick());
    }
}

