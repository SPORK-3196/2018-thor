package org.usfirst.frc.team3196.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.analog.adis16448.frc.ADIS16448_IMU.Axis;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Sensors extends Subsystem {

	public static final ADIS16448_IMU gyro = new ADIS16448_IMU(Axis.kY);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void resetGyro() {
		gyro.reset();
	}
	
	public double readGyro() {
		return gyro.getAngleZ();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

