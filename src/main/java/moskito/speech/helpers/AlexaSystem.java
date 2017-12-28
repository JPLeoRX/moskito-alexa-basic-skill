package moskito.speech.helpers;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.CoreSpeechletRequest;
import com.amazon.speech.speechlet.interfaces.display.DisplayInterface;
import com.amazon.speech.speechlet.interfaces.system.SystemInterface;
import com.amazon.speech.speechlet.interfaces.system.SystemState;
import com.amazon.speech.speechlet.SpeechletV2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Global state of Alexa currently running
 *
 * Singleton object
 * Create new instance when {@link SpeechletV2} runs
 * {@link SpeechletV2#onLaunch(SpeechletRequestEnvelope)} or {@link SpeechletV2#onIntent(SpeechletRequestEnvelope)}
 *
 * Contains all info about system that may be accessed throughout the course of skill execution
 *
 * @author Leo Ertuna
 */
public final class AlexaSystem {
    private static AlexaSystem current;
    private static final Logger LOGGER = LogManager.getLogger();

    private SpeechletRequestEnvelope<? extends CoreSpeechletRequest> requestEnvelope;
    private SystemState systemState;
    private boolean hasDisplay;
    private String deviceId;
    private String apiEndpoint;
    private String apiAccessToken;

    // Constructor
    //------------------------------------------------------------------------------------------------------------------
    private AlexaSystem(SpeechletRequestEnvelope<? extends CoreSpeechletRequest> requestEnvelope) {
        this.requestEnvelope = requestEnvelope;

        systemState = requestEnvelope.getContext().getState(SystemInterface.class, SystemState.class);
        hasDisplay = systemState.getDevice().getSupportedInterfaces().isInterfaceSupported(DisplayInterface.class);
        deviceId = systemState.getDevice().getDeviceId();
        apiEndpoint = systemState.getApiEndpoint();
        apiAccessToken = systemState.getApiAccessToken();

        LOGGER.info("Created {" + this + "}");
        current = this;
    }

    public static void newCurrent(SpeechletRequestEnvelope<? extends CoreSpeechletRequest> requestEnvelope) {
        current = new AlexaSystem(requestEnvelope);
    }
    //------------------------------------------------------------------------------------------------------------------



    // Getters
    //------------------------------------------------------------------------------------------------------------------
    public static AlexaSystem getCurrent() {
        return current;
    }

    public static SystemState getSystemState() {
        return current.systemState;
    }

    public static boolean hasDisplay() {
        return current.hasDisplay;
    }

    public static String getDeviceId() {
        return current.deviceId;
    }

    public static String getApiEndpoint() {
        return current.apiEndpoint;
    }

    public static String getApiAccessToken() {
        return current.apiAccessToken;
    }
    //------------------------------------------------------------------------------------------------------------------



    // Others
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Alexa System: hasDisplay={" + hasDisplay + "}, deviceId={" + deviceId + "}, apiEndpoint={" + apiEndpoint + "}, apiAccessToken={" + apiAccessToken + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlexaSystem that = (AlexaSystem) o;
        return hasDisplay == that.hasDisplay &&
                Objects.equals(requestEnvelope, that.requestEnvelope) &&
                Objects.equals(systemState, that.systemState) &&
                Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(apiEndpoint, that.apiEndpoint) &&
                Objects.equals(apiAccessToken, that.apiAccessToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestEnvelope, systemState, hasDisplay, deviceId, apiEndpoint, apiAccessToken);
    }
    //------------------------------------------------------------------------------------------------------------------
}