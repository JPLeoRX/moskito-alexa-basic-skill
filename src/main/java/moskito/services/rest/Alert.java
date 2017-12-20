package moskito.services.rest;

public class Alert {
    private String name;
    private Status statusNew;
    private Status statusOld;
    private String time;

    public Alert(String name, Status statusNew, Status statusOld, String time) {
        this.name = name;
        this.statusNew = statusNew;
        this.statusOld = statusOld;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Status getStatusNew() {
        return statusNew;
    }

    public Status getStatusOld() {
        return statusOld;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Alert: " + "name=" + name + ", statusNew=" + statusNew + ", statusOld=" + statusOld + ", time=" + time;
    }
}
