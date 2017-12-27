package moskito.services;

import moskito.services.rest.helpers.ParserJSON;

public class AmazonUser {
    public static AmazonUser current;
    private String accessToken;
    private String name;
    private String email;

    public AmazonUser(String accessToken) {
        this.accessToken = accessToken;
        this.read();
    }

    private void read() {
        try {
            ParserJSON parserJSON = new ParserJSON("https://api.amazon.com/user/profile",
                    "Authorization", "bearer " + accessToken);

            this.name = parserJSON.getJsonObject().get("name").toString();
            this.email = parserJSON.getJsonObject().get("email").toString();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
