package moskito.services.rest;

public class Threshold {
    private String name;
    private Status status;
    private String value;

    public Threshold(String name, Status status, String value) {
        this.name = name;
        this.status = status;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Threshold{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", value='" + value + '\'' +
                '}';
    }
}
