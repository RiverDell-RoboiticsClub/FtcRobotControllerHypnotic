package org.firstinspires.ftc.teamcode.subsystems.shooting;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawController {
    public static final double openPos = 0.62;
    public static final double closePos = 0.3;
    public static final long closeMs = 300L;

    private final Servo claw;

    private boolean active = false;
    private long startMs = 0L;

    public ClawController(Servo claw/*, double openPos, double closedPos, long closeMs*/) {
        this.claw = claw;
        /*this.openPos = openPos;
        this.closedPos = closedPos;
        this.closeMs = closeMs;*/
        claw.setPosition(openPos);
    }

    public void trigger(long nowMs) {
        claw.setPosition(closePos);
        active = true;
        startMs = nowMs;
    }

    public void update(long nowMs) {
        if (active && nowMs >= startMs + closeMs) {
            claw.setPosition(openPos);
            active = false;
        }
    }
}