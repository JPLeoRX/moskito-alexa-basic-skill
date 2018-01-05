package moskito.services;

public class MoskitoHomeRestURLs {
    private static final String MAIN_URL = "https://adorable-francis.bespoken.link/api";
    public static final String TOKEN_VERIFICATION_URL = MAIN_URL + "/token/${accessToken}/verify";
    public static final String USER_APP_URL = MAIN_URL + "/users/${accessToken}/app";
}
