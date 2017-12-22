package moskito;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import moskito.services.IntentNames;
import moskito.services.Responses;
import moskito.speech.MoskitoSpeechletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Deprecated
public class MoskitoSpeechlet implements Speechlet, MoskitoSpeechletResponse {
    private static final Logger LOGGER = LogManager.getLogger();

    // Default Session methods
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onSessionStarted(SessionStartedRequest sessionStartedRequest, Session session) throws SpeechletException {
        LOGGER.info("onSessionEnded requestId={}, sessionId={}", sessionStartedRequest.getRequestId(), session.getSessionId());
    }

    @Override
    public void onSessionEnded(SessionEndedRequest sessionEndedRequest, Session session) throws SpeechletException {
        LOGGER.info("onSessionEnded requestId={}, sessionId={}", sessionEndedRequest.getRequestId(), session.getSessionId());
    }
    //------------------------------------------------------------------------------------------------------------------



    // Skill methods
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
        LOGGER.info("onLaunch requestId={}, sessionId={}", launchRequest.getRequestId(), session.getSessionId());

        // Redirect into welcome response
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {
        LOGGER.info("onIntent requestId={}, sessionId={}", intentRequest.getRequestId(), session.getSessionId());

        //LOGGER.info();

        // Setup locale
        Responses.initialize(intentRequest.getLocale());

        // Redirect into handle intent
        return null;
    }
    //------------------------------------------------------------------------------------------------------------------
}