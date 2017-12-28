package moskito.speech.responses.default_responses;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import moskito.services.Responses;

/**
 * Response to an unknown command
 *
 * @author Leo Ertuna
 */
public class ErrorCommandResponse extends ErrorResponse {
    public ErrorCommandResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        super(requestEnvelope);
    }

    @Override
    protected void initializeSpeechText()  {
        this.speechText = Responses.get("ErrorMessageUnknownCommand");
    }
}