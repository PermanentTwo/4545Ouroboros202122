package org.firstinspires.ftc.teamcode.HardwareClasses;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Intake{
    public DcMotor intake;
    public DcMotor intake2;
    public ElapsedTime runtime;
    LinearOpMode opMode;

    public Intake(LinearOpMode opMode) {
        opMode = this.opMode;
        intake = hardwareMap.dcMotor.get("intake");
        intake2 = hardwareMap.dcMotor.get("intake2");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        intake2.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        runtime = new ElapsedTime();
    }

    public void intakeForward(double power, double time) {
        runtime.reset();
        while (runtime.seconds() < time && opMode.opModeIsActive()) {
            intake.setPower(power);
            intake2.setPower(power);
        }
        killIntake();
    }

    public void intakeBackward(double power, double time) {
        runtime.reset();
        while (runtime.seconds() < time && opMode.opModeIsActive()) {
            intake.setPower(-power);
            intake2.setPower(-power);
        }
        killIntake();
    }

    public void killIntake() {
        intake.setPower(0);
        intake2.setPower(0);

    }
}