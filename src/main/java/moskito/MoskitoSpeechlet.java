package moskito;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import moskito.services.Responses;
import moskito.speech.MoskitoSpeechletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class MoskitoSpeechlet implements Speechlet, MoskitoSpeechletResponse {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onSessionStarted(SessionStartedRequest sessionStartedRequest, Session session) throws SpeechletException {
        LOGGER.info("onSessionEnded requestId={}, sessionId={}", sessionStartedRequest.getRequestId(), session.getSessionId());
    }


    @Override
    public void onSessionEnded(SessionEndedRequest sessionEndedRequest, Session session) throws SpeechletException {
        LOGGER.info("onSessionEnded requestId={}, sessionId={}", sessionEndedRequest.getRequestId(), session.getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
        LOGGER.info("onLaunch requestId={}, sessionId={}", launchRequest.getRequestId(), session.getSessionId());

        // Redirect into welcome response
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {
        LOGGER.info("onIntent requestId={}, sessionId={}", intentRequest.getRequestId(), session.getSessionId());

        // Redirect into our response, amazon help response, or ask user for input again
        Intent intent = intentRequest.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        // Setup locale
        Responses.initialize(intentRequest.getLocale());

        if ("StatusIntent".equals(intentName))
            return getStatusResponse();

        else if ("ThresholdsIntent".equals(intentName))
            return getThresholdsResponse();

        else if ("AlertsIntent".equals(intentName))
            return getAlertsResponse();

        else if ("AMAZON.HelpIntent".equals(intentName))
            return getHelpResponse();

        else
            return getErrorResponse();
    }
}