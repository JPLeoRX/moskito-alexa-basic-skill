package moskito;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import moskito.services.IntentNames;
import moskito.services.Responses;
import moskito.speech.MoskitoSpeechletResponse;
import moskito.speech.helpers.AlexaCardFactory;
import moskito.speech.helpers.AlexaResponseFactory;
import moskito.speech.helpers.AlexaSpeechFactory;
import moskito.speech.responses.*;
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
        // Get intent
        Intent intent = requestEnvelope.getRequest().getIntent();

        // Get user
        User user = requestEnvelope.getSession().getUser();
        if (user != null)
            LOGGER.info("User: id={" + user.getUserId() + "}, accessToken={" + user.getAccessToken() + "}");

        // If access token is null
        if (user.getAccessToken() == null) {
            LOGGER.info("User is not authorized");
            return AlexaResponseFactory.newResponse(
                    AlexaSpeechFactory.newPlainTextOutputSpeech("Please use the companion app to authenticate on Amazon to start using this skill"),
                    AlexaCardFactory.newLinkAccountCard(),
                    null,
                    false
            );
        }
        else {
            LOGGER.info("User is authorized: accessToken={" + user.getAccessToken() + "}");
        }
        /**
         * In the Java library, User.getAccessToken() returns null if the user has not successfully linked their account.
         */

        // If there is no intent
        if (intent == null) {
            LOGGER.error("Intent is null");
            DefaultErrorResponse defaultErrorResponse = new DefaultErrorResponse(requestEnvelope);
            return defaultErrorResponse.respond();
        }

        // Status
        else if (IntentNames.STATUS_INTENT.equals(intent.getName())) {
            LOGGER.info("Intent: " + intent.getName());
            MoskitoStatusResponse moskitoStatusResponse = new MoskitoStatusResponse(requestEnvelope);
            return moskitoStatusResponse.respond();
        }

        // Thresholds
        else if (IntentNames.THRESHOLDS_INTENT.equals(intent.getName())) {
            LOGGER.info("Intent: " + intent.getName());
            MoskitoThresholdsResponse moskitoThresholdsResponse = new MoskitoThresholdsResponse(requestEnvelope);
            return moskitoThresholdsResponse.respond();
        }

        // Alerts
        else if (IntentNames.ALERTS_INTENT.equals(intent.getName())) {
            LOGGER.info("Intent: " + intent.getName());
            MoskitoAlertsResponse moskitoAlertsResponse = new MoskitoAlertsResponse(requestEnvelope);
            return moskitoAlertsResponse.respond();
        }

        // Login
        else if (IntentNames.LOGIN_INTENT.equals(intent.getName())) {
            LOGGER.info("Intent: " + intent.getName());
            return AlexaResponseFactory.newResponse(
                    AlexaSpeechFactory.newPlainTextOutputSpeech("Login"),
                    AlexaCardFactory.newLinkAccountCard(),
                    null,
                    true
            );
        }

        // Amazon Help
        else if (IntentNames.AMAZON_HELP_INTENT.equals(intent.getName())) {
            LOGGER.info("Intent: " + intent.getName());
            DefaultHelpResponse helpResponse = new DefaultHelpResponse(requestEnvelope);
            return helpResponse.respond();
        }

        // If the intent is not recognized
        else {
            LOGGER.error("Intent is not recognized");
            DefaultErrorResponse defaultErrorResponse = new DefaultErrorResponse(requestEnvelope);
            return defaultErrorResponse.respond();
        }
    }
}