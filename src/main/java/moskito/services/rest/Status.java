package moskito.services.rest;

/**
 * Helper Enumerator
 */
enum Status {
    GREEN("green"), RED("red");
    private String name;

    private Status(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Status getValueByName(String name) {
        for (Status value : Status.values())
            if (value.getName().equals(name))
                return value;
        throw new IllegalArgumentException("No such value with name: " + name);
    }
}
