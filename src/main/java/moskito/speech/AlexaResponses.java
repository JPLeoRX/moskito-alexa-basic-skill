package moskito.speech;

import com.amazon.speech.speechlet.SpeechletResponse;

/**
 * Helper methods to deal with different responses
 *
 * Creates new Ask and Tell responses
 *
 * @author Leo Ertuna
 */
public final class AlexaResponses {
    public static SpeechletResponse getTellResponse(String cardTitle, String speechText) {
        return SpeechletResponse.newTellResponse(
                AlexaSpeech.getPlainTextOutputSpeech(speechText),
                AlexaCards.getSimpleCard(cardTitle, speechText));
    }

    public static SpeechletResponse getTellResponse(String cardTitle, String cardText, String speechText) {
        return SpeechletResponse.newTellResponse(
                AlexaSpeech.getPlainTextOutputSpeech(speechText),
                AlexaCards.getSimpleCard(cardTitle, cardText));
    }

    public static SpeechletResponse getTellResponse(String cardTitle, String cardText, String cardSmallImageUrl, String cardLargeImageUrl, String speechText) {
        return SpeechletResponse.newTellResponse(
                AlexaSpeech.getPlainTextOutputSpeech(speechText),
                AlexaCards.getStandardCard(cardTitle, cardText, cardSmallImageUrl, cardLargeImageUrl));
    }

    public static SpeechletResponse getAskResponse(String cardTitle, String speechText) {
        return SpeechletResponse.newAskResponse(
                AlexaSpeech.getPlainTextOutputSpeech(speechText),
                AlexaSpeech.getReprompt(speechText),
                AlexaCards.getSimpleCard(cardTitle, speechText));
    }

    public static SpeechletResponse getAskResponse(String cardTitle, String cardText, String speechText) {
        return SpeechletResponse.newAskResponse(
                AlexaSpeech.getPlainTextOutputSpeech(speechText),
                AlexaSpeech.getReprompt(speechText),
                AlexaCards.getSimpleCard(cardTitle, cardText));
    }

    public static SpeechletResponse getAskResponse(String cardTitle, String cardText, String cardSmallImageUrl, String cardLargeImageUrl, String speechText) {
        return SpeechletResponse.newAskResponse(
                AlexaSpeech.getPlainTextOutputSpeech(speechText),
                AlexaSpeech.getReprompt(speechText),
                AlexaCards.getStandardCard(cardTitle, cardText, cardSmallImageUrl, cardLargeImageUrl));
    }
}