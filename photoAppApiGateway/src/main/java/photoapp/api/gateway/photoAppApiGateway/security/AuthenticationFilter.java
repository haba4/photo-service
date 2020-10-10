package photoapp.api.gateway.photoAppApiGateway.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import photoapp.api.gateway.photoAppApiGateway.config.ApplicationProperties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationFilter extends BasicAuthenticationFilter {
    private ApplicationProperties appProperties;

    public AuthenticationFilter(AuthenticationManager authenticationManager, ApplicationProperties appProperties) {
        super(authenticationManager);
        this.appProperties = appProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader(appProperties.getAuthTokenHeaderName());
        if (authorizationHeader == null || !authorizationHeader.startsWith(appProperties.getAuthTokenHeaderPrefix())) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(authorizationHeader);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String authorizationHeader) {
        if (authorizationHeader == null) {
            return null;
        }
        String token = authorizationHeader.replace(appProperties.getAuthTokenHeaderPrefix(), "");
        String userId = Jwts.parser()
                .setSigningKey(appProperties.getTokenSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        if (userId == null) {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
    }
}
