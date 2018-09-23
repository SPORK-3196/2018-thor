package org.usfirst.frc.team3196.robot.commands;

import org.usfirst.frc.team3196.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithEncoder extends Command {

	int encoderDist = 0;
	double timeout = 0;
	double targetTime = 0;
	
    public DriveWithEncoder(int dist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ssDrive);
    	
    	encoderDist = dist;
    	targetTime = -1;
    }
    
    public DriveWithEncoder(int dist, double timeoutS) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ssDrive);
    	
    	encoderDist = dist;
    	timeout = timeoutS;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ssDrive.resetEncoders();
    	Robot.ssDrive.setSetpoint(encoderDist);
    	Robot.ssDrive.enable();
    	
    	if(targetTime >= 0) targetTime = Timer.getFPGATimestamp()+timeout;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println(Robot.ssDrive.getPIDController().getError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(targetTime == -1) return (Robot.ssDrive.getEncoderLeft() >= (encoderDist - 50) && Robot.ssDrive.getEncoderLeft() >= (encoderDist + 50));
    	else return (Robot.ssDrive.getEncoderLeft() >= (encoderDist - 50) && Robot.ssDrive.getEncoderLeft() >= (encoderDist + 50)) ||
    			(Timer.getFPGATimestamp() >= targetTime);
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.print("Finished forward at ");
    	System.out.println(Robot.ssDrive.getEncoderLeft());
    	
    	Robot.ssDrive.stopMotors();
    	Robot.ssDrive.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
