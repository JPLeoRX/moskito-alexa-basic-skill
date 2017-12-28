package moskito.speech.factories;

import com.amazon.speech.speechlet.interfaces.display.element.Image;
import com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1;
import com.amazon.speech.speechlet.interfaces.display.template.ListTemplate2;

/**
 * Helper methods to create new list items
 *  @author Leo Ertuna
 */
public final class AlexaListItemFactory {
    public static ListTemplate1.ListItem newListItem1(ListTemplate1.ListItem.TextContent textContent, Image image) {
        ListTemplate1.ListItem item = new ListTemplate1.ListItem();
        item.setTextContent(textContent);
        item.setImage(image);
        return item;
    }

    public static ListTemplate2.ListItem newListItem2(ListTemplate2.ListItem.TextContent textContent, Image image) {
        ListTemplate2.ListItem item = new ListTemplate2.ListItem();
        item.setTextContent(textContent);
        item.setImage(image);
        return item;
    }
}