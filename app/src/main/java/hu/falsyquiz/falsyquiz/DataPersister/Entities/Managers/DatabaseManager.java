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
 * This class manages all of the app's DAOs.
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

    /**
     * This function returns all the questions in the database.
     */
    public List<Question> findAllQuestions() {
        return questionDao.loadAll();
    }

    /**
     * This function inserts a question into the database.
     * @param question The question to be inserted.
     */
    public Long insertQuestion(Question question) {
        return questionDao.insert(question);
    }

    /**
     * This method modifies a question in the database.
     * @param question The question that needs to be modified.
     */
    public void updateQuestion(Question question) {
        questionDao.update(question);
    }

    /**
     * This method deletes a question from the database.
     * @param question The question that needs to be deleted.
     */
    public void deleteQuestion(Question question) {
        questionDao.delete(question);
    }

    /**
     * This method deletes all the questions in the database.
     */
    public void deleteAllQuestions() { questionDao.deleteAll(); }

    /**
     * This function returns all the games in the database.
     */
    public List<Game> findAllGames() {
        return gameDao.loadAll();
    }

    /**
     * This function inserts a game into the database.
     * @param game The game to be inserted.
     */
    public Long insertGame(Game game) {
        return gameDao.insert(game);
    }

    /**
     * This method modifies a game in the database.
     * @param game The game that needs to be modified.
     */
    public void updateGame(Game game) {
        gameDao.update(game);
    }

    /**
     * This method deletes a game from the database.
     * @param game The game that needs to be deleted.
     */
    public void deleteGame(Game game) {
        gameDao.delete(game);
    }

    /**
     * This function returns all the configurations in the database.
     */
    public List<Configuration> getAllConfiguration() {
        return configurationDao.loadAll();
    }

    /**
     * This method inserts a configuration into the database.
     * @param configuration The configuration to be inserted.
     */
    public void insertConfiguration(Configuration configuration) {
        configurationDao.insert(configuration);
    }

    /**
     * This method deletes all the configurations from the database.
     */
    public void deleteAllConfigurations() {
        configurationDao.deleteAll();
    }
}
