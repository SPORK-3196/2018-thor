package org.usfirst.frc.team3196.robot.commands;

import org.usfirst.frc.team3196.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnWithGyro extends Command {

	long degrees = 0;
	
    public TurnWithGyro(long degreesToTurn) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ssDrive);
    	
    	degrees = degreesToTurn;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ssDrive.drive.tankDrive(0.5, -0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//if(Robot.ssDrive.getEncoderLeft() >= encoderValLeft || Robot.ssDrive.getEncoderRight() >= encoderValRight) return true;
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
