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

        dt.turnPID(140, true, .7/144, .015, .02/144, 2);
        /*while (opModeIsActive()) {
            telemetry.addData("angle", dt.sensors.getGyroYaw());
            telemetry.update();
        }*/
    }
}
