package hu.falsyquiz.falsyquiz.DataPersister.Entities.Managers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import hu.falsyquiz.falsyquiz.DataPersister.Entities.DaoSession;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.GameDao;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.QuestionDao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Class to test DatabaseManager. Only findAll methods are tested. Other methods does not change
 * GreenDAO's logic.
 */

@RunWith(MockitoJUnitRunner.class)
public class DatabaseManagerTest {

    public static final int MAX_TIME = 2000;

    public static final boolean OK = true;

    @Mock
    private DaoSession daoSession;

    @Mock
    private QuestionDao questionDao;

    @Mock
    private GameDao gameDao;

    private DatabaseManager databaseManager;

    @Test(timeout = MAX_TIME)
    public void testFindAllQuestionsShouldReturnEmptyList() {
        when(questionDao.loadAll()).thenReturn(new ArrayList<Question>());
        when(daoSession.getQuestionDao()).thenReturn(questionDao);
        databaseManager = new DatabaseManager(daoSession);
        assertEquals(OK, databaseManager.findAllQuestions().isEmpty());
    }

    @Test(timeout = MAX_TIME)
    public void testFindAllQuestionsShouldReturnListWithOneQuestion() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        when(questionDao.loadAll()).thenReturn(questions);
        when(daoSession.getQuestionDao()).thenReturn(questionDao);
        databaseManager = new DatabaseManager(daoSession);
        assertEquals(1, databaseManager.findAllQuestions().size());
    }

    @Test(timeout = MAX_TIME)
    public void testFindAllGamesShouldReturnEmptyList() {
        when(gameDao.loadAll()).thenReturn(new ArrayList<Game>());
        when(daoSession.getGameDao()).thenReturn(gameDao);
        databaseManager = new DatabaseManager(daoSession);
        assertEquals(OK, databaseManager.findAllGames().isEmpty());
    }

    @Test(timeout = MAX_TIME)
    public void testFindAllGamesShouldReturnListWithOneGame() {
        List<Game> games = new ArrayList<>();
        games.add(new Game());
        when(gameDao.loadAll()).thenReturn(games);
        when(daoSession.getGameDao()).thenReturn(gameDao);
        databaseManager = new DatabaseManager(daoSession);
        assertEquals(1, databaseManager.findAllGames().size());
    }
}