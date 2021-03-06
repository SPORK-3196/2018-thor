package org.usfirst.frc.team3196.robot.commands;

import org.usfirst.frc.team3196.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootBox extends Command {
	
	double startTime = 0;
	double shootPower = 0.7;
	
    public ShootBox() {
    	requires(Robot.ssIntake);
    }
    
    public ShootBox(double power) {
    	requires(Robot.ssIntake);
    	shootPower = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ssIntake.intake.set(-shootPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Timer.getFPGATimestamp() >= (startTime+2));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ssIntake.intake.set(0);
    	Robot.ssLift.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
