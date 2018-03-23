package hu.falsyquiz.falsyquiz.DataPersister.Entities.Managers;

import java.util.List;

import hu.falsyquiz.falsyquiz.DataPersister.Entities.DaoSession;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.QuestionDao;

/**
 *
 */

public class DatabaseManager {

    private QuestionDao questionDao;

    public DatabaseManager(DaoSession daoSession) {
        questionDao = daoSession.getQuestionDao();
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
}
