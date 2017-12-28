package moskito.speech.responses.default_responses;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.interfaces.display.template.Template;
import moskito.services.Responses;
import moskito.speech.factories.*;
import moskito.speech.helpers.HintRandomizer;
import moskito.speech.responses.core_response_logic.IntentResponse;

/**
 * Response to "alexa, ask moskito to help" command
 *
 * @author Leo Ertuna
 */
public class HelpResponse extends IntentResponse {
    public HelpResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        super(requestEnvelope);
    }

    // Initialization
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void initializeObjectRest() {
        // no rest object
    }

    @Override
    protected void initializeCardTitle() {
        this.cardTitle = Responses.get("HelpTitle");
    }

    @Override
    protected void initializeCardText() {
        this.cardText = speechText;
    }

    @Override
    protected void initializeSpeechText() {
        this.speechText = Responses.get("HelpMessage");
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

        return AlexaDisplayResponseFactory.newBodyTemplate2andHintResponse(
                cardTitle, cardText, speechText,
                speechText, "","",
                null, null, Template.BackButtonBehavior.HIDDEN,
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
