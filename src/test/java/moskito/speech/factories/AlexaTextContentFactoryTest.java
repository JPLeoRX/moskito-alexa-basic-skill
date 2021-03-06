package moskito.speech.factories;

import com.amazon.speech.speechlet.interfaces.display.template.BodyTemplate2;
import com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlexaTextContentFactoryTest {

    @Test
    public void newTextContent2() {
        String primaryText1 = "Primary Text 1";
        String secondaryText1 = "Secondary Text 1";
        String tertiaryText1 = "Tertiary Text 1";

        BodyTemplate2.TextContent t1 = AlexaTextContentFactory.newTextContent2(primaryText1);
        assertEquals(primaryText1, t1.getPrimaryText().getText());
        assertEquals("", t1.getSecondaryText().getText());
        assertEquals("", t1.getTertiaryText().getText());

        BodyTemplate2.TextContent t2 = AlexaTextContentFactory.newTextContent2(primaryText1, secondaryText1);
        assertEquals(primaryText1, t2.getPrimaryText().getText());
        assertEquals(secondaryText1, t2.getSecondaryText().getText());
        assertEquals("", t2.getTertiaryText().getText());

        BodyTemplate2.TextContent t3 = AlexaTextContentFactory.newTextContent2(primaryText1, secondaryText1, tertiaryText1);
        assertEquals(primaryText1, t3.getPrimaryText().getText());
        assertEquals(secondaryText1, t3.getSecondaryText().getText());
        assertEquals(tertiaryText1, t3.getTertiaryText().getText());
    }

    @Test
    public void newTextContentList1() {
        String primaryText1 = "Primary Text 1";
        String secondaryText1 = "Secondary Text 1";
        String tertiaryText1 = "Tertiary Text 1";

        ListTemplate1.ListItem.TextContent t1 = AlexaTextContentFactory.newTextContentList1(primaryText1);
        assertEquals(primaryText1, t1.getPrimaryText().getText());
        assertEquals("", t1.getSecondaryText().getText());
        assertEquals("", t1.getTertiaryText().getText());

        ListTemplate1.ListItem.TextContent t2 = AlexaTextContentFactory.newTextContentList1(primaryText1, secondaryText1);
        assertEquals(primaryText1, t2.getPrimaryText().getText());
        assertEquals(secondaryText1, t2.getSecondaryText().getText());
        assertEquals("", t2.getTertiaryText().getText());

        ListTemplate1.ListItem.TextContent t3 = AlexaTextContentFactory.newTextContentList1(primaryText1, secondaryText1, tertiaryText1);
        assertEquals(primaryText1, t3.getPrimaryText().getText());
        assertEquals(secondaryText1, t3.getSecondaryText().getText());
        assertEquals(tertiaryText1, t3.getTertiaryText().getText());
    }
}