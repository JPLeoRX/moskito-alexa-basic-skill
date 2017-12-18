package speech;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoskitoSpeechlet implements SpeechletV2, MoskitoSpeechletResponse {
    private static final Logger LOGGER = LogManager.getLogger();


    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> speechletRequestEnvelope) {
        LOGGER.info("onSessionStarted requestId={}, sessionId={}", speechletRequestEnvelope.getRequest().getRequestId(), speechletRequestEnvelope.getSession().getSessionId());
    }

    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> speechletRequestEnvelope) {
        LOGGER.info("onSessionEnded requestId={}, sessionId={}", speechletRequestEnvelope.getRequest().getRequestId(), speechletRequestEnvelope.getSession().getSessionId());
    }

    /**
     * This is called when we launch the skill with no intent
     *
     * ex: "Alexa open Moskito"
     */
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> speechletRequestEnvelope) {
        LOGGER.info("onLaunch requestId={}, sessionId={}", speechletRequestEnvelope.getRequest().getRequestId(), speechletRequestEnvelope.getSession().getSessionId());

        // Redirect into welcome response
        return getWelcomeResponse();
    }

    /**
     * This is the most used call to our skill
     *
     * ex: "Alexa ask Moskito to check app status"
     */
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> speechletRequestEnvelope) {
        LOGGER.info("onIntent requestId={}, sessionId={}", speechletRequestEnvelope.getRequest().getRequestId(), speechletRequestEnvelope.getSession().getSessionId());

        // Redirect into our response, amazon help response, or ask user for input again
        IntentRequest intentRequest = speechletRequestEnvelope.getRequest();
        Intent intent = intentRequest.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("MoskitoIntent".equals(intentName))
            return getActualResponse();

        else if ("AMAZON.HelpIntent".equals(intentName))
            return getHelpResponse();

        else
            return getErrorResponse();
    }


}
