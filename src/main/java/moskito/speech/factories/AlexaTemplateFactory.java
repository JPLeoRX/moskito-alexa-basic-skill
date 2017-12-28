package moskito.speech.factories;

import com.amazon.speech.speechlet.Directive;
import com.amazon.speech.speechlet.interfaces.display.directive.RenderTemplateDirective;
import com.amazon.speech.speechlet.interfaces.display.element.Image;
import com.amazon.speech.speechlet.interfaces.display.template.BodyTemplate2;
import com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1;
import com.amazon.speech.speechlet.interfaces.display.template.Template;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods to create new templates, classes derived from {@link Template}
 *
 * Creates body templates: {@link BodyTemplate2)
 * Creates list templates: {@link ListTemplate1}
 *
 * Also has methods to create {@link RenderTemplateDirective} and {@link List<Directive>}
 *
 * @author Leo Ertuna
 */
public final class AlexaTemplateFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    private AlexaTemplateFactory() {

    }



    // Templates
    //------------------------------------------------------------------------------------------------------------------
    public static BodyTemplate2 newBodyTemplate2(String title, BodyTemplate2.TextContent textContent, Image image, Image backgroundImage, Template.BackButtonBehavior backButtonBehavior) {
        BodyTemplate2 template = new BodyTemplate2();

        template.setTitle(title);
        template.setTextContent(textContent);
        template.setImage(image);
        template.setBackgroundImage(backgroundImage);
        template.setBackButtonBehavior(backButtonBehavior);

        LOGGER.info("Created Body Template 2: {" + template + "}");
        return template;
    }

    public static ListTemplate1 newListTemplate1(String title, List<ListTemplate1.ListItem> listItems, Image backgroundImage, Template.BackButtonBehavior backButtonBehavior) {
        ListTemplate1 template = new ListTemplate1();

        template.setTitle(title);
        template.setListItems(listItems);
        template.setBackgroundImage(backgroundImage);
        template.setBackButtonBehavior(backButtonBehavior);

        LOGGER.info("Created List Template 1: {" + template + "}");
        return template;
    }
    //------------------------------------------------------------------------------------------------------------------



    // Others
    //------------------------------------------------------------------------------------------------------------------
    public static RenderTemplateDirective newRenderTemplateDirective(Template template) {
        RenderTemplateDirective renderTemplateDirective = new RenderTemplateDirective();
        renderTemplateDirective.setTemplate(template);

        LOGGER.info("Created Render Template Directive: {" + renderTemplateDirective + "}");
        return renderTemplateDirective;
    }

    public static List<Directive> newListOfDirectives(Directive ... directivesArray) {
        List<Directive> directivesList = new ArrayList<>();
        for(Directive directive : directivesArray)
            directivesList.add(directive);

        LOGGER.info("Created List of Directives: {" + directivesList + "}");
        return directivesList;
    }
    //------------------------------------------------------------------------------------------------------------------
}