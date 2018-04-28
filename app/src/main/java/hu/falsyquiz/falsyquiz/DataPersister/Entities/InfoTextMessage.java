package hu.falsyquiz.falsyquiz.DataPersister.Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import hu.falsyquiz.falsyquiz.Activities.AbstractActivity;
import hu.falsyquiz.falsyquiz.R;


/**
 * This class represents the information messages that appear during the game.
 */
public class InfoTextMessage {

    public interface MessageListener {
        AbstractActivity getActivity();
    }

    private static List<String> correctAnswerMessages;
    private static List<String> wrongAnswerMessages;
    private static List<String> phoneCallMessages;
    private static Random rnd;
    private static MessageListener messageListener;

    /**
     * This method initializes the information messages and generates a random number.
     */
    private static void initializeTextMessages() {
        correctAnswerMessages = new ArrayList<>(Arrays.asList(getTextMessageArray(R.array.correctAnswer_text_array)));
        wrongAnswerMessages = new ArrayList<>(Arrays.asList(getTextMessageArray(R.array.wrongAnswer_text_array)));
        phoneCallMessages = new ArrayList<>(Arrays.asList(getTextMessageArray(R.array.phoneCall_text_array)));
        rnd = new Random();
    }

    /**
     * This method initializes the information messages if they have not been initialized yet.
     */
    public static void initTextMessages(MessageListener messageListener) {
        InfoTextMessage.messageListener = messageListener;
        if (correctAnswerMessages == null) {
            initializeTextMessages();
        }
    }

    /**
     * This function returns a praiseworthy message when the player responds well.
     */
    public static String getCorrectAnswerMessage() {
        return correctAnswerMessages.get(rnd.nextInt(correctAnswerMessages.size()));
    }

    /**
     * This function returns with a rater message when the player responds poorly.
     */
    public static String getWrongAnswerMessage() {
        return wrongAnswerMessages.get(rnd.nextInt(wrongAnswerMessages.size()));
    }

    /**
     * This function returns with a message when the player calling for help.
     */
    public static String getPhoneCallMessage() {
        return phoneCallMessages.get(rnd.nextInt(phoneCallMessages.size()));
    }

    /**
     * This function returns the information message based on its id.
     * @param id The id of the information message we want to get.
     */
    public static String getTextMessage(int id) {
        return messageListener.getActivity().getResources().getString(id);
    }

    /**
     * This function returns the information message array based on its id.
     * @param id The id of the information message array we want to get.
     */
    public static String[] getTextMessageArray(int id) {
        return messageListener.getActivity().getResources().getStringArray(id);
    }


}
