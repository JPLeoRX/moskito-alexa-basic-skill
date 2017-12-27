package moskito.speech.responses;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.Directive;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.interfaces.core.directive.HintDirective;
import com.amazon.speech.speechlet.interfaces.display.directive.RenderTemplateDirective;
import com.amazon.speech.speechlet.interfaces.display.template.BodyTemplate2;
import com.amazon.speech.speechlet.interfaces.display.template.Template;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import moskito.services.Responses;
import moskito.speech.helpers.*;

import java.util.List;

public class DefaultWelcomeResponse extends IntentResponse {
    public DefaultWelcomeResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        super(requestEnvelope);
    }

    // Initialization
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void initializeObjectRest() {

    }

    @Override
    protected void initializeCardTitle() {
        this.cardTitle = Responses.get("WelcomeTitle");
    }

    @Override
    protected void initializeCardText() {
        this.cardText = speechText;
    }

    @Override
    protected void initializeSpeechText() {
        this.speechText = Responses.get("WelcomeMessage");
    }
    //------------------------------------------------------------------------------------------------------------------



    // Response
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected SpeechletResponse getResponseWithDisplay() {
        // Initialize
        this.initializeCardTitle();
        this.initializeSpeechText();
        this.initializeCardText();

        return AlexaDisplayResponseFactory.newBodyTemplate2andHintResponse(
                cardTitle, cardText, speechText,
                speechText, "", "",
                null, null,
                Template.BackButtonBehavior.HIDDEN,
                Responses.get(HintRandomizer.getHintKey()),
                true
        );

//        // Create card
//        Card card = AlexaCardFactory.newSimpleCard(cardTitle, cardText);
//
//        // Create speech
//        PlainTextOutputSpeech speech = AlexaSpeechFactory.newPlainTextOutputSpeech(speechText);
//
//        // Create text content
//        BodyTemplate2.TextContent textContent = AlexaTextContentFactory.newTextContent2(speechText, "", "");
//
//        // Create template
//        BodyTemplate2 template = AlexaTemplateFactory.newBodyTemplate2(cardTitle, textContent, null, null, Template.BackButtonBehavior.HIDDEN);
//
//        // Create render directive
//        RenderTemplateDirective renderTemplateDirective = AlexaTemplateFactory.newRenderTemplateDirective(template);
//
//        // Create hint directive
//        HintDirective hintDirective = AlexaHintFactory.newHintDirective(Responses.get(HintRandomizer.getHintKey()));
//
//        // Create list of directives
//        List<Directive> directives = AlexaTemplateFactory.newListOfDirectives(renderTemplateDirective, hintDirective);
//
//        // Return response
//        return AlexaResponseFactory.newResponse(speech, card, directives, true);
    }

    @Override
    protected SpeechletResponse getResponse() {
        // Initialize
        this.initializeCardTitle();
        this.initializeSpeechText();
        this.initializeCardText();

        // Return response
        return AlexaResponseFactory.newAskResponse(cardTitle, cardText, speechText);
    }
    //------------------------------------------------------------------------------------------------------------------
}
