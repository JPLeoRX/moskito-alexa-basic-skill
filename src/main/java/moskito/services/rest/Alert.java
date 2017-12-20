package moskito.services.rest;

/**
 * Alert Object
 *
 * Stores all data that might be needed to use Moskito's Alert
 *
 * Store the data read from JSON in this object
 *
 * Read the data from this object when forming Alexa's response
 *
 * @author Leo Ertuna
 */
public class Alert {
    private String thresholdName;
    private Status statusNew;
    private String valueNew;
    private Status statusOld;
    private String valueOld;
    private String timestamp;

    public Alert(String thresholdName, Status statusNew, String valueNew, Status statusOld, String valueOld, String timestamp) {
        this.thresholdName = thresholdName;
        this.statusNew = statusNew;
        this.valueNew = valueNew;
        this.statusOld = statusOld;
        this.valueOld = valueOld;
        this.timestamp = timestamp;
    }

    public String getThresholdName() {
        return thresholdName;
    }

    public String getStatusNew() {
        return statusNew.getName();
    }

    public String getStatusOld() {
        return statusOld.getName();
    }

    public String getValueNew() {
        if (valueNew.equals("none yet"))
            return "none";
        return StringHelper.trimDouble(Double.valueOf(valueNew));
    }

    public String getValueOld() {
        if (valueOld.equals("none yet"))
            return "none";
        return StringHelper.trimDouble(Double.valueOf(valueOld));
    }

    public String getTime() {
        return StringHelper.trimDateAndTime(timestamp);
    }

    @Override
    public String toString() {
        return "Alert: " + "name=" + thresholdName + ", statusNew=" + statusNew + ", statusOld=" + statusOld + ", time=" + timestamp;
    }
}
