package org.usfirst.frc.team3196.robot.subsystems;

import org.usfirst.frc.team3196.robot.Robot;
import org.usfirst.frc.team3196.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveBackup extends Subsystem {

	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public WPI_TalonSRX climber = new WPI_TalonSRX(8);
	
	public WPI_TalonSRX frontRight = new WPI_TalonSRX(4);
	public WPI_TalonSRX rearRight = new WPI_TalonSRX(2);
	public SpeedControllerGroup right = new SpeedControllerGroup(frontRight, rearRight);

	public WPI_TalonSRX frontLeft = new WPI_TalonSRX(3);
	public WPI_TalonSRX rearLeft = new WPI_TalonSRX(1);
	public SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, rearLeft);
	
	public DifferentialDrive drive = new DifferentialDrive(left, right);

	private double _encoderOffsetLeft = 0;
	private double _encoderOffsetRight = 0;
	
	public void stopMotors() {
		frontRight.stopMotor();
		rearRight.stopMotor();
		frontLeft.stopMotor();
		rearLeft.stopMotor();
	}
	
	public void resetEncoders() {
		_encoderOffsetLeft = getEncoderLeft();
		_encoderOffsetRight = getEncoderRight();
	}
	
	public double getEncoderLeft() {
		return Robot.ssDrive.rearLeft.getSelectedSensorPosition(0)-_encoderOffsetLeft;
	}
	
	public double getEncoderRight() {
		return -(Robot.ssDrive.rearRight.getSelectedSensorPosition(0)+_encoderOffsetRight);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	// Configure encoders
    	Robot.ssDrive.frontLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    	Robot.ssDrive.frontLeft.setSensorPhase(false);
    	Robot.ssDrive.rearLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    	Robot.ssDrive.rearLeft.setSensorPhase(false);
    	Robot.ssDrive.frontRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    	Robot.ssDrive.frontRight.setSensorPhase(false);
    	Robot.ssDrive.rearRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    	Robot.ssDrive.rearRight.setSensorPhase(false);
    	
    	//setDefaultCommand(new DriveWithJoystick());
    }
}

