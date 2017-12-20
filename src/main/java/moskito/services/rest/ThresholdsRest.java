package moskito.services.rest;

import moskito.services.rest.basic_entities.ObjectRest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

/**
 * Thresholds Object
 * @author Leo Ertuna
 */
public final class ThresholdsRest extends ObjectRest {
    private LinkedList<Threshold> list;

    public ThresholdsRest(String appUrl) {
        super(appUrl);
    }

    @Override
    protected void read() {
        list = new LinkedList<>();

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

    public LinkedList<Threshold> getList() {
        return list;
    }
}