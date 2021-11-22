package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareClasses.DriveTrain;

@Autonomous(group = "Testing", name = "EncoderMoveTest")
public class EncoderMoveTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DriveTrain dt = new DriveTrain(this);

        waitForStart();

        dt.encoderMove(-.6, 10, 3);
    }
}