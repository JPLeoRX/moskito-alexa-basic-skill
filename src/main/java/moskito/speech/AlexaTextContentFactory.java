package moskito.speech;

import com.amazon.speech.speechlet.interfaces.display.template.BodyTemplate1;
import com.amazon.speech.speechlet.interfaces.display.element.TripleTextContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helper methods to deal with text contents, classes derived from {@link TripleTextContent}
 *
 * @author Leo Ertuna
 */
public final class AlexaTextContentFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    private AlexaTextContentFactory() {

    }

    public static BodyTemplate1.TextContent getTextContent1(String primaryText) {
        BodyTemplate1.TextContent textContent = new BodyTemplate1.TextContent();

        textContent.setPrimaryText(AlexaTextFieldFactory.newPlainText(primaryText));

        LOGGER.info("Created: {" + textContent + "}");
        return textContent;
    }
}