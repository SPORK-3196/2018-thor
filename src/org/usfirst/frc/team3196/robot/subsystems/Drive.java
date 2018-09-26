package org.usfirst.frc.team3196.robot.subsystems;

import org.usfirst.frc.team3196.robot.Robot;
import org.usfirst.frc.team3196.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Drive extends PIDSubsystem {

	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public WPI_TalonSRX frontRight = new WPI_TalonSRX(4);
	public WPI_TalonSRX rearRight = new WPI_TalonSRX(2);
	public SpeedControllerGroup right = new SpeedControllerGroup(frontRight, rearRight);

	public WPI_TalonSRX frontLeft = new WPI_TalonSRX(3);
	public WPI_TalonSRX rearLeft = new WPI_TalonSRX(1);
	public SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, rearLeft);
	
	public DifferentialDrive drive = new DifferentialDrive(left, right);

	private int _encoderOffsetLeft = 0;
	private int _encoderOffsetRight = 0;
	
	public void stopMotors() {
		frontRight.stopMotor();
		rearRight.stopMotor();
		frontLeft.stopMotor();
		rearLeft.stopMotor();
	}
	
	public void resetEncoders() {
		_encoderOffsetLeft += getEncoderLeft();
		_encoderOffsetRight += getEncoderRight();
		System.out.println("Encoders reset");
	}
	
	public int getEncoderLeft() {
		return Robot.ssDrive.rearLeft.getSelectedSensorPosition(0)-_encoderOffsetLeft;
	}
	
	public int getEncoderRight() {
		return -(Robot.ssDrive.rearRight.getSelectedSensorPosition(0)+_encoderOffsetRight);
	}
	
    // Initialize your subsystem here
    public Drive(double P, double I, double D) {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super(P,I,D);
    	setOutputRange(-0.75, 0.75);
    	disable();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoystick());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return getEncoderLeft();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	double turn = 0;
    	if(Robot.ssSensors.readGyro() > 3) turn = -0.3;
    	else if(Robot.ssSensors.readGyro() < -3) turn = 0.3;
    	
    	drive.arcadeDrive(output, turn);
    }
}
