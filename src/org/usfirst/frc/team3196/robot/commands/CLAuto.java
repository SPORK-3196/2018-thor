package org.usfirst.frc.team3196.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CLAuto extends CommandGroup {

    public CLAuto() {
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
    	
    	addSequential(new DriveWithEncoder(2000));
    	addSequential(new RotateWithGyro(-80));
    	addSequential(new DriveWithEncoder(3500));
    	addSequential(new RotateWithGyro(80));
    	addSequential(new LiftWithEncoder(4500));
    	addSequential(new DriveWithEncoder(5000));
    	addSequential(new ShootBox());
    }
}
