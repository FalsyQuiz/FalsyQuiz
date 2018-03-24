package hu.falsyquiz.falsyquiz.DataPersister.Entities.Managers;

import java.util.List;

import hu.falsyquiz.falsyquiz.DataPersister.Entities.Configuration;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.ConfigurationDao;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.DaoSession;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.GameDao;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.QuestionDao;

/**
 *
 */

public class DatabaseManager {

    private QuestionDao questionDao;
    private GameDao gameDao;
    private ConfigurationDao configurationDao;

    public DatabaseManager(DaoSession daoSession) {
        questionDao = daoSession.getQuestionDao();
        gameDao = daoSession.getGameDao();
        configurationDao = daoSession.getConfigurationDao();
    }

    public List<Question> findAllQuestions() {
        return questionDao.loadAll();
    }

    public Long insertQuestion(Question question) {
        return questionDao.insert(question);
    }

    public void updateQuestion(Question question) {
        questionDao.update(question);
    }

    public void deleteQuestion(Question question) {
        questionDao.delete(question);
    }

    public void deleteAllQuestions() { questionDao.deleteAll(); }

    public List<Game> findAllGames() {
        return gameDao.loadAll();
    }

    public Long insertGame(Game game) {
        return gameDao.insert(game);
    }

    public void updateGame(Game game) {
        gameDao.update(game);
    }

    public void deleteGame(Game game) {
        gameDao.delete(game);
    }

    public List<Configuration> getAllConfiguration() {
        return configurationDao.loadAll();
    }

    public void insertConfiguration(Configuration configuration) {
        configurationDao.insert(configuration);
    }

    public void deleteAllConfigurations() {
        configurationDao.deleteAll();
    }
}
