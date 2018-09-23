package org.usfirst.frc.team3196.robot.subsystems;

import org.usfirst.frc.team3196.robot.Robot;
import org.usfirst.frc.team3196.robot.commands.IntakeWithTriggers;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Compressor compressor = new Compressor(15);
	public Solenoid solenoidSqueeze = new Solenoid(15, 1);

	public WPI_TalonSRX intakeMotorLeft = new WPI_TalonSRX(6);
	public WPI_TalonSRX intakeMotorRight = new WPI_TalonSRX(5);
	public SpeedControllerGroup intake = new SpeedControllerGroup(intakeMotorLeft, intakeMotorRight);
	
	public double intakeThrustLimiter = 0.8;

    public void initDefaultCommand() {
    	// Motor configurations
    	intakeMotorLeft.setInverted(true);
    	intakeMotorLeft.configContinuousCurrentLimit(10, 50);
    	intakeMotorLeft.enableCurrentLimit(true);
    	intakeMotorRight.configContinuousCurrentLimit(10, 50);
    	intakeMotorRight.enableCurrentLimit(true);
    	
    	Robot.ssIntake.compressor.start();
    	setDefaultCommand(new IntakeWithTriggers());
    }
}

