package moskito.speech.helpers;

import com.amazon.speech.ui.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helper methods to create new cards, classes derived from {@link Card}
 *
 * Creates {@link StandardCard}(title-text-image), {@link SimpleCard} (title-text) and {@link LinkAccountCard}
 *
 * @author Leo Ertuna
 */
public final class AlexaCardFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    private AlexaCardFactory() {

    }

    /**
     * Standard Card contains title, text, and image (can be displayed as small and large version)
     * @param title card title
     * @param text card text
     * @param smallImageUrl url to the small image of the card (shown on smaller displays)
     * @param largeImageUrl url to the large image of the card (shown on larger displays)
     * @return standard card
     */
    public static StandardCard newStandardCard(String title, String text, String smallImageUrl, String largeImageUrl) {
        // Create image
        Image image = new Image();
        image.setSmallImageUrl(smallImageUrl);
        image.setLargeImageUrl(largeImageUrl);

        // Create card
        StandardCard card = new StandardCard();
        card.setTitle(title);
        card.setText(text);
        card.setImage(image);

        LOGGER.info("Created Standard Card: {" + card + "}");
        return card;
    }

    /**
     * Simple Card contains only title and text
     * @param title card title
     * @param text card text
     * @return simple card
     */
    public static SimpleCard newSimpleCard(String title, String text) {
        // Create card
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(text);

        LOGGER.info("Created Simple Card: {" + card + "}");
        return card;
    }

    /**
     * Link Account Card is a special card that should be shown if the user hasn't linked their account yet
     * @return link account card
     */
    public static LinkAccountCard newLinkAccountCard() {
        // Create card
        LinkAccountCard card = new LinkAccountCard();

        LOGGER.info("Created Link Account Card: {" + card + "}");
        return card;
    }
}