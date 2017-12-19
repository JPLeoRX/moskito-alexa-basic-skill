package moskito.services;

import java.util.ResourceBundle;

public class Responses {
    public static ResourceBundle responseBundle;

    public static String getString(String key) {
        return responseBundle.getString(key);
    }
}
