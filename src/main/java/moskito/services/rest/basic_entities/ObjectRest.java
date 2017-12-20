package moskito.services.rest.basic_entities;

/**
 * Basic structure of any REST Object
 * @author Leo Ertuna
 */
public abstract class ObjectRest {
    protected static final String RESTPATH = "moskito-inspect-rest";

    String appUrl;                          // The url of the application that uses moskito rest api
    String requestUrl;                      // The url of our final request to moskito rest api

    ObjectRest() {

    }

    protected ObjectRest(String appUrl) {
        this.appUrl = appUrl;
        this.requestUrl = appUrl + "/" + RESTPATH + "/" + this.getCaseUrl();
        this.read();
    }

    // Core functionality
    //------------------------------------------------------------------------------------------------------------------
    // Read the REST response into this object
    protected abstract void read();

    // Specify the case-url for this REST object
    protected abstract String getCaseUrl();
    //------------------------------------------------------------------------------------------------------------------


    // URL Getters
    //------------------------------------------------------------------------------------------------------------------
    protected String getRequestUrl() {
        return requestUrl;
    }

    protected String getAppUrl() {
        return appUrl;
    }
    //------------------------------------------------------------------------------------------------------------------
}