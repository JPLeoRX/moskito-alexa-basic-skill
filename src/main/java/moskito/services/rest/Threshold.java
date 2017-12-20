package moskito.services.rest;

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
    private String name;
    private Status status;
    private String value;

    public Threshold(String name, Status status, String value) {
        this.name = name;
        this.status = status;
        this.value = value;
    }

    // Getters
    //------------------------------------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public String getStatus() {
        return status.getName();
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
        if (!(o instanceof Threshold)) return false;
        Threshold threshold = (Threshold) o;
        return Objects.equals(name, threshold.name) &&
                status == threshold.status &&
                Objects.equals(value, threshold.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status, value);
    }
    //------------------------------------------------------------------------------------------------------------------
}