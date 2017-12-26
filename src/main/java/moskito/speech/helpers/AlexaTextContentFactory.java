package moskito.speech.helpers;

import com.amazon.speech.speechlet.interfaces.display.template.BodyTemplate1;
import com.amazon.speech.speechlet.interfaces.display.element.TripleTextContent;
import com.amazon.speech.speechlet.interfaces.display.template.BodyTemplate2;
import com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1;
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

    public static BodyTemplate1.TextContent newTextContent1(String primaryText) {
        BodyTemplate1.TextContent textContent = new BodyTemplate1.TextContent();

        textContent.setPrimaryText(AlexaTextFieldFactory.newPlainText(primaryText));

        LOGGER.info("Created Text Content 1: {" + textContent + "}");
        return textContent;
    }

    public static BodyTemplate2.TextContent newTextContent2(String primaryText, String secondaryText, String tertiaryText) {
        BodyTemplate2.TextContent textContent = new BodyTemplate2.TextContent();

        textContent.setPrimaryText(AlexaTextFieldFactory.newPlainText(primaryText));
        textContent.setSecondaryText(AlexaTextFieldFactory.newPlainText(secondaryText));
        textContent.setTertiaryText(AlexaTextFieldFactory.newPlainText(tertiaryText));

        LOGGER.info("Created Text Content 2: {" + textContent + "}");
        return textContent;
    }

    public static ListTemplate1.ListItem.TextContent newTextContentList1(String primaryText, String secondaryText, String tertiaryText) {
        ListTemplate1.ListItem.TextContent textContent = new ListTemplate1.ListItem.TextContent();

        textContent.setPrimaryText(AlexaTextFieldFactory.newPlainText(primaryText));
        textContent.setSecondaryText(AlexaTextFieldFactory.newPlainText(secondaryText));
        textContent.setTertiaryText(AlexaTextFieldFactory.newPlainText(tertiaryText));

        LOGGER.info("Created Text Content List 1: {" + textContent + "}");
        return textContent;
    }
}