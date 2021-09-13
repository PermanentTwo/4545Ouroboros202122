package org.firstinspires.ftc.teamcode.HardwareClasses;

import com.qualcomm.robotcore.hardware.DcMotor;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

public class Intake {
    public DcMotor intake;

    public Intake()
    {
        intake = hardwareMap.dcMotor.get("intake");
    }

    public void intakeForward()
    {
        intake.setPower(1);
    }

    public void intakeBackward()
    {
        intake.setPower(-1);
    }

    public void killIntake()
    {
        intake.setPower(0);
    }
}
