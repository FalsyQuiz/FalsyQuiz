package hu.falsyquiz.falsyquiz.Actions;


import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;

/**
 * Created by dave on 2018.04.24..
 */

public class Actions {
    public static void vibrate(Activity activity, int milliseconds) {
        Vibrator v = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(milliseconds);
    }

    public static void playRandomSound() {
        //to be implemented...
    }
}
