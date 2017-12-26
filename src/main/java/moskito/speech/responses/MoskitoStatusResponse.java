package moskito.speech.responses;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.Directive;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.interfaces.display.directive.RenderTemplateDirective;
import com.amazon.speech.speechlet.interfaces.display.element.Image;
import com.amazon.speech.speechlet.interfaces.display.template.BodyTemplate2;
import com.amazon.speech.speechlet.interfaces.display.template.Template;
import com.amazon.speech.ui.Card;

import com.amazon.speech.ui.PlainTextOutputSpeech;
import moskito.services.AppsURL;
import moskito.services.Responses;
import moskito.services.rest.StatusRest;
import moskito.speech.helpers.*;

import java.util.List;

public class MoskitoStatusResponse extends IntentResponse {
    private StatusRest status;

    public MoskitoStatusResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        super(requestEnvelope);
    }

    // Initialization
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void initializeObjectRest() {
        this.status = new StatusRest(AppsURL.current);
    }

    @Override
    protected void initializeCardTitle() {
        this.cardTitle = Responses.get("StatusResponseTitle");
    }

    @Override
    protected void initializeCardText() {
        this.cardText = speechText;

    }

    @Override
    protected void initializeSpeechText() {
        this.speechText = Responses.get("StatusResponseDefault").replace("${status}", status.getStatusString());

    }
    //------------------------------------------------------------------------------------------------------------------

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

        // Create text content
        BodyTemplate2.TextContent textContent = AlexaTextContentFactory.newTextContent2(cardText, "", "");

        // Create image
        Image image = AlexaImageFactory.newImage(status.getStatusImageUrl(), 75, 75);

        // Create template
        BodyTemplate2 template = AlexaTemplateFactory.newBodyTemplate2(cardTitle, textContent, image, null, Template.BackButtonBehavior.HIDDEN);

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
        this.initializeCardText();
        this.initializeSpeechText();

        return AlexaResponseFactory.newTellResponse(cardTitle, cardText, speechText);
    }
}