package moskito.speech;

import com.amazon.speech.speechlet.SpeechletResponse;

import moskito.services.Responses;
import moskito.speech.helpers.AlexaResponseFactory;

/**
 * Core speechlet logic implemented for moskito speechlet
 *
 * @author Leo Ertuna
 */
public interface MoskitoSpeechletResponse extends SpeechletResponseLogic {

    // Basic responses
    //------------------------------------------------------------------------------------------------------------------
    @Override
    default SpeechletResponse getWelcomeResponse() {
        return AlexaResponseFactory.newAskResponse(Responses.get("Title"), Responses.get("WelcomeMessage"), Responses.get("WelcomeMessage"));
    }

    @Override
    default SpeechletResponse getHelpResponse() {
        return null;
    }

    @Override
    default SpeechletResponse getErrorResponse() {
        return AlexaResponseFactory.newAskResponse(Responses.get("Title"), Responses.get("ErrorMessage"), Responses.get("ErrorMessage"));
    }
    //------------------------------------------------------------------------------------------------------------------
}