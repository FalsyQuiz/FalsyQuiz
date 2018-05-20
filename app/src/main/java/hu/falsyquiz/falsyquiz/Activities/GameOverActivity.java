package hu.falsyquiz.falsyquiz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.R;
import hu.falsyquiz.falsyquiz.Tools.SongPlayer;


/**
 * This activity is responsible for the end of the game.
 */

public class GameOverActivity extends AbstractActivity {

    public static final String EXTRA_GAMER_KEY = "EXTRA_GAMER_KEY";

    @BindView(R.id.gameOverActivity_resultText)
    TextView resultText;

    @BindView(R.id.gameOverActivity_numOfQuestionsText)
    TextView numOfQuestionsText;

    @BindView(R.id.gameOverActivity_percentageText)
    TextView percentageText;

    @BindView(R.id.gameOverActivity_resultPicture)
    ImageView resultPicture;

    private SongPlayer songPlayer;

    /**
     * This method creates a GameOverActivity and sets the background color to white.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Game game = intent.getParcelableExtra(EXTRA_GAMER_KEY);
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.white));

        if (game.gameOver()) {
            fail(game);
        } else {
            win(game);
        }
    }

    /**
     * This method stops the currently playing sound.
     */
    @Override
    public void onPause() {
        super.onPause();
        if (songPlayer != null) {
            songPlayer.stop();
        }
    }

    /**
     * This method runs when the player loses the game.
     * @param game The game which is currently played.
     */
    private void fail(Game game) {
        resultText.setText(getString(R.string.gameOverActivity_failed_text));
        resultText.setTextColor(getResources().getColor(R.color.red));
        resultPicture.setImageResource(R.mipmap.failed);
        songPlayer = new SongPlayer(this, R.raw.laugh);
        songPlayer.playSong();
        percentageText.setTextColor(getResources().getColor(R.color.colorPrimary));
        numOfQuestionsText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        printResults(game);
    }

    /**
     * This method runs when the player wins the game.
     * @param game The game which is currently played.
     */
    private void win(Game game) {
        resultText.setText(getString(R.string.gameOverActivity_win_text));
        resultText.setTextColor(getResources().getColor(R.color.red));
        songPlayer = new SongPlayer(this, R.raw.human_audience_cheer_and_clap);
        songPlayer.playSong();
        percentageText.setTextColor(getResources().getColor(R.color.colorPrimary));
        numOfQuestionsText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        resultPicture.setImageResource(R.mipmap.winner);
        printResults(game);
    }

    /**
     * This method prints the results.
     * @param game The game which is currently played.
     */
    private void printResults(Game game) {
        numOfQuestionsText.setText(game.getCorrectAnswers() + " / " + game.getNumOfQuestions());
        float percent = ((float) game.getCorrectAnswers() / game.getNumOfQuestions()) * 100;
        percentageText.setText(getString(R.string.gameOverActivity_percentageText) + " " + percent + "%");
    }
}
