package moskito.services.rest.basic_entities;

import moskito.services.Responses;

/**
 * Helper Enumerator
 */
public enum Status {
    GREEN("Green"), YELLOW("Yellow"), RED("Red"), OFF("Off");
    private String name;

    private Status(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getString() {
        return Responses.get("StatusColor" + name);
    }

    public static Status getValueByName(String name) {
        for (Status value : Status.values())
            if (value.getName().toLowerCase().equals(name.toLowerCase()))
                return value;
        throw new IllegalArgumentException("No such value with name: " + name);
    }
}