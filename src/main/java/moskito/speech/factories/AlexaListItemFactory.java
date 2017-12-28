package moskito.speech.factories;

import com.amazon.speech.speechlet.interfaces.display.element.Image;
import com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1;

public final class AlexaListItemFactory {
    public static ListTemplate1.ListItem newListItem1(ListTemplate1.ListItem.TextContent textContent, Image image) {
        ListTemplate1.ListItem item = new ListTemplate1.ListItem();
        item.setTextContent(textContent);
        item.setImage(image);
        return item;
    }
}