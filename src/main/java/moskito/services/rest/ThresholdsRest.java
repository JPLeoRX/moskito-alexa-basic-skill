package moskito.services.rest;

import moskito.services.rest.abstractions.CollectionRest;
import moskito.services.rest.basic_entities.Status;
import moskito.services.rest.basic_entities.Threshold;
import moskito.services.rest.helpers.ParserJSON;
import moskito.services.rest.helpers.StringHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Thresholds Object
 * @author Leo Ertuna
 */
public final class ThresholdsRest extends CollectionRest<Threshold> {
    private static final Logger LOGGER = LogManager.getLogger();

    public ThresholdsRest(String appUrl) {
        super(appUrl);
        LOGGER.info("Created {" + this + "}");
    }

    @Override
    protected void read() {
        ParserJSON parserJSON = new ParserJSON(this.getRequestUrl());
        JSONObject root = parserJSON.getJsonObject();
        JSONObject results = root.getJSONObject("results");
        JSONArray statuses = results.getJSONArray("statuses");

        for (int i = 0; i < statuses.length(); i++) {
            JSONObject status = (JSONObject) statuses.get(i);

            String trName = StringHelper.splitCamelCase(status.getString("name"));
            Status trStatus = Status.getValueByName(status.getString("colorCode"));
            String trValue = status.getString("value");

            Threshold threshold = new Threshold(trName, trStatus, trValue);
            list.add(threshold);
        }
    }

    @Override
    protected String getCaseUrl() {
        return "thresholds";
    }

    @Override
    public String toString() {
        return "ThresholdsRest: list=" + list;
    }
}