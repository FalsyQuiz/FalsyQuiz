package hu.falsyquiz.falsyquiz.Actions;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;

/**
 * Created by dave on 2018.04.24..
 * This class collects the actions that appear in the game.
 */

public class Actions {

    /**
     * This method vibrates the phone in the corresponding activity for the time specified as the
     * parameter.
     * @param activity The activity where the vibration takes place.
     * @param milliseconds For how many seconds the vibration will hold.
     */
    public static void vibrate(Activity activity, int milliseconds) {
        Vibrator v = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(milliseconds);
    }

    public static void playRandomSound() {
        //to be implemented...
    }
}
