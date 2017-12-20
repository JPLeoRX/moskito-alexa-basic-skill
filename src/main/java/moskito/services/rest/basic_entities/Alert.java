package moskito.services.rest.basic_entities;

import moskito.services.rest.helpers.StringHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

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
public final class Alert {
    private static final Logger LOGGER = LogManager.getLogger();

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
        
        LOGGER.info("Created {" + this + "}");
    }

    // Getters
    //------------------------------------------------------------------------------------------------------------------
    public String getThresholdName() {
        return thresholdName;
    }

    public String getStatusNew() {
        return statusNew.getName();
    }

    public String getValueNew() {
        return StringHelper.trimValue(valueNew);
    }

    public String getStatusOld() {
        return statusOld.getName();
    }

    public String getValueOld() {
        return StringHelper.trimValue(valueOld);
    }

    public String getTime() {
        return StringHelper.trimDateAndTime(timestamp);
    }
    //------------------------------------------------------------------------------------------------------------------



    // Others
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Alert: " + "name=" + thresholdName + ", statusNew=" + statusNew + ", statusOld=" + statusOld + ", time=" + timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alert)) return false;
        Alert alert = (Alert) o;
        return Objects.equals(thresholdName, alert.thresholdName) &&
                statusNew == alert.statusNew &&
                Objects.equals(valueNew, alert.valueNew) &&
                statusOld == alert.statusOld &&
                Objects.equals(valueOld, alert.valueOld) &&
                Objects.equals(timestamp, alert.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thresholdName, timestamp);
    }
    //------------------------------------------------------------------------------------------------------------------
}