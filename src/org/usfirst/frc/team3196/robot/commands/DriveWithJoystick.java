package org.usfirst.frc.team3196.robot.commands;

import org.usfirst.frc.team3196.robot.Robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithJoystick extends Command {
	
    public DriveWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ssDrive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ssDrive.drive.setDeadband(0.05);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Input from joysticks and output to motors
		double driveSpeed = -Robot.jsDrive.getY(Hand.kLeft)*1;
		double driveRot = Robot.jsDrive.getX(Hand.kLeft)*0.7;
    	Robot.ssDrive.drive.arcadeDrive(driveSpeed, driveRot);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
