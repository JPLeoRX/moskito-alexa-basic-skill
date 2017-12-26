package moskito.speech;

import com.amazon.speech.speechlet.SpeechletResponse;

import moskito.services.Responses;
import moskito.speech.helpers.AlexaResponseFactory;
import moskito.speech.responses.MoskitoThresholdsResponse;

public interface MoskitoSpeechletResponse extends SpeechletResponseLogic {

    // Basic responses
    //------------------------------------------------------------------------------------------------------------------
    @Override
    default SpeechletResponse getWelcomeResponse() {
        return AlexaResponseFactory.newAskResponse(Responses.get("Title"), Responses.get("WelcomeMessage"), Responses.get("WelcomeMessage"));
    }

    @Override
    default SpeechletResponse getHelpResponse() {
        return AlexaResponseFactory.newAskResponse(Responses.get("Title"), Responses.get("HelpMessage"), Responses.get("HelpMessage"));
    }

    @Override
    default SpeechletResponse getErrorResponse() {
        return AlexaResponseFactory.newAskResponse(Responses.get("Title"), Responses.get("ErrorMessage"), Responses.get("ErrorMessage"));
    }
    //------------------------------------------------------------------------------------------------------------------
}