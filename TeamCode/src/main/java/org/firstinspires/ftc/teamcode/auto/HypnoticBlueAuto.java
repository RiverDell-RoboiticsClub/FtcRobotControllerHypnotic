package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.subsystems.turret.ImuAlign;

public class HypnoticBlueAuto extends OpMode {
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

        ImuAlign imuAlign = new ImuAlign();

    }
    public void init_loop(){

    }
    public void start(){

    }
    public void loop(){

    }
    public void stop(){

    }
}
