package org.firstinspires.ftc.teamcode.HardwareClasses;

import com.qualcomm.robotcore.hardware.DcMotor;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

public class Intake {
    public DcMotor intake;
    public Intake()
    {
        intake = hardwareMap.dcMotor.get("intake");
    }
}
