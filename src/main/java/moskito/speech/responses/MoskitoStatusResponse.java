package moskito.speech.responses;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.interfaces.display.template.Template;
import moskito.services.AppsURL;
import moskito.services.DisplayHelper;
import moskito.services.Responses;
import moskito.services.rest.StatusRest;
import moskito.speech.AlexaResponses;

public interface MoskitoStatusResponse {
    default SpeechletResponse getStatusResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        // Read the data
        StatusRest status = new StatusRest(AppsURL.current);

        String cardTitle = Responses.get("StatusResponseTitle");
        String speechText = Responses.get("StatusResponseDefault").replace("${status}", status.getStatus());

        if (DisplayHelper.hasDisplay(requestEnvelope))
            return AlexaResponses.getResponse(
                    cardTitle,
                    speechText,
                    speechText,
                    "https://s3.amazonaws.com/hurricane-data/hurricaneBackground.png",
                    1024,
                    600,
                    Template.BackButtonBehavior.HIDDEN,
                    true);
        else
            return AlexaResponses.getTellResponse(cardTitle, speechText);
    }
}
