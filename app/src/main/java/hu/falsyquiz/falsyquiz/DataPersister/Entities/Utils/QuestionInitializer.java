package hu.falsyquiz.falsyquiz.DataPersister.Entities.Utils;

import java.util.ArrayList;
import java.util.List;

import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;

/**
 * Created by Peti on 2018. 03. 24..
 */

public class QuestionInitializer {

    public static List<Question> defaultQuestions;

    public static void initDefaultQuestions() {
        defaultQuestions = new ArrayList<>();
        defaultQuestions.add(new Question((Long)null, "Legyen X és Y független azonos eloszlású valószínűségi változó, X~N(5,9), Y~N(6, 16), mennyi a várható értéke X+3Y-nak?)", "5", "23", "egyéb", "egyik sem", Question.OPTION_B, false));
        defaultQuestions.add(new Question((Long)null, "Milyen alakú az INSERT utasítás Oracle-ben?", "INSERT INTO [adatbázis név][tábla név]", "INSERT [táblanév]", "INSERT INTO [tábla név] VALUES (értékek)" , "Attól függ...", Question.OPTION_D, true));

    }

    public static List<Question> getDefaultQuestions() {
        if (defaultQuestions == null) {
            initDefaultQuestions();
        }

        return defaultQuestions;
    }
}
