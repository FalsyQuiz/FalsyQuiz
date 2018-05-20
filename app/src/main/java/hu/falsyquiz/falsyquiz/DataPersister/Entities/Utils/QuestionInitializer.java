package hu.falsyquiz.falsyquiz.DataPersister.Entities.Utils;

import java.util.ArrayList;
import java.util.List;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;

/**
 * Created by Peti on 2018. 03. 24..
 * This class is responsible for initiating the questions.
 */

public class QuestionInitializer {

    public static List<Question> defaultQuestions;

    /**
     * This method initiating the default questions.
     */
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
                "Fordítás során melyik elemző kezdi meg először a működését?",
                "Lexikális", "Szintaktikus", "Szemantikus" ,
                "Egyéb", Question.OPTION_A, false));

        defaultQuestions.add(new Question((Long )null,
                "Mi a lexikális elemző kimenetele?",
                "Egyéb", "tokensorozat", "szintaxisfa" ,
                "lexikális szótár", Question.OPTION_B, false));

        defaultQuestions.add(new Question((Long )null,
                "Szeretnél 1 bónusz életet?",
                "Ide vele!!!", "Nem, anélkül is király vagyok!",
                "Ez ugye csak költői kérdés?" ,
                "Vannak életek ebben a játékban???", Question.OPTION_B, true));

        defaultQuestions.add(new Question((Long )null,
                "Melyik a Java legújabb, már megjelent verziója?",
                "7", "8", "9" , "10", Question.OPTION_C,
                false));

        defaultQuestions.add(new Question((Long )null,
                "Mi a jar?",
                "Befőttes üveg", "Mosogatószer", "Összezipelt Java osztályok" ,
                "Legyen már vége a játéknak", Question.OPTION_C,
                true));

        defaultQuestions.add(new Question((Long )null,
                "Melyik nyelv hello word-je ez? (let ((hello0 (lambda() " +
                        "(display \"Hello world\") (newline))))\n" + "  (hello0))",
                "Scheme", "Scala", "Common Lisp" ,
                "Adj egy bónusz életet pls.", Question.OPTION_A,
                true));

        defaultQuestions.add(new Question((Long )null,
                "Melyik nyelv hello word-je ez? Universe bear hatchery powers world.\n" +
                        "    bear hatchery powers o.      bear hatchery powers hell     marshy marshy marshy a snowmelt",
                "Smalltalk", "Shakespeare", "Nem tudom" ,
                "HomeSpring", Question.OPTION_D,
                false));

        defaultQuestions.add(new Question((Long )null,
                "Melyik nyelv hello word-je ez? view layout [text \"Hello world!\" button \"Quit\" [quit]]",
                "Rebol", "Lehet", "Delphi" ,
                "Processing", Question.OPTION_A,
                false));

        defaultQuestions.add(new Question((Long )null,
                "Melyik nyelv hello word-je ez? \\starttext\n" + "Hello, world!\n" + "\\stoptext",
                "TeX", "LaTeX", "ConTeXt" ,
                "Ilyen nincs", Question.OPTION_C,
                true));

        defaultQuestions.add(new Question((Long )null,
                "Melyik nyelv hello word-je ez? view layout [text \"Hello world!\" button \"Quit\" [quit]]",
                "Rebol", "Lehet", "Delphi" ,
                "Processing", Question.OPTION_A,
                false));

        defaultQuestions.add(new Question((Long )null,
                "Melyik nyelv hello word-je ez? ?- write('Hello world!'), nl.\n" + "Hello world!\n" +
                        "true.\n" + "\n" + "?-",
                "Scala", "Ilyen nincs", "Prolog" ,
                "Erlang", Question.OPTION_C,
                false));

        defaultQuestions.add(new Question((Long )null,
                "Melyik nyelv hello word-je ez? fn main() {\n" + "    println(\"hello, world\");\n" + "}",
                "C++", "Rust", "Haskell" ,
                "Deplhi", Question.OPTION_B,
                false));

        defaultQuestions.add(new Question((Long )null,
                "Melyik nyelv hello word-je ez? H",
                "HQ9+", "R", "Java" ,
                "Omgrofl", Question.OPTION_A,
                true));

        defaultQuestions.add(new Question((Long )null,
                "Melyik nyelv hello word-je ez? ++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++\n" +
                        "..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.",
                "Brainfuck", "C++ titkos írással", "Befunge" ,
                "LOLCODE", Question.OPTION_A,
                true));

        defaultQuestions.add(new Question((Long )null,
                "Mikor alapították meg a Microsoftot?",
                "1960-as évek", "1970-es évek", "1980-as évek" ,
                "Egyéb", Question.OPTION_B,
                false));

        defaultQuestions.add(new Question((Long )null,
                "Hány elvből áll a Neumann-elvek?",
                "4", "5", "7" ,
                "8", Question.OPTION_C,
                false));

        defaultQuestions.add(new Question((Long )null,
                "Melyik évben jelent meg az első androidos telefon?",
                "2000", "2004", "2008" ,
                "Ezek közül egyik sem", Question.OPTION_C,
                false));

        defaultQuestions.add(new Question((Long )null,
                "Alapesetben milyen típusú visszatérési értékkel rendelkezik a C++ main függvénye?",
                "int", "char",
                "void", "float",
                Question.OPTION_A, false));
        defaultQuestions.add(new Question((Long )null,

                "A Java nyelv használatával hogyan kaphatjuk meg egyszerűen 25-nek a hatodik hatványát?",
                "Integer.pow(6, 25)", "Math.power(25, 6)",
                "Math.pow(6, 25)", "Math.pow(25, 6)",
                Question.OPTION_D, false));

        defaultQuestions.add(new Question((Long )null,
                "Mennyi a QR-felbontás műveletigénye?",
                "3*n*n + O(n)", "2*n*n*n + O(n*n)",
                "4*n*n*n + O(n*n)", "n*n",
                Question.OPTION_B, false));

        defaultQuestions.add(new Question((Long )null,
                "Minek a rövidítése a mutex?",
                "mutant exception", "mutual execution",
                "mutual exclusion", "mutató exportálása",
                Question.OPTION_C, false));

        defaultQuestions.add(new Question((Long )null,
                "Assembly nyelvben melyik paranccsal ugorhatunk a kód egy másik részére?",
                "jmp", "jump",
                "gt", "goto",
                Question.OPTION_A, false));

        defaultQuestions.add(new Question((Long )null,
                "Melyik algoritmussal határozhatunk meg egy minimális súlyú feszítőerdőt?",
                "Kruskal-algoritmus", "Dijkstra-algoritmus",
                "Bellman-Ford algoritmus", "Floyd-Warshall algoritmus",
                Question.OPTION_A, false));

        defaultQuestions.add(new Question((Long )null,
                "Hány darab módszert ismerünk a bináris fák bejárására?",
                "2", "1",
                "4", "3",
                Question.OPTION_C, false));

        defaultQuestions.add(new Question((Long )null,
                "Hány darab oszlopból áll egy 5-változós formula igazságtáblája?",
                "10", "6",
                "11", "5",
                Question.OPTION_B, false));

        defaultQuestions.add(new Question((Long )null,
                "Melyik bonyolultsági osztályba tartozik a FÖLDRAJZI JÁTÉK probléma?",
                "P-beli", "PSPACE-teljes",
                "NP-teljes", "coNP-teljes",
                Question.OPTION_B, false));

        defaultQuestions.add(new Question((Long )null,
                "Mi a Déli Tömb legnépszerűbb előadótermének neve?",
                "Bólyai János terem", "Bólyai József terem",
                "Bolyai Lyános terem", "Bolyai János terem",
                Question.OPTION_D, true));

        defaultQuestions.add(new Question((Long )null,
                "Melyik nem egy Android verzió elnevezése?",
                "Haribo", "KitKat",
                "Oreo", "Jelly Bean",
                Question.OPTION_A, false));

        defaultQuestions.add(new Question((Long )null,
                "Az alábbiak közül melyik a Java nyelv egy kulcsszava?",
                "explicit", "register",
                "transient", "virtual",
                Question.OPTION_C, true));

        defaultQuestions.add(new Question((Long )null,
                "Az alábbiak közül melyik a C++ nyelv egy kulcsszava?",
                "throws", "interface",
                "super", "explicit",
                Question.OPTION_D, true));

        defaultQuestions.add(new Question((Long )null,
                "Melyik paranccsal fordíthatunk le a parancssorban egy .java fájlt?",
                "java", "javac",
                "jcompile", "jar",
                Question.OPTION_B, false));

        defaultQuestions.add(new Question((Long )null,
                "Mi lesz az output az alábbi Java kód lefuttatása során? public class Divide\n" +
                        " { public static void main(String[] args) {System.out.println(5/3);} }",
                "1", "",
                "2", "1.6666666",
                Question.OPTION_A, false));
    }

    /**
     * This function returns with the default questions.
     */
    public static List<Question> getDefaultQuestions() {
        if (defaultQuestions == null) {
            initDefaultQuestions();
        }

        return defaultQuestions;
    }
}
