package moskito.speech.factories;

import com.amazon.speech.speechlet.interfaces.display.element.Image;
import com.amazon.speech.speechlet.interfaces.display.element.ImageInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods to create new images that can be used with Render Templates
 *
 * Creates {@link Image}
 *
 * @author Leo Ertuna
 */
public final class AlexaImageFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    private AlexaImageFactory() {

    }

    /**
     * Creates a new image
     * @param imageUrl url to the image
     * @param imageWidth width of the image
     * @param imageHeight height of the image
     * @return image
     */
    public static Image newImage(String imageUrl, int imageWidth, int imageHeight) {
        // Create new Image Instance
        ImageInstance imageInstance = new ImageInstance();
        imageInstance.setUrl(imageUrl);
        imageInstance.setHeightPixels(imageHeight);
        imageInstance.setWidthPixels(imageWidth);

        // Create a lis of Image Sources
        List<ImageInstance> imageSources = new ArrayList<>();
        imageSources.add(imageInstance);

        // Create new Image
        Image image = new Image();
        image.setSources(imageSources);

        LOGGER.info("Created Image: {" + image + "}");
        return image;
    }

    /**
     * Creates a new image for status object. It uses default size of the picture 75x75
     * @param imageUrl url of the image
     * @return image
     */
    public static Image newStatusImage(String imageUrl) {
        return newImage(imageUrl, 75, 75);
    }
}