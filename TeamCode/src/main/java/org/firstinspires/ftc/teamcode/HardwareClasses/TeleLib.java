package org.firstinspires.ftc.teamcode.HardwareClasses;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
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
    public DcMotor wheel;
    public Servo arm;
    public Servo grabber;
    public Servo hitter;

    public ThreadHandler th_arcade;

    @Override
    public void init() {
        //Comments
        br = hardwareMap.dcMotor.get("br");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        fl = hardwareMap.dcMotor.get("fr");
        intake = hardwareMap.dcMotor.get("intake");
        lift = hardwareMap.dcMotor.get("lift");
        wheel = hardwareMap.dcMotor.get("wheel");

        //TODO: Sophia - Initialize the Servos' hardware maps

        arm = hardwareMap.servo.get("arm");
        grabber = hardwareMap.servo.get ("grabber");
        hitter = hardwareMap.servo.get ("hitter");

        //Set directions and zero power behavior
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        br.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.FORWARD);
        fl.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        lift.setDirection(DcMotorSimple.Direction.FORWARD);

        th_arcade = new ThreadHandler();
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
    double half = .5;

    //TODO: Sophia - Create arcade drive method (GP1: left stick linear, right stick turning)
        //I got most of this from the example so I don't think i did it right
    public void arcadeDrive() {


        double right_stick_x;
        double left_stick_x;
        double left_stick_y;

        if (gamepad1.a && halfToggle == false) {
            th_arcade.queue(half_speed);
        } else if (gamepad1.a && halfToggle == false) {
            th_arcade.queue(full_speed);
        }
        left_stick_y = gamepad1.left_stick_y * half;
        left_stick_x = gamepad1.left_stick_x * half;
        right_stick_x = gamepad1.right_stick_x * half;

        if (Math.abs(left_stick_x) > 0.05 || Math.abs(left_stick_y) > 0.05 || Math.abs(right_stick_x) > 0.05) {

            fr.setPower(left_stick_y - left_stick_x - right_stick_x);
            fl.setPower(left_stick_y + left_stick_x + right_stick_x);
            br.setPower(left_stick_y + left_stick_x - right_stick_x);
            bl.setPower(left_stick_y - left_stick_x + right_stick_x);

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

        //lift method (GP2: right joystick up and down)
        public void lift () {
            if (Math.abs(gamepad2.right_stick_y) > .05) {
                lift.setPower(gamepad2.right_stick_y);
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

        }

        //TODO: Sophia - create grabber method (GP2: a = grabber pos toggle bw 0 and 1; b = arm pos toggle bw 0 and 1)
        //^^This is a bit complicated so you make sure to ask for help if you need it!
        public void box () {

            if (gamepad2.a == false && grabber.getPosition() == 1) {
                grabber.setPosition(0);
            }
            else if (gamepad2.a == true && grabber.getPosition() == 0) {
                grabber.setPosition(1);
            }
        }

        public void wheel () {
            if (gamepad2.b == true){
                wheel.setPower(1);
            }
            else if (!gamepad2.b){
                wheel.setPower(0);
            }
        }
    }