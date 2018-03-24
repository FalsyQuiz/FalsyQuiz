package hu.falsyquiz.falsyquiz.DataPersister.Entities.Managers;

import java.util.ArrayList;
import java.util.List;

import hu.falsyquiz.falsyquiz.DataPersister.Entities.Configuration;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;

/**
 *
 */

public class DataManager {

    private DatabaseManager databaseManager;

    private List<Question> questions;

    public DataManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        questions = databaseManager.findAllQuestions();
    }

    public void createQuestion(Question question) {
        Long id = databaseManager.insertQuestion(question);
        question.setId(id);
        questions.add(question);
    }

    public void deleteQuestion (Question question) {
        databaseManager.deleteQuestion(question);
        questions.remove(question);
    }

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

    public void updateQuestion(Question question) {
        databaseManager.updateQuestion(question);
        for (Question q : questions) {
            if (q.getId().equals(question.getId())) {
                q = question;
            }
        }
    }

    public void deleteAllQuestions() {
        databaseManager.deleteAllQuestions();
    }

    public List<Game> getAllGames() {
        return databaseManager.findAllGames();
    }

    public void createGame(Game game) {
        Long id = databaseManager.insertGame(game);
        game.setId(id);
    }

    public void updataGame(Game game) {
        databaseManager.updateGame(game);
    }

    public void createConfiguration(Configuration configuration) {
        databaseManager.insertConfiguration(configuration);
    }

    public String getConfigurationValue(String key) {
        for (Configuration configuration : databaseManager.getAllConfiguration()) {
            if (configuration.getKey().equals(key)) {
                return configuration.getValue();
            }
        }

        return null;
    }

    public void deleteAllConfiguration() {
        databaseManager.getAllConfiguration();
    }
}
