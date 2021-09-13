package org.firstinspires.ftc.teamcode.HardwareClasses;

import android.provider.SearchRecentSuggestions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Output {
    //add lift and claw

    public DcMotor lift;
    public Servo arm;
    public Servo grabber;
    public LinearOpMode opMode;

    ElapsedTime time = new ElapsedTime();

    public Output(LinearOpMode opMode)
    {
        this.opMode = opMode;
    }

    //We need to move the lift up 1200 encoder counts
    public void liftUp()
    {
        while (lift.getCurrentPosition() < 1200 && time.seconds() < 2 && opMode.opModeIsActive())
        {
            lift.setPower(.7);
        }
        lift.setPower(0);
    }
}
