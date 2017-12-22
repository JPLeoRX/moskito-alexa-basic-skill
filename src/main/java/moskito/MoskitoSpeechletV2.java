package moskito;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import moskito.services.IntentNames;
import moskito.services.Responses;
import moskito.speech.MoskitoSpeechletResponse;
import moskito.speech.responses.MoskitoAlertsResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Leo Ertuna
 */
public class MoskitoSpeechletV2 implements SpeechletV2, MoskitoSpeechletResponse {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
        LOGGER.info("onSessionEnded requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(), requestEnvelope.getSession().getSessionId());
    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
        LOGGER.info("onSessionEnded requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(), requestEnvelope.getSession().getSessionId());
    }


    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
        LOGGER.info("onLaunch requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(), requestEnvelope.getSession().getSessionId());

        // Setup locale
        Responses.initialize(requestEnvelope.getRequest().getLocale());

        // Redirect into welcome response
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        LOGGER.info("onIntent requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(), requestEnvelope.getSession().getSessionId());

        // Setup locale
        Responses.initialize(requestEnvelope.getRequest().getLocale());

        // Redirect into handle intent
        return handleIntent(requestEnvelope);
    }


    private SpeechletResponse handleIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        Intent intent = requestEnvelope.getRequest().getIntent();
        // If there is no intent
        if (intent == null)
            return getErrorResponse();

            // Status
        else if (IntentNames.STATUS_INTENT.equals(intent.getName()))
            return getStatusResponse(requestEnvelope);

            // Thresholds
        else if (IntentNames.THRESHOLDS_INTENT.equals(intent.getName()))
            return getThresholdsResponse(requestEnvelope);

            // Alerts
        else if (IntentNames.ALERTS_INTENT.equals(intent.getName())) {
            MoskitoAlertsResponse moskitoAlertsResponse = new MoskitoAlertsResponse(requestEnvelope);
            return moskitoAlertsResponse.respond();
        }

        // Amazon Help
        else if (IntentNames.AMAZON_HELP_INTENT.equals(intent.getName()))
            return getHelpResponse();

            // If the intent is not recognized
        else
            return getErrorResponse();
    }
}