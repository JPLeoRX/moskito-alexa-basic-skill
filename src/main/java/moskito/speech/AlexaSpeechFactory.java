package moskito.speech;

import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;

/**
 * Helper methods to deal with different speech options
 *
 * Creates text based speech and reprompts
 *
 * @author Leo Ertuna
 */
public final class AlexaSpeechFactory {
    public static PlainTextOutputSpeech newPlainTextOutputSpeech(String speechText) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);
        return speech;
    }

    private static Reprompt newReprompt(OutputSpeech outputSpeech) {
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(outputSpeech);
        return reprompt;
    }

    public static Reprompt newReprompt(String speechText) {
        return newReprompt(newPlainTextOutputSpeech(speechText));
    }
}