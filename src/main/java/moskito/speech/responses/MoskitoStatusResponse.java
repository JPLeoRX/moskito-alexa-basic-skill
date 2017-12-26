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
import moskito.speech.*;
import moskito.services.rest.StatusRest;
import moskito.speech.helpers.AlexaCardFactory;
import moskito.speech.helpers.AlexaImageFactory;
import moskito.speech.helpers.AlexaSpeechFactory;
import moskito.speech.helpers.AlexaTextFieldFactory;

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
        this.cardText = Responses.get("StatusResponseDefault").replace("${status}", status.getStatusString());

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
        this.initializeCardText();
        this.initializeSpeechText();

        // Create card
        Card card = AlexaCardFactory.newSimpleCard(cardTitle, cardText);

        // Create speech
        PlainTextOutputSpeech speech = AlexaSpeechFactory.newPlainTextOutputSpeech(speechText);

        // Create text content
        BodyTemplate2.TextContent textContent = new BodyTemplate2.TextContent();
        textContent.setPrimaryText(AlexaTextFieldFactory.newPlainText(cardText));

        // Create image
        Image image = AlexaImageFactory.newImage(status.getStatusImageUrl(), 75, 75);

        // Create template
        BodyTemplate2 template = new BodyTemplate2();
        template.setTitle(cardTitle);
        template.setTextContent(textContent);
        template.setBackButtonBehavior(Template.BackButtonBehavior.HIDDEN);
        template.setImage(image);

        // Create render directive
        RenderTemplateDirective renderTemplateDirective = AlexaScreen.getRenderTemplateDirective(template);

        // Create list of directives
        List<Directive> directives = AlexaScreen.getListOfDirectives(renderTemplateDirective);

        return AlexaResponses.getResponse(speech, card, directives, true);
    }

    @Override
    protected SpeechletResponse getResponse() {
        this.initializeObjectRest();
        this.initializeCardTitle();
        this.initializeCardText();
        this.initializeSpeechText();

        return AlexaResponses.getTellResponse(cardTitle, cardText, speechText);
    }
}