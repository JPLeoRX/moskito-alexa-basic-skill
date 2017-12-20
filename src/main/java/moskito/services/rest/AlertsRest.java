package moskito.services.rest;

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
            Status statusOld = Status.getValueByName(alertJson.getString("oldStatus"));
            String time = alertJson.getString("timestamp");

            Alert alert = new Alert(name, statusNew, statusOld, time);
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