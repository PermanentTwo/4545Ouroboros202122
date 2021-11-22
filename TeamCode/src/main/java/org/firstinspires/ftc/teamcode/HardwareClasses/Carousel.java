package org.firstinspires.ftc.teamcode.HardwareClasses;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Carousel {
    public DcMotor carouselLeft;
    public DcMotor carouselRight;
    public ElapsedTime runtime;
    LinearOpMode opMode;


    public Carousel(LinearOpMode opMode) {
        this.opMode = opMode;
        carouselLeft = opMode.hardwareMap.dcMotor.get("lc");
        carouselRight = opMode.hardwareMap.dcMotor.get("rc");
        carouselLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        carouselRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        runtime = new ElapsedTime();



    }
    public void carouselLeftRightDoubleTrouble(double time, boolean isLeft){
        runtime.reset();
        if (isLeft) {
            while (runtime.seconds() < time) {
                carouselLeft.setPower(.4);
                carouselRight.setPower(.4);
            }
        }
        else {
            while (runtime.seconds() < time) {
                carouselLeft.setPower(-.4);
                carouselRight.setPower(-.4);
            }
        }
        carouselLeft.setPower(0);
        carouselRight.setPower(0);
    }

}