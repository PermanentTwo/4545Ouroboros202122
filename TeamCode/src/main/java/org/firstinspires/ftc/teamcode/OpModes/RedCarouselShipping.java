package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareClasses.DriveTrain;
import org.firstinspires.ftc.teamcode.HardwareClasses.Output;
import org.firstinspires.ftc.teamcode.HardwareClasses.VisionCamera;

@Autonomous(group = "Autonomous", name = "Red Carousel Shipping")
public class RedCarouselShipping extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        //Carousel carousel = new Carousel(this);
        DriveTrain dt = new DriveTrain(this);
        //Intake intake = new Intake(this);
        Output out = new Output(this);
        //Sensors sensors = new Sensors(this);
        VisionCamera vision = new VisionCamera(this);

        waitForStart();
        switch (vision.senseRedCarousel(this)) {
            case 1:
                dt.encoderMove(.5, 5, 2);
                dt.turnPID(135, false, .7/135, .01, .02/135, 2);
                dt.encoderMove(-.5, 19, 3);
                out.box(true);
                sleep(500);
                out.box(false);
                sleep(500);
                dt.encoderMove(.5, 5, 2);
                dt.turnPID(45, true, .7/45, .01, .02/45, 2);
                dt.encoderMove(-1, 100, 4);
            case 2:
                dt.encoderMove(.5, 5, 2);
                dt.turnPID(135, false, .7/135, .01, .02/135, 2);
                dt.encoderMove(-.5, 17, 3);
                out.liftUp(2);
                dt.encoderMove(-.3, 3, 2);
                out.box(true);
                sleep(500);
                out.box(false);
                sleep(500);
                dt.encoderMove(.3, 5, 2);
                out.liftDown();
                dt.turnPID(45, true, .7/45, .01, .02/45, 2);
                dt.encoderMove(-1, 100, 4);
            case 3:
                dt.encoderMove(.5, 5, 2);
                dt.turnPID(135, false, .7/135, .01, .02/135, 2);
                dt.encoderMove(-.5, 17, 3);
                out.liftUp(3);
                out.box(true);
                sleep(500);
                out.box(false);
                out.liftDown();
                sleep(500);
                dt.encoderMove(.3, 5, 2);
                out.liftDown();
                dt.turnPID(45, true, .7/45, .01, .02/45, 2);
                dt.encoderMove(-1, 100, 4);
        }
    }
}
