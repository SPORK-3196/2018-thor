package org.usfirst.frc.team3196.robot.commands;

import org.usfirst.frc.team3196.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftWithEncoder extends Command {

	public int encoderDist;
	
    public LiftWithEncoder(int dist) {
    	requires(Robot.ssLift);
    	
    	encoderDist = dist;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ssLift.getPIDController().setP(0.01);
    	Robot.ssLift.resetEncoder();
    	Robot.ssLift.setSetpoint(encoderDist);
    	Robot.ssLift.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.ssLift.getEncoder() >= (encoderDist - 50) && Robot.ssLift.getEncoder() >= (encoderDist + 50));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ssLift.getPIDController().setP(0.0001);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
