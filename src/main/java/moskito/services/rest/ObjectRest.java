package moskito.services.rest;


public abstract class ObjectRest {
    public static final String RESTPATH = "moskito-inspect-rest";

    private String appUrl;                          // The url of the application that uses moskito rest api
    private String requestUrl;                      // The url of our final request to moskito rest api

    public ObjectRest(String appUrl) {
        this.appUrl = appUrl;
        this.requestUrl = appUrl + "/" + RESTPATH + "/" + this.getCaseUrl();
    }

    protected abstract void read();

    protected abstract String getCaseUrl();

    protected String getRequestUrl() {
        return requestUrl;
    }

    protected String getAppUrl() {
        return appUrl;
    }
}