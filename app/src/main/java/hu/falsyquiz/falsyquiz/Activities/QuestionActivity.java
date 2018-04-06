package hu.falsyquiz.falsyquiz.Activities;

import android.os.Bundle;
import android.util.Log;
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

public class QuestionActivity extends AbstractActivity {

    public static final int NUMBER_OF_LIVES = 5;

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

        initListeners();

        game();
    }

    private void game() {
        printQuestion();
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

    private void initListeners() {
        fifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fifty();
            }
        });
    }

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
}
