package moskito.speech;

import com.amazon.speech.speechlet.SpeechletResponse;

import moskito.services.AppsURL;
import moskito.services.Responses;
import moskito.services.rest.*;
import moskito.services.rest.basic_entities.Alert;
import moskito.services.rest.basic_entities.Threshold;
import moskito.speech.responses.MoskitoStatusResponse;
import moskito.speech.responses.MoskitoThresholdsResponse;

import java.util.List;

public interface MoskitoSpeechletResponse extends SpeechletResponseLogic, MoskitoStatusResponse, MoskitoThresholdsResponse {

    // Basic responses
    //------------------------------------------------------------------------------------------------------------------
    @Override
    default SpeechletResponse getWelcomeResponse() {
        return AlexaResponses.getAskResponse(Responses.get("Title"), Responses.get("WelcomeMessage"));
    }

    @Override
    default SpeechletResponse getHelpResponse() {
        return AlexaResponses.getAskResponse(Responses.get("Title"), Responses.get("HelpMessage"));
    }

    @Override
    default SpeechletResponse getErrorResponse() {
        return AlexaResponses.getAskResponse(Responses.get("Title"), Responses.get("ErrorMessage"));
    }
    //------------------------------------------------------------------------------------------------------------------
}