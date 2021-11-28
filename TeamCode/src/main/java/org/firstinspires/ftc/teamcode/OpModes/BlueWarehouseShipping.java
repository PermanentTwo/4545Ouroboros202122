package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareClasses.DriveTrain;
import org.firstinspires.ftc.teamcode.HardwareClasses.Output;
import org.firstinspires.ftc.teamcode.HardwareClasses.VisionCamera;

@Autonomous(group = "Blue", name = "Blue Warehouse Shipping")
public class BlueWarehouseShipping extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {


        //Carousel carousel = new Carousel(this);
        DriveTrain dt = new DriveTrain(this);
        //Intake intake = new Intake(this);
        Output out = new Output(this);
        //Sensors sensors = new Sensors(this);
        VisionCamera vision = new VisionCamera(this);

        waitForStart();

        int pos = vision.senseBlueWarehouse(this);
        switch (pos) {
            case 1:
                dt.encoderMove(.5, 7, 2);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(.6, 12, 3);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(-.5, 12, 2);

                out.box(true);
                sleep(500);
                out.box(false);
                dt.encoderMove(.5, 5, 2);
                sleep(500);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(1, 60, 2);

                break;

            case 2:

                dt.encoderMove(.5, 7, 2);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(.6, 14, 3);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(-.5, 9, 2);
                out.liftUp(2);
                dt.encoderMove(-.5, 2.5, 2);
                out.box(true);
                sleep(500);
                out.box(false);
                dt.encoderMove(.5, 5, 2);
                out.liftDown();
                sleep(500);
                dt.turnPID(93, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(1, 60, 2);
                break;
            case 3:
                dt.encoderMove(.5, 7, 2);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(.6, 14, 3);
                dt.turnPID(90, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(-.5, 9, 2);
                out.liftUp(3);
                dt.encoderMove(-.5, 2.5, 2);
                out.box(true);
                sleep(500);
                out.box(false);
                dt.encoderMove(.5, 5, 2);
                out.liftDown();
                sleep(500);
                dt.turnPID(93, true, .7/90, .025, .02/90, 2);
                dt.encoderMove(1, 60, 2);


                break;
        }
    }
}