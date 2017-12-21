package moskito.services.images;

import com.amazon.speech.speechlet.interfaces.display.element.Image;
import com.amazon.speech.speechlet.interfaces.display.element.ImageInstance;

import java.util.ArrayList;
import java.util.List;

public class ImageHelper {
    public static Image getImage(String imageUrl, int imageWidth, int imageHeight) {
        ImageInstance imageInstance = new ImageInstance();
        imageInstance.setUrl(imageUrl);
        imageInstance.setHeightPixels(imageHeight);
        imageInstance.setWidthPixels(imageWidth);

        List<ImageInstance> imageSources = new ArrayList<>();
        imageSources.add(imageInstance);

        Image image = new Image();
        image.setSources(imageSources);

        return image;
    }
}