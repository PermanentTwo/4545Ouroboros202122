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

        waitForStart();

        out.lift.setPower(1);
        sleep(5000);
        out.lift.setPower(0);
    }
}
