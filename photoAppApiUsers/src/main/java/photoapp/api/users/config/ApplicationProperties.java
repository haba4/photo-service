package photoapp.api.users.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationProperties {
    @Value("${gateway.ip}")
    private String gatewayIp;
    @Value("${token.expiration}")
    private String tokenExpiration;
    @Value("${token.secret}")
    private String tokenSecret;

    public String getGatewayIp() {
        return gatewayIp;
    }

    public String getTokenExpiration() {
        return tokenExpiration;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }
}
