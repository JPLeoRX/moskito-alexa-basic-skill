package moskito.services.rest;

import moskito.services.MoskitoHomeRestURLs;
import moskito.services.rest.helpers.ParserJSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoskitoHomeTokenVerification {
    private static final Logger LOGGER = LogManager.getLogger();

    private boolean valid;

    public MoskitoHomeTokenVerification(String accessToken) {
        this.read(accessToken);
        LOGGER.info("Created {" + this + "}");

    }

    private void read(String accessToken) {
        ParserJSON parserJSON = new ParserJSON(MoskitoHomeRestURLs.TOKEN_VERIFICATION_URL.replace("${accessToken}", accessToken));
        valid = parserJSON.getJsonObject().getBoolean("valid");
    }

    public boolean isValid() {
        return valid;
    }
}
