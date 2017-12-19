package moskito.services;

import java.util.Locale;
import java.util.ResourceBundle;

public class Responses {
    private static final String BASE_NAME = "ResponsesBundle";
    private static ResourceBundle responsesBundle;

    public static void initialize(Locale locale) {
        // If it wasn't yet setup, or if the locale of the device has changed after last setup
        if ((responsesBundle == null) || (!responsesBundle.getLocale().equals(locale)))
            responsesBundle = ResourceBundle.getBundle(BASE_NAME, locale);
    }

    public static String get(String key) {
        return responsesBundle.getString(key);
    }
}
