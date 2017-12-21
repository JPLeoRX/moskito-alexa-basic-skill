package moskito.speech;

import com.amazon.speech.ui.Image;
import com.amazon.speech.ui.SimpleCard;
import com.amazon.speech.ui.StandardCard;

/**
 * Helper methods to deal with cards
 *
 * Creates Standard Cards(title-text-image) and Simple Cards (title-text)
 *
 * @author Leo Ertuna
 */
public final class AlexaCardFactory {
    /**
     * Standard Card contains title, text, and image (can be displayed as small and large version)
     * @param title
     * @param text
     * @param smallImageUrl
     * @param largeImageUrl
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

        return card;
    }

    /**
     * Simple Card contains only title and text
     * @param title
     * @param text
     * @return simple card
     */
    public static SimpleCard newSimpleCard(String title, String text) {
        // Create card
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(text);

        return card;
    }
}