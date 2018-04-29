package hu.falsyquiz.falsyquiz.Tools;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Peti on 2018. 04. 23..
 * This class is responsible for playing the songs.
 */

public class SongPlayer {

    private MediaPlayer mediaPlayer;

    public SongPlayer(Context context, int songResourceId) {
        mediaPlayer = MediaPlayer.create(context, songResourceId);
    }

    /**
     * This method starts playing the song.
     */
    public void playSong() {
        mediaPlayer.start();
    }

    /**
     * This method stops the song being played.
     */
    public void stop() {
        mediaPlayer.stop();
    }
}
