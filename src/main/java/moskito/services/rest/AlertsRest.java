package moskito.services.rest;

import moskito.services.rest.basic_entities.Alert;
import moskito.services.rest.abstractions.ObjectRest;
import moskito.services.rest.basic_entities.Status;
import moskito.services.rest.helpers.ParserJSON;
import moskito.services.rest.helpers.StringHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

/**
 * Alerts Object
 * @author Leo Ertuna
 */
public class AlertsRest extends ObjectRest {
    private LinkedList<Alert> list;

    public AlertsRest(String appUrl) {
        super(appUrl);
    }

    @Override
    protected void read() {
        list = new LinkedList<>();

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

    public LinkedList<Alert> getList() {
        return list;
    }
}