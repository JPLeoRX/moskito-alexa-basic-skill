package moskito.speech.responses;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.Directive;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.interfaces.display.directive.RenderTemplateDirective;
import com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1;
import com.amazon.speech.speechlet.interfaces.display.template.Template;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import moskito.services.AppsURL;
import moskito.services.Responses;
import moskito.services.rest.ThresholdsRest;
import moskito.services.rest.basic_entities.Threshold;
import moskito.speech.helpers.*;

import java.util.LinkedList;
import java.util.List;

public class MoskitoThresholdsResponse extends IntentResponse {
    private ThresholdsRest thresholdsRest;
    private List<Threshold> thresholds;

    private String speechLine1;
    private List<String> speechLines = new LinkedList<>();

    public MoskitoThresholdsResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        super(requestEnvelope);
    }

    // Initialization
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void initializeObjectRest() {
        this.thresholdsRest = new ThresholdsRest(AppsURL.current);
        this.thresholds = thresholdsRest.getList();
    }

    @Override
    protected void initializeCardTitle() {
        this.cardTitle = Responses.get("ThresholdsResponseTitle");
    }

    @Override
    protected void initializeCardText() {
        this.cardText = speechText;
    }

    @Override
    protected void initializeSpeechText() {
        // Create first line
        speechLine1 = Responses.get("ThresholdsResponseLine1").replace("${count}", String.valueOf(thresholds.size()));

        // Create all the lines
        for (int i = 0; i < thresholds.size(); i++) {
            Threshold t = thresholds.get(i);
            speechLines.add(" " + Responses.get("ThresholdsResponseLineN")
                    .replace("${index}", String.valueOf(i + 1))
                    .replace("${thresholdName}", t.getName())
                    .replace("${status}", t.getStatusString())
                    .replace("${value}", t.getValue())
            );
        }

        // Combine everything in one string
        speechText = speechLine1;
        for (String l : speechLines)
            speechText += l;
    }

    @Override
    protected SpeechletResponse getResponseWithDisplay() {
        this.initializeObjectRest();
        this.initializeCardTitle();
        this.initializeSpeechText();
        this.initializeCardText();

        // Create card
        Card card = AlexaCardFactory.newSimpleCard(cardTitle, cardText);

        // Create speech
        PlainTextOutputSpeech speech = AlexaSpeechFactory.newPlainTextOutputSpeech(speechText);

        // Create list items
        List<ListTemplate1.ListItem> listItems = new LinkedList<>();
        for (Threshold t : thresholds) {
            listItems.add(AlexaListItemFactory.newListItem1(
                    AlexaTextContentFactory.newTextContentList1(
                            t.getName(),
                            t.getStatusString() + " / " + t.getValue(),
                            ""
                    ),
                    AlexaImageFactory.newImage(t.getStatusImageUrl(), 75, 75)));
        }

        // Create template
        ListTemplate1 template = AlexaTemplateFactory.newListTemplate1(cardTitle, listItems, null, Template.BackButtonBehavior.HIDDEN);

        // Create render directive
        RenderTemplateDirective renderTemplateDirective = AlexaTemplateFactory.newRenderTemplateDirective(template);

        // Create list of directives
        List<Directive> directives = AlexaTemplateFactory.newListOfDirectives(renderTemplateDirective);

        return AlexaResponseFactory.newResponse(speech, card, directives, true);
    }

    @Override
    protected SpeechletResponse getResponse() {
        this.initializeObjectRest();
        this.initializeCardTitle();
        this.initializeSpeechText();
        this.initializeCardText();

        return AlexaResponseFactory.newTellResponse(cardTitle, cardText, speechText);
    }
    //    default SpeechletResponse getThresholdsResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
//        // Read the data

//
//
//        String cardTitle = Responses.get("ThresholdsResponseTitle");
//
//        String line1 = Responses.get("ThresholdsResponseLine1").replace("${count}", String.valueOf(thresholds.size()));
//        List<String> lines = new LinkedList<>();
//        for (int i = 0; i < thresholds.size(); i++) {
//            Threshold t = thresholds.get(i);
//            lines.add(" " + Responses.get("ThresholdsResponseLineN")
//                    .replace("${index}", String.valueOf(i + 1))
//                    .replace("${thresholdName}", t.getName())
//                    .replace("${status}", t.getStatus())
//                    .replace("${value}", t.getValue())
//            );
//        }
//
//
//        String speechText = line1;
//        for (String l : lines)
//            speechText += l;
//
//        return AlexaResponseFactory.newTellResponse(cardTitle, speechText, speechText);
//    }
}
