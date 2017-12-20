package moskito;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import moskito.services.Responses;
import moskito.speech.MoskitoSpeechletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

        // Setup locale
        Responses.initialize(intentRequest.getLocale());

        // Redirect into handle intent
        return handleIntent(intentRequest.getIntent());
    }

    private SpeechletResponse handleIntent(Intent intent) {
        // If there is no intent
        if (intent == null)
            return getErrorResponse();

        // Status
        else if ("StatusIntent".equals(intent.getName()))
            return getStatusResponse();

        // Thresholds
        else if ("ThresholdsIntent".equals(intent.getName()))
            return getThresholdsResponse();

        // Alerts
        else if ("AlertsIntent".equals(intent.getName())) {
            int number = 1;
            Slot slot = intent.getSlot("Number");

            if (slot != null)                                                       // If there is such slot
                if (slot.getValue() != null)                                        // If there is a value
                    number = Integer.valueOf(slot.getValue());                      // We will use this value in our call

            return getAlertsResponse(number);
        }

        // Amazon Help
        else if ("AMAZON.HelpIntent".equals(intent.getName()))
            return getHelpResponse();

        // If the intent is not recognized
        else
            return getErrorResponse();
    }
    //------------------------------------------------------------------------------------------------------------------
}