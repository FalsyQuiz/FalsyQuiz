package hu.falsyquiz.falsyquiz.Game;

import android.os.Handler;

import java.util.List;
import java.util.Random;

import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;
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
    }

    public static final int NUMBER_OF_LIVES = 5;
    public static final int BONUS_LIFE = 1;
    public static final int MINUS_LIFE = 1;

    public static final boolean FIFTY_USED = true;
    public static final boolean PHONE_USED = true;
    public static final boolean SURPRISE_USED = true;

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
        game.setId(0L);
        game.setLives(1);
        game.setUsedFifty(!FIFTY_USED);
        game.setUsedPhone(!PHONE_USED);
        game.setUsedSurprise(!SURPRISE_USED);
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
        startTimer(Timer.DEFAULT_NORMAL_TIME);
        listener.setButtonsEnability(true);
    }

    public void answerQuestion(String answer) {

       timer.stop();
       if (actualQuestion.getAnswer().equals(answer)) {
           game.setLives(game.getLives() + (actualQuestion.getBonus() ? BONUS_LIFE : 0));
           listener.correctAnswer(answer);
       } else {
           game.setLives(game.getLives() - MINUS_LIFE);
           listener.wrongAnswer(actualQuestion.getAnswer(), answer);
       }
       listener.showLives(game.getLives());
       checkGameState();
    }

    private void checkGameState() {
        game.setNumOfQuestions(game.getNumOfQuestions() + 1);
        if (!game.gameOver() && questions.isEmpty()) {
            listener.win();
        } else if (game.gameOver()) {
            listener.gameOver();
        } else {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    game.setCorrectAnswers(game.getCorrectAnswers() + 1);
                    next();
                }

            }, TIME_BEFORE_NEXT_QUESTION);

        }
    }

    private void next() {
        newQuestion();
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
