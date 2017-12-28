package moskito.speech.responses;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.interfaces.display.template.Template;
import moskito.services.rest.AmazonUser;
import moskito.services.Responses;
import moskito.speech.factories.AlexaDisplayResponseFactory;
import moskito.speech.responses.core.IntentResponse;

public class MoskitoUserResponse extends IntentResponse {
    private AmazonUser amazonUser;

    public MoskitoUserResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        super(requestEnvelope);
    }

    // Initialization
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void initializeObjectRest() {
        this.amazonUser = new AmazonUser(requestEnvelope.getSession().getUser().getAccessToken());
    }

    @Override
    protected void initializeCardTitle() {
        this.cardTitle = Responses.get("UserResponseTitle");
    }

    @Override
    protected void initializeCardText() {
        this.cardText = speechText;
    }

    @Override
    protected void initializeSpeechText() {
        this.speechText = Responses.get("UserResponseMessage").replace("${userName}", amazonUser.getName()).replace("${email}", amazonUser.getEmail());
    }
    //------------------------------------------------------------------------------------------------------------------



    // Responses
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
                "Name: " + amazonUser.getName(), "E-mail: " + amazonUser.getEmail(), "",
                null, null, Template.BackButtonBehavior.HIDDEN, true);
    }

    @Override
    protected SpeechletResponse getResponse() {
        return getDefaultSpeechTellResponse();
    }
    //------------------------------------------------------------------------------------------------------------------
}
