package moskito.services;

import java.util.Locale;

public class Responses {
    private static final String BASE_NAME = "ResponsesBundle";
    private static final Localization LOCALIZATION = new Localization(BASE_NAME);

    public static void initialize(Locale locale) {
        LOCALIZATION.load(locale);
    }

    public static String get(String key) {
        return LOCALIZATION.get(key);
    }
}