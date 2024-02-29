package net.ictcampus.martialartapi.controller.security;

//Die Klasse enthält nur Konstanten die in anderen Klassen verwendet werden.
//Sie werden hier gesammelt um die anderen Klassen schlanker zu machen und Änderung
//hier vereinfacht durchführen zu können

public class SecurityConstants {
    public static final String SIGN_UP_URL = "/users/sign-up";
    public static final String[] API_DOCUMENTATION_URLS = {
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**"
    };


    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

}
