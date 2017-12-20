package moskito.services.rest.helpers;

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
        String monthString = "";
        switch (month) {
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break;
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
            default:
                monthString = "Invalid month";
                break;
        }

        return monthString;
    }

    /**
     * Convert timestamp returned by moskito rest api into a readable form
     * @param timestamp timestamp from moskito rest api
     * @return date MONTH DAY, time HOUR:MINUTE:SECOND
     */
    public static String trimDateAndTime(String timestamp) {
        String[] sp = timestamp.split("T");
        String[] date = sp[0].split("-");
        String[] time = sp[1].split(":");

        String month = date[1];
        String day = date[2];
        String hour = time[0];
        String minute = time[1];
        String second = time[2].split(",")[0];

        return "date " + monthString(Integer.valueOf(month)) + " " + day + ", time " + hour + ":" + minute + ":" + second;
    }

    public static String trimDouble(double a) {
        return String.valueOf((int) a);
    }

    public static String trimValue(String value) {
        if (value.equals("none yet"))
            return "none";
        return StringHelper.trimDouble(Double.valueOf(value));
    }
}