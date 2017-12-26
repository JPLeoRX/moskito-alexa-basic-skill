package moskito.speech.helpers;

import com.amazon.speech.speechlet.interfaces.display.element.PlainText;
import com.amazon.speech.speechlet.interfaces.display.element.RichText;
import com.amazon.speech.speechlet.interfaces.display.element.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helper methods to create new texts, classes derived from {@link TextField}
 *
 * Creates {@link PlainText} and {@link RichText}
 *
 * @author Leo Ertuna
 */
public final class AlexaTextFieldFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    private AlexaTextFieldFactory() {

    }

    /**
     * Plain text, mostly used in current templates
     * @param s text string
     * @return plain text
     */
    public static PlainText newPlainText(String s) {
        PlainText plainText = new PlainText();
        plainText.setText(s);

        LOGGER.info("Created: {" + plainText + "}");
        return plainText;
    }

    /**
     * Rich text (not currently used)
     * @param s text string
     * @return rich text
     */
    public static RichText newRichText(String s) {
        RichText richText = new RichText();
        richText.setText(s);

        LOGGER.info("Created: {" + richText + "}");
        return richText;
    }
}