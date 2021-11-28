package org.firstinspires.ftc.teamcode.HardwareClasses;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ThreadHandler;

public abstract class TeleLib extends OpMode {

    public DcMotor bl;
    public DcMotor br;
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor intake;
    public DcMotor lift;
    //public DcMotor wheel;
    public Servo boxServo;
    //public Servo linearActuator;
    public DcMotor carouselLeft;
    public DcMotor carouselRight;

    public ThreadHandler th_arcade;
    public ThreadHandler th_lift;

    @Override
    public void init() {
        //Comments
        br = hardwareMap.dcMotor.get("br");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        fl = hardwareMap.dcMotor.get("fl");
        intake = hardwareMap.dcMotor.get("intake");
        lift = hardwareMap.dcMotor.get("lift");
        //wheel = hardwareMap.dcMotor.get("wheel");
        carouselLeft = hardwareMap.dcMotor.get("lc");
        carouselRight = hardwareMap.dcMotor.get("rc");

        //TODO: Sophia - Initialize the Servos' hardware maps

        boxServo = hardwareMap.servo.get("box");
        //linearActuator = hardwareMap.servo.get ("la");

        //Set directions and zero power behavior
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        carouselLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        carouselRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        /*fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
*/

        br.setDirection(DcMotorSimple.Direction.FORWARD);
        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        lift.setDirection(DcMotorSimple.Direction.FORWARD);

        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        th_arcade = new ThreadHandler();
        th_lift = new ThreadHandler();
    }

    Thread half_speed = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 300){

            }
            half = .5;
            halfToggle = true;
        }
    });

    Thread full_speed = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 300){

            }
            half = 1;
            halfToggle = false;
        }
    });

    boolean halfToggle = false;
    double half = 1;


    public void arcadeDrive() {


        double right_stick_x;
        double left_stick_y;

        if (gamepad1.a && halfToggle == false) {
            th_arcade.queue(half_speed);
        } else if (gamepad1.a && halfToggle == true) {
            th_arcade.queue(full_speed);
        }
        left_stick_y = gamepad1.left_stick_y * half;
        right_stick_x = gamepad1.right_stick_x * half;

        if (Math.abs(left_stick_y) > 0.05 || Math.abs(right_stick_x) > 0.05) {

            fr.setPower(left_stick_y + right_stick_x);
            fl.setPower(left_stick_y - right_stick_x);
            br.setPower(left_stick_y + right_stick_x);
            bl.setPower(left_stick_y - right_stick_x);

        } else {
            fr.setPower(0);
            fl.setPower(0);
            br.setPower(0);
            bl.setPower(0);
        }

    }
    //intake method (GP2: right bumper forward, left bumper backwards)
    public void intake () {
        boolean right_bumper = gamepad2.right_bumper;
        boolean left_bumper = gamepad2.left_bumper;
        if (right_bumper) {
            intake.setPower(1);
        } else if (left_bumper) {
            intake.setPower(-1);
        } else {
            intake.setPower(0);
        }
    }

    boolean liftOverrideToggle = false;
    double liftHalf = 1;
    Thread no_override = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 300){

            }
            liftOverrideToggle = false;
            liftHalf = 1;

        }
    });
    Thread yes_override = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 300){

            }
            liftOverrideToggle = true;
            liftHalf = .5;
        }
    });
    Thread down_macro = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 300){

            }
            while (lift.getCurrentPosition() > 0) {
                lift.setPower(-1);
            }
            lift.setPower(0);
        }
    });

    //lift method (GP2: left joystick up and down)
    public void lift () {
        if (gamepad2.left_stick_button && liftOverrideToggle) {
            th_lift.queue(no_override);
        } else if (gamepad2.left_stick_button && !liftOverrideToggle) {
            th_lift.queue(yes_override);
        }
        /*
        if (gamepad2.left_trigger > .5) {
            th_lift.queue(down_macro);
        }

        if (Math.abs(gamepad2.left_stick_y) > .5 && lift.getCurrentPosition() > 0 && !liftOverrideToggle) {
            lift.setPower(gamepad2.left_stick_y);
        } else if (Math.abs(gamepad2.left_stick_y) > .5 && liftOverrideToggle){
            lift.setPower(gamepad2.left_stick_y);
        }
        lift.setPower(0);*/
        if (Math.abs(gamepad2.left_stick_y) > .2) {
            lift.setPower(gamepad2.left_stick_y * liftHalf);

        } else {
            lift.setPower(0);
        }
    }

    //Set power of all motors to 0
    public void killMotors () {
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
        lift.setPower(0);
        intake.setPower(0);

    }




    //TODO: Sophia - create grabber method (GP2: a = grabber pos toggle bw 0 and 1; b = arm pos toggle bw 0 and 1)
    //^^This is a bit complicated so you make sure to ask for help if you need it!
    public void box () {

        if (gamepad2.a) {
            boxServo.setPosition(0);
        }
        else if (gamepad2.b) {
            boxServo.setPosition(.4);
        }
    }

    public void carousel(){
        if (gamepad2.left_trigger > .5){
            carouselRight.setPower(.5);
            carouselLeft.setPower(.5);
        }
        else if (gamepad2.right_trigger > .5) {
            carouselLeft.setPower(-.5);
            carouselRight.setPower(-.5);

        }
        else {
            carouselLeft.setPower(0);
            carouselRight.setPower(0);
        }

    }

    public void telemetry() {
        telemetry.addData("half on?", half);
        telemetry.addData("lift override on?", liftOverrideToggle);
        telemetry.addData("box servo pos", boxServo.getPosition());
        telemetry.addData("lift power", lift.getPower());
        telemetry.addData("lift pos", -lift.getCurrentPosition());
        telemetry.addData("bl", bl.getCurrentPosition());
        telemetry.addData("br", br.getCurrentPosition());
        telemetry.addData("fl", fl.getCurrentPosition());
        telemetry.addData("fr", fr.getCurrentPosition());
        telemetry.addData("lc power", carouselLeft.getPower());
        //telemetry.addData("rc power", carouselRight.getPower());
        telemetry.update();
    }
}