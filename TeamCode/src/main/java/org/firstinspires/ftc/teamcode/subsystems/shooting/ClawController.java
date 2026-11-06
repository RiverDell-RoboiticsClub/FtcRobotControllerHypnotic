package org.firstinspires.ftc.teamcode.subsystems.shooting;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawController {
    public static final double openPos = 0.62;
    public static final double closePos = 0.3;
    public static final long closeMs = 300L;

    private final Servo pollenClaw;
    private final Servo nectarClaw;


    private boolean active = false;
    private long startMs = 0L;

    public ClawController(Servo pollenClaw, Servo nectarClaw) {
        this.pollenClaw = pollenClaw;
        pollenClaw.setPosition(openPos);
        this.nectarClaw = nectarClaw;
        nectarClaw.setPosition(openPos);
    }

    public void trigger(long nowMs) {
        pollenClaw.setPosition(closePos);
        nectarClaw.setPosition(closePos);
        active = true;
        startMs = nowMs;
    }

    public void update(long nowMs) {
        if (active && nowMs >= startMs + closeMs) {
            pollenClaw.setPosition(openPos);
            nectarClaw.setPosition(openPos);
            active = false;
        }
    }
}