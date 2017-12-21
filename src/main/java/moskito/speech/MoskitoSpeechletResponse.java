package moskito.speech;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Directive;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;

import com.amazon.speech.speechlet.interfaces.display.DisplayInterface;
import com.amazon.speech.speechlet.interfaces.display.directive.RenderTemplateDirective;
import com.amazon.speech.speechlet.interfaces.display.element.Image;
import com.amazon.speech.speechlet.interfaces.display.element.PlainText;
import com.amazon.speech.speechlet.interfaces.display.template.BodyTemplate1;
import com.amazon.speech.speechlet.interfaces.display.template.Template;
import com.amazon.speech.speechlet.interfaces.system.SystemInterface;
import com.amazon.speech.speechlet.interfaces.system.SystemState;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import moskito.services.AppsURL;
import moskito.services.Responses;
import moskito.services.images.ImageHelper;
import moskito.services.rest.*;
import moskito.services.rest.basic_entities.Alert;
import moskito.services.rest.basic_entities.Threshold;

import java.util.ArrayList;
import java.util.List;

public interface MoskitoSpeechletResponse extends SpeechletResponseLogic {

    // Basic responses
    //------------------------------------------------------------------------------------------------------------------
    @Override
    default SpeechletResponse getWelcomeResponse() {
        return AlexaResponses.getAskResponse(Responses.get("Title"), Responses.get("WelcomeMessage"));
    }

    @Override
    default SpeechletResponse getHelpResponse() {
        return AlexaResponses.getAskResponse(Responses.get("Title"), Responses.get("HelpMessage"));
    }

    @Override
    default SpeechletResponse getErrorResponse() {
        return AlexaResponses.getAskResponse(Responses.get("Title"), Responses.get("ErrorMessage"));
    }
    //------------------------------------------------------------------------------------------------------------------



    // Responses to our skill
    //------------------------------------------------------------------------------------------------------------------
    default SpeechletResponse getStatusResponse() {
        String cardTitle = Responses.get("StatusResponseTitle");

        StatusRest status = new StatusRest(AppsURL.current);
        String speechText = Responses.get("StatusResponseDefault").replace("${status}", status.getStatus());
        String smallImageUrl = "https://www.moskito.org/applications/control/${status}.png".replace("${status}", status.getStatus().toLowerCase());
        String largeImageUrl = "https://www.moskito.org/applications/control/${status}.png".replace("${status}", status.getStatus().toLowerCase());;

//        SystemState system = envelope.getContext().getState(SystemInterface.class, SystemState.class);
//        boolean hasDisplay = system.getDevice().getSupportedInterfaces().isInterfaceSupported(DisplayInterface.class);


//        // Create the card
//        Card card = AlexaCardFactory.newSimpleCard(cardTitle, speechText);
//
//        // Create the speech
//        PlainTextOutputSpeech speech = AlexaSpeech.getPlainTextOutputSpeech(speechText);
//
//        // Create screen text
//        BodyTemplate1.TextContent screenText = AlexaScreen.getTextContent1(speechText);
//
//        // Create image
//        Image image = ImageHelper.getImage("https://s3.amazonaws.com/hurricane-data/hurricaneBackground.png", 1024, 600);
//
//        // Create the templete
//        BodyTemplate1 bodyTemplate1 = AlexaScreen.getBodyTemplate1(cardTitle, cardTitle, screenText, image, Template.BackButtonBehavior.HIDDEN);
//
//        // Create render directive
//        RenderTemplateDirective renderTemplateDirective = AlexaScreen.getRenderTemplateDirective(bodyTemplate1);
//
//        List<Directive> directives = new ArrayList<>();
//        directives.add(renderTemplateDirective);
//
//        //return AlexaResponses.getTellResponse(cardTitle, speechText, smallImageUrl, largeImageUrl, speechText);

        return AlexaResponses.getResponse(cardTitle, speechText, speechText, "https://s3.amazonaws.com/hurricane-data/hurricaneBackground.png", 1024, 600, Template.BackButtonBehavior.HIDDEN, true);

        //return AlexaResponses.getResponse(speech, card, directives, true);
    }

    default SpeechletResponse getThresholdsResponse() {
        String cardTitle = Responses.get("ThresholdsResponseTitle");

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

        return AlexaResponses.getTellResponse(cardTitle, speechText, speechText);
    }

    default SpeechletResponse getAlertsResponse(int numberOfAlerts) {
        String cardTitle = Responses.get("AlertsResponseTitle");

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



        return AlexaResponses.getTellResponse(cardTitle, speechText.trim(), speechText.trim());
    }
    //------------------------------------------------------------------------------------------------------------------
}