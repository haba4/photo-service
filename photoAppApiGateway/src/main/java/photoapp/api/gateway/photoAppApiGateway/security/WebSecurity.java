package photoapp.api.gateway.photoAppApiGateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import photoapp.api.gateway.photoAppApiGateway.config.ApplicationProperties;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private ApplicationProperties appProperties;

    @Autowired
    public WebSecurity(ApplicationProperties applicationProperties) {
        this.appProperties = applicationProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http
                .authorizeRequests()
                .antMatchers(appProperties.getH2ConsoleUrl()).permitAll()
                .antMatchers(HttpMethod.POST, appProperties.getRegisterUrl()).permitAll()
                .antMatchers(HttpMethod.POST, appProperties.getLoginUrl()).permitAll()
                .anyRequest().authenticated()
        .and()
                .addFilter(new AuthenticationFilter(authenticationManager(), appProperties));

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
