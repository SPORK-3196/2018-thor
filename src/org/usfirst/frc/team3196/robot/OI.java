/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3196.robot;

import org.usfirst.frc.team3196.robot.commands.IntakeIn;
import org.usfirst.frc.team3196.robot.commands.IntakeOut;
import org.usfirst.frc.team3196.robot.commands.ResetGyro;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public Joystick jsDrive = new Joystick(0);
	
	public Button jsDrive_A = new JoystickButton(jsDrive, 1);
	public Button jsDrive_B = new JoystickButton(jsDrive, 2);
	public Button jsDrive_X = new JoystickButton(jsDrive, 3);
	public Button jsDrive_Y = new JoystickButton(jsDrive, 4);
	public Button jsDrive_LB = new JoystickButton(jsDrive, 5);
	public Button jsDrive_RB = new JoystickButton(jsDrive, 6);
	public Button jsDrive_BACK = new JoystickButton(jsDrive, 7);
	public Button jsDrive_START = new JoystickButton(jsDrive, 8);
	public Button jsDrive_LC = new JoystickButton(jsDrive, 9);
	public Button jsDrive_RC = new JoystickButton(jsDrive, 10);
	
	
	public Joystick jsMech = new Joystick(2);
	public Button jsMech_A = new JoystickButton(jsMech, 1);
	public Button jsMech_B = new JoystickButton(jsMech, 2);
	public Button jsMech_X = new JoystickButton(jsMech, 3);
	public Button jsMech_Y = new JoystickButton(jsMech, 4);
	public Button jsMech_LB = new JoystickButton(jsMech, 5);
	public Button jsMech_RB = new JoystickButton(jsMech, 6);
	public Button jsMech_BACK = new JoystickButton(jsMech, 7);
	public Button jsMech_START = new JoystickButton(jsMech, 8);
	public Button jsMech_LC = new JoystickButton(jsMech, 9);
	public Button jsMech_RC = new JoystickButton(jsMech, 10);
	
	
	public OI() {
		jsMech_A.whileHeld(new IntakeIn());
		jsMech_B.whileHeld(new IntakeOut());
		jsDrive_A.whileHeld(new ResetGyro());
	}
}
