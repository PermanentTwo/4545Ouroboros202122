package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareClasses.Carousel;
import org.firstinspires.ftc.teamcode.HardwareClasses.DriveTrain;
import org.firstinspires.ftc.teamcode.HardwareClasses.Intake;
import org.firstinspires.ftc.teamcode.HardwareClasses.Output;
import org.firstinspires.ftc.teamcode.HardwareClasses.Sensors;
import org.firstinspires.ftc.teamcode.HardwareClasses.VisionCamera;

@Autonomous(group = "Blue", name = "Blue Warehouse Park")
public class BlueWarehousePark extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {


        //Carousel carousel = new Carousel(this);
        DriveTrain dt = new DriveTrain(this);
        //Intake intake = new Intake(this);
        //Output out = new Output(this);
        //Sensors sensors = new Sensors(this);
        //VisionCamera vision = new VisionCamera(this);

        waitForStart();
        dt.encoderMove(.6, 10, 3);
        dt.turnPID(90, true, .7/90, .02, .02/90, 3);
        dt.encoderMove(-1, 60, 4);

        /*int pos = vision.senseBlue(this);
        switch (pos) {
            case 1:
                dt.turnPID(45, true, 90 / .7, .01, .02 / 45, 2);
                dt.encoderMove(.5, 3, 2);
                dt.turnPID(180, true, 180 / .7, .01, .02 / 180, 2);
                dt.encoderMove(.5, 5, 2);
                out.box(true);
                sleep(500);
                out.box(false);
                sleep(500);
                dt.turnPID(45, true, .7 / 45, .01, .02 / 45, 2);
                dt.encoderMove(-.5, -2, 2);
            case 2:
                dt.turnPID(45, true, 90 / .7, .01, .02 / 45, 2);
                dt.encoderMove(.5, 3, 2);
                dt.turnPID(180, true, 180 / .7, .01, .02 / 180, 2);
                dt.encoderMove(.5, 5, 2);
                dt.encoderMove(.5, 3, 3);
                out.liftUp(2);
                out.box(true);
                sleep(500);
                out.box(false);
                out.liftDown();
                dt.turnPID(45, true, .7 / 45, .01, .02 / 45, 2);
                dt.encoderMove(-.5, -2, 2);
            case 3:
                dt.turnPID(45, true, 90 / .7, .01, .02 / 45, 2);
                dt.encoderMove(.5, 3, 2);
                dt.turnPID(180, true, 180 / .7, .01, .02 / 180, 2);
                dt.encoderMove(.5, 5, 2);
                dt.encoderMove(.5, 3, 3);
                out.liftUp(3);
                out.box(true);
                sleep(500);
                out.box(false);
                out.liftDown();
                dt.turnPID(45, true, .7 / 45, .01, .02 / 45, 2);
                dt.encoderMove(-.5, -2, 2);

        }*/
    }
}