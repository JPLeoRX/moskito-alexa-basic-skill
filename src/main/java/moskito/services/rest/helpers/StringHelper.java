package moskito.services.rest.helpers;

import moskito.services.Responses;

public final class StringHelper {
    /**
     * Convert camel cased string into separate words
     * @param camelCaseString
     * @return
     */
    public static String splitCamelCase(String camelCaseString) {
        return camelCaseString.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }

    /**
     * Convert month from integer to string
     * @param month integer from 1 to 12
     * @return verbal representation of the month
     */
    public static String monthString(int month) {
        return Responses.get("m" + String.format("%02d", month));
    }

    /**
     * Convert timestamp returned by moskito rest api into a readable form
     * @param timestamp timestamp from moskito rest api
     * @return date MONTH DAY, time HOUR:MINUTE:SECOND
     */
    public static String trimDateAndTimeForSpeech(String timestamp) {
        String[] sp = timestamp.split("T");
        String[] date = sp[0].split("-");
        String[] time = sp[1].split(":");

        String month = date[1];
        String day = date[2];
        String hour = time[0];
        String minute = time[1];
        String second = time[2].split(",")[0];

        return Responses.get("HelperDate") + " " + monthString(Integer.valueOf(month)) + " " + day + ", " + Responses.get("HelperTime") + " " + hour + ":" + minute + ":" + second;
    }

    /**
     * Convert timestamp returned by moskito rest api into a readable form
     * @param timestamp timestamp from moskito rest api
     * @return DAY-MONTH HOUR:MINUTE
     */
    public static String trimDateAndTimeForDisplay(String timestamp) {
        String[] sp = timestamp.split("T");
        String[] date = sp[0].split("-");
        String[] time = sp[1].split(":");

        String month = date[1];
        String day = date[2];
        String hour = time[0];
        String minute = time[1];

        return day + "-" + monthString(Integer.valueOf(month)).substring(0, 3) + " " + hour + ":" + minute;
    }

    private static String trimDouble(double a) {
        return String.valueOf((int) a);
    }

    public static String trimValue(String value) {
        if (value.equals("none yet"))
            return "none";
        return StringHelper.trimDouble(Double.valueOf(value));
    }
}