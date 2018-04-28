package hu.falsyquiz.falsyquiz.Actions;

import hu.falsyquiz.falsyquiz.Application.BaseApplication;
import hu.falsyquiz.falsyquiz.Tools.VibratorEngine;

/**
 * Created by dave on 2018.04.24..
 */

public class Actions {

    private VibratorEngine vibratorEngine;

    public Actions(VibratorEngine vibratorEngine) {
        BaseApplication.getInstance().getMainComponent().inject(this);
        this.vibratorEngine = vibratorEngine;
    }

    public void vibrate(int milliseconds) {
        vibratorEngine.vibrate(milliseconds);
    }

    public void playRandomSound() {
        //to be implemented...
    }
}
