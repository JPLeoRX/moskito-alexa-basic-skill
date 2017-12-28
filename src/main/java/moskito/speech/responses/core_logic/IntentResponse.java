package moskito.speech.responses.core_logic;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;

/**
 * All moskito intent responses should be derived from this class
 *
 * @author Leo Ertuna
 */
public abstract class IntentResponse extends CustomResponse<IntentRequest> {
    public IntentResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        super(requestEnvelope);
    }

    @Override
    public String toString() {
        return super.toString().replace("CustomResponse: ", "IntentResponse: ");
    }
}