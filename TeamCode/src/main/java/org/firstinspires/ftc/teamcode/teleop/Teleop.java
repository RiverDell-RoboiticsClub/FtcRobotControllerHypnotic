package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends OpMode {

    private DcMotor frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive, intakeLeft, intakeRight, pollenShooter, nectarShooter;
    private Servo pollenHood, nectarHood, pollenClaw, nectarClaw, pollenGate, nectarGate;
    private boolean yPressedLast = false;
    private boolean intakeOn = true;      // replaces the intakePower toggle
    private static final double INTAKE_POWER = 1.0;
    private static final double TRIGGER_THRESHOLD = 0.2;
    private GoBildaPinpointDriver driver;
    private Pose2D position;
    private double heading;
    private double xPos;
    private double yPos;

    public void init(){
        // 2 servo gate, 2 servo rotation, 2 servo claw, 4 motor drive, 2 motor intake, 2 motor shooter
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRight");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeft");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRight");

        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);

        intakeLeft = hardwareMap.get(DcMotor.class, "intakeLeft");
        intakeRight = hardwareMap.get(DcMotor.class, "intakeRight");
        pollenShooter = hardwareMap.get(DcMotor.class, "pollenShooter");
        nectarShooter = hardwareMap.get(DcMotor.class, "nectarShooter");

        intakeLeft.setDirection(DcMotor.Direction.FORWARD);
        intakeRight.setDirection(DcMotor.Direction.FORWARD);

        pollenHood = hardwareMap.get(Servo.class, "pollenHood");
        nectarHood = hardwareMap.get(Servo.class, "nectarHood");
        pollenClaw = hardwareMap.get(Servo.class, "pollenClaw");
        nectarClaw = hardwareMap.get(Servo.class, "nectarClaw");
        pollenGate = hardwareMap.get(Servo.class, "pollenGate");
        nectarGate = hardwareMap.get(Servo.class, "nectarGate");

        driver = hardwareMap.get(GoBildaPinpointDriver.class, "driver");
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

        boolean yNow = gamepad1.y || gamepad2.y;
        if (yNow && !yPressedLast) {
            intakeOn = !intakeOn;
        }
        yPressedLast = yNow;

        // REVERSE (only while intake is off and left trigger is held)
        boolean reverseHeld = gamepad1.left_trigger > TRIGGER_THRESHOLD
                || gamepad2.left_trigger > TRIGGER_THRESHOLD;

        double intakePower = INTAKE_POWER;
        if (intakeOn) {
        } else if (reverseHeld) {
            intakeLeft.setDirection(DcMotor.Direction.REVERSE);
            intakeRight.setDirection(DcMotor.Direction.REVERSE);
        } else {
            intakePower = 0.0;
        }
        intakeLeft.setPower(intakePower);
        intakeRight.setPower(intakePower);

        // Odometry Code
        driver.update();
        position = driver.getPosition();
        heading = position.getHeading(AngleUnit.DEGREES);
        xPos = position.getX(DistanceUnit.INCH);
        yPos = position.getY(DistanceUnit.INCH);

        // Experimental Code

        // Define goal's position
        double targetX = 10;
        double targetY = 138;

        // Calculate x/y offsets from goal
        double xOffset = targetX - xPos;
        double yOffset = targetY - yPos;

        // Determine distance to goal
        double distanceToTarget = Math.hypot(xOffset, yOffset);

        telemetry.addData("Intake", intakeOn ? "ON" : "OFF");
        telemetry.addData("Distance to Target", distanceToTarget);
        telemetry.update();
    }
}
