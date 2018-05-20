package hu.falsyquiz.falsyquiz.Game;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import hu.falsyquiz.falsyquiz.Activities.QuestionActivity;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;
import static org.junit.Assert.*;

/**
 * Created by viktoria on 2018. 05. 03..
 */
public class GameRefereeTest {

    public static final boolean OK = true;

    private GameReferee gameReferee;
    private Game game;
    private GameReferee.GameRefereeListener gameRefereeListener = new GameReferee.GameRefereeListener() {
        @Override
        public void printQuestion(Question question) {
        }

        @Override
        public void gameOver() {

        }

        @Override
        public void win() {

        }

        @Override
        public void correctAnswer(String correctAnswer) {

        }

        @Override
        public void wrongAnswer(String correctAnswer, String wrongAnswer) {

        }

        @Override
        public void phoneCallShowAnswer(String answer) {

        }

        @Override
        public void tick(long timeLeft) {

        }

        @Override
        public void timeIsOver() {

        }

        @Override
        public void setButtonsEnability(boolean enabled) {

        }

        @Override
        public void showLives(int lives) {

        }

        @Override
        public void setInfoText(String text) {

        }
    };
    private List<Question> questions;

    @Before
    public void setUp() throws Exception {
        game = new Game();
        game.setId(0L);
        game.setLives(GameReferee.NUMBER_OF_LIVES);
        game.setUsedFifty(!GameReferee.FIFTY_USED);
        game.setUsedPhone(!GameReferee.PHONE_USED);
        game.setUsedSurprise(!GameReferee.SURPRISE_USED);
        questions = new ArrayList<>();
        questions.add(new Question((Long )null,
                "Hány byte-on tárol egy double-t a C++?",
                "4", "6", "8", "implementáció-függő",
                Question.OPTION_D, true));

        questions.add(new Question((Long )null,
                "Mitől válik egy osztály absztrakttá C++-ban?",
                "Nincs paraméter nélküli konstruktora.", "Van virtuális függvénye.",
                "Van tisztán virtuális függvénye.", "Van statikus metódusa.",
                Question.OPTION_C, false));

        questions.add(new Question((Long )null,
                "Van többszörös öröklődés a Java nyelvben?",
                "Nincs.", "Van.",
                "Attól függ...", "Lehet.",
                Question.OPTION_A, false));

        questions.add(new Question((Long )null,
                "Mi x * x deriváltja?",
                "2 * x", "x * x * x",
                "2*x / 2 ", "x * x / 2",
                Question.OPTION_A, false));

        questions.add(new Question((Long )null,
                "Mennyi a Hamming-távolsága az alábbi két kódnak: 100101,010101?",
                "1", "2",
                "3", "4",
                Question.OPTION_B, false));
        gameReferee = new GameReferee(gameRefereeListener, questions);
        gameReferee.setActualQuestion(questions.get(0));
        gameReferee.setGame(game);
    }

    @Test
    public void testUsedSurpriseHelp() throws Exception {
        gameReferee.usedSurpriseHelp();
        assertEquals(OK, game.getUsedSurprise());
    }

    @Test
    public void testUsedFifty() throws Exception {
        gameReferee.usedFifty();
        assertEquals(OK, game.getUsedFifty());
    }

    @Test
    public void testEndCheckLives() throws Exception {
        gameReferee.end();
        assertEquals(2, game.getLives());
    }
}