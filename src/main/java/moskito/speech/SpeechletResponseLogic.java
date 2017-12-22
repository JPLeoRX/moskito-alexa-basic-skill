package moskito.speech;

import com.amazon.speech.speechlet.SpeechletResponse;

/**
 * Core logic of speechlet responses
 *
 * Any speechlet must handle at least 3 responses
 *
 * Welcome Response         occurs when the app was open with no intent (ex: "Alexa open moskito")
 *
 * Help Response            occurs when Amazon Help intent was fired (ex: "Alexa ask moskito for help")
 *
 * Error Response           occurs when no intent was fired (ex: "Alexa ask moskito to jibber jabber")
 *
 * @author Leo Ertuna
 */
public interface SpeechletResponseLogic {
    SpeechletResponse getWelcomeResponse();

    SpeechletResponse getHelpResponse();

    SpeechletResponse getErrorResponse();
}