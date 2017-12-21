package moskito.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class Localization {
    private static final Logger LOGGER = LogManager.getLogger();

    private String baseName;
    private ResourceBundle resourceBundle;

    public Localization(String baseName) {
        this.baseName = baseName;
        LOGGER.info("Created {" + this + "}");
    }

    public void load(Locale locale) {
        // If it wasn't yet setup, or if the locale has changed after last setup
        if ((resourceBundle == null) || (!resourceBundle.getLocale().equals(locale))) {
            resourceBundle = ResourceBundle.getBundle(baseName, locale);
            LOGGER.info("Updated: {" + this + "}");
        }

        else {
            LOGGER.info("No updates needed for: {" + this + "}");
        }
    }

    public String get(String key) {
        return resourceBundle.getString(key);
    }

    @Override
    public String toString() {
        return "Localization: " + " baseName=" + baseName + ", resourceBundle=" + resourceBundle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Localization)) return false;
        Localization that = (Localization) o;
        return Objects.equals(baseName, that.baseName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(baseName);
    }
}