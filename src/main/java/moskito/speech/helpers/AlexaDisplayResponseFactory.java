package moskito.speech.helpers;

import com.amazon.speech.speechlet.Directive;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.interfaces.core.directive.HintDirective;
import com.amazon.speech.speechlet.interfaces.display.directive.RenderTemplateDirective;
import com.amazon.speech.speechlet.interfaces.display.element.Image;
import com.amazon.speech.speechlet.interfaces.display.template.BodyTemplate2;
import com.amazon.speech.speechlet.interfaces.display.template.Template;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import moskito.services.Responses;

import java.util.List;

public final class AlexaDisplayResponseFactory {
    private AlexaDisplayResponseFactory() {

    }

    public static SpeechletResponse newBodyTemplate2andHintResponse(String cardTitle, String cardText, String speechText,
                                                                    String primaryText, String secondaryText, String tertiaryText,
                                                                    Image image, Image backgroundImage,
                                                                    Template.BackButtonBehavior backButtonBehavior,
                                                                    String hintText,
                                                                    boolean shouldEndSession) {
        // Create card
        Card card = AlexaCardFactory.newSimpleCard(cardTitle, cardText);

        // Create speech
        PlainTextOutputSpeech speech = AlexaSpeechFactory.newPlainTextOutputSpeech(speechText);

        // Create text content
        BodyTemplate2.TextContent textContent = AlexaTextContentFactory.newTextContent2(primaryText, secondaryText, tertiaryText);

        // Create template
        BodyTemplate2 template = AlexaTemplateFactory.newBodyTemplate2(cardTitle, textContent, image, backgroundImage, backButtonBehavior);

        // Create render directive
        RenderTemplateDirective renderTemplateDirective = AlexaTemplateFactory.newRenderTemplateDirective(template);

        // Create hint directive
        HintDirective hintDirective = AlexaHintFactory.newHintDirective(hintText);

        // Create list of directives
        List<Directive> directives = AlexaTemplateFactory.newListOfDirectives(renderTemplateDirective, hintDirective);

        // Return response
        return AlexaResponseFactory.newResponse(speech, card, directives, shouldEndSession);
    }



    public static SpeechletResponse newBodyTemplate2Response(String cardTitle, String cardText, String speechText,
                                                                    String primaryText, String secondaryText, String tertiaryText,
                                                                    Image image, Image backgroundImage,
                                                                    Template.BackButtonBehavior backButtonBehavior,
                                                                    boolean shouldEndSession) {
        // Create card
        Card card = AlexaCardFactory.newSimpleCard(cardTitle, cardText);

        // Create speech
        PlainTextOutputSpeech speech = AlexaSpeechFactory.newPlainTextOutputSpeech(speechText);

        // Create text content
        BodyTemplate2.TextContent textContent = AlexaTextContentFactory.newTextContent2(primaryText, secondaryText, tertiaryText);

        // Create template
        BodyTemplate2 template = AlexaTemplateFactory.newBodyTemplate2(cardTitle, textContent, image, backgroundImage, backButtonBehavior);

        // Create render directive
        RenderTemplateDirective renderTemplateDirective = AlexaTemplateFactory.newRenderTemplateDirective(template);

        // Create list of directives
        List<Directive> directives = AlexaTemplateFactory.newListOfDirectives(renderTemplateDirective);

        // Return response
        return AlexaResponseFactory.newResponse(speech, card, directives, shouldEndSession);
    }
}