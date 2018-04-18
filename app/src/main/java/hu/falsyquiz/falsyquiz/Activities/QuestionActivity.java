package hu.falsyquiz.falsyquiz.Activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;
import hu.falsyquiz.falsyquiz.Game.GameReferee;
import hu.falsyquiz.falsyquiz.R;
import lombok.AllArgsConstructor;

public class QuestionActivity extends AbstractActivity implements GameReferee.GameRefereeListener {

    @AllArgsConstructor
    public class AnswerListener implements View.OnClickListener {

        private String answer;

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

        @Override
        public void onClick(View view) {
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

    private GameReferee gameReferee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);

        gameReferee = new GameReferee(this, dataManager.getAllQuestions());

        initOnClickListeners();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    private void initOnClickListeners() {
        optionA.setOnClickListener(new AnswerListener(Question.OPTION_A));
        optionB.setOnClickListener(new AnswerListener(Question.OPTION_B));
        optionC.setOnClickListener(new AnswerListener(Question.OPTION_C));
        optionD.setOnClickListener(new AnswerListener(Question.OPTION_D));
        phone.setOnClickListener(new PhoneCallListener());
    }

    private void answerQuestion(String answer) {
        gameReferee.answerQuestion(answer);
    }

    private void usePhoneCall(ArrayList<String> availableAnswers) {gameReferee.usePhoneCall(availableAnswers);}

    @Override
    public void printQuestion(Question question) {
        questionText.setText(question.getQuestion());
        optionA.setText(question.getOptionA());
        optionB.setText(question.getOptionB());
        optionC.setText(question.getOptionC());
        optionD.setText(question.getOptionD());
    }

    @Override
    public void gameOver() {

    }

    @Override
    public void win() {

    }

    @Override
    public void correctAnswer(String answer) {
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

    @Override
    public void wrongAnswer(String correctAnswer, String wrongAnswer) {
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

    @Override
    public void tick(long timeLeft) {
        long timeLeftSec = timeLeft / 1000;
        this.timeLeft.setText(timeLeftSec < 10 ? "0" + timeLeftSec : timeLeftSec + "");
    }

    @Override
    public void timeIsOver() {
        timeLeft.setText(R.string.questionActivity_timeIsOver_text);
        setButtonsEnability(!ENABLED);
    }

    public void setButtonsEnability(boolean enabled) {
        optionA.setEnabled(enabled);
        optionB.setEnabled(enabled);
        optionC.setEnabled(enabled);
        optionD.setEnabled(enabled);
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
}
