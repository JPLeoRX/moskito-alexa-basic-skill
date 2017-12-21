package moskito.speech;

import com.amazon.speech.speechlet.SpeechletResponse;
import moskito.services.AppsURL;
import moskito.services.Responses;
import moskito.services.rest.*;
import moskito.services.rest.basic_entities.Alert;
import moskito.services.rest.basic_entities.Threshold;

import java.util.List;

public interface MoskitoSpeechletResponse extends SpeechletResponseLogic {
    String cardTitle = "MoSKito";

    // Basic responses
    //------------------------------------------------------------------------------------------------------------------
    @Override
    default SpeechletResponse getWelcomeResponse() {
        return AlexaResponses.getAskResponse(cardTitle, Responses.get("WelcomeMessage"));
    }

    @Override
    default SpeechletResponse getHelpResponse() {
        return AlexaResponses.getAskResponse(cardTitle, Responses.get("HelpMessage"));
    }

    @Override
    default SpeechletResponse getErrorResponse() {
        return AlexaResponses.getAskResponse(cardTitle, Responses.get("ErrorMessage"));
    }
    //------------------------------------------------------------------------------------------------------------------



    // Responses to our skill
    //------------------------------------------------------------------------------------------------------------------
    default SpeechletResponse getStatusResponse() {
        StatusRest status = new StatusRest(AppsURL.current);
        String speechText = Responses.get("StatusResponseDefault").replace("${status}", status.getStatus());
        String cardTitle = Responses.get("StatusResponseTitle");
        String smallImageUrl = "https://www.moskito.org/applications/control/${status}.png".replace("${status}", status.getStatus().toLowerCase());
        String largeImageUrl = "https://www.moskito.org/applications/control/${status}.png".replace("${status}", status.getStatus().toLowerCase());;

        return AlexaResponses.getTellResponse(cardTitle, speechText, smallImageUrl, largeImageUrl, speechText);
    }

    default SpeechletResponse getThresholdsResponse() {
        ThresholdsRest thresholdsRest = new ThresholdsRest(AppsURL.current);
        List<Threshold> thresholds = thresholdsRest.getList();

        String speechText = Responses.get("ThresholdsResponseLine1").replace("${count}", String.valueOf(thresholds.size()));
        for (int i = 0; i < thresholds.size(); i++) {
            Threshold t = thresholds.get(i);
            speechText += " " + Responses.get("ThresholdsResponseLineN")
                    .replace("${index}", String.valueOf(i + 1))
                    .replace("${thresholdName}", t.getName())
                    .replace("${status}", t.getStatus())
                    .replace("${value}", t.getValue());
        }
        speechText = speechText.trim();
        String cardTitle = Responses.get("ThresholdsResponseTitle");

        return AlexaResponses.getTellResponse(cardTitle, speechText, speechText);
    }

    default SpeechletResponse getAlertsResponse(int numberOfAlerts) {
        AlertsRest alertsRest = new AlertsRest(AppsURL.current);
        List<Alert> alerts = alertsRest.getList();
        String speechText;

        // If the user asks for too many alerts
        if (numberOfAlerts > alerts.size())
            return AlexaResponses.getAskResponse(cardTitle, Responses.get("AlertsResponseRetry"));

        // If there's only one alert to be displayed
        else if (numberOfAlerts == 1) {
            Alert a = alerts.get(0);
            speechText = Responses.get("AlertsResponseDefault")
                    .replace("${thresholdName}", a.getThresholdName())
                    .replace("${oldStatus}", a.getStatusOld())
                    .replace("${oldValue}", a.getValueOld())
                    .replace("${newValue}", a.getValueNew())
                    .replace("${newStatus}", a.getStatusNew())
                    .replace("${time}", a.getTime());
        }

        // If there are several alerts
        else {
            speechText = Responses.get("AlertsResponseLine1").replace("${count}", String.valueOf(numberOfAlerts));
            for (int i = 0; i < numberOfAlerts; i++) {
                if (i < alerts.size()) {
                    Alert a = alerts.get(i);
                    speechText += " " + Responses.get("AlertsResponseLineN")
                            .replace("${index}", String.valueOf(i + 1))
                            .replace("${thresholdName}", a.getThresholdName())
                            .replace("${oldStatus}", a.getStatusOld())
                            .replace("${oldValue}", a.getValueOld())
                            .replace("${newValue}", a.getValueNew())
                            .replace("${newStatus}", a.getStatusNew())
                            .replace("${time}", a.getTime());
                }
            }
        }

        String cardTitle = Responses.get("AlertsResponseTitle");

        return AlexaResponses.getTellResponse(cardTitle, speechText.trim(), speechText.trim());
    }
    //------------------------------------------------------------------------------------------------------------------
}