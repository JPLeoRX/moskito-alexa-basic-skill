package moskito.services.rest.basic_entities;

/**
 * Helper Enumerator
 */
public enum Status {
    GREEN("green"), YELLOW("yellow"), RED("red"), OFF("off");
    private String name;

    private Status(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Status getValueByName(String name) {
        for (Status value : Status.values())
            if (value.getName().equals(name.toLowerCase()))
                return value;
        throw new IllegalArgumentException("No such value with name: " + name);
    }
}