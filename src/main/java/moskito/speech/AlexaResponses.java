package moskito.speech;

import com.amazon.speech.speechlet.Directive;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.interfaces.display.directive.RenderTemplateDirective;
import com.amazon.speech.speechlet.interfaces.display.element.Image;
import com.amazon.speech.speechlet.interfaces.display.template.BodyTemplate2;
import com.amazon.speech.speechlet.interfaces.display.template.Template;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import moskito.services.images.ImageHelper;

import java.util.List;

/**
 * Helper methods to deal with different responses
 *
 * Creates new Ask and Tell responses
 *
 * @author Leo Ertuna
 */
public final class AlexaResponses {
    public static SpeechletResponse getResponse(OutputSpeech outputSpeech, Card card, List<Directive> directives, boolean shouldEndSession) {
        SpeechletResponse response = new SpeechletResponse();

        response.setOutputSpeech(outputSpeech);
        response.setCard(card);
        response.setDirectives(directives);
        response.setNullableShouldEndSession(shouldEndSession);

        return response;
    }

    public static SpeechletResponse getResponse(String cardTitle, String cardText, String speechText, String imageUrl, int imageWidth, int imageHeight, Template.BackButtonBehavior backButtonBehavior, boolean shouldEndSession) {
        // Create card
        Card card = AlexaCardFactory.newSimpleCard(cardTitle, cardText);

        // Create speech
        PlainTextOutputSpeech speech = AlexaSpeech.getPlainTextOutputSpeech(speechText);

        // Create text content
        BodyTemplate2.TextContent screenText = AlexaTextContentFactory.newTextContent2(speechText);

        // Create image content
        Image backgroundImage = ImageHelper.getImage(imageUrl, imageWidth, imageHeight);
        Image image = ImageHelper.getImage("http://burgershop-control.demo.moskito.org/moskito-control/img/smiley_green.png", 580, 580);

        // Create template
        Template template = AlexaScreen.getBodyTemplate2(cardTitle, cardTitle, screenText, backgroundImage, image, backButtonBehavior);

        // Create render directive
        RenderTemplateDirective renderTemplateDirective = AlexaScreen.getRenderTemplateDirective(template);

        // Create list of directives
        List<Directive> directives = AlexaScreen.getListOfDirectives(renderTemplateDirective);

        return AlexaResponses.getResponse(speech, card, directives, shouldEndSession);
    }

    public static SpeechletResponse getTellResponse(String cardTitle, String speechText) {
        return SpeechletResponse.newTellResponse(
                AlexaSpeech.getPlainTextOutputSpeech(speechText),
                AlexaCardFactory.newSimpleCard(cardTitle, speechText));
    }

    public static SpeechletResponse getTellResponse(String cardTitle, String cardText, String speechText) {
        return SpeechletResponse.newTellResponse(
                AlexaSpeech.getPlainTextOutputSpeech(speechText),
                AlexaCardFactory.newSimpleCard(cardTitle, cardText));
    }

    public static SpeechletResponse getTellResponse(String cardTitle, String cardText, String cardSmallImageUrl, String cardLargeImageUrl, String speechText) {
        return SpeechletResponse.newTellResponse(
                AlexaSpeech.getPlainTextOutputSpeech(speechText),
                AlexaCardFactory.newStandardCard(cardTitle, cardText, cardSmallImageUrl, cardLargeImageUrl));
    }

    public static SpeechletResponse getAskResponse(String cardTitle, String speechText) {
        return SpeechletResponse.newAskResponse(
                AlexaSpeech.getPlainTextOutputSpeech(speechText),
                AlexaSpeech.getReprompt(speechText),
                AlexaCardFactory.newSimpleCard(cardTitle, speechText));
    }

    public static SpeechletResponse getAskResponse(String cardTitle, String cardText, String speechText) {
        return SpeechletResponse.newAskResponse(
                AlexaSpeech.getPlainTextOutputSpeech(speechText),
                AlexaSpeech.getReprompt(speechText),
                AlexaCardFactory.newSimpleCard(cardTitle, cardText));
    }

    public static SpeechletResponse getAskResponse(String cardTitle, String cardText, String cardSmallImageUrl, String cardLargeImageUrl, String speechText) {
        return SpeechletResponse.newAskResponse(
                AlexaSpeech.getPlainTextOutputSpeech(speechText),
                AlexaSpeech.getReprompt(speechText),
                AlexaCardFactory.newStandardCard(cardTitle, cardText, cardSmallImageUrl, cardLargeImageUrl));
    }
}