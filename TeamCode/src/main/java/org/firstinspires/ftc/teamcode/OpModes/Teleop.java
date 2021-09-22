package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwareClasses.TeleLib;

@TeleOp(group = "Teleop", name = "Teleop")
public class Teleop extends TeleLib {

    @Override
    public void loop() {
        lift();
        arcadeDrive();
        intake();
        claw();
    }

    @Override
    public void stop() {
        killMotors();
    }
}
