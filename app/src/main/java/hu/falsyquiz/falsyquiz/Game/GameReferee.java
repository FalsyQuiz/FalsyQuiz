package hu.falsyquiz.falsyquiz.Game;

import java.util.ArrayList;
import android.os.Handler;
import java.util.List;
import java.util.Random;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.InfoTextMessage;
import hu.falsyquiz.falsyquiz.R;
import lombok.Getter;
import lombok.Setter;

/**
 * The class that plays the game.
 */

public class GameReferee implements Timer.TimerListener {

    private static final long TIME_BEFORE_NEXT_QUESTION = 2500;

    public interface GameRefereeListener {
        void printQuestion(Question question);
        void gameOver();
        void win();
        void correctAnswer(String correctAnswer);
        void wrongAnswer(String correctAnswer, String wrongAnswer);
        void phoneCallShowAnswer(String answer);
        void tick(long timeLeft);
        void timeIsOver();
        void setButtonsEnability(boolean enabled);
        void showLives(int lives);
        void setInfoText(String text);
    }

    public static final int NUMBER_OF_LIVES = 3;
    public static final int BONUS_LIFE = 1;
    public static final int MINUS_LIFE = 1;
    public static final int PHONE_SUCCESS_THRESHOLD = 50;

    public static final boolean FIFTY_USED = true;
    public static final boolean PHONE_USED = true;
    public static final boolean SURPRISE_USED = true;

    private GameRefereeListener listener;

    private List<Question> questions;
    @Setter
    private Question actualQuestion;

    @Getter
    @Setter
    private Game game;

    @Setter
    private Timer timer;

    public GameReferee(GameRefereeListener listener, List<Question> questions) {
        this.listener = listener;
        this.questions = questions;
        game = new Game();
        game.setId(0L);
        game.setLives(NUMBER_OF_LIVES);
        game.setUsedFifty(!FIFTY_USED);
        game.setUsedPhone(!PHONE_USED);
        game.setUsedSurprise(!SURPRISE_USED);
        listener.showLives(game.getLives());
    }

    /**
     * This method starts the game.
     */
    public void play() {
        newQuestion();
    }

    /**
     * This method starts the timer.
     * @param time
     */
    private void startTimer(long time) {
        timer = new Timer(time, this);
        timer.start();
    }

    /**
     * This method jumps to the next question.
     */
    private void newQuestion() {
        Random random = new Random();
        actualQuestion = questions.remove(random.nextInt(questions.size()));
        listener.printQuestion(actualQuestion);
        if(actualQuestion.getBonus()) listener.setInfoText(InfoTextMessage
                .getTextMessage(R.string.questionActivity_bonusQuestion_text));

        startTimer((random.nextInt(Timer.QUESTION_TIME_SEC_CORRIG) + Timer.QUESTION_TIME_SEC_LOWER_BOUND) * 1000);
        listener.setButtonsEnability(true);
    }

    /**
     * This method answers the current question. Stops the timer, sets the lives, sets the
     * information text and check whether the game is over or not.
     * @param answer The player's answer.
     */
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

    /**
     * This method implements the phone help.
     * @param availableAnswers Answers that phone help can suggest.
     */
    public void usePhoneCall(ArrayList<String> availableAnswers) {
        Random rnd = new Random();
        if (!availableAnswers.contains(actualQuestion.getAnswer())
                || (rnd.nextInt(100) + 1) > PHONE_SUCCESS_THRESHOLD) {
            availableAnswers.remove(actualQuestion.getAnswer());
            listener.phoneCallShowAnswer(
                    availableAnswers.get(rnd.nextInt(availableAnswers.size()))
            );
        } else listener.phoneCallShowAnswer(actualQuestion.getAnswer());
        game.setUsedPhone(PHONE_USED);
        listener.setInfoText(InfoTextMessage.getPhoneCallMessage());
    }

    public void usedSurpriseHelp() {
        game.setUsedSurprise(SURPRISE_USED);
    }

    public void usedFifty() {
        game.setUsedFifty(FIFTY_USED);
    }

    /**
     * This method checks if the game is over.
     */
    private void checkGameState() {
        game.setNumOfQuestions(game.getNumOfQuestions() + 1);
        if (!game.gameOver() && questions.isEmpty()) {

            listener.win();
        } else if (game.gameOver()) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    listener.gameOver();
                }

            }, TIME_BEFORE_NEXT_QUESTION);

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

    /**
     * This method jumps to the next question.
     */
    private void next() {
        newQuestion();
    }

    /**
     * This method ticks the timer.
     * @param timeLeft How much time is left to answer the current question.
     */
    @Override
    public void tick(long timeLeft) {
        listener.tick(timeLeft);
    }

    /**
     * This method is responsible for changes occurring at the end of a question.
     */
    @Override
    public void end() {
        game.setLives(game.getLives() - MINUS_LIFE);
        listener.timeIsOver();
        listener.showLives(game.getLives());
        checkGameState();
    }

    public void finish() {
        timer.stop();
    }
}
