package org.firstinspires.ftc.teamcode.subsystems.tuning;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;



public class FlywheelPIDF {
    private DcMotorEx flywheelMotor;
    private double Kp;
    private double Ki;
    private double Kd;
    private double Kf;
    private double derivative = 0;
    private double error = 0;
    private double out = 0;

    private double integralSum = 0;

    private double lastError = 0;

    private double curVelocity;
    private double targetVelocity = 900.0;

    ElapsedTime timer = new ElapsedTime();

    public FlywheelPIDF(DcMotorEx flywheel, double Kp, double Ki, double Kd, double Kf){
        this.flywheelMotor=flywheel;
        this.Kp = Kp;
        this.Kd = Kd;
        this.Ki=Ki;
        this.Kf = Kf;
    }
    public void runPID(DcMotorEx flywheelMotor) {
//set new pidf coefficients
            PIDFCoefficients pidfCoefficients = new PIDFCoefficients(Kp,0,Kd,Kf);
            flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients);

            flywheelMotor.setVelocity(targetVelocity);
            curVelocity = flywheelMotor.getVelocity();

    }
}
