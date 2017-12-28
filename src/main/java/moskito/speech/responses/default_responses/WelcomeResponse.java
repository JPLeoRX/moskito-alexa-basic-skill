package moskito.speech.responses.default_responses;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.speechlet.interfaces.display.template.Template;
import moskito.services.Responses;
import moskito.speech.factories.*;
import moskito.speech.helpers.HintRandomizer;
import moskito.speech.responses.core_response_logic.LaunchResponse;

/**
 * Response to "alexa, open moskito" command
 *
 * @author Leo Ertuna
 */
public class WelcomeResponse extends LaunchResponse {
    public WelcomeResponse(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
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
        this.cardTitle = Responses.get("WelcomeTitle");
    }

    @Override
    protected void initializeCardText() {
        this.cardText = speechText;
    }

    @Override
    protected void initializeSpeechText() {
        this.speechText = Responses.get("WelcomeMessage");
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
