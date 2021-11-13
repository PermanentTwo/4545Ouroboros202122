package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareClasses.VisionCamera;

@Autonomous(group = "Autonomous", name = "Vision Testing")
public class VisionTesting extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        VisionCamera vision = new VisionCamera(this);

        waitForStart();
        telemetry.addData("loc", vision.senseRed(this));
        telemetry.update();
        
    }
}
