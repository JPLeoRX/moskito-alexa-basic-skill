package moskito.services.rest;

public final class Threshold {
    private String name;
    private Status status;
    private double value;

    public Threshold(String name, Status status, String value) {
        this.name = name;
        this.status = status;
        this.value = Double.valueOf(value);
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status.getName();
    }

    public String getValue() {
        return StringHelper.trimDouble(value);
    }

    @Override
    public String toString() {
        return "Threshold: name=" + name + ", status=" + status + ", value=" + value;
    }
}