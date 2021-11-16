package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareClasses.DriveTrain;
import org.firstinspires.ftc.teamcode.HardwareClasses.Output;

@Autonomous(group = "Autonomous", name = "lift Testing")
public class LiftTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        Output out = new Output(this);
        DriveTrain dt = new DriveTrain(this);

        waitForStart();

        out.liftUp(2);
        dt.encoderMove(-.3, 3, 1);
        out.box(true);

    }
}
