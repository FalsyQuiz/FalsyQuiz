package hu.falsyquiz.falsyquiz.DataPersister.Entities.Utils;

import org.junit.Test;

import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;

import static org.junit.Assert.assertEquals;

/**
 * Created by viktoria on 2018. 05. 03..
 */
public class QuestionInitializerTest {

    @Test
    public void initDefaultQuestions() throws Exception {
        QuestionInitializer.initDefaultQuestions();
        assertEquals(45, QuestionInitializer.getDefaultQuestions().size() );
    }

    @Test
    public void initDefaultQuestionsCheckFirst() throws Exception {
        QuestionInitializer.initDefaultQuestions();
        Question expectedQuestion = new Question((Long) null,
                "Legyen X és Y független azonos eloszlású valószínűségi változó, X~N(5,9)," +
                        " Y~N(6, 16), mennyi a várható értéke X+3Y-nak?)",
                "5", "23", "egyéb", "egyik sem",
                Question.OPTION_B, false);
        assertEquals( expectedQuestion.getQuestion(),
                QuestionInitializer.getDefaultQuestions().get(0).getQuestion() );
    }

    @Test
    public void initDefaultQuestionsCheckLast() throws Exception {
        QuestionInitializer.initDefaultQuestions();
        Question expectedQuestion = new Question((Long )null,
                "Mi lesz az output az alábbi Java kód lefuttatása során? public class Divide\n" +
                        " { public static void main(String[] args) {System.out.println(5/3);} }",
                "1", "",
                "2", "1.6666666",
                Question.OPTION_A, false);
        assertEquals( expectedQuestion.getQuestion(),
                QuestionInitializer.getDefaultQuestions().get(
                        QuestionInitializer.getDefaultQuestions().size() - 1 ).getQuestion() );
    }


}