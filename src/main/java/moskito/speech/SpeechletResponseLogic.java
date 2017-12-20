package moskito.speech;

import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.*;

public interface SpeechletResponseLogic {
    // Response Logic
    //------------------------------------------------------------------------------------------------------------------
    SpeechletResponse getWelcomeResponse();

    SpeechletResponse getHelpResponse();

    SpeechletResponse getErrorResponse();
    //------------------------------------------------------------------------------------------------------------------



    // Helper Methods
    //------------------------------------------------------------------------------------------------------------------
    default PlainTextOutputSpeech getPlainTextOutputSpeech(String speechText) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);
        return speech;
    }

    default Reprompt getReprompt(OutputSpeech outputSpeech) {
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(outputSpeech);
        return reprompt;
    }

    default SpeechletResponse getTellResponse(String cardTitle, String cardText, String speechText) {
        SimpleCard card = SpeechletCards.getSimpleCard(cardTitle, cardText);
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    default SpeechletResponse getTellResponse(String cardTitle, String cardText, String cardSmallImageUrl, String cardLargeImageUrl, String speechText) {
        StandardCard card = SpeechletCards.getStandardCard(cardTitle, cardText, cardSmallImageUrl, cardLargeImageUrl);
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    default SpeechletResponse getAskResponse(String cardTitle, String speechText) {
        SimpleCard card = SpeechletCards.getSimpleCard(cardTitle, speechText);
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
        Reprompt reprompt = getReprompt(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
    //------------------------------------------------------------------------------------------------------------------
}