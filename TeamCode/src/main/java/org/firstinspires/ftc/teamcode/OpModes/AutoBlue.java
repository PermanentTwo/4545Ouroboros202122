package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareClasses.Output;

@Autonomous(group = "Autonomous", name = "Blue Sample Auto")
public class AutoBlue extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        Output out = new Output(this);

        out.liftUp();
    }
}
