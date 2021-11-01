package org.firstinspires.ftc.teamcode.HardwareClasses;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Carousel {
    public CRServo carouselLeft;
    public CRServo carouselRight;
    public ElapsedTime runtime;
    LinearOpMode opMode;


    public Carousel(LinearOpMode opMode) {
        opMode = this.opMode;
        carouselLeft = hardwareMap.crservo.get("carouselLeft");
        carouselRight = hardwareMap.crservo.get("carouselRight");


    }
    public void carouselLeftRightDoubleTrouble(double time){
        runtime.reset();
        while(runtime.seconds() < time){
            carouselLeft.setPower(1);
            carouselRight.setPower(1);
        }
        carouselLeft.setPower(0);
        carouselRight.setPower(0);
    }

}