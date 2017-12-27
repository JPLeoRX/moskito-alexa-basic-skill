package moskito.speech.helpers;

import com.amazon.speech.speechlet.interfaces.core.Hint;
import com.amazon.speech.speechlet.interfaces.core.PlainTextHint;
import com.amazon.speech.speechlet.interfaces.core.directive.HintDirective;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helper methods to create new Hints
 *
 * Hints are displayed in the bottom of the screen
 * Hints are displayed as 'Try "Alexa, ${yourHintText}"'
 *
 * Creates {@link PlainTextHint} and {@link HintDirective}
 *
 * @author Leo Ertuna
 */
public final class AlexaHintFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    private AlexaHintFactory() {

    }

    /**
     * Creates a Plain Text Hint, hint from the given text
     * @param hintText text of the hint
     * @return plain text hint
     */
    public static PlainTextHint newPlainTextHint(String hintText) {
        PlainTextHint plainTextHint = new PlainTextHint();
        plainTextHint.setText(hintText);

        LOGGER.info("Created Plain Text Hint: {" + plainTextHint + "}");
        return plainTextHint;
    }

    /**
     * Creates a Hint Directive, similar to Render Template Directive {@link com.amazon.speech.speechlet.interfaces.display.directive.RenderTemplateDirective}
     * @param hint hint of the directive, usually a {@link PlainTextHint}
     * @return hint directive
     */
    public static HintDirective newHintDirective(Hint hint) {
        HintDirective hintDirective = new HintDirective();
        hintDirective.setHint(hint);

        LOGGER.info("Created Hint Directive: {" + hintDirective + "}");
        return hintDirective;
    }

    /**
     * Creates a Hint Directive {@link #newHintDirective(Hint)} with Plain Text Hint from given text {@link #newPlainTextHint(String)}
     * @param hintText text of the hint
     * @return hint directive
     */
    public static HintDirective newHintDirective(String hintText) {
        return newHintDirective(newPlainTextHint(hintText));
    }
}