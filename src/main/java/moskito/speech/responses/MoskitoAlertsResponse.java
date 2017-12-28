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
import moskito.speech.helpers.IntentNames;
import moskito.services.Responses;
import moskito.speech.factories.*;
import moskito.services.rest.AlertsRest;
import moskito.services.rest.basic_entities.Alert;
import moskito.speech.responses.core_logic.IntentResponse;

import java.util.LinkedList;
import java.util.List;

/**
 * Response to "alexa, ask moskito about alerts" command
 *
 * @author Leo Ertuna
 */
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
                    .replace("${oldStatus}", a.getStatusOldString())
                    .replace("${oldValue}", a.getValueOld())
                    .replace("${newValue}", a.getValueNew())
                    .replace("${newStatus}", a.getStatusNewString())
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
                            .replace("${oldStatus}", a.getStatusOldString())
                            .replace("${oldValue}", a.getValueOld())
                            .replace("${newValue}", a.getValueNew())
                            .replace("${newStatus}", a.getStatusNewString())
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
        this.initializeCardTitle();

        // If the user asks for too many alerts
        if (numberOfAlerts > alerts.size()) {
            // Return an error response
            return AlexaDisplayResponseFactory.newBodyTemplate2Response(
                    cardTitle, Responses.get("AlertsResponseRetry"), Responses.get("AlertsResponseRetry"),
                    Responses.get("AlertsResponseRetry"), "", "",
                    null, null, Template.BackButtonBehavior.HIDDEN, false
                    );
        }

        // If the number of alerts is valid
        else {
            // Initialize
            this.initializeSpeechText();
            this.initializeCardText();

            // Create card
            Card card = AlexaCardFactory.newSimpleCard(cardTitle, cardText);

            // Create speech
            PlainTextOutputSpeech speech = AlexaSpeechFactory.newPlainTextOutputSpeech(speechText);

            // Create list items
            List<ListTemplate1.ListItem> listItems = new LinkedList<>();
            for (int i = 0; i < numberOfAlerts; i++) {
                Alert a = alerts.get(i);
                listItems.add(AlexaListItemFactory.newListItem1(
                        AlexaTextContentFactory.newTextContentList1(
                                a.getThresholdName(),
                                a.getStatusOldString() + " / " + a.getValueOld() + " -> " + a.getStatusNewString() + " / " + a.getValueNew(),
                                a.getTimeDisplay()
                        ),
                        AlexaImageFactory.newStatusImage(a.getStatusNewImageUrl())
                ));
            }

            // Create template
            ListTemplate1 template = AlexaTemplateFactory.newListTemplate1(cardTitle, listItems, null, Template.BackButtonBehavior.HIDDEN);

            // Create render directive
            RenderTemplateDirective renderTemplateDirective = AlexaTemplateFactory.newRenderTemplateDirective(template);

            // Create list of directives
            List<Directive> directives = AlexaTemplateFactory.newListOfDirectives(renderTemplateDirective);

            // Return response
            return AlexaResponseFactory.newResponse(speech, card, directives, true);

        }
    }

    @Override
    protected SpeechletResponse getResponse() {
        this.initializeNumberOfAlerts();
        this.initializeObjectRest();
        this.initializeCardTitle();

        // If the user asks for too many alerts
        if (numberOfAlerts > alerts.size()) {
            return AlexaResponseFactory.newAskResponse(cardTitle, Responses.get("AlertsResponseRetry"), Responses.get("AlertsResponseRetry"));
        }

        // If number of alerts is valid
        else {
            this.initializeSpeechText();
            this.initializeCardText();
            return AlexaResponseFactory.newTellResponse(cardTitle, speechText.trim(), speechText.trim());
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}