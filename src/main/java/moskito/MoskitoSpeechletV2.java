package moskito;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import moskito.services.AppsURL;
import moskito.services.rest.MoskitoHomeTokenVerification;
import moskito.services.rest.MoskitoHomeUser;
import moskito.speech.helpers.AlexaSystem;
import moskito.speech.helpers.IntentNames;
import moskito.services.Responses;
import moskito.speech.factories.*;
import moskito.speech.responses.*;
import moskito.speech.responses.default_responses.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Leo Ertuna
 */
public class MoskitoSpeechletV2 implements SpeechletV2 {
    private static final Logger LOGGER = LogManager.getLogger();

    // Speechelt methods
    //------------------------------------------------------------------------------------------------------------------
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

        // Setup Alexa System
        AlexaSystem.newCurrent(requestEnvelope);
        LOGGER.info("Current System is: {" + AlexaSystem.getCurrent() + "}");

        // Redirect into welcome response
        WelcomeResponse welcomeResponse = new WelcomeResponse(requestEnvelope);
        return welcomeResponse.respond();
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        LOGGER.info("onIntent requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(), requestEnvelope.getSession().getSessionId());

        // Setup locale
        Responses.initialize(requestEnvelope.getRequest().getLocale());

        // Setup Alexa System
        AlexaSystem.newCurrent(requestEnvelope);
        LOGGER.info("Current System is: {" + AlexaSystem.getCurrent() + "}");

        // Redirect into handle intent
        return handleIntent(requestEnvelope);
    }
    //------------------------------------------------------------------------------------------------------------------



    // Intents
    //------------------------------------------------------------------------------------------------------------------
    private SpeechletResponse handleIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        // Verify user token
        MoskitoHomeTokenVerification verification = new MoskitoHomeTokenVerification(requestEnvelope.getSession().getUser().getAccessToken());

        // If user is not authorized
        if (!verification.isValid()) {
            LOGGER.info("User is not authorized");

            // Return a link account response
            return AlexaResponseFactory.newLinkAccountResponse(Responses.get("LoginMessage"));
        }

        // If the user is authorized
        else {
            // Print access token
            LOGGER.info("User is authorized: accessToken={" + requestEnvelope.getSession().getUser().getAccessToken() + "}");

            // Save the current user's app URL
            MoskitoHomeUser user = new MoskitoHomeUser(requestEnvelope.getSession().getUser().getAccessToken());
            AppsURL.current = user.getAppUrl();
        }

        // Get intent
        Intent intent = requestEnvelope.getRequest().getIntent();

        // If there is no intent
        if (intent == null) {
            LOGGER.error("Intent is null");

            ErrorResponse errorResponse = new ErrorProblemResponse(requestEnvelope);
            return errorResponse.respond();
        }

        // Status
        else if (IntentNames.STATUS_INTENT.equals(intent.getName())) {
            LOGGER.info("Intent: " + intent.getName());

            MoskitoStatusResponse statusResponse = new MoskitoStatusResponse(requestEnvelope);
            return statusResponse.respond();
        }

        // Thresholds
        else if (IntentNames.THRESHOLDS_INTENT.equals(intent.getName())) {
            LOGGER.info("Intent: " + intent.getName());

            MoskitoThresholdsResponse thresholdsResponse = new MoskitoThresholdsResponse(requestEnvelope);
            return thresholdsResponse.respond();
        }

        // Alerts
        else if (IntentNames.ALERTS_INTENT.equals(intent.getName())) {
            LOGGER.info("Intent: " + intent.getName());

            MoskitoAlertsResponse alertsResponse = new MoskitoAlertsResponse(requestEnvelope);
            return alertsResponse.respond();
        }

        // User
        else if (IntentNames.USER_INTENT.equals(intent.getName())) {
            LOGGER.info("Intent: " + intent.getName());

            MoskitoUserResponse userResponse = new MoskitoUserResponse(requestEnvelope);
            return userResponse.respond();
        }

        // Login/Switch User
        else if (IntentNames.LOGIN_INTENT.equals(intent.getName())) {
            LOGGER.info("Intent: " + intent.getName());

            // Return a link account response
            return AlexaResponseFactory.newLinkAccountResponse(Responses.get("LoginMessage"));
        }

        // Amazon Help
        else if (IntentNames.AMAZON_HELP_INTENT.equals(intent.getName())) {
            LOGGER.info("Intent: " + intent.getName());

            HelpResponse helpResponse = new HelpResponse(requestEnvelope);
            return helpResponse.respond();
        }

        // If the intent is not recognized
        else {
            LOGGER.error("Intent is not recognized");

            ErrorResponse errorResponse = new ErrorCommandResponse(requestEnvelope);
            return errorResponse.respond();
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}