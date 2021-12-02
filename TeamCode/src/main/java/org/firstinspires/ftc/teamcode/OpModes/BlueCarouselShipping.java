package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareClasses.Carousel;
import org.firstinspires.ftc.teamcode.HardwareClasses.DriveTrain;
import org.firstinspires.ftc.teamcode.HardwareClasses.Output;
import org.firstinspires.ftc.teamcode.HardwareClasses.VisionCamera;

@Autonomous(group = "Blue", name = "Blue Carousel Shipping")
public class BlueCarouselShipping extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //ALLIGN - OUTSIDE EDGE CLOSEST TO WAREHOUSE
        Carousel carousel = new Carousel(this);
        DriveTrain dt = new DriveTrain(this);
        //Intake intake = new Intake(this);
        Output out = new Output(this);
        //Sensors sensors = new Sensors(this);
        VisionCamera vision = new VisionCamera(this);

        waitForStart();
        switch (vision.senseBlueCarousel(this)) {
            case 1:
                dt.encoderMove(.5, 7, 2);
                dt.turnPID(90, true, .7/90, 0.025, .02/90, 2);
                dt.encoderMove(-.6, 15.5, 3);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(-.5, 13.5, 2);
                out.box(true);
                sleep(500);
                out.box(false);
                dt.encoderMove(.5, 5 , 2);

                dt.turnPID(77, false, .8/80, 0, 0, 2);
                dt.encoderMove(.5, 28, 3);
                dt.encoderMove(.25, 10, 3);
                carousel.carouselLeftRightDoubleTrouble(5, true);
                dt.turnPID(105, false, .9/105, 0, 0, 2);
                dt.encoderMove(.5, 8.25, 3);

                // dt.turnPID(45, false, .7/45, .01, .02/45, 2);
                //dt.encoderMove(-1, 100, 4);
                break;
            case 2:
                dt.encoderMove(.5, 6, 2);
                dt.turnPID(148, true, .7/148, .012, .02/148, 2);
                dt.encoderMove(-.5, 17, 3);
                out.liftUp(2);
                dt.encoderMove(-.3, 2, 2);
                out.box(true);
                sleep(500);
                out.box(false);
                dt.encoderMove(.5, 2.5 , 2);
                out.liftDown();
                dt.encoderMove(.5, 5 , 2);

                dt.turnPID(36.5, false, .8/40, 0, 0, 2);
                dt.encoderMove(.5, 15.5, 3);
                dt.encoderMove(.25, 11, 3);
                carousel.carouselLeftRightDoubleTrouble(5, true);
                dt.turnPID(95, false, .75/90, 0, 0, 2);
                dt.encoderMove(.5, 8, 3);
                /*
                dt.turnPID(45, false, .7/45, .01, .02/45, 2);
                dt.encoderMove(-1, 100, 4);*/
                break;
            case 3:
                dt.encoderMove(.5, 6, 2);
                dt.turnPID(148, true, .7/148, .012, .02/148, 2);
                dt.encoderMove(-.5, 17, 3);
                out.liftUp(3);
                dt.encoderMove(-.3, 2, 2);
                out.box(true);
                sleep(500);
                out.box(false);
                dt.encoderMove(.5, 2.5 , 2);
                out.liftDown();
                dt.encoderMove(.5, 5 , 2);

                dt.turnPID(37, false, .8/40, 0, 0, 2);
                dt.encoderMove(.5, 15.5, 3);
                dt.encoderMove(.25, 11, 3);
                carousel.carouselLeftRightDoubleTrouble(5, true);
                dt.turnPID(95, false, .75/90, 0, 0, 2);
                dt.encoderMove(.5, 8, 3);
                break;


               /* sleep(500);
                dt.encoderMove(.3, 5, 2);
                out.liftDown();
                dt.turnPID(45, false, .7/45, .01, .02/45, 2);
                dt.encoderMove(-1, 100, 4);
                break;*/
        }
    }
}
