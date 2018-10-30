package org.usfirst.frc.team3196.robot.commands;

import org.usfirst.frc.team3196.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateWithGyro extends Command {
	
	public int degSetpoint;

    public RotateWithGyro(int degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.ssDrive);
    	requires(Robot.ssSensors);
    	
    	degSetpoint = degrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ssSensors.resetGyro();
    	Robot.ssSensors.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double turn = 0;
    	if(Robot.ssSensors.readGyro() > (degSetpoint + 1)) turn = -0.50;
    	else if(Robot.ssSensors.readGyro() < (degSetpoint - 1)) turn = 0.50;
    	
    	Robot.ssDrive.drive.arcadeDrive(0, turn);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Robot.ssSensors.readGyro() >= (degSetpoint - 1) && Robot.ssSensors.readGyro() <= (degSetpoint + 1));
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
