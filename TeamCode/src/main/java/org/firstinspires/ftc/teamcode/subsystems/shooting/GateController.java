
package org.firstinspires.ftc.teamcode.subsystems.shooting;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class GateController {

    public enum GateCycleState {
        IDLE, OPEN_INTAKE
    }

    private final Servo pollenGateServo;
    private final Servo nectarGateServo;

    private final DcMotor leftIntakeMotor;
    private final DcMotor rightIntakeMotor;

    private final FlywheelPIDF pollenPID;
    private final FlywheelPIDF nectarPID;

    private final double gateOpenPos = 0.67;
    private final double gateClosedPos = 0.485;

    private final long intakeDurationMs = 1050;
    private final long clawTriggerBeforeEndMs = 400;

    private final double intakeSequencePower = 1.0;

    private boolean gateClosed = false;
    private GateCycleState state = GateCycleState.IDLE;

    private long startMs = 0L;
    private boolean clawTriggerSent = false;

    public GateController(
            Servo pollenGateServo,
            Servo nectarGateServo,
            DcMotor leftIntakeMotor,
            DcMotor rightIntakeMotor,
            FlywheelPIDF pollenPID,
            FlywheelPIDF nectarPID) {

        this.pollenGateServo = pollenGateServo;
        this.nectarGateServo = nectarGateServo;
        this.leftIntakeMotor = leftIntakeMotor;
        this.rightIntakeMotor = rightIntakeMotor;

        this.pollenPID = pollenPID;
        this.nectarPID = nectarPID;
    }

    public void setGateClosed(boolean closed) {
        gateClosed = closed;

        pollenGateServo.setPosition(
                gateClosed ? gateClosedPos : gateOpenPos);

        nectarGateServo.setPosition(
                gateClosed ? gateClosedPos : gateOpenPos);
    }

    public void toggleGate() {
        if (state == GateCycleState.IDLE) {
            setGateClosed(!gateClosed);
        }
    }

    public boolean update(long nowMs) {

        boolean shouldTriggerClaw = false;

        if (state == GateCycleState.OPEN_INTAKE) {

            long elapsed = nowMs - startMs;
            long remaining = intakeDurationMs - elapsed;

            if (remaining <= clawTriggerBeforeEndMs
                    && !clawTriggerSent) {

                clawTriggerSent = true;
                shouldTriggerClaw = true;
            }

            if (elapsed >= intakeDurationMs) {

                leftIntakeMotor.setPower(0.0);
                rightIntakeMotor.setPower(0.0);

                setGateClosed(true);

                state = GateCycleState.IDLE;
            }
        }

        return shouldTriggerClaw;
    }

    public void startIntakeSequence(long nowMs) {

        if (state != GateCycleState.IDLE) return;

        setGateClosed(false);

        leftIntakeMotor.setPower(intakeSequencePower);
        rightIntakeMotor.setPower(intakeSequencePower);

        startMs = nowMs;
        state = GateCycleState.OPEN_INTAKE;

        clawTriggerSent = false;
    }

    public boolean isBusy() {
        return state != GateCycleState.IDLE;
    }

    public boolean isGateClosed() {
        return gateClosed;
    }

    public GateCycleState getState() {
        return state;
    }
}
