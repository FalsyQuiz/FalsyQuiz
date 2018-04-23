package hu.falsyquiz.falsyquiz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.R;
import hu.falsyquiz.falsyquiz.Tools.SongPlayer;

public class GameOverActivity extends AbstractActivity {

    public static final String EXTRA_GAMER_KEY = "EXTRA_GAMER_KEY";

    @BindView(R.id.gameOverActivity_resultText)
    TextView resultText;

    @BindView(R.id.gameOverActivity_numOfQuestionsText)
    TextView numOfQuestionsText;

    @BindView(R.id.gameOverActivity_percentageText)
    TextView percentageText;

    private SongPlayer songPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Game game = intent.getParcelableExtra(EXTRA_GAMER_KEY);

        if (game.gameOver()) {
            fail(game);
        } else {
            win(game);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (songPlayer != null) {
            songPlayer.stop();
        }
    }

    private void fail(Game game) {
        resultText.setText(getString(R.string.gameOverActivity_failed_text));
        songPlayer = new SongPlayer(this, R.raw.laugh);
        songPlayer.playSong();
        printResults(game);
    }

    private void win(Game game) {
        resultText.setText(getString(R.string.gameOverActivity_win_text));
        printResults(game);
    }

    private void printResults(Game game) {
        numOfQuestionsText.setText(game.getCorrectAnswers() + " / " + game.getNumOfQuestions());
        float percent = ((float) game.getCorrectAnswers() / game.getNumOfQuestions()) * 100;
        percentageText.setText(getString(R.string.gameOverActivity_percentageText) + " " + percent + "%");
    }
}
