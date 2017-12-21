package moskito.speech;

import com.amazon.speech.speechlet.Directive;
import com.amazon.speech.speechlet.interfaces.display.directive.RenderTemplateDirective;
import com.amazon.speech.speechlet.interfaces.display.element.Image;
import com.amazon.speech.speechlet.interfaces.display.element.PlainText;
import com.amazon.speech.speechlet.interfaces.display.template.BodyTemplate1;
import com.amazon.speech.speechlet.interfaces.display.template.Template;

import java.util.ArrayList;
import java.util.List;

public class AlexaScreen {
    public static PlainText getScreenText(String s) {
        PlainText plainText = new PlainText();
        plainText.setText(s);
        return plainText;
    }

    public static BodyTemplate1.TextContent getTextContent1(String primaryText) {
        BodyTemplate1.TextContent textContent = new BodyTemplate1.TextContent();
        textContent.setPrimaryText(getScreenText(primaryText));
        return textContent;
    }

    public static BodyTemplate1 getBodyTemplate1(String token, String title, BodyTemplate1.TextContent textContent, Image image, Template.BackButtonBehavior backButtonBehavior) {
        BodyTemplate1 bodyTemplate1 = new BodyTemplate1();
        bodyTemplate1.setToken(token);
        bodyTemplate1.setTitle(title);
        bodyTemplate1.setTextContent(textContent);
        bodyTemplate1.setBackgroundImage(image);
        bodyTemplate1.setBackButtonBehavior(backButtonBehavior);
        return bodyTemplate1;
    }

    public static RenderTemplateDirective getRenderTemplateDirective(Template template) {
        RenderTemplateDirective renderTemplateDirective = new RenderTemplateDirective();
        renderTemplateDirective.setTemplate(template);
        return renderTemplateDirective;
    }

    public static List<Directive> getListOfDirectives(Directive ... directivesArray) {
        List<Directive> directivesList = new ArrayList<>();
        for(Directive directive : directivesArray)
            directivesList.add(directive);
        return directivesList;
    }
}