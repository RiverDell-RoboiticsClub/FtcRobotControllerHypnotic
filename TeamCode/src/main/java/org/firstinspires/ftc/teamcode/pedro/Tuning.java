package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.pedropathing.tuning.autotune.Procedure;
import com.pedropathing.tuning.autotune.Tuner;

import org.firstinspires.ftc.teamcode.pedro.procedures.ForesightTuner;
import org.firstinspires.ftc.teamcode.pedro.procedures.MecanumTuner;
import org.firstinspires.ftc.teamcode.pedro.procedures.PinpointTuner;
import org.firstinspires.ftc.teamcode.pedro.procedures.TwoWheelTuner;

public class Tuning {
    //Automatic Mecanum Drive Identification (FIRST)
    //https://pedropathing.com/docs/pathing/tuning/drivetrain/mecanum

    @Tuner
    public static Procedure mecanumTuner() {
        return new MecanumTuner();
    }

    //Automatic pinpoint tuning
    //https://pedropathing.com/docs/pathing/tuning/localization/pinpoint

    @Tuner
    public static Procedure pinpointTuner() {
        return new PinpointTuner();

    }
    //for two wheel
    //https://pedropathing.com/docs/pathing/tuning/localization/two-wheel

    /*@Tuner
    public static Procedure twoWheelTuner() {
        return new TwoWheelTuner();
    }*/

    //Automatic Foresight Tuning (LAST)
    //https://pedropathing.com/docs/pathing/tuning/foresight
    /*@Tuner
    public static Procedure foresightTuner() {
        return new ForesightTuner((hardwareMap) -> new PinpointLocalizer(hardwareMap, Constants.localizerConfig), (hardwareMap) -> new Mecanum(hardwareMap, Constants.drivetrainConfig));
    }*/
}
