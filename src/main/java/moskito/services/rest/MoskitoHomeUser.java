package moskito.services.rest;

import moskito.services.MoskitoHomeRestURLs;
import moskito.services.rest.helpers.ParserJSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoskitoHomeUser {
    private static final Logger LOGGER = LogManager.getLogger();

    private String appUrl;

    public MoskitoHomeUser(String accessToken) {
        this.read(accessToken);
        LOGGER.info("Created {" + this + "}");
    }

    private void read(String accessToken) {
        ParserJSON parserJSON = new ParserJSON(MoskitoHomeRestURLs.USER_APP_URL.replace("${accessToken}", accessToken));
        appUrl = parserJSON.getJsonObject().get("app_url").toString();
    }

    public String getAppUrl() {
        return appUrl;
    }

    @Override
    public String toString() {
        return "MoskitoHomeUser: appUrl={" + appUrl + "}";
    }
}
