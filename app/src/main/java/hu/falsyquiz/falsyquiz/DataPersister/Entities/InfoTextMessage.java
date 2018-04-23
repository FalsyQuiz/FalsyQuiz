package hu.falsyquiz.falsyquiz.DataPersister.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InfoTextMessage {
    private static List<String> correctAnswerMessages;
    private static List<String> wrongAnswerMessages;
    private static Random rnd;

    public static final String BONUS_QUESTION_TEXT = "BÓNUSZKÉRDÉS!";
    public static final String BONUS_LIFE_TEXT = "+1 ÉLET";

    private static void initializeTextMessages() {
        correctAnswerMessages = new ArrayList<>();
        wrongAnswerMessages = new ArrayList<>();
        rnd = new Random();

        correctAnswerMessages.add("Helyes válasz!");
        correctAnswerMessages.add("Szép volt!");
        correctAnswerMessages.add("Hajrá, csak így tovább!");

        wrongAnswerMessages.add("Rossz válasz!");
        wrongAnswerMessages.add("Ezt azért illene tudni...");
        wrongAnswerMessages.add("Ej, ej, ebből így nem lesz diploma!");

    }

    public static void initTextMessages() {
        if (correctAnswerMessages == null) {
            initializeTextMessages();
        }
    }

    public static String getCorrectAnswerMessage() {
        return correctAnswerMessages.get(rnd.nextInt(correctAnswerMessages.size()));
    }

    public static String getWrongAnswerMessage() {
        return wrongAnswerMessages.get(rnd.nextInt(wrongAnswerMessages.size()));
    }


}
