package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareClasses.Carousel;
import org.firstinspires.ftc.teamcode.HardwareClasses.DriveTrain;
import org.firstinspires.ftc.teamcode.HardwareClasses.Intake;
import org.firstinspires.ftc.teamcode.HardwareClasses.Output;
import org.firstinspires.ftc.teamcode.HardwareClasses.Sensors;
import org.firstinspires.ftc.teamcode.HardwareClasses.VisionCamera;

@Autonomous(group = "Autonomous", name = "Blue Carousel")
public class BlueCarousel extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Carousel carousel = new Carousel(this);
        DriveTrain dt = new DriveTrain(this);
        Intake intake = new Intake(this);
        Output out = new Output(this);
        Sensors sensors = new Sensors(this);
        VisionCamera vision = new VisionCamera(this);



        waitForStart();
        int pos = vision.senseBlue(this);
        switch (pos) {

            case 1:


            case 2:


            case 3:
        }

    }
}
