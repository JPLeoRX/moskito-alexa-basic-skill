package moskito.services.rest.basic_entities;

import moskito.services.rest.helpers.StringHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Threshold Object
 *
 * Stores all data that might be needed to use Moskito's Threshold
 *
 * Store the data read from JSON in this object
 *
 * Read the data from this object when forming Alexa's response
 *
 * @author Leo Ertuna
 */
public final class Threshold {
    private static final Logger LOGGER = LogManager.getLogger();

    private String name;
    private Status status;
    private String value;

    public Threshold(String name, Status status, String value) {
        this.name = name;
        this.status = status;
        this.value = value;

        LOGGER.info("Created {" + this + "}");
    }

    // Getters
    //------------------------------------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public String getStatusName() {
        return status.getName();
    }

    public String getStatusString() {
        return status.getString();
    }

    public String getStatusImageUrl() {
        return status.getImageUrl();
    }

    public Status getStatus() {
        return status;
    }

    public String getValue() {
        return StringHelper.trimValue(value);
    }
    //------------------------------------------------------------------------------------------------------------------



    // Others
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Threshold: name=" + name + ", status=" + status + ", value=" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Threshold threshold = (Threshold) o;
        return Objects.equals(name, threshold.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    //------------------------------------------------------------------------------------------------------------------
}