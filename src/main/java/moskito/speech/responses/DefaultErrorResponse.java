package moskito.speech.responses;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.interfaces.display.template.Template;
import moskito.services.Responses;
import moskito.speech.factories.*;
import moskito.speech.helpers.HintRandomizer;
import moskito.speech.responses.core.IntentResponse;

public class DefaultErrorResponse extends IntentResponse {
    public DefaultErrorResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        super(requestEnvelope);
    }

    // Initialization
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void initializeObjectRest() {

    }

    @Override
    protected void initializeCardTitle() {
        this.cardTitle = Responses.get("ErrorTitle");
    }

    @Override
    protected void initializeCardText() {
        this.cardText = speechText;
    }

    @Override
    protected void initializeSpeechText() {
        this.speechText = Responses.get("ErrorMessage");
    }
    //------------------------------------------------------------------------------------------------------------------



    // Response
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected SpeechletResponse getResponseWithDisplay() {
        // Initialize
        this.initializeCardTitle();
        this.initializeSpeechText();
        this.initializeCardText();

        // Return response
        return AlexaDisplayResponseFactory.newBodyTemplate2andHintResponse(
                cardTitle, cardText, speechText,
                speechText, "", "",
                null, null,
                Template.BackButtonBehavior.HIDDEN,
                Responses.get(HintRandomizer.getHintKey()),
                false
        );
    }

    @Override
    protected SpeechletResponse getResponse() {
        return this.getDefaultSpeechAskResponse();
    }
    //------------------------------------------------------------------------------------------------------------------
}
