package hu.falsyquiz.falsyquiz.Tools;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Peti on 2018. 04. 23..
 */

public class SongPlayer {

    private MediaPlayer mediaPlayer;

    public SongPlayer(Context context, int songResourceId) {
        mediaPlayer = MediaPlayer.create(context, songResourceId);
    }

    public void playSong() {
        mediaPlayer.start();
    }

    public void stop() {
        mediaPlayer.stop();
    }
}
