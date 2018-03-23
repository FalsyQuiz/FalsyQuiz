package hu.falsyquiz.falsyquiz.DataPersister.Entities.Managers;

import java.util.List;

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

    public void updateQuestion(Question question) {
        databaseManager.updateQuestion(question);
        for (Question q : questions) {
            if (q.getId().equals(question.getId())) {
                q = question;
            }
        }
    }
}
