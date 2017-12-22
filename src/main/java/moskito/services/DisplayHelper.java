package moskito.services;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.interfaces.display.DisplayInterface;
import com.amazon.speech.speechlet.interfaces.system.SystemInterface;
import com.amazon.speech.speechlet.interfaces.system.SystemState;

public class DisplayHelper {
    public static boolean hasDisplay(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        SystemState system = requestEnvelope.getContext().getState(SystemInterface.class, SystemState.class);
        return system.getDevice().getSupportedInterfaces().isInterfaceSupported(DisplayInterface.class);
    }
}