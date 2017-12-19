package moskito.services.rest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class ParserJSON {
    private String url;
    private String jsonString;
    private JSONObject jsonObject;

    public ParserJSON(String url) {
        this.url = url;
        this.formJsonString();
        this.jsonObject = new JSONObject(this.jsonString);
    }

    public String getJsonString() {
        return jsonString;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    private void formJsonString() {
        try {
            // Create a request and get the response
            HttpClient client = HttpClients.custom().build();
            HttpUriRequest request = RequestBuilder.get().setUri(url).setHeader(HttpHeaders.ACCEPT, "application/json").build();
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            // Convert input stream into a string
            jsonString = "";
            Scanner scanner = new Scanner(entity.getContent());
            while (scanner.hasNext())
                jsonString += scanner.nextLine();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}