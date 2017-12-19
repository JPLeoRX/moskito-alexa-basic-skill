package moskito.speech;

import com.amazon.speech.speechlet.SpeechletResponse;
import moskito.services.ReadWebpage;

public interface MoskitoSpeechletResponse extends SpeechletResponseLogic {
    String cardTitle = "Moskito";
    String welcomeText = "Welcome to the Moskito Alexa Skill, you can say \"check my app status\"";
    String helpText = "You can say \"check my app status\" to me!";
    String errorText = "This is unsupported. Please try something else.";

    default SpeechletResponse getWelcomeResponse() {
        return getAskResponse(cardTitle, welcomeText);
    }

    default SpeechletResponse getActualResponse() {
        ReadWebpage webpage = new ReadWebpage();
        String statusText = webpage.getSuccessBool() ? "green" : "red";
        String speechText = "Your app status is " + statusText;
        return getTellResponse(cardTitle, speechText);
    }

    default SpeechletResponse getHelpResponse() {
        return getAskResponse(cardTitle, helpText);
    }

    default SpeechletResponse getErrorResponse() {
        return getAskResponse(cardTitle, errorText);
    }
}