package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareClasses.DriveTrain;
import org.firstinspires.ftc.teamcode.HardwareClasses.VisionCamera;

@Autonomous(group = "Autonomous", name = "PID Testing")
public class PIDTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DriveTrain dt = new DriveTrain(this);

        waitForStart();

        dt.turnPID(90, true, .7/90, .01, .02/90, 3);
        /*while (opModeIsActive()) {
            telemetry.addData("angle", dt.sensors.getGyroYaw());
            telemetry.update();
        }*/
    }
}
