package org.usfirst.frc.team3196.robot.commands;

import org.usfirst.frc.team3196.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeWithTriggers extends Command {

	public double deadband(double val) {
		if(val <= 0.05 && val >= -0.05) return 0;
		return val;
	}
	
	public boolean isPressed(double val) {
		return (val >= 0.05 || val <= -0.05);
	}
	
    public IntakeWithTriggers() {
    	requires(Robot.ssIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftTriggerInput = deadband(Robot.jsMech.getTriggerAxis(Hand.kLeft));
    	double rightTriggerInput = deadband(Robot.jsMech.getTriggerAxis(Hand.kRight));
    	
    	double intakeSpeed = 0;
    	if(isPressed(rightTriggerInput)) intakeSpeed = rightTriggerInput;
    	else if(isPressed(leftTriggerInput)) intakeSpeed = -leftTriggerInput;
    	Robot.ssIntake.intake.set(intakeSpeed*Robot.ssIntake.intakeThrustLimiter);
    	
    	Robot.ssIntake.solenoidSqueeze.set(Robot.jsMech.getAButton());
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
