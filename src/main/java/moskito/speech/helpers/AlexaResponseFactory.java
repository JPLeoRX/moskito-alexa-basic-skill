package moskito.speech.helpers;

import com.amazon.speech.speechlet.Directive;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.Reprompt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Helper methods to deal with different responses
 *
 * Creates new Ask, Tell and custom responses {@link SpeechletResponse}
 *
 * @author Leo Ertuna
 */
public final class AlexaResponseFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    private AlexaResponseFactory() {

    }

    public static SpeechletResponse newLinkAccountResponse(String speechText) {
        return AlexaResponseFactory.newResponse(
                AlexaSpeechFactory.newPlainTextOutputSpeech(speechText),
                AlexaCardFactory.newLinkAccountCard(),
                null,
                false
        );
    }

    public static SpeechletResponse newResponse(OutputSpeech outputSpeech, Card card, List<Directive> directives, boolean shouldEndSession) {
        SpeechletResponse response = new SpeechletResponse();

        response.setOutputSpeech(outputSpeech);
        response.setCard(card);
        response.setDirectives(directives);
        response.setNullableShouldEndSession(shouldEndSession);

        LOGGER.info("Created Response: {" + response + "}");
        return response;
    }

    public static SpeechletResponse newTellResponse(String cardTitle, String cardText, String speechText) {
        Card card = AlexaCardFactory.newSimpleCard(cardTitle, cardText);
        OutputSpeech outputSpeech = AlexaSpeechFactory.newPlainTextOutputSpeech(speechText);
        SpeechletResponse response = SpeechletResponse.newTellResponse(outputSpeech, card);

        LOGGER.info("Created Tell Response: {" + response + "}");
        return response;
    }

    public static SpeechletResponse newAskResponse(String cardTitle, String cardText, String speechText) {
        Card card = AlexaCardFactory.newSimpleCard(cardTitle, cardText);
        OutputSpeech outputSpeech = AlexaSpeechFactory.newPlainTextOutputSpeech(speechText);
        Reprompt reprompt = AlexaSpeechFactory.newReprompt(speechText);
        SpeechletResponse response = SpeechletResponse.newAskResponse(outputSpeech, reprompt, card);

        LOGGER.info("Created Ask Response: {" + response + "}");
        return response;
    }
}