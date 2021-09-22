package org.firstinspires.ftc.teamcode.HardwareClasses;

import android.provider.SearchRecentSuggestions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Output {
    //add lift and claw

    public DcMotor lift;
    public Servo box;
    public LinearOpMode opMode;

    ElapsedTime time = new ElapsedTime();

    public Output(LinearOpMode opMode)
    {

        this.opMode = opMode;
    }

    //We need to move the lift up 1200 encoder counts
    public void liftUp(int position) {

        if (position == 1) {
            while (lift.getCurrentPosition() <= 1000 && opMode.opModeIsActive()) {
                box(true);
            }
            box(false);
        }
        else if (position == 2) {
            while (lift.getCurrentPosition() <= 2000 && lift.getCurrentPosition() >1000 && opMode.opModeIsActive()){
              box(true);
            }
            box(false);
        }
        else if (position == 3) {
            while (lift.getCurrentPosition() <= 3000 && lift.getCurrentPosition() >2000 && opMode.opModeIsActive()){
                box(true);
            }
            box(false);
        }
    }

    public void box(boolean push) {

        if (push){
            box.setPosition(1);
        }
        else if (!push){
            box.setPosition(0);
        }
    }
}
