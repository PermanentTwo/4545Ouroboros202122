package org.firstinspires.ftc.teamcode.HardwareClasses;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

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
        box = opMode.hardwareMap.servo.get ("box");
        lift = opMode.hardwareMap.dcMotor.get("lift");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    //We need to move the lift up 1200 encoder counts
    public void liftUp(int position) {

        ElapsedTime time = new ElapsedTime();
        if (position == 1) {

        }
        else if (position == 2) {
            while (lift.getCurrentPosition() < 1000 && opMode.opModeIsActive()){
              lift.setPower(0.5);
            }
        }
        else if (position == 3) {
            while (lift.getCurrentPosition() < 1520 && opMode.opModeIsActive() && time.seconds() < 3){
                lift.setPower(0.5);
                opMode.telemetry.addData("lift current pos", lift.getCurrentPosition());
                opMode.telemetry.update();
            }
            lift.setPower(0);
        }
        lift.setPower(0);
        opMode.sleep(200);
        //box(true);
    }
    public void liftDown() {
        while (lift.getCurrentPosition() > 0 && opMode.opModeIsActive()) {
            lift.setPower(-.5);
        }
        lift.setPower(0);
    }

    public void box(boolean push) {

        if (push){
            box.setPosition(0);
        }
        else if (!push){
            box.setPosition(.5);
        }
        opMode.sleep(1000);
    }
}
