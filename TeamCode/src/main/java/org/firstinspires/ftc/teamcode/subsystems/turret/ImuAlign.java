package org.firstinspires.ftc.teamcode.subsystems.turret;

public class ImuAlign {
    private double yShoot;
    private double xShoot;
    private double angleShoot;
    private double distanceToTarget;
    private static double distanceToRPMConstant = 100;
    public void update(double xPos, double yPos, double angle, boolean blue){
        if (yPos>72){
            yShoot = 60;
        }
        else{
            yShoot = 144-60;
        }
        if (xPos>72){
            xShoot = 144-60;
        }
        else{
            xShoot = 60;
        }
        double xOffset = xPos-xShoot;
        double yOffset = yPos-yShoot;
        angleShoot=Math.atan2(Math.abs(xOffset),Math.abs(yOffset));
        distanceToTarget = Math.hypot(xOffset, yOffset);
    }
    public double target(){
        return distanceToTarget*distanceToRPMConstant;
    }
    public double getTurretAngle(){
        return angleShoot*(28/20)*(93/130);
    }
}

/*
        // Define goal's position
        double targetX = 10;
        double targetY = 138;

        // Calculate x/y offsets from goal
        double xOffset = targetX - xPos;
        double yOffset = targetY - yPos;

        // Determine distance to goal
        double distanceToTarget = Math.hypot(xOffset, yOffset);
       */
