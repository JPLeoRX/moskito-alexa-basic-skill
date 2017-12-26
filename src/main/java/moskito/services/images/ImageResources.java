package moskito.services.images;

import com.amazon.speech.speechlet.interfaces.display.element.Image;
import moskito.speech.helpers.AlexaImageFactory;

public enum ImageResources {
    ALEXA_LOGO ("https://s3.amazonaws.com/mkusters-images/alexa-logo.png", 640, 290),
    BODY_TEMPLATE_1("https://s3.amazonaws.com/mkusters-images/Body_Template_1.png", 1024, 600),
    BODY_TEMPLATE_2("https://s3.amazonaws.com/mkusters-images/Body_Template_2.png", 1024, 600),
    BODY_TEMPLATE_3("https://s3.amazonaws.com/mkusters-images/Body_Template_3.png", 1024, 600),
    BODY_TEMPLATE_6("https://s3.amazonaws.com/mkusters-images/Body_Template_6.png", 1024, 600),
    LIST_TEMPLATE_1("https://s3.amazonaws.com/mkusters-images/List_Template_1.png", 1024, 600),
    LIST_TEMPLATE_2("https://s3.amazonaws.com/mkusters-images/List_Template_2.png", 1024, 600),
    BACKGROUND("https://s3.amazonaws.com/mkusters-images/road_background.png", 1024, 600),
    PORTRAIT_SAMPLE("https://s3.amazonaws.com/mkusters-images/portrait.png", 192, 280),
    SQUARE_SAMPLE("https://s3.amazonaws.com/mkusters-images/square.png", 280, 280),
    SUPER_WIDE_RATIO_SAMPLE("https://s3.amazonaws.com/mkusters-images/16_9ratio.png", 372, 280),
    WIDE_RATIO_SAMPLE("https://s3.amazonaws.com/mkusters-images/4_3ratio.png", 498, 280),
    FOUR_COLORS("https://s3.amazonaws.com/mkusters-images/four-colors.png", 200, 200),
    VIDEO_EXAMPLE("https://s3.amazonaws.com/mkusters-images/video_screenshot.jpeg", 650, 381);

    private Image image;

    private ImageResources(final String stringURI, final int width, final int height) {
        this.image = AlexaImageFactory.newImage(stringURI, width, height);
    }

    public Image getImage() {
        return this.image;
    }
}