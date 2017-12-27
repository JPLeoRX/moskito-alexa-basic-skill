package moskito.speech.responses.core;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.CoreSpeechletRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import moskito.services.DisplayHelper;

/**
 * Core response functionality
 *
 * All response classes must be extended from here
 *
 * We use pre-defined intent and launch responses {@link IntentResponse} {@link LaunchResponse}
 *
 * @param <R> either {@link IntentRequest} or {@link LaunchRequest}
 */
public abstract class CustomResponse<R extends CoreSpeechletRequest> {
    // Request enveloped passed from the speechlet
    protected SpeechletRequestEnvelope<R> requestEnvelope;

    // Any response should have at least these fields
    protected String cardTitle;
    protected String cardText;
    protected String speechText;

    // Constructor
    //------------------------------------------------------------------------------------------------------------------
    public CustomResponse(SpeechletRequestEnvelope<R> requestEnvelope) {
        this.requestEnvelope = requestEnvelope;
    }
    //------------------------------------------------------------------------------------------------------------------



    // Decide which response to show
    //------------------------------------------------------------------------------------------------------------------
    public SpeechletResponse respond() {
        if (DisplayHelper.hasDisplay(requestEnvelope))
            return getResponseWithDisplay();
        else
            return getResponse();
    }
    //------------------------------------------------------------------------------------------------------------------



    // Initialization
    //------------------------------------------------------------------------------------------------------------------
    protected abstract void initializeObjectRest();

    protected abstract void initializeCardTitle();

    protected abstract void initializeCardText();

    protected abstract void initializeSpeechText();
    //------------------------------------------------------------------------------------------------------------------



    // Responses
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