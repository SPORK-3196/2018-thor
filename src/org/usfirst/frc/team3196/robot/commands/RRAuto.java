package org.usfirst.frc.team3196.robot.commands;

import org.usfirst.frc.team3196.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RRAuto extends CommandGroup {

    public RRAuto() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	//requires(Robot.ssDrive);

    	addSequential(new DriveWithEncoder(9500));
    	addSequential(new RotateWithGyro(-85));
    	addSequential(new LiftWithEncoder(3000));
    	addSequential(new DriveWithEncoder(2000, 2));
    	addSequential(new ShootBox());
    }
}
