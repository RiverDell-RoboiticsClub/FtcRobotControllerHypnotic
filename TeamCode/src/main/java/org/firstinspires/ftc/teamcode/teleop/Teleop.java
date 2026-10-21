package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends OpMode {

    private DcMotor frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive, intakeLeft, intakeRight, pollenShooter, nectarShooter;
    private Servo pollenHood, nectarHood, pollenClaw, nectarClaw, pollenGate, nectarGate;

    public void init(){
        // 2 servo gate, 2 servo rotation, 2 servo claw, 4 motor drive, 2 motor intake, 2 motor shooter
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRight");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeft");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRight");

        intakeLeft = hardwareMap.get(DcMotor.class, "intakeLeft");
        intakeRight = hardwareMap.get(DcMotor.class, "intakeRight");
        pollenShooter = hardwareMap.get(DcMotor.class, "pollenShooter");
        nectarShooter = hardwareMap.get(DcMotor.class, "nectarShooter");

        pollenHood = hardwareMap.get(Servo.class, "pollenHood");
        nectarHood = hardwareMap.get(Servo.class, "nectarHood");
        pollenClaw = hardwareMap.get(Servo.class, "pollenClaw");
        nectarClaw = hardwareMap.get(Servo.class, "nectarClaw");
        pollenGate = hardwareMap.get(Servo.class, "pollenGate");
        nectarGate = hardwareMap.get(Servo.class, "nectarGate");
    }

    public void loop() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontLeftDrive.setPower(frontLeftPower);
        frontRightDrive.setPower(frontRightPower);
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);
    }
}
