package moskito.services.rest;

import moskito.services.rest.abstractions.ObjectRest;
import moskito.services.rest.basic_entities.Status;
import moskito.services.rest.helpers.ParserJSON;
import org.json.JSONObject;

/**
 * Status Object
 * @author Leo Ertuna
 */
public final class StatusRest extends ObjectRest {

    private Status status;                      // The status value, GREEN or RED

    public StatusRest(String appUrl) {
        super(appUrl);
    }

    @Override
    protected void read() {
        ParserJSON parserJSON = new ParserJSON(this.getRequestUrl());
        JSONObject root = parserJSON.getJsonObject();
        JSONObject results = root.getJSONObject("results");
        this.status = Status.getValueByName(results.getString("status").toLowerCase());
    }

    @Override
    protected String getCaseUrl() {
        return "status";
    }

    public String getStatus() {
        return status.getString();
    }
}