package hu.falsyquiz.falsyquiz.Game;

import android.os.Handler;

import java.util.List;
import java.util.Random;

import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;
import lombok.Getter;

import static hu.falsyquiz.falsyquiz.Activities.QuestionActivity.ENABLED;
import static hu.falsyquiz.falsyquiz.Activities.QuestionActivity.TIME_BEFORE_RESULT;

/**
 *
 */

public class GameReferee {

    public interface GameRefereeListener {
        void printQuestion(Question question);
        void gameOver();
        void win();
        void correctAnswer(String correctAnswer);
        void wrongAnswer(String correctAnswer, String wrongAnswer);
        void setButtonsEnability(boolean enabled);
    }

    public static final int NUMBER_OF_LIVES = 5;
    public static final int BONUS_LIFE = 1;
    public static final int MINUS_LIFE = 1;

    private GameRefereeListener listener;

    private List<Question> questions;
    private Question actualQuestion;
    @Getter
    private Game game;

    public GameReferee(GameRefereeListener listener, List<Question> questions) {
        this.game.setUsedFifty(false);
        this.game.setUsedPhone(false);
        this.listener = listener;
        this.questions = questions;
        game = new Game();
        game.setLives(NUMBER_OF_LIVES);
        play();
    }

    private void play() {
        newQuestion();
    }

    private void newQuestion() {
        Random random = new Random();
        actualQuestion = questions.remove(random.nextInt(questions.size()));
        listener.printQuestion(actualQuestion);
    }

    public void answerQuestion(String answer) {
       if (actualQuestion.getAnswer().equals(answer)) {
           game.setLives(game.getLives() + (actualQuestion.getBonus() ? BONUS_LIFE : 0));
           listener.correctAnswer(answer);
       } else {
           game.setLives(game.getLives() - MINUS_LIFE);
           listener.wrongAnswer(actualQuestion.getAnswer(), answer);
       }

       checkGameState();
    }

    private void checkGameState() {
        if (!game.gameOver() && questions.isEmpty()) {
            listener.win();
        } else if (game.gameOver()) {
            listener.gameOver();
        } else {
            next();
        }
    }

    private void next() {
        //TODO continue game
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                listener.setButtonsEnability(ENABLED);
                newQuestion();
            }

        }, TIME_BEFORE_RESULT);
    }

}
