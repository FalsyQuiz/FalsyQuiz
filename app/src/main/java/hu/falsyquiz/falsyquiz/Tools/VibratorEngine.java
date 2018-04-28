package hu.falsyquiz.falsyquiz.Tools;

import android.content.Context;
import android.os.Vibrator;

/**
 * This class is responsible for providing the Vibrator.
 */
public class VibratorEngine {
    public static final int SHORT_VIBRATION_TIME = 250;
    public static final int LONG_VIBRATION_TIME = 500;
    public static final int EXTRA_SUPER_LONG_TIME = 10000;

    private Vibrator vibratorEngine;

    public VibratorEngine(Context context) {
        vibratorEngine = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**
     * This method vibrates the device for the time specified in the parameter.
     * @param vibrationLength The time until the device vibrates.
     */
    public void vibrate(int vibrationLength) {
        if (vibratorEngine != null) {
            vibratorEngine.vibrate(vibrationLength);
        }
    }
}
