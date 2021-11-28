package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareClasses.Carousel;
import org.firstinspires.ftc.teamcode.HardwareClasses.DriveTrain;
import org.firstinspires.ftc.teamcode.HardwareClasses.Output;
import org.firstinspires.ftc.teamcode.HardwareClasses.VisionCamera;

@Autonomous(group = "Red", name = "Red Carousel Shipping")
public class RedCarouselShipping extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        //ALIGN: INSIDE EDGE CLOSEST TO CAROUSEL
        Carousel carousel = new Carousel(this);
        DriveTrain dt = new DriveTrain(this);
        //Intake intake = new Intake(this);
        Output out = new Output(this);
        //Sensors sensors = new Sensors(this);
        VisionCamera vision = new VisionCamera(this);

        waitForStart();
        switch (vision.senseRedCarousel(this)) {
            case 1:
                dt.encoderMove(.5, 7, 2);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(.6, 17.5, 3);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(-.5, 12, 2);

                out.box(true);
                sleep(500);
                out.box(false);
                dt.encoderMove(.5, 10, 2);
                sleep(500);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(.6, 22, 3);
                dt.encoderMove(.25, 12, 3);
                carousel.carouselLeftRightDoubleTrouble(4.5, false);
                dt.encoderMove(-.5, 5, 2);
                dt.turnPID(60, true, .7/60, .025, .02/60, 2);
                dt.encoderMove(.5, 12, 2);
                break;

            case 2:
                dt.encoderMove(.5, 7, 2);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(.6, 17.5, 3);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(-.5, 12.5, 2);
                out.liftUp(2);
                dt.encoderMove(-.5, 2.5, 2);
                out.box(true);
                sleep(500);
                out.box(false);
                dt.encoderMove(.5, 14.5, 2);
                out.liftDown();
                sleep(500);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(.6, 26, 3);
                dt.encoderMove(.25, 6, 3);
                carousel.carouselLeftRightDoubleTrouble(4.5, false);
                dt.encoderMove(-.5, 5, 2);
                dt.turnPID(60, true, .7/60, .025, .02/60, 2);
                dt.encoderMove(.5, 12, 2);
                break;


            case 3:
                dt.encoderMove(.5, 7, 2);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(.6, 17.5, 3);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(-.5, 13, 2);
                out.liftUp(3);
                dt.encoderMove(-.5, 2.5, 2);
                out.box(true);
                sleep(500);
                out.box(false);
                dt.encoderMove(.5, 14.5, 2);
                out.liftDown();
                sleep(500);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(.6, 26, 3);
                dt.encoderMove(.25, 6, 3);
                carousel.carouselLeftRightDoubleTrouble(4.5, false);
                dt.encoderMove(-.5, 5, 2);
                dt.turnPID(60, true, .7/60, .025, .02/60, 2);
                dt.encoderMove(.5, 12, 2);
                break;
                /*
                dt.encoderMove(.3, 5, 2);
                out.liftDown();
                dt.turnPID(90, true, .7/45, .01, .02/45, 2);
                dt.encoderMove(.5, 100, 4);*/
        }
    }
}
