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

    public DriveTrain(LinearOpMode opMode)
    {
        this.opMode = opMode;
        br = hardwareMap.dcMotor.get("br");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        fl = hardwareMap.dcMotor.get("fr");

        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        br.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.FORWARD);
        fl.setDirection(DcMotorSimple.Direction.FORWARD);
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

        double count = 2.0;
        if (fr.getCurrentPosition() == 0) {
            count--;
        }
        if (fl.getCurrentPosition() == 0) {
            count--;
        }
        if (br.getCurrentPosition() == 0) {
            count--;
        }
        if (bl.getCurrentPosition() == 0) {
            count--;
        }
        if (count == 0) {
            return 0;
        }
        return (fl.getCurrentPosition() + fr.getCurrentPosition()) / count;
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
        double finalPower = power;
        power = .2;
        double d = 0;
        while (d < distance && time.seconds() < runtimeS && opMode.opModeIsActive()) {
            d = Math.abs(getEncoderAverage() - initEncoder);
            if (d <= distance / 1.25) {
                power += .05;
            } else if (d > distance / 1.25) {
                power += -.025;
            }

            setMotorsPower(power);
            if (!opMode.opModeIsActive()) {
                killMotors();
            }
            opMode.telemetry.addData("Encoder distance left", (distance - getEncoderAverage()));
            opMode.telemetry.update();

        }
        killMotors();
    }

}
