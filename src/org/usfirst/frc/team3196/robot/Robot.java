/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3196.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3196.robot.commands.Baseline;
import org.usfirst.frc.team3196.robot.commands.CLAuto;
import org.usfirst.frc.team3196.robot.commands.CRAuto;
import org.usfirst.frc.team3196.robot.commands.LLAuto;
import org.usfirst.frc.team3196.robot.commands.RRAuto;
import org.usfirst.frc.team3196.robot.subsystems.Drive;
import org.usfirst.frc.team3196.robot.subsystems.Intake;
import org.usfirst.frc.team3196.robot.subsystems.Lift;
import org.usfirst.frc.team3196.robot.subsystems.Sensors;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI m_oi;
	
	public static Sensors ssSensors = new Sensors();
	
	public static Drive ssDrive = new Drive(1, 0, 0);
	public static Lift ssLift = new Lift(0.01, 0, 0);
	public static Intake ssIntake = new Intake();

	public static XboxController jsDrive = new XboxController(0);
	public static XboxController jsMech = new XboxController(1);

	Command auto;
	String side;
	String gamedata;
	
	public CommandGroup chooseAuto(char side, String gd) {
		if(side == 'B') {
			System.out.println("BBaseline");
			return new Baseline();
		}
		
		char sw = gd.charAt(0);
		if(gd.length() > 0) {
			if(side == 'C') {
				if(sw == 'L') {
					System.out.println("CLAuto");
					return new CLAuto();
				}
				else if(sw == 'R') {
					System.out.println("CRAuto");
					return new CRAuto();
				}
			} else if(side == 'L') {
				if(sw == 'L') {
					System.out.println("LLAuto");
					return new LLAuto();
				}
				else {
					System.out.println("LBaseline");
					return new Baseline();
				}
			} else if(side == 'R') {
				if(sw == 'R') {
					System.out.println("RRAuto");
					return new RRAuto();
				}
				else {
					System.out.println("RBaseline");
					return new Baseline();
				}
			}
		}
		
		return new Baseline();
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();

		side = "L";
		SmartDashboard.putString("Side", side);
		
		ssSensors.resetGyro();
		ssDrive.resetEncoders();

		ssDrive.getPIDController().setP(SmartDashboard.getNumber("Drive_P", 0.001));
		ssDrive.getPIDController().setI(SmartDashboard.getNumber("Drive_I", 0));
		ssDrive.getPIDController().setD(SmartDashboard.getNumber("Drive_D", 0));
		
		SmartDashboard.putNumber("Drive_P", ssDrive.getPIDController().getP());
		SmartDashboard.putNumber("Drive_I", ssDrive.getPIDController().getI());
		SmartDashboard.putNumber("Drive_D", ssDrive.getPIDController().getD());
		//CameraServer.getInstance().startAutomaticCapture();
		//CameraServer.getInstance().addAxisCamera("http://axis-camera.local/mjpg/video.mjpg");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		gamedata = DriverStation.getInstance().getGameSpecificMessage();
		side = SmartDashboard.getString("Side", "B");
		auto = chooseAuto(side.charAt(0), gamedata);
		
    	Robot.ssDrive.resetEncoders();
    	Robot.ssSensors.resetGyro();
    	Robot.ssDrive.resetEncoders();
    	
    	ssDrive.getPIDController().setP(SmartDashboard.getNumber("Drive_P", 0.0008));
		ssDrive.getPIDController().setI(SmartDashboard.getNumber("Drive_I", 0));
		ssDrive.getPIDController().setD(SmartDashboard.getNumber("Drive_D", 0));
    	
		//m_autonomousCommand = m_chooser.getSelected();
		
		System.out.print("Starting auto with ");
		System.out.print(ssDrive.getPIDController().getP());
		System.out.print(" ");
		System.out.print(ssDrive.getPIDController().getI());
		System.out.print(" ");
		System.out.print(ssDrive.getPIDController().getD());

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (auto != null) {
			auto.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		// Read drive encoders and print to dashboard
    	SmartDashboard.putNumber("Left Encoder", Robot.ssDrive.getEncoderLeft());
    	SmartDashboard.putNumber("Right Encoder", Robot.ssDrive.getEncoderRight());

		// Read lift encoder and print to dashboard
    	SmartDashboard.putNumber("Lift Encoder", Robot.ssLift.getEncoder());
    	//System.out.println(Robot.ssLift.getEncoder());
    	
    	if(Robot.ssLift.liftLeft.getSensorCollection().isRevLimitSwitchClosed()) Robot.ssLift.resetEncoder();
		
		// Read Gyro and print to dashboard
    	SmartDashboard.putNumber("GyroX", Robot.ssSensors.gyro.getAngleX());
    	SmartDashboard.putNumber("GyroY", Robot.ssSensors.gyro.getAngleY());
    	SmartDashboard.putNumber("GyroZ", Robot.ssSensors.gyro.getAngleZ());
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (auto != null) {
			auto.cancel();
		}
		
		// Config camera
		//UsbCamera cam = new UsbCamera("cam0", "/dev/video0");
		//cam.setFPS(10);
		//cam.setResolution(80, 60);
		//CameraServer.getInstance().startAutomaticCapture(cam);
		
		ssDrive.resetEncoders();
		ssDrive.disable();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		// Read drive encoders and print to dashboard
    	SmartDashboard.putNumber("Left Encoder", Robot.ssDrive.getEncoderLeft());
    	SmartDashboard.putNumber("Right Encoder", Robot.ssDrive.getEncoderRight());

		// Read lift encoder and print to dashboard
    	SmartDashboard.putNumber("Lift Encoder", Robot.ssLift.getEncoder());
    	//System.out.println(Robot.ssLift.getEncoder());
    	
    	if(Robot.ssLift.liftLeft.getSensorCollection().isRevLimitSwitchClosed()) Robot.ssLift.resetEncoder();
		
		// Read Gyro and print to dashboard
    	SmartDashboard.putNumber("GyroX", Robot.ssSensors.gyro.getAngleX());
    	SmartDashboard.putNumber("GyroY", Robot.ssSensors.gyro.getAngleY());
    	SmartDashboard.putNumber("GyroZ", Robot.ssSensors.gyro.getAngleZ());
    	
    	//System.out.println("Yo2");
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
