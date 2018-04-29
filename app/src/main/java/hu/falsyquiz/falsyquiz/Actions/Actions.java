package hu.falsyquiz.falsyquiz.Actions;

import hu.falsyquiz.falsyquiz.Application.BaseApplication;
import hu.falsyquiz.falsyquiz.Tools.VibratorEngine;

/**
 * Created by dave on 2018.04.24..
 * This class collects the actions that appear in the game.
 */

public class Actions {

    private VibratorEngine vibratorEngine;

    public Actions(VibratorEngine vibratorEngine) {
        BaseApplication.getInstance().getMainComponent().inject(this);
        this.vibratorEngine = vibratorEngine;
    }

    /**
     * This method vibrates the phone in the corresponding activity for the time specified as the
     * parameter.
     * @param milliseconds For how many seconds the vibration will hold.
     */
    public void vibrate(int milliseconds) {
        vibratorEngine.vibrate(milliseconds);
    }

    public void playRandomSound() {
        //to be implemented...
    }
}
