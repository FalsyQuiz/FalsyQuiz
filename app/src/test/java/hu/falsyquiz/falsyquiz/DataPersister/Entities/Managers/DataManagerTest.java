package hu.falsyquiz.falsyquiz.DataPersister.Entities.Managers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Peti on 2018. 04. 29..
 */

@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    public static final boolean OK = true;

    public static final int MAX_TIME = 2000;

    @Mock
    private DatabaseManager databaseManager;

    private DataManager dataManager;

    @Test(timeout = MAX_TIME)
    public void testCreateQuestion_managerShouldContainOneQuestion() {
        Question question = new Question();
        when(databaseManager.insertQuestion(question)).thenReturn(1L);
        dataManager = new DataManager(databaseManager);
        dataManager.createQuestion(question);
        assertEquals(OK, dataManager.getAllQuestions().size() == 1 && dataManager.getAllQuestions().get(0).getId() == 1);
    }

    @Test(timeout = MAX_TIME)
    public void testDeleteQuestion_managerShouldContainZeroQuestions() {
        Question question = new Question();
        when(databaseManager.insertQuestion(question)).thenReturn(1L);
        dataManager = new DataManager(databaseManager);
        dataManager.createQuestion(question);
        question.setId(1L);
        dataManager.deleteQuestion(question);
        assertEquals(OK, dataManager.getAllQuestions().isEmpty());
    }

    @Test(timeout = MAX_TIME)
    public void testUpdateQuestion_questionShouldBeUpdated() {
        Question question = new Question();
        question.setQuestion("a");
        when(databaseManager.insertQuestion(question)).thenReturn(1L);
        dataManager = new DataManager(databaseManager);
        dataManager.createQuestion(question);
        question.setId(1L);
        Question updatedQuestion = new Question();
        updatedQuestion.setId(1L);
        updatedQuestion.setQuestion("b");
        dataManager.updateQuestion(updatedQuestion);
        assertEquals(OK, dataManager.getAllQuestions().size() == 1 && dataManager.getAllQuestions().get(0).getQuestion().equals("b"));
    }

    @Test(timeout = MAX_TIME)
    public void testDeleteAllQuestions_dataManagerShouldNotContainAnyQuestion() {
        Question question = new Question();
        when(databaseManager.insertQuestion(question)).thenReturn(1L);
        dataManager = new DataManager(databaseManager);
        dataManager.createQuestion(question);
        dataManager.deleteAllQuestions();
        assertEquals(OK, dataManager.getAllQuestions().isEmpty());
    }

    @Test(timeout = MAX_TIME)
    public void testCreateGame_dataManagerShouldContainOneGame() {
        Game game = new Game();
        when(databaseManager.insertGame(game)).thenReturn(1L);
        dataManager = new DataManager(databaseManager);
        dataManager.createGame(game);
        assertEquals(1, dataManager.getAllGames().size());
    }

    @Test(timeout = MAX_TIME)
    public void testUpdateGame_dataManagerShouldContainUpdatedGame() {
        Game game = new Game();
        game.setLives(1);
        when(databaseManager.insertGame(game)).thenReturn(1L);
        dataManager = new DataManager(databaseManager);
        dataManager.createGame(game);
        Game updatedGame = new Game();
        updatedGame.setId(1L);
        updatedGame.setLives(2);
        dataManager.updataGame(updatedGame);
        assertEquals(OK, dataManager.getAllGames().size() == 1 && dataManager.getAllGames().get(0).getLives() == 2);
    }

}