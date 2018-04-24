package hu.falsyquiz.falsyquiz.Game;

import android.os.Handler;

import java.util.List;
import java.util.Random;

import hu.falsyquiz.falsyquiz.Activities.AbstractActivity;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;

import hu.falsyquiz.falsyquiz.DataPersister.Entities.InfoTextMessage;
import hu.falsyquiz.falsyquiz.R;
import lombok.Getter;

/**
 *
 */

public class GameReferee implements Timer.TimerListener {

    private static final long TIME_BEFORE_NEXT_QUESTION = 1500;

    public interface GameRefereeListener {
        void printQuestion(Question question);
        void gameOver();
        void win();
        void correctAnswer(String correctAnswer);
        void wrongAnswer(String correctAnswer, String wrongAnswer);
        void tick(long timeLeft);
        void timeIsOver();
        void setButtonsEnability(boolean enabled);
        void showLives(int lives);
        void setInfoText(String text);
    }

    public static final int NUMBER_OF_LIVES = 5;
    public static final int BONUS_LIFE = 1;
    public static final int MINUS_LIFE = 1;

    public static final boolean FIFTY_USED = true;
    public static final boolean PHONE_USED = true;

    private GameRefereeListener listener;

    private List<Question> questions;
    private Question actualQuestion;

    @Getter
    private Game game;

    private Timer timer;

    public GameReferee(GameRefereeListener listener, List<Question> questions) {
        this.listener = listener;
        this.questions = questions;
        game = new Game();
        game.setLives(NUMBER_OF_LIVES);
        game.setUsedFifty(!FIFTY_USED);
        game.setUsedPhone(!PHONE_USED);
        listener.showLives(game.getLives());
    }

    public void play() {
        newQuestion();
    }

    private void startTimer(long time) {
        timer = new Timer(time, this);
        timer.start();
    }

    private void newQuestion() {
        Random random = new Random();
        actualQuestion = questions.remove(random.nextInt(questions.size()));
        listener.printQuestion(actualQuestion);
        if(actualQuestion.getBonus()) listener.setInfoText(InfoTextMessage
                .getTextMessage(R.string.questionActivity_bonusQuestion_text));
        startTimer(Timer.DEFAULT_NORMAL_TIME);
        listener.setButtonsEnability(true);
    }

    public void answerQuestion(String answer) {
        timer.stop();
       if (actualQuestion.getAnswer().equals(answer)) {
           if (actualQuestion.getBonus()) {
               game.setLives(game.getLives() + BONUS_LIFE);
               listener.setInfoText(InfoTextMessage.getTextMessage(R.string.questionActivity_bonusLife_text));
           } else listener.setInfoText(InfoTextMessage.getCorrectAnswerMessage());
           listener.correctAnswer(answer);
       } else {
           game.setLives(game.getLives() - MINUS_LIFE);
           listener.wrongAnswer(actualQuestion.getAnswer(), answer);
           listener.setInfoText(InfoTextMessage.getWrongAnswerMessage());
       }
       listener.showLives(game.getLives());
       checkGameState();
    }

    private void checkGameState() {
        if (!game.gameOver() && questions.isEmpty()) {
            listener.win();
        } else if (game.gameOver()) {
            listener.gameOver();
        } else {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    newQuestion();
                }

            }, TIME_BEFORE_NEXT_QUESTION);

        }
    }

    private void next() {
        //TODO continue game
    }

    @Override
    public void tick(long timeLeft) {
        listener.tick(timeLeft);
    }

    @Override
    public void end() {
        game.setLives(game.getLives() - MINUS_LIFE);
        listener.timeIsOver();
        listener.showLives(game.getLives());
        checkGameState();
    }

}
