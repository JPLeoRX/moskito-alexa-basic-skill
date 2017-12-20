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
public final class AlexaCards {
    public static StandardCard getStandardCard(String title, String text, String smallImageUrl, String largeImageUrl) {
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

    public static SimpleCard getSimpleCard(String title, String text) {
        // Create card
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(text);

        return card;
    }
}