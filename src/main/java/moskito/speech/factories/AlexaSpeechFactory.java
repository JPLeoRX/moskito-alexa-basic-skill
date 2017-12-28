package moskito.speech.factories;

import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helper methods to deal with different speech options
 *
 * Creates text based speech {@link OutputSpeech]} and re-prompt {@link Reprompt}
 *
 * @author Leo Ertuna
 */
public final class AlexaSpeechFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    private AlexaSpeechFactory() {

    }

    /**
     * Creates basic output speech
     * @param speechText text of the speech
     * @return output speech
     */
    public static PlainTextOutputSpeech newPlainTextOutputSpeech(String speechText) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        LOGGER.info("Created Output Speech: {" + speech + "}");
        return speech;
    }

    /**
     * Creates basic re-prompt
     * @param speechText text of the speech
     * @return re-prompt
     */
    public static Reprompt newReprompt(String speechText) {
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(newPlainTextOutputSpeech(speechText));

        LOGGER.info("Created Re-prompt: {" + reprompt + "}");
        return reprompt;
    }
}