package moskito.speech.factories;

import com.amazon.speech.speechlet.interfaces.display.element.PlainText;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlexaTextFieldFactoryTest {
    @Test
    public void newPlainText() {
        String s1 = "Text 1";
        PlainText t1 = AlexaTextFieldFactory.newPlainText(s1);

        Assert.assertEquals(s1, t1.getText());
    }
}