package org.firstinspires.ftc.teamcode.HardwareClasses;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DriveTrain {

    public DcMotor bl;
    public DcMotor br;
    public DcMotor fl;
    public DcMotor fr;

    public double COUNTS_PER_INCH = 35;

    public LinearOpMode opMode;
    public Sensors sensors;

    public DriveTrain(LinearOpMode opMode)
    {
        this.opMode = opMode;
        br = opMode.hardwareMap.dcMotor.get("br");
        fr = opMode.hardwareMap.dcMotor.get("fr");
        bl = opMode.hardwareMap.dcMotor.get("bl");
        fl = opMode.hardwareMap.dcMotor.get("fl");

        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        br.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.FORWARD);
        fl.setDirection(DcMotorSimple.Direction.FORWARD);
        sensors = new Sensors(opMode);
    }
    //reset encoders
    public void resetEncoders()
    {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void killMotors()
    {
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }
    public double getEncoderAverage() {


        return (bl.getCurrentPosition() + br.getCurrentPosition()) / 2;
    }
    public void setMotorsPower(double power) {

        fl.setPower(power);
        fr.setPower(power);
        br.setPower(power);
        bl.setPower(power);
    }

    public void encoderMove(double power, double distance, double runtimeS) {
        resetEncoders();
        ElapsedTime time = new ElapsedTime();

        double initEncoder = 0;

        time.reset();

        distance = distance * COUNTS_PER_INCH;
        double d = 0;
        while (d < distance && time.seconds() < runtimeS && opMode.opModeIsActive()) {
            d = Math.abs(getEncoderAverage() - initEncoder);


            setMotorsPower(power);
            if (!opMode.opModeIsActive()) {
                killMotors();
            }
            opMode.telemetry.addData("Encoder distance left", (distance - getEncoderAverage()));
            opMode.telemetry.update();

        }
        killMotors();
    }
    public void turn(double power, boolean isRight) {
        if (isRight) {
            fr.setPower(-power);
            br.setPower(-power);
            fl.setPower(power);
            bl.setPower(power);
        } else if (!isRight){
            fr.setPower(power);
            br.setPower(power);
            fl.setPower(-power);
            bl.setPower(-power);
        }
    }

    public void turnPID(double angleChange, boolean turnRight, double kP, double kI, double kD, double timeout) {

        ElapsedTime time = new ElapsedTime();
        ElapsedTime timeoutTimer = new ElapsedTime();

        double error;
        double power;

        double proportional;
        double integral = 0;
        double derivative;

        double prevRunTime;

        double initAngle = sensors.getGyroYaw();

        double lastError = angleChange - 2 - Math.abs(sensors.getGyroYaw() - initAngle);

        time.reset();
        timeoutTimer.reset();

        double control = .4;

        while (Math.abs(sensors.getGyroYaw() - (angleChange + initAngle)) > .1 && timeoutTimer.seconds() < timeout && opMode.opModeIsActive()) {
            prevRunTime = time.seconds();



            error = angleChange - Math.abs(sensors.getGyroYaw() - initAngle);



            proportional = error * kP;

            integral += (error * (time.seconds() - prevRunTime)) * kI;


            derivative = ((error - lastError) / (time.seconds() - prevRunTime)) * kD;


            power = (proportional + integral + derivative)*control;

            if (power < .3 && kI == 0 && kD == 0) {
                power = .25;
            }

            if(control < 1){
                control += .01;
            }

            turn(power, turnRight);

            opMode.telemetry.addData("error ", error);
            opMode.telemetry.addData("P", proportional);
            opMode.telemetry.addData("I", integral);
            opMode.telemetry.addData("D", derivative);
            opMode.telemetry.addData("power", power);
            opMode.telemetry.update();

            lastError = error;
            if (sensors.getGyroYaw() < 360 && sensors.getGyroYaw() > 180)
            {
                initAngle = 360;
            }

            opMode.idle();


        }
        opMode.telemetry.addLine("exited");
        opMode.telemetry.update();

        killMotors();

    }

}
