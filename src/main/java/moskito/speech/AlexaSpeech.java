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
public final class AlexaSpeech {
    public static PlainTextOutputSpeech getPlainTextOutputSpeech(String speechText) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);
        return speech;
    }

    public static Reprompt getReprompt(OutputSpeech outputSpeech) {
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(outputSpeech);
        return reprompt;
    }

    public static Reprompt getReprompt(String speechText) {
        return getReprompt(getPlainTextOutputSpeech(speechText));
    }
}