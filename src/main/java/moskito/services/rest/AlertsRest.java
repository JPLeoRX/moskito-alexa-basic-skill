package moskito.services.rest;

import moskito.services.rest.abstractions.CollectionRest;
import moskito.services.rest.basic_entities.Alert;
import moskito.services.rest.basic_entities.Status;
import moskito.services.rest.helpers.ParserJSON;
import moskito.services.rest.helpers.StringHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Alerts Object
 * @author Leo Ertuna
 */
public final class AlertsRest extends CollectionRest<Alert> {
    private static final Logger LOGGER = LogManager.getLogger();

    public AlertsRest(String appUrl) {
        super(appUrl);
        LOGGER.info("Created {" + this + "}");
    }

    @Override
    protected void read() {
        ParserJSON parserJSON = new ParserJSON(this.getRequestUrl());
        JSONObject root = parserJSON.getJsonObject();
        JSONObject results = root.getJSONObject("results");
        JSONArray alerts = results.getJSONArray("alerts");

        for (int i = 0; i < alerts.length(); i++) {
            JSONObject alertJson = (JSONObject) alerts.get(i);

            String name = StringHelper.splitCamelCase(alertJson.getString("name"));
            Status statusNew = Status.getValueByName(alertJson.getString("newStatus"));
            String valueNew = alertJson.getString("newValue");
            Status statusOld = Status.getValueByName(alertJson.getString("oldStatus"));
            String valueOld = alertJson.getString("oldValue");
            String time = alertJson.getString("timestamp");

            Alert alert = new Alert(name, statusNew, valueNew, statusOld, valueOld, time);
            list.add(alert);
        }
    }

    @Override
    protected String getCaseUrl() {
        return "alerts";
    }

    @Override
    public String toString() {
        return "AlertsRest: list=" + list;
    }
}