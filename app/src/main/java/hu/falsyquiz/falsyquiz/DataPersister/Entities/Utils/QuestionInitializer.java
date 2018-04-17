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

        defaultQuestions.add(new Question((Long) null,
                "Legyen X és Y független azonos eloszlású valószínűségi változó, X~N(5,9)," +
                        " Y~N(6, 16), mennyi a várható értéke X+3Y-nak?)",
                "5", "23", "egyéb", "egyik sem",
                Question.OPTION_B, false));

        defaultQuestions.add(new Question((Long )null,
                "Milyen alakú az INSERT utasítás Oracle-ben?",
                "INSERT INTO [adatbázis név][tábla név]", "INSERT [táblanév]",
                "INSERT INTO [tábla név] VALUES (értékek)" , "Attól függ...",
                Question.OPTION_D, true));

        defaultQuestions.add(new Question((Long )null,
                "Milyen típusú garbage collectort használ a C++?",
                "Stop the world", "Mark and sweep",
                "Nincs garbage collector" , "Egyéb",
                Question.OPTION_C, false));

        defaultQuestions.add(new Question((Long )null,
                "Mire jó a friend kulcsszó C++-ban?",
                "Segítségével egy függvény hozzáférhet egy osztály privát adattagjaihoz",
                "Semmire, ilyen nincs C++-ban",
                "Publikussá teszi egy osztály privát adattagjait" , "Attól függ...",
                Question.OPTION_D, false));

        defaultQuestions.add(new Question((Long )null,
                "Mennyi a beszúró rendezés műveletigénye?",
                "n * n", "n * log n", "n * n * n" , "konstans",
                Question.OPTION_A, false));

        defaultQuestions.add(new Question((Long )null,
                "Milyen kódot képes végrehajtani a Java JVM-je?",
                "tárgykód", "bájtkód", "forráskód", "egyiket sem",
                Question.OPTION_B, false));

        defaultQuestions.add(new Question((Long )null,
                "Hány byte-on tárol egy double-t a C++?",
                "4", "6", "8", "implementáció-függő",
                Question.OPTION_D, false));

        defaultQuestions.add(new Question((Long )null,
                "Mitől válik egy osztály absztrakttá C++-ban?",
                "Nincs paraméter nélküli konstruktora.", "Van virtuális függvénye.",
                "Van tisztán virtuális függvénye.", "Van statikus metódusa.",
                Question.OPTION_C, false));

        defaultQuestions.add(new Question((Long )null,
                "Van többszörös öröklődés a Java nyelvben?",
                "Nincs.", "Van.",
                "Attól függ...", "Lehet.",
                Question.OPTION_A, false));

        defaultQuestions.add(new Question((Long )null,
                "Mi x * x deriváltja?",
                "2 * x", "x * x * x",
                "2*x / 2 ", "x * x / 2",
                Question.OPTION_A, false));

        defaultQuestions.add(new Question((Long )null,
                "Mennyi a Hamming-távolsága az alábbi két kódnak: 100101,010101?",
                "1", "2",
                "3", "4",
                Question.OPTION_B, false));

        defaultQuestions.add(new Question((Long )null,
                "Milyen típusú garbage collectort használ a C?",
                "Mark and sweep", "Stop the world", "Egyéb" ,
                "Nincs garbage collector", Question.OPTION_D, false));

        defaultQuestions.add(new Question((Long )null,
                "Milyen gráfokra alkalmazható Dijkstra algoritmusa?",
                "irányított, pozitív élsúlyokkal", "irányított, negatív élsúlyokkal",
                "irányítatlan, pozitív élsúlyokkal" , "irányítatlan," +
                "negatív élsúlyokkal",
                Question.OPTION_A, false));

        defaultQuestions.add(new Question((Long )null,
                "Fordítás során melyik elemnző kezdi meg először a működését?",
                "Lexikális", "Szintaktikus", "Szemantikus" ,
                "Egyéb", Question.OPTION_A, false));

        defaultQuestions.add(new Question((Long )null,
                "Mi a lexikális elemző kimenetele?",
                "Egyéb", "tokensorozat", "szintaxisfa" ,
                "lexikális szótár", Question.OPTION_B, false));
    }

    public static List<Question> getDefaultQuestions() {
        if (defaultQuestions == null) {
            initDefaultQuestions();
        }

        return defaultQuestions;
    }
}
