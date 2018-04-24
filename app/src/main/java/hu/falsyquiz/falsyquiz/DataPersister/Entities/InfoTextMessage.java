package hu.falsyquiz.falsyquiz.DataPersister.Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import hu.falsyquiz.falsyquiz.Activities.AbstractActivity;
import hu.falsyquiz.falsyquiz.R;

public class InfoTextMessage {

    public interface MessageListener {
        AbstractActivity getActivity();
    }

    private static List<String> correctAnswerMessages;
    private static List<String> wrongAnswerMessages;
    private static Random rnd;
    private static MessageListener messageListener;

    private static void initializeTextMessages() {
        correctAnswerMessages = new ArrayList<>(Arrays.asList(getTextMessageArray(R.array.correctAnswer_text_array)));
        wrongAnswerMessages = new ArrayList<>(Arrays.asList(getTextMessageArray(R.array.wrongAnswer_text_array)));
        rnd = new Random();
    }

    public static void initTextMessages(MessageListener messageListener) {
        InfoTextMessage.messageListener = messageListener;
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

    public static String getTextMessage(int id) {
        return messageListener.getActivity().getResources().getString(id);
    }

    public static String[] getTextMessageArray(int id) {
        return messageListener.getActivity().getResources().getStringArray(id);
    }


}
