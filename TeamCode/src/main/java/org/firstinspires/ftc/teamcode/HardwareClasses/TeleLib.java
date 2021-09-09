package org.firstinspires.ftc.teamcode.HardwareClasses;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class TeleLib extends OpMode {

    public DcMotor bl;
    public DcMotor br;
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor intake;
    public DcMotor lift;
    public Servo arm;
    public Servo grabber;
    public Servo hitter;

    @Override
    public void init() {
        //TODO: Krish - Initialize the DcMotors' hardware maps
        //Comments
            br=hardwareMap.dcMotor.get("br");
            fr=hardwareMap.dcMotor.get("fr");
            bl=hardwareMap.dcMotor.get("bl");
            fr=hardwareMap.dcMotor.get("fr");

        //TODO: Sophia - Initialize the Servos' hardware maps

        //TODO: Shriya - Set directions and zero power behavior
    }

    //TODO: Sophia - Create arcade drive method (GP1: left stick linear, right stick turning)
    public void arcadeDrive()
    {

    }

    //TODO: Krish - Create intake method (GP2: right bumper forward, left bumper backwards)
    public void intake()
    {
        boolean right_bumper = gamepad2.right_bumper;
        boolean left_bumper = gamepad2.left_bumper;
        if (right_bumper) {
            intake.setPower(1);
        } else if (left_bumper) {
            intake.setPower(-1);
        } else {
            intake.setPower(0);
        }
    }

    //TODO: Shriya - Create lift method (GP2: right joystick up and down)
    public void lift()
    {

    }

    //TODO: Krish - Set power of all motors to 0
    public void killMotors()
    {
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);

    }

    //TODO: Sophia - create grabber method (GP2: a = grabber pos toggle bw 0 and 1; b = arm pos toggle bw 0 and 1)
    //^^This is a bit complicated so you make sure to ask for help if you need it!
    public void claw()
    {

    }
}
