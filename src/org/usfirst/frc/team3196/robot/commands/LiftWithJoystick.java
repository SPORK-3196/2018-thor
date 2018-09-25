package org.usfirst.frc.team3196.robot.commands;

import org.usfirst.frc.team3196.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftWithJoystick extends Command {
	
	public int liftPos;
	
	public double deadband(double val) {
		if(val >= -0.05 && val <= 0.05) return 0;
		return val;
	}
	
	public boolean isPressed(double val) {
		return (val >= 0.05 || val <= -0.05);
	}

    public LiftWithJoystick() {
        requires(Robot.ssLift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	liftPos = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double liftSpeed = deadband((Robot.jsMech.getY(Hand.kLeft))*Robot.ssLift.liftThrustLimit);
    	
    	if(isPressed(liftSpeed)) {
    		Robot.ssLift.disable();
    		Robot.ssLift.lift.set(-liftSpeed);
    		liftPos = Robot.ssLift.getEncoder();
    	} else {
    		Robot.ssLift.setSetpoint((double)(liftPos));
    		Robot.ssLift.enable();
    	}
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
