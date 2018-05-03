package hu.falsyquiz.falsyquiz.Actions;

import android.content.Context;
import java.util.Random;
import hu.falsyquiz.falsyquiz.Application.BaseApplication;
import hu.falsyquiz.falsyquiz.R;
import hu.falsyquiz.falsyquiz.Tools.SongPlayer;
import hu.falsyquiz.falsyquiz.Tools.VibratorEngine;
import lombok.Setter;

/**
 * Created by dave on 2018.04.24..
 * This class collects the actions that appear in the game.
 */

public class Actions {

    public static final int NUM_OF_ACTIONS = 2;
    public static final int NUM_OF_SOUNDS = 8;
    public static final int RANDOM_SONG_ACTION = 0;
    public static final int HIDE_ANSWERS_ACTION = 1;

    private VibratorEngine vibratorEngine;

    private SongPlayer songPlayer;

    @Setter
    private ActionsListener actionsListener;


    public interface ActionsListener {
        Context getContext();
        void hideAnswers();
        void showAnswers();
    }

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
        Random rndSound = new Random();
        int num = rndSound.nextInt(NUM_OF_SOUNDS);
        switch (num) {
            case 0:
                songPlayer = new SongPlayer(actionsListener.getContext(), R.raw.air_horn);
                break;
            case 1:
                songPlayer = new SongPlayer(actionsListener.getContext(), R.raw.beep);
                break;
            case 2:
                songPlayer = new SongPlayer(actionsListener.getContext(), R.raw.father);
                break;
            case 3:
                songPlayer = new SongPlayer(actionsListener.getContext(), R.raw.machine_gun_hit_car);
                break;
            case 4:
                songPlayer = new SongPlayer(actionsListener.getContext(), R.raw.metal_strike);
                break;
            case 5:
                songPlayer = new SongPlayer(actionsListener.getContext(), R.raw.rocket);
                break;
            case 6:
                songPlayer = new SongPlayer(actionsListener.getContext(), R.raw.vader);
                break;
            case 7:
                songPlayer = new SongPlayer(actionsListener.getContext(), R.raw.whistle);
                break;
        }
        songPlayer.playSong();
    }

    public void hideAnswers() {
        songPlayer = new SongPlayer(actionsListener.getContext(), R.raw.laugh);
        songPlayer.playSong();
        actionsListener.hideAnswers();
    }

    public void showAnswers() {
        actionsListener.showAnswers();
        songPlayer.stop();
    }

    public void stopSound() {
        if (songPlayer != null) {
            songPlayer.stop();
        }
    }
}
