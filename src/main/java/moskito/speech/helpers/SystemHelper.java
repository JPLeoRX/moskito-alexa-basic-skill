package moskito.speech.helpers;

import com.amazon.speech.speechlet.Context;
import com.amazon.speech.speechlet.interfaces.system.SystemInterface;
import com.amazon.speech.speechlet.interfaces.system.SystemState;

public final class SystemHelper {
    /**
     * Helper method that retrieves the system state from the request context.
     * @param context request context.
     * @return SystemState the systemState
     */
    public static SystemState getSystemState(Context context) {
        return context.getState(SystemInterface.class, SystemState.class);
    }

}
