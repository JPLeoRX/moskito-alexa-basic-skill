package moskito;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.*;

/**
 * SpeechletV2 wrap
 *
 * @author Leo Ertuna
 */
public class MoskitoSpeechletV2 extends MoskitoSpeechlet implements SpeechletV2 {
    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
        try {
            this.onSessionStarted(requestEnvelope.getRequest(), requestEnvelope.getSession());
        }

        catch (SpeechletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
        try {
            return this.onLaunch(requestEnvelope.getRequest(), requestEnvelope.getSession());
        }

        catch (SpeechletException e) {
            e.printStackTrace(); return null;
        }
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        try {
            return this.onIntent(requestEnvelope.getRequest(), requestEnvelope.getSession());
        }

        catch (SpeechletException e) {
            e.printStackTrace(); return null;
        }
    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
        try {
            this.onSessionEnded(requestEnvelope.getRequest(), requestEnvelope.getSession());
        }

        catch (SpeechletException e) {
            e.printStackTrace();
        }
    }
}