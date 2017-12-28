package moskito.speech.factories;

import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlexaSpeechFactoryTest {

    @Test
    public void newPlainTextOutputSpeech() {
        String speechText = "Speech Text";

        PlainTextOutputSpeech s1 = AlexaSpeechFactory.newPlainTextOutputSpeech(speechText);
        assertEquals(speechText, s1.getText());
    }

    @Test
    public void newReprompt() {
        String speechText = "Speech Text";

        Reprompt s1 = AlexaSpeechFactory.newReprompt(speechText);
        assertEquals(speechText, ((PlainTextOutputSpeech) s1.getOutputSpeech()).getText());
    }
}