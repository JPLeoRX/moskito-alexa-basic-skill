package moskito.services.rest;

import moskito.services.AppsURL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

public class ThresholdsRest extends ObjectRest {
    private LinkedList<Threshold> list = new LinkedList<>();

    public ThresholdsRest(String appUrl) {
        super(appUrl);
        this.read();

        System.out.println(list);
    }

    @Override
    protected void read() {
        ParserJSON parserJSON = new ParserJSON(this.getRequestUrl());
        JSONObject root = parserJSON.getJsonObject();
        JSONObject results = root.getJSONObject("results");
        JSONArray statuses = results.getJSONArray("statuses");

        for (int i = 0; i < statuses.length(); i++) {
            JSONObject status = (JSONObject) statuses.get(i);

            String trName = status.getString("name");
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