package moskito.services;

import moskito.services.rest.helpers.ParserJSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class AmazonUser {
    private static final Logger LOGGER = LogManager.getLogger();

    private String name;
    private String email;

    public AmazonUser(String accessToken) {
        this.read(accessToken);
        LOGGER.info("Created {" + this + "}");
    }

    private void read(String accessToken) {
        ParserJSON parserJSON = new ParserJSON("https://api.amazon.com/user/profile",
                "Authorization", "bearer " + accessToken);
        this.name = parserJSON.getJsonObject().get("name").toString();
        this.email = parserJSON.getJsonObject().get("email").toString();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "AmazonUser:  name={" + name + "}, email={" + email + "}";
    }
}