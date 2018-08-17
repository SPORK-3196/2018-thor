package org.usfirst.frc.team3196.robot.commands;

import org.usfirst.frc.team3196.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithEncoder extends Command {

	long encoderValLeft = 0;
	long encoderValRight = 0;
	
    public DriveWithEncoder(long valLeft, long valRight) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ssDrive);
    	
    	encoderValLeft = valLeft;
    	encoderValRight = valRight;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ssDrive.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ssDrive.drive.arcadeDrive(0.7, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.ssDrive.getEncoderLeft() >= encoderValLeft || Robot.ssDrive.getEncoderRight() >= encoderValRight) return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ssDrive.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
