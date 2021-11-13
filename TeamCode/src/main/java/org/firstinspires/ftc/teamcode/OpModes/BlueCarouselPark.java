package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareClasses.Carousel;
import org.firstinspires.ftc.teamcode.HardwareClasses.DriveTrain;
import org.firstinspires.ftc.teamcode.HardwareClasses.Intake;
import org.firstinspires.ftc.teamcode.HardwareClasses.Output;
import org.firstinspires.ftc.teamcode.HardwareClasses.Sensors;
import org.firstinspires.ftc.teamcode.HardwareClasses.VisionCamera;

@Autonomous(group = "Autonomous", name = "Blue Carousel Park")
public class BlueCarouselPark extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //Carousel carousel = new Carousel(this);
        DriveTrain dt = new DriveTrain(this);
        //Intake intake = new Intake(this);
        Output out = new Output(this);
        //Sensors sensors = new Sensors(this);
        VisionCamera vision = new VisionCamera(this);



        waitForStart();


        int pos = vision.senseRed(this);
        switch (pos) {

            case 1:

                dt.encoderMove(.5, 5, 2);
                dt.turnPID(45, false, 45/.7, .01, .02/45, 2);
                dt.encoderMove(.7, 24, 3);
                out.box(true);
                sleep(500);
                out.box(false);
                dt.encoderMove(-.5, -20, 3);
                dt.turnPID(135, true, .7/135, .01, .02/135, 2);
                dt.encoderMove(.8, 20, 3);
                //carousel.carouselLeftRightDoubleTrouble(5);
                dt.encoderMove(-1, -100, 5);


            case 2:

                dt.encoderMove(.5, 5, 2);
                dt.turnPID(45, false, 45/.7, .01, .02/45, 2);
                dt.encoderMove(.7, 24, 3);
                out.liftUp(2);
                out.box(true);
                sleep(500);
                out.box(false);
                out.liftDown();
                dt.encoderMove(-.5, -20, 3);
                dt.turnPID(135, true, .7/135, .01, .02/135, 2);
                dt.encoderMove(.8, 20, 3);
                //carousel.carouselLeftRightDoubleTrouble(5);
                dt.encoderMove(-1, -100, 5);

            case 3:
                dt.encoderMove(.5, 5, 2);
                dt.turnPID(45, false, 45/.7, .01, .02/45, 2);
                dt.encoderMove(.7, 24, 3);
                out.liftUp(3);
                out.box(true);
                sleep(500);
                out.box(false);
                out.liftDown();
                dt.encoderMove(-.5, -20, 3);
                dt.turnPID(135, true, .7/135, .01, .02/135, 2);
                dt.encoderMove(.8, 20, 3);
                //carousel.carouselLeftRightDoubleTrouble(5);
                dt.encoderMove(-1, -100, 5);
        }

    }
}
