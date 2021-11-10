package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareClasses.Carousel;
import org.firstinspires.ftc.teamcode.HardwareClasses.DriveTrain;
import org.firstinspires.ftc.teamcode.HardwareClasses.Intake;
import org.firstinspires.ftc.teamcode.HardwareClasses.Output;
import org.firstinspires.ftc.teamcode.HardwareClasses.Sensors;
import org.firstinspires.ftc.teamcode.HardwareClasses.VisionCamera;

@Autonomous(group = "Autonomous", name = "Red Carousel")
public class RedCarousel extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DriveTrain dt = new DriveTrain(this);
        //Intake intake = new Intake(this);
        //Output out = new Output(this);
        //Sensors sensors = new Sensors(this);
        //VisionCamera vision = new VisionCamera(this);



        waitForStart();
        dt.encoderMove(.6, 10, 3);
        dt.turnPID(90, true, .7/90, .02, .02/90, 3);
        dt.encoderMove(1, 100, 4);
        /*switch (pos) {

            case 1:

                dt.encoderMove(.5, 5, 2);
                dt.turnPID(45, true, 45/.7, .01, .02/45, 2);
                dt.encoderMove(.7, 24, 3);
                out.box(true);
                sleep(500);
                out.box(false);
                dt.encoderMove(-.5, -20, 3);
                dt.turnPID(135, false, .7/135, .01, .02/135, 2);
                dt.encoderMove(.8, 20, 3);
                carousel.carouselLeftRightDoubleTrouble(5);
                dt.encoderMove(-1, -100, 5);


            case 2:

                dt.encoderMove(.5, 5, 2);
                dt.turnPID(45, true, 45/.7, .01, .02/45, 2);
                dt.encoderMove(.7, 24, 3);
                out.liftUp(2);
                out.box(true);
                sleep(500);
                out.box(false);
                out.liftDown();
                dt.encoderMove(-.5, -20, 3);
                dt.turnPID(135, false, .7/135, .01, .02/135, 2);
                dt.encoderMove(.8, 20, 3);
                carousel.carouselLeftRightDoubleTrouble(5);
                dt.encoderMove(-1, -100, 5);

            case 3:
                dt.encoderMove(.5, 5, 2);
                dt.turnPID(45, true, 45/.7, .01, .02/45, 2);
                dt.encoderMove(.7, 24, 3);
                out.liftUp(3);
                out.box(true);
                sleep(500);
                out.box(false);
                out.liftDown();
                dt.encoderMove(-.5, -20, 3);
                dt.turnPID(135, false, .7/135, .01, .02/135, 2);
                dt.encoderMove(.8, 20, 3);
                carousel.carouselLeftRightDoubleTrouble(5);
                dt.encoderMove(-1, -100, 5);
        }*/
    }
}
