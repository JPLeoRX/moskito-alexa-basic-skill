package moskito.speech;

import com.amazon.speech.speechlet.SpeechletResponse;
import moskito.services.AppsURL;
import moskito.services.Responses;
import moskito.services.rest.StatusRest;
import moskito.services.rest.Threshold;
import moskito.services.rest.ThresholdsRest;

import java.util.List;

public interface MoskitoSpeechletResponse extends SpeechletResponseLogic {
    String cardTitle = "Moskito";
    String welcomeText = "Welcome to the Moskito Alexa Skill, you can say \"check my app status\"";
    String defaultText = "There seems to be an internal problem, please try another command";
    String helpText = "You can say \"check my app status\" to me!";
    String errorText = "This is unsupported. Please try something else.";

    default SpeechletResponse getWelcomeResponse() {
        return getAskResponse(cardTitle, welcomeText);
    }

    default SpeechletResponse getActualResponse() {
        return getAskResponse(cardTitle, defaultText);
    }

    default SpeechletResponse getHelpResponse() {
        return getAskResponse(cardTitle, helpText);
    }

    default SpeechletResponse getErrorResponse() {
        return getAskResponse(cardTitle, errorText);
    }



    default SpeechletResponse getStatusResponse() {
        StatusRest status = new StatusRest(AppsURL.current);
        String speechText = Responses.getString("StatusResponse").replace("${status}", status.getStatus());
        return getTellResponse(cardTitle, speechText);
    }

    default SpeechletResponse getThresholdsResponse() {
        ThresholdsRest thresholdsRest = new ThresholdsRest(AppsURL.current);
        List<Threshold> thresholds = thresholdsRest.getList();


        String speechText = Responses.getString("ThresholdResponseLine1").replace("${count}", String.valueOf(thresholds.size()));
        for (int i = 0; i < thresholds.size(); i++) {
            Threshold t = thresholds.get(i);
            speechText += " " + Responses.getString("ThresholdResponseLineN")
                    .replace("${index}", String.valueOf(i + 1))
                    .replace("${name}", t.getName())
                    .replace("${status}", String.valueOf(t.getStatus()))
                    .replace("${value}", t.getValue());
            //speechText += "Threshold " + (i + 1) + " named " + t.getName() + ", with " + t.getStatus() + " status, and its value is " + t.getValue() + ". ";
        }
        speechText = speechText.trim();

        return getTellResponse(cardTitle, speechText);
    }
}