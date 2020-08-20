package pl.euvic.configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.Filter;

public class JwtAuthorizationFilter implements Filter {
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, String secret) {
    }
}
