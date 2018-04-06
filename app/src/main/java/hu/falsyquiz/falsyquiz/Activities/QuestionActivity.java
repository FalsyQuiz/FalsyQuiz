package hu.falsyquiz.falsyquiz.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;
import hu.falsyquiz.falsyquiz.R;
import lombok.AllArgsConstructor;

public class QuestionActivity extends AbstractActivity {

    @AllArgsConstructor
    public class AnswerListener implements View.OnClickListener {

        private String answer;

        @Override
        public void onClick(View view) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    checkAnswer(answer);
                }

            }, TIME_BEFORE_RESULT);
        }
    }

    public static final int NUMBER_OF_LIVES = 5;
    public static final int TIME_BEFORE_RESULT = 1500;

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

    private List<Question> questionList;
    private Game game;
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);

        questionList = dataManager.getAllQuestionsList();
        game = new Game();
        game.setLives(NUMBER_OF_LIVES);
        game();
    }

    private void game() {
        printQuestion();
        initOnClickListeners();
    }

    private void initOnClickListeners() {
        optionA.setOnClickListener(new AnswerListener(Question.OPTION_A));
        optionB.setOnClickListener(new AnswerListener(Question.OPTION_B));
        optionC.setOnClickListener(new AnswerListener(Question.OPTION_C));
        optionD.setOnClickListener(new AnswerListener(Question.OPTION_D));

    }

    private void checkAnswer(String answer) {

        //correct answer
        if (answer.equals(question.getAnswer())) {
            game.setCorrectAnswers(game.getCorrectAnswers() + 1);
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
        //not correct answer
        else {
            game.setLives( game.getLives() - 1 );
            switch (answer) {
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
            switch (question.getAnswer()) {
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
    }


    private void printQuestion() {
        Random random = new Random();
        int num = random.nextInt(questionList.size());
        question = questionList.remove(num);

        questionText.setText(question.getQuestion());
        optionA.setText(question.getOptionA());
        optionB.setText(question.getOptionB());
        optionC.setText(question.getOptionC());
        optionD.setText(question.getOptionD());
    }
}
