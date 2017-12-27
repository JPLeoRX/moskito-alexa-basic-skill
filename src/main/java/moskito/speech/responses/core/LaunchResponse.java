package moskito.speech.responses.core;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.LaunchRequest;

/**
 * All moskito launch responses should be derived from this class
 *
 * @author Leo Ertuna
 */
public abstract class LaunchResponse extends CustomResponse<LaunchRequest> {
    public LaunchResponse(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
        super(requestEnvelope);
    }
}