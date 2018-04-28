package hu.falsyquiz.falsyquiz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;
import butterknife.BindView;
import butterknife.ButterKnife;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.InfoTextMessage;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;
import hu.falsyquiz.falsyquiz.Game.GameReferee;
import hu.falsyquiz.falsyquiz.R;
import hu.falsyquiz.falsyquiz.Tools.SongPlayer;
import hu.falsyquiz.falsyquiz.Tools.VibratorEngine;
import lombok.AllArgsConstructor;
import static hu.falsyquiz.falsyquiz.Actions.Actions.playRandomSound;
import static hu.falsyquiz.falsyquiz.Actions.Actions.vibrate;

public class QuestionActivity extends AbstractActivity implements GameReferee.GameRefereeListener, InfoTextMessage.MessageListener {

    @AllArgsConstructor
    public class AnswerListener implements View.OnClickListener {

        private String answer;

        /**
         * This method initializes the onClickListener for the answer buttons.
         * @param view
         */
        @Override
        public void onClick(View view) {
            setButtonsEnability(!ENABLED);
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    answerQuestion(answer);
                }

            }, TIME_BEFORE_RESULT);
        }
    }

    @AllArgsConstructor
    public class PhoneCallListener implements View.OnClickListener {

        /**
         * This method initializes the onClickListener for the phone button
         * @param view
         */
        @Override
        public void onClick(View view) {
            songPlayer = new SongPlayer(QuestionActivity.this, R.raw.dialing);
            songPlayer.playSong();
            ArrayList<String> availableAnswers = new ArrayList<>();
            if (optionA.getVisibility() == View.VISIBLE) availableAnswers.add(Question.OPTION_A);
            if (optionB.getVisibility() == View.VISIBLE) availableAnswers.add(Question.OPTION_B);
            if (optionC.getVisibility() == View.VISIBLE) availableAnswers.add(Question.OPTION_C);
            if (optionD.getVisibility() == View.VISIBLE) availableAnswers.add(Question.OPTION_D);
            usePhoneCall(availableAnswers);
            phone.setVisibility(View.INVISIBLE);
        }
    }

    public static final int TIME_BEFORE_RESULT = 1500;
    public static boolean ENABLED = true;
    public static final int NOT_ENOUGH_TIME_LEFT = 10;
    public static final int VERY_REALLY_NOT_ENOUGH_TIME_LEFT = 5;


    @BindView(R.id.questionActivity_questionText)
    TextView questionText;

    @BindView(R.id.questionActivity_optionA_button)
    Button optionA;

    @BindView(R.id.questionActivity_optionB_button)
    Button optionB;

    @BindView(R.id.questionActivity_optionC_button)
    Button optionC;

    @BindView(R.id.questionActivity_optionD_button)
    Button optionD;

    @BindView(R.id.questionActivity_fiftyButton)
    ImageButton fifty;

    @BindView(R.id.questionActivity_callButton)
    ImageButton phone;

    @BindView(R.id.questionActivity_surpriseButton)
    ImageButton surprise;

    @BindView(R.id.questionActivity_timeLeft_text)
    TextView timeLeft;

    @BindView(R.id.questionActivity_livesText)
    TextView livesText;

    @BindView(R.id.questionActivity_infoText)
    TextView infoText;

    private GameReferee gameReferee;
    private SongPlayer songPlayer;

    /**
     * This method creates a QuestionActivity, initializes the information messages that appear
     * during the application, initializes the onClickListeners and a GameReferee which starts
     * playing.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);
        InfoTextMessage.initTextMessages(this);

        gameReferee = new GameReferee(this, dataManager.getAllQuestions());
        gameReferee.play();

        initOnClickListeners();

    }

    /**
     * This method initializes the onClickListener for the answer, fifty, phone and surprise
     * buttons.
     */
    private void initOnClickListeners() {
        optionA.setOnClickListener(new AnswerListener(Question.OPTION_A));
        optionB.setOnClickListener(new AnswerListener(Question.OPTION_B));
        optionC.setOnClickListener(new AnswerListener(Question.OPTION_C));
        optionD.setOnClickListener(new AnswerListener(Question.OPTION_D));
        phone.setOnClickListener(new PhoneCallListener());
        fifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fifty();
            }
        });
        surprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surprise();
            }
        });
    }

    /**
     * This method calls the GameReferee's answerQuestions method.
     * @param answer The player's current answer.
     */
    private void answerQuestion(String answer) {
        gameReferee.answerQuestion(answer);
    }

    /**
     * This method calls the GameReferee's usePhoneCall method.
     * @param availableAnswers
     */
    private void usePhoneCall(ArrayList<String> availableAnswers) {gameReferee.usePhoneCall(availableAnswers);}

    /**
     * This method stops the currently playing song and prints a question.
     * @param question The game's current question.
     */
    @Override
    public void printQuestion(Question question) {
        if (songPlayer != null)
            songPlayer.stop();
        questionText.setText(question.getQuestion());
        optionA.setText(question.getOptionA());
        optionB.setText(question.getOptionB());
        optionC.setText(question.getOptionC());
        optionD.setText(question.getOptionD());
    }

    /**
     * This method implements the fifty help.
     */
    private void fifty() {
        Random random = new Random();
        int randomNumber1 = random.nextInt(4);
        int randomNumber2 = random.nextInt(4);
        while (randomNumber1 == randomNumber2) {
            randomNumber2 = random.nextInt(4);
        }
        setButtonInvisible(randomNumber1);
        setButtonInvisible(randomNumber2);
        fifty.setVisibility(View.INVISIBLE);
    }

    /**
     * This method makes a button invisible based on its parameter.
     * @param buttonNumber The number of the button to be invisible.
     */
    private void setButtonInvisible(int buttonNumber) {
        switch (buttonNumber) {
            case 0:
                optionA.setVisibility(View.INVISIBLE);
                break;
            case 1:
                optionB.setVisibility(View.INVISIBLE);
                break;
            case 2:
                optionC.setVisibility(View.INVISIBLE);
                break;
            case 3:
                optionD.setVisibility(View.INVISIBLE);
                break;
        }
    }

    /**
     * This method implements the surprise "help".
     */
    private void surprise() {
        Random random = new Random();
        int randomNumber = random.nextInt(5);
        switch (randomNumber) {
            case 0:
                vibrate(this, 2000);
                break;
            case 1:
                playRandomSound();
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            //...
        }
    }

    /**
     * This method stops the currently playing song and create a GameOverActivity.
     */
    @Override
    public void gameOver() {
        songPlayer.stop();
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra(GameOverActivity.EXTRA_GAMER_KEY, gameReferee.getGame());
        clearAndStartActivity(intent);
    }

    /**
     * This method stops the currently playing song and create a GameOverActivity.
     */
    @Override
    public void win() {
        songPlayer.stop();
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra(GameOverActivity.EXTRA_GAMER_KEY, gameReferee.getGame());
        clearAndStartActivity(intent);
    }

    /**
     * This method runs when the user has answered correctly. It plays a celebrating song and
     * colors the good answer's button green.
     * @param answer The player's current answer.
     */
    @Override
    public void correctAnswer(String answer) {

        setButtonsVisible();
        songPlayer = new SongPlayer(this, R.raw.good_answer_song);
        songPlayer.playSong();

        switch (answer) {
            case Question.OPTION_A:
                optionA.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_correctAnswerColor));
                break;
            case Question.OPTION_B:
                optionB.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_correctAnswerColor));
                break;
            case Question.OPTION_C:
                optionC.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_correctAnswerColor));
                break;
            case Question.OPTION_D:
                optionD.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_correctAnswerColor));
                break;
        }
    }

    /**
     * This method runs when the user has not answered correctly. It plays and annoying song and
     * color the good answer's button green and the wrong answer's button red.
     * @param correctAnswer The good answer to the current question.
     * @param wrongAnswer The wrong answer to the current question, which the player has chosen.
     */
    @Override
    public void wrongAnswer(String correctAnswer, String wrongAnswer) {

        setButtonsVisible();
        songPlayer = new SongPlayer(this, R.raw.annoying_noise);
        songPlayer.playSong();

        switch (wrongAnswer) {
            case Question.OPTION_A:
                optionA.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_wrongAnswerColor));
                break;
            case Question.OPTION_B:
                optionB.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_wrongAnswerColor));
                break;
            case Question.OPTION_C:
                optionC.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_wrongAnswerColor));
                break;
            case Question.OPTION_D:
                optionD.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_wrongAnswerColor));
                break;
        }
        switch (correctAnswer) {
            case Question.OPTION_A:
                optionA.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_correctAnswerColor));
                break;
            case Question.OPTION_B:
                optionB.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_correctAnswerColor));
                break;
            case Question.OPTION_C:
                optionC.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_correctAnswerColor));
                break;
            case Question.OPTION_D:
                optionD.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_correctAnswerColor));
                break;
        }
    }

    /**
     * This method sets all of the answer buttons visible.
     */
    private void setButtonsVisible() {
        optionA.setVisibility(View.VISIBLE);
        optionB.setVisibility(View.VISIBLE);
        optionC.setVisibility(View.VISIBLE);
        optionD.setVisibility(View.VISIBLE);
    }

    /**
     * This method colors a button after the user used the phone help.
     * @param answer
     */
    @Override
    public void phoneCallShowAnswer(String answer) {
        switch (answer) {
            case Question.OPTION_A:
                optionA.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_phoneCallShowAnswerColor));
                break;
            case Question.OPTION_B:
                optionB.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_phoneCallShowAnswerColor));
                break;
            case Question.OPTION_C:
                optionC.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_phoneCallShowAnswerColor));
                break;
            case Question.OPTION_D:
                optionD.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_phoneCallShowAnswerColor));
                break;
        }
    }

    /**
     * This method changes how much time is left for the current question every second and plays
     * songs when there is not much time left.
     * @param timeLeft How much time is left to answer the current question.
     */
    @Override
    public void tick(long timeLeft) {
        long timeLeftSec = timeLeft / 1000;
        this.timeLeft.setText(timeLeftSec < 10 ? "0" + timeLeftSec : timeLeftSec + "");
        if ( timeLeftSec == NOT_ENOUGH_TIME_LEFT ) {
            songPlayer = new SongPlayer(this, R.raw.female_scream);
            songPlayer.playSong();
            vibratorEngine.vibrate(VibratorEngine.EXTRA_SUPER_LONG_TIME);
        }
        if ( timeLeftSec == VERY_REALLY_NOT_ENOUGH_TIME_LEFT ) {
            songPlayer = new SongPlayer(this, R.raw.female_giving_birth);
            songPlayer.playSong();
        }
    }

    /**
     * This method shows the user that the time is over.
     */
    @Override
    public void timeIsOver() {
        timeLeft.setText(R.string.questionActivity_timeIsOver_text);
        setButtonsEnability(!ENABLED);
    }

    /**
     * This method sets the availability of all of the buttons based on its parameter.
     * @param enabled The desired availability of the buttons.
     */
    @Override
    public void setButtonsEnability(boolean enabled) {
        optionA.setEnabled(enabled);
        optionB.setEnabled(enabled);
        optionC.setEnabled(enabled);
        optionD.setEnabled(enabled);
        optionA.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_buttonColor));
        optionB.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_buttonColor));
        optionC.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_buttonColor));
        optionD.setBackgroundColor(getResources().getColor(R.color.QuestionActivity_buttonColor));
        if ( !gameReferee.getGame().getUsedFifty() && enabled ) {
            fifty.setEnabled(enabled);
        } else if ( !enabled ) {
            fifty.setEnabled(enabled);
        }
        if ( !gameReferee.getGame().getUsedPhone() && enabled ) {
            phone.setEnabled(enabled);
        } else if ( !enabled ) {
            phone.setEnabled(enabled);
        }
        surprise.setEnabled(enabled);
    }

    /**
     * This method shows how much life a player still has.
     * @param lives Number of lives left.
     */
    @Override
    public void showLives(int lives) {
        livesText.setText("");
        livesText.append(Integer.toString(lives) + "×");
        livesText.setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.ic_favorite_black_18dp,0);
    }

    /**
     * This method prints useful information for the player.
     * @param text The current information to be printed.
     */
    @Override
    public void setInfoText(String text) {
        infoText.setText(text);
        AlphaAnimation textFade = new AlphaAnimation(1.0f, 0.0f);
        infoText.startAnimation(textFade);
        textFade.setDuration(1000);
        textFade.setFillAfter(true);
        textFade.setStartOffset(2000 + textFade.getStartOffset());
    }

    /**
     * This function returns the QuestionActivity.
     */
    @Override
    public AbstractActivity getActivity() {
        return this;
    }
}
