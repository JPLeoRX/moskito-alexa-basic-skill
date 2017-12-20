package moskito.speech;

import com.amazon.speech.speechlet.SpeechletResponse;
import moskito.services.AppsURL;
import moskito.services.Responses;
import moskito.services.rest.*;

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
        String speechText = Responses.get("StatusResponse").replace("${status}", status.getStatus());
        return getTellResponse(cardTitle, speechText);
    }

    default SpeechletResponse getThresholdsResponse() {
        ThresholdsRest thresholdsRest = new ThresholdsRest(AppsURL.current);
        List<Threshold> thresholds = thresholdsRest.getList();

        String speechText = Responses.get("ThresholdsResponseLine1").replace("${count}", String.valueOf(thresholds.size()));
        for (int i = 0; i < thresholds.size(); i++) {
            Threshold t = thresholds.get(i);
            speechText += " " + Responses.get("ThresholdsResponseLineN")
                    .replace("${index}", String.valueOf(i + 1))
                    .replace("${name}", t.getName())
                    .replace("${status}", String.valueOf(t.getStatus()))
                    .replace("${value}", t.getValue());
        }
        speechText = speechText.trim();

        return getTellResponse(cardTitle, speechText);
    }

    default SpeechletResponse getAlertsResponse() {
        AlertsRest alertsRest = new AlertsRest(AppsURL.current);
        List<Alert> alerts = alertsRest.getList();

        String speechText = "";

        if (alerts.size() > 5)
            speechText = Responses.get("AlertsResponseLine1").replace("${count}", "5");
        else
            speechText = Responses.get("AlertsResponseLine1").replace("${count}", String.valueOf(alerts.size()));

        for (int i = 0; i < 5; i++) {
            if (i < alerts.size()) {
                Alert a = alerts.get(i);
                speechText += " " + Responses.get("AlertsResponseLineN")
                        .replace("${index}", String.valueOf(i + 1))
                        .replace("${name}", a.getName())
                        .replace("${oldStatus}", String.valueOf(a.getStatusOld()))
                        .replace("${newStatus}", String.valueOf(a.getStatusNew()))
                        .replace("${time}", a.getTime());
            }

        }
        speechText = speechText.trim();

        return getTellResponse(cardTitle, speechText);
    }
}