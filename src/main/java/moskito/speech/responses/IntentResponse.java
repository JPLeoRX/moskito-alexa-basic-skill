package moskito.speech.responses;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import moskito.services.DisplayHelper;

/**
 * All moskito responses should be derived from this class
 *
 * @author Leo Ertuna
 */
public abstract class IntentResponse {
    // Request enveloped passed from the speechlet
    protected SpeechletRequestEnvelope<IntentRequest> requestEnvelope;

    // Any response should have at least these fields
    protected String cardTitle;
    protected String cardText;
    protected String speechText;



    public IntentResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        this.requestEnvelope = requestEnvelope;
    }

    public SpeechletResponse respond() {
        if (DisplayHelper.hasDisplay(requestEnvelope))
            return getResponseWithDisplay();
        else
            return getResponse();
    }

    //------------------------------------------------------------------------------------------------------------------
    protected abstract void initializeObjectRest();

    protected abstract void initializeCardTitle();

    protected abstract void initializeCardText();

    protected abstract void initializeSpeechText();
    //------------------------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------------------------
    /**
     * Will be called for a device with display
     * @return
     */
    protected abstract SpeechletResponse getResponseWithDisplay();

    /**
     * Will be called for a device with no display
     * @return
     */
    protected abstract SpeechletResponse getResponse();
    //------------------------------------------------------------------------------------------------------------------
}