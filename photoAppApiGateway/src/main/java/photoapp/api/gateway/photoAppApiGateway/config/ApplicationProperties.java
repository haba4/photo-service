package photoapp.api.gateway.photoAppApiGateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationProperties {
    @Value("${api.login.url.path}")
    private String loginUrl;
    @Value("${api.registration.url.path}")
    private String registerUrl;
    @Value("${api.h2console.url.path}")
    private String h2ConsoleUrl;
    @Value("${authorization.token.header.name}")
    private String authTokenHeaderName;
    @Value("${authorization.token.header.prefix}")
    private String authTokenHeaderPrefix;
    @Value("${token.secret}")
    private String tokenSecret;

    public String getLoginUrl() {
        return loginUrl;
    }

    public String getRegisterUrl() {
        return registerUrl;
    }

    public String getH2ConsoleUrl() {
        return h2ConsoleUrl;
    }

    public String getAuthTokenHeaderName() {
        return authTokenHeaderName;
    }

    public String getAuthTokenHeaderPrefix() {
        return authTokenHeaderPrefix;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }
}
