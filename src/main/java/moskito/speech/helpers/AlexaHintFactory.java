package moskito.speech.helpers;

import com.amazon.speech.speechlet.interfaces.core.Hint;
import com.amazon.speech.speechlet.interfaces.core.PlainTextHint;
import com.amazon.speech.speechlet.interfaces.core.directive.HintDirective;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hints are displayed in the bottom of the screen
 * Hints are displayed as 'Try "Alexa, ${yourHintText}"'
 *
 * @author Leo Ertuna
 */
public final class AlexaHintFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    private AlexaHintFactory() {

    }

    public static PlainTextHint newPlainTextHint(String hintText) {
        PlainTextHint plainTextHint = new PlainTextHint();
        plainTextHint.setText(hintText);

        LOGGER.info("Created Plain Text Hint: {" + plainTextHint + "}");
        return plainTextHint;
    }

    public static HintDirective newHintDirective(Hint hint) {
        HintDirective hintDirective = new HintDirective();
        hintDirective.setHint(hint);

        LOGGER.info("Created Hint Directive: {" + hintDirective + "}");
        return hintDirective;
    }

    public static HintDirective newHintDirective(String hintText) {
        return newHintDirective(newPlainTextHint(hintText));
    }
}
