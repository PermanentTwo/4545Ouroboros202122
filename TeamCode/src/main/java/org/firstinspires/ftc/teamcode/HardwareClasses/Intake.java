package org.firstinspires.ftc.teamcode.HardwareClasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

public class Intake{
    public DcMotor intake;
    public DcMotor intake2;
    public ElapsedTime runtime;

    public Intake() {
        intake = hardwareMap.dcMotor.get("intake");
        intake2 = hardwareMap.dcMotor.get("intake2");
        runtime = new ElapsedTime();
    }

    public void intakeForward(double power, double time) {
        runtime.reset();
        while (runtime.seconds() < time) {
            intake.setPower(power);
            intake2.setPower(power);

        }
    }

    public void intakeBackward(double power, double time) {
        runtime.reset();
        while (runtime.seconds() < time) {
            intake.setPower(-power);
            intake2.setPower(-power);
        }
    }

    public void killIntake(double time) {
        if (runtime.seconds() > time) {
            intake.setPower(0);
            intake2.setPower(0);

        }
    }
}