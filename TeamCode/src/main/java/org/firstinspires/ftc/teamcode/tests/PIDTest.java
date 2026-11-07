package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
@TeleOp(name = "PIDTest", group = "Teleop")
public class PIDTest extends OpMode {

    public DcMotorEx pollenShooter;
    public double highVelocity = 3000;
    public double lowVelocity = 1000;

    public double curTargetVelocity = highVelocity;

    double F = 0;
    double P = 0;
    double D = 0;

    double[] stepSizes = {100.0, 10.0, 1.0, 0.1};
    int stepIndex = 0;

    @Override
    public void init(){
        pollenShooter = hardwareMap.get(DcMotorEx.class, "motor");
        pollenShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pollenShooter.setDirection(DcMotorSimple.Direction.REVERSE);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0.0, D, F);
        pollenShooter.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
    }

    @Override
    public void loop(){

        if (gamepad1.yWasPressed()){
            if(curTargetVelocity == highVelocity){
                curTargetVelocity = lowVelocity;
            }
            else{curTargetVelocity=highVelocity;}
        }

        if (gamepad1.bWasPressed()){
            stepIndex = (stepIndex + 1)%stepSizes.length;
        }

        if (gamepad1.dpadUpWasPressed()){
            F += stepSizes[stepIndex];
        }
        if (gamepad1.dpadDownWasPressed()){
            F -= stepSizes[stepIndex];

        }
        if (gamepad1.dpadLeftWasPressed()){
            P -= stepSizes[stepIndex];

        }
        if (gamepad1.dpadRightWasPressed()){
            P += stepSizes[stepIndex];

        }
        if (gamepad1.leftBumperWasPressed()){
            D -= stepSizes[stepIndex];

        }
        if (gamepad1.rightBumperWasPressed()){
            D -= stepSizes[stepIndex];

        }

        //set new pidf coefficients
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P,0,D,F);
        pollenShooter.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients);

        pollenShooter.setVelocity(curTargetVelocity);
        double curVelocity = pollenShooter.getVelocity();
        telemetry.addData("Target Velocity", curTargetVelocity);
        telemetry.addData("P", P);
        telemetry.addData("D", D);
        telemetry.addData("F", F);
        telemetry.update();
    }

}
