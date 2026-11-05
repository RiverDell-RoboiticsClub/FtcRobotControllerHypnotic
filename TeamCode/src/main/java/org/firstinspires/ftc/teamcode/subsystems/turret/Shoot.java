package org.firstinspires.ftc.teamcode.subsystems.turret;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Shoot {
    private DcMotor intakeLeft, intakeRight, pollenShooter, nectarShooter;
    private Servo pollenHood, nectarHood, pollenClaw, nectarClaw, pollenGate, nectarGate;

    private double intakePower = 1000.0;
    private double pollenPower = 4000.0;
    private double nectarPower = 4000.0;
    private double pHoodPos = 1.0;
    private double nHoodPos = 1.0;
    private double pClawPos = 1.0;
    private double nClawPos = 1.0;
    private double pGatePos = 1.0;
    private double nGatePos = 1.0;
    public Shoot(DcMotor intakeLeft,DcMotor intakeRight, DcMotor pollenShooter, DcMotor nectarShooter, Servo pollenHood, Servo nectarHood, Servo pollenClaw, Servo nectarClaw, Servo pollenGate, Servo nectarGate){
        this.intakeLeft = intakeLeft;
        this.intakeRight = intakeRight;
        this.pollenShooter = pollenShooter;
        this.nectarShooter = nectarShooter;
        this.pollenHood = pollenHood;
        this.nectarHood = nectarHood;
        this.pollenClaw = pollenClaw;
        this.nectarClaw = nectarClaw;
        this.pollenGate = pollenGate;
        this.nectarGate = nectarGate;
    }
    public void pollenShootSequence(double angle){
        intakeLeft.setPower(1000.0);
    }
    public void nectarShootSequence(double angle){}
}
