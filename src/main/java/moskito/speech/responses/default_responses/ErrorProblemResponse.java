package moskito.speech.responses.default_responses;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import moskito.services.Responses;

/**
 * Response to an internal problem
 *
 * @author Leo Ertuna
 */
public class ErrorProblemResponse extends ErrorResponse {
    public ErrorProblemResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        super(requestEnvelope);
    }

    @Override
    protected void initializeSpeechText()  {
        this.speechText = Responses.get("ErrorMessageInternalProblem");
    }
}