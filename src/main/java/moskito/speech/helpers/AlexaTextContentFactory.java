package moskito.speech.helpers;

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

    /**
     * Body Template 2
     * @param primaryText primary text
     * @return new text content for body template 2
     */
    public static BodyTemplate2.TextContent newTextContent2(String primaryText) {
        return newTextContent2(primaryText, "", "");
    }

    /**
     * Body Template 2
     * @param primaryText primary text
     * @param secondaryText secondary text
     * @param tertiaryText tertiary text
     * @return new text content for body template 2
     */
    public static BodyTemplate2.TextContent newTextContent2(String primaryText, String secondaryText, String tertiaryText) {
        // Create new text content
        BodyTemplate2.TextContent textContent = new BodyTemplate2.TextContent();

        // Set texts
        textContent.setPrimaryText(AlexaTextFieldFactory.newPlainText(primaryText));
        textContent.setSecondaryText(AlexaTextFieldFactory.newPlainText(secondaryText));
        textContent.setTertiaryText(AlexaTextFieldFactory.newPlainText(tertiaryText));

        LOGGER.info("Created Text Content 2: {" + textContent + "}");
        return textContent;
    }

    /**
     * List Template 1
     * @param primaryText primary text
     * @return new text content for list template 1
     */
    public static ListTemplate1.ListItem.TextContent newTextContentList1(String primaryText) {
        return newTextContentList1(primaryText, "", "");
    }

    /**
     * List Template 1
     * @param primaryText primary text
     * @param secondaryText secondary text
     * @return new text content for list template 1
     */
    public static ListTemplate1.ListItem.TextContent newTextContentList1(String primaryText, String secondaryText) {
        return newTextContentList1(primaryText, secondaryText, "");
    }

    /**
     * List Template 1
     * @param primaryText primary text
     * @param secondaryText secondary text
     * @param tertiaryText tertiary text
     * @return new text content for list template 1
     */
    public static ListTemplate1.ListItem.TextContent newTextContentList1(String primaryText, String secondaryText, String tertiaryText) {
        // Create new text content
        ListTemplate1.ListItem.TextContent textContent = new ListTemplate1.ListItem.TextContent();

        // Set texts
        textContent.setPrimaryText(AlexaTextFieldFactory.newPlainText(primaryText));
        textContent.setSecondaryText(AlexaTextFieldFactory.newPlainText(secondaryText));
        textContent.setTertiaryText(AlexaTextFieldFactory.newPlainText(tertiaryText));

        LOGGER.info("Created Text Content List 1: {" + textContent + "}");
        return textContent;
    }
}