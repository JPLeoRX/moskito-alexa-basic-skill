package moskito.speech.responses;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.interfaces.display.template.Template;

import moskito.services.AppsURL;
import moskito.services.Responses;
import moskito.services.rest.StatusRest;
import moskito.speech.helpers.*;
import moskito.speech.responses.core.IntentResponse;

public class MoskitoStatusResponse extends IntentResponse {
    private StatusRest status;

    public MoskitoStatusResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        super(requestEnvelope);
    }

    // Initialization
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void initializeObjectRest() {
        this.status = new StatusRest(AppsURL.current);
    }

    @Override
    protected void initializeCardTitle() {
        this.cardTitle = Responses.get("StatusResponseTitle");
    }

    @Override
    protected void initializeCardText() {
        this.cardText = speechText;
    }

    @Override
    protected void initializeSpeechText() {
        this.speechText = Responses.get("StatusResponseDefault").replace("${status}", status.getStatusString());

    }
    //------------------------------------------------------------------------------------------------------------------


    // Response
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected SpeechletResponse getResponseWithDisplay() {
        // Initialize
        this.initializeObjectRest();
        this.initializeCardTitle();
        this.initializeSpeechText();
        this.initializeCardText();

        // Return response
        return AlexaDisplayResponseFactory.newBodyTemplate2Response(
                cardTitle, cardText, speechText,
                speechText, "", "",
                AlexaImageFactory.newImage(status.getStatusImageUrl(), 75, 75), null,
                Template.BackButtonBehavior.HIDDEN,
                true
        );
    }

    @Override
    protected SpeechletResponse getResponse() {
        // Initialize
        this.initializeObjectRest();
        this.initializeCardTitle();
        this.initializeCardText();
        this.initializeSpeechText();

        // Return response
        return AlexaResponseFactory.newTellResponse(cardTitle, cardText, speechText);
    }
    //------------------------------------------------------------------------------------------------------------------
}