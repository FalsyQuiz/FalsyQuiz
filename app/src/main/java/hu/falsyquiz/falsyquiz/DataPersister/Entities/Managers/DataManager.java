package hu.falsyquiz.falsyquiz.DataPersister.Entities.Managers;

import java.util.ArrayList;
import java.util.List;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Configuration;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;
import lombok.Getter;

/**
 * This class manages the class which is used to manage the database and stores the questions in
 * its cache.
 */

public class DataManager {

    private DatabaseManager databaseManager;

    private List<Question> questions;

    private List<Game> games;

    public DataManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        questions = databaseManager.findAllQuestions();
        games = databaseManager.findAllGames();
    }

    /**
     * This method inserts a question to the database by calling the DatabaseManager's
     * insertQuestion method and stores it in the cache.
     * @param question The question that needs to be inserted.
     */
    public void createQuestion(Question question) {
        Long id = databaseManager.insertQuestion(question);
        question.setId(id);
        questions.add(question);
    }

    /**
     * This method deletes a question from the database by calling the DatabaseManager's
     * deleteQuestion method and also deletes the question from the cache.
     * @param question The question that needs to be deleted.
     */
    public void deleteQuestion (Question question) {
        databaseManager.deleteQuestion(question);
        questions.remove(question);
    }

    /**
     * This function returns all the questions in the cache.
     */
    public List<Question> getAllQuestions() {
        return questions;
    }

    public List<Question> getAllQuestionsList() {
        List<Question> questions = new ArrayList<>();
        for (Question question : this.questions) {
            questions.add(question);
        }

        return questions;
    }

    /**
     * This method modifies a question in the database by calling the DatabaseManager's
     * updateQuestion method and also updates the cache accordingly.
     * @param question The question that needs to be updated.
     */
    public void updateQuestion(Question question) {
        databaseManager.updateQuestion(question);
        for (int i = 0; i < questions.size(); ++i) {
            if (questions.get(i).getId().equals(question.getId())) {
                questions.set(i, question);
                break;
            }
        }
    }

    /**
     * This method deletes all the questions form the database by calling the DatabaseManager's
     * deleteAllQuestions method.
     */
    public void deleteAllQuestions() {
        databaseManager.deleteAllQuestions();
        questions.clear();
    }

    /**
     * This function gets all the games from the database by calling the DatabaseManager's
     * findAllGames function.
     */
    public List<Game> getAllGames() {
        return games;
    }

    /**
     * This method inserts a game into the database by calling the DatabaseManager's insertGame
     * function.
     * @game The game to be inserted.
     */
    public void createGame(Game game) {
        game.setId(null);
        Long id = databaseManager.insertGame(game);
        game.setId(id);
        games.add(game);
    }

    /**
     * This method updates a game in the database by calling the DatabaseManager's updateGame
     * method.
     * @game The game to be updated.
     */
    public void updataGame(Game game) {
        databaseManager.updateGame(game);
        for (int i = 0; i < games.size(); ++i) {
            if (game.getId().equals(games.get(i).getId())) {
                games.set(i, game);
                break;
            }
        }
    }

    /**
     * This method inserts a configuration to the database by calling the DatabaseManager's
     * insertConfiguration method.
     * @param configuration The configuration to be inserted.
     */
    public void createConfiguration(Configuration configuration) {
        databaseManager.insertConfiguration(configuration);
    }

    /**
     * This function returns the value of the configuration based on its key.
     * @param key
     */
    public String getConfigurationValue(String key) {
        for (Configuration configuration : databaseManager.getAllConfiguration()) {
            if (configuration.getKey().equals(key)) {
                return configuration.getValue();
            }
        }
        return null;
    }

    /**
     * This method deletes all the configurations from the database by calling the DatabaseManager's
     * deleteAllConfigurations method.
     */
    public void deleteAllConfiguration() {
        databaseManager.deleteAllConfigurations();
    }
}
