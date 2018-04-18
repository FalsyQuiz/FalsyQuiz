package hu.falsyquiz.falsyquiz.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;
import lombok.Getter;

/**
 *
 */

public class GameReferee implements Timer.TimerListener {

    public interface GameRefereeListener {
        void printQuestion(Question question);
        void gameOver();
        void win();
        void correctAnswer(String correctAnswer);
        void wrongAnswer(String correctAnswer, String wrongAnswer);
        void phoneCallShowAnswer(String answer);
        void tick(long timeLeft);
        void timeIsOver();
    }

    public static final int NUMBER_OF_LIVES = 5;
    public static final int BONUS_LIFE = 1;
    public static final int MINUS_LIFE = 1;
    public static final int PHONE_SUCCESS_THRESHOLD = 80;

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
        play();
    }

    private void play() {
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

       checkGameState();
    }

    public void usePhoneCall(ArrayList<String> availableAnswers) {
        Random rnd = new Random();
        if (!availableAnswers.contains(actualQuestion.getAnswer())
                || (rnd.nextInt(100) + 1) > PHONE_SUCCESS_THRESHOLD) {
            availableAnswers.remove(actualQuestion.getAnswer());
            listener.phoneCallShowAnswer(
                    availableAnswers.get(rnd.nextInt(availableAnswers.size()))
            );
        } else listener.phoneCallShowAnswer(actualQuestion.getAnswer());
        game.setUsedPhone(true);
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
    }

    @Override
    public void tick(long timeLeft) {
        listener.tick(timeLeft);
    }

    @Override
    public void end() {
        game.setLives(game.getLives() - MINUS_LIFE);
        listener.timeIsOver();
        checkGameState();
    }

}
