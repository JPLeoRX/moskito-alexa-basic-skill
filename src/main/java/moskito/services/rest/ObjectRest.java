package moskito.services.rest;

/**
 * Basic structure of any REST Object
 * @author Leo Ertuna
 */
public abstract class ObjectRest {
    protected static final String RESTPATH = "moskito-inspect-rest";

    private String appUrl;                          // The url of the application that uses moskito rest api
    private String requestUrl;                      // The url of our final request to moskito rest api

    protected ObjectRest(final String appUrl) {
        this.appUrl = appUrl;
        this.requestUrl = appUrl + "/" + RESTPATH + "/" + this.getCaseUrl();
        this.read();
    }

    // Read the REST response into this object
    protected abstract void read();

    // Specify the case-url for this REST object
    protected abstract String getCaseUrl();

    protected String getRequestUrl() {
        return requestUrl;
    }

    protected String getAppUrl() {
        return appUrl;
    }
}