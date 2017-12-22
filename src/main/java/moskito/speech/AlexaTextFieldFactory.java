package moskito.speech;

import com.amazon.speech.speechlet.interfaces.display.element.PlainText;
import com.amazon.speech.speechlet.interfaces.display.element.RichText;

public final class AlexaTextFieldFactory {
    private AlexaTextFieldFactory() {

    }

    /**
     *
     * @param s
     * @return
     */
    public static PlainText newPlainText(String s) {
        PlainText plainText = new PlainText();
        plainText.setText(s);
        return plainText;
    }

    /**
     *
     * @param s
     * @return
     */
    public static RichText newRichText(String s) {
        RichText richText = new RichText();
        richText.setText(s);
        return richText;
    }
}