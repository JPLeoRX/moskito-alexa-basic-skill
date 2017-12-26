package moskito.speech.responses;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.Directive;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.interfaces.display.directive.RenderTemplateDirective;
import com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1;
import com.amazon.speech.speechlet.interfaces.display.template.Template;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import moskito.services.AppsURL;
import moskito.services.IntentNames;
import moskito.services.Responses;
import moskito.speech.AlexaImageFactory;
import moskito.services.rest.AlertsRest;
import moskito.services.rest.basic_entities.Alert;
import moskito.speech.*;

import java.util.LinkedList;
import java.util.List;

public class MoskitoAlertsResponse extends IntentResponse {
    private int numberOfAlerts = 1;
    private AlertsRest alertsRest;
    private List<Alert> alerts;

    private String speechLine1;
    private List<String> speechLines = new LinkedList<>();

    public MoskitoAlertsResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        super(requestEnvelope);
    }

    // Initialization
    //------------------------------------------------------------------------------------------------------------------
    protected void initializeNumberOfAlerts() {
        // Get intent's slot
        Slot slot = requestEnvelope.getRequest().getIntent().getSlot(IntentNames.ALERTS_INTENT_SLOT_NUMBER);

        // If there is such slot
        if (slot != null)
            // If this slot has a value
            if (slot.getValue() != null)
                // We will use this value in our call
                numberOfAlerts = Integer.valueOf(slot.getValue());
    }

    @Override
    protected void initializeObjectRest() {
        // Create new Rest object
        this.alertsRest = new AlertsRest(AppsURL.current);
        this.alerts = alertsRest.getList();
    }

    @Override
    protected void initializeCardTitle() {
        this.cardTitle = Responses.get("AlertsResponseTitle");
    }

    @Override
    protected void initializeCardText() {
        this.cardText = speechText;
    }

    @Override
    protected void initializeSpeechText() {
        // If there's only one alert to be displayed
        if (numberOfAlerts == 1) {
            Alert a = alerts.get(0);
            speechText = Responses.get("AlertsResponseDefault")
                    .replace("${thresholdName}", a.getThresholdName())
                    .replace("${oldStatus}", a.getStatusOld())
                    .replace("${oldValue}", a.getValueOld())
                    .replace("${newValue}", a.getValueNew())
                    .replace("${newStatus}", a.getStatusNew())
                    .replace("${time}", a.getTimeSpeech());
        }

        // If there are several alerts
        else {
            speechLine1 = Responses.get("AlertsResponseLine1").replace("${count}", String.valueOf(numberOfAlerts));
            for (int i = 0; i < numberOfAlerts; i++) {
                if (i < alerts.size()) {
                    Alert a = alerts.get(i);
                    speechLines.add(" " + Responses.get("AlertsResponseLineN")
                            .replace("${index}", String.valueOf(i + 1))
                            .replace("${thresholdName}", a.getThresholdName())
                            .replace("${oldStatus}", a.getStatusOld())
                            .replace("${oldValue}", a.getValueOld())
                            .replace("${newValue}", a.getValueNew())
                            .replace("${newStatus}", a.getStatusNew())
                            .replace("${time}", a.getTimeSpeech())
                    );
                }
            }

            speechText = speechLine1;
            for (String s : speechLines)
                speechText += s;
        }
    }
    //------------------------------------------------------------------------------------------------------------------



    // Responses
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected SpeechletResponse getResponseWithDisplay() {
        this.initializeNumberOfAlerts();
        this.initializeObjectRest();

        // If the user asks for too many alerts
        if (numberOfAlerts > alerts.size()) {
            return AlexaResponses.getAskResponse(cardTitle, Responses.get("AlertsResponseRetry"));
        }

        else {
            this.initializeCardTitle();
            this.initializeSpeechText();
            this.initializeCardText();

            if (numberOfAlerts > 0)
            {
                // Create card
                Card card = AlexaCardFactory.newSimpleCard(cardTitle, cardText);

                // Create speech
                PlainTextOutputSpeech speech = AlexaSpeechFactory.newPlainTextOutputSpeech(speechText);

                List<ListTemplate1.ListItem> listItems = new LinkedList<>();
                for (int i = 0; i < numberOfAlerts; i++) {
                    Alert a = alerts.get(i);
                    ListTemplate1.ListItem item = new ListTemplate1.ListItem();

                    item.setImage(AlexaImageFactory.newImage("https://www.moskito.org/applications/control/" + a.getStatusNewName().toLowerCase() + ".png", 75, 75));

                    item.setTextContent(AlexaTextContentFactory.newTextContentList1(
                            a.getThresholdName(),
                            a.getStatusOld() + " / " + a.getValueOld() + " -> " + a.getStatusNew() + " / " + a.getValueNew(),
                            a.getTimeDisplay()));
                    listItems.add(item);
                }

                ListTemplate1 listTemplate1 = new ListTemplate1();
                listTemplate1.setListItems(listItems);
                listTemplate1.setTitle(cardTitle);
                listTemplate1.setBackButtonBehavior(Template.BackButtonBehavior.HIDDEN);

                // Create render directive
                RenderTemplateDirective renderTemplateDirective = AlexaScreen.getRenderTemplateDirective(listTemplate1);

                // Create list of directives
                List<Directive> directives = AlexaScreen.getListOfDirectives(renderTemplateDirective);

                return AlexaResponses.getResponse(speech, card, directives, true);
            }

        }

        return getResponse();
    }

    @Override
    protected SpeechletResponse getResponse() {
        this.initializeNumberOfAlerts();
        this.initializeObjectRest();

        // If the user asks for too many alerts
        if (numberOfAlerts > alerts.size()) {
            return AlexaResponses.getAskResponse(cardTitle, Responses.get("AlertsResponseRetry"));
        }

        // If number of alerts is valid
        else {
            this.initializeCardTitle();
            this.initializeSpeechText();
            this.initializeCardText();
            return AlexaResponses.getTellResponse(cardTitle, speechText.trim(), speechText.trim());
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}
