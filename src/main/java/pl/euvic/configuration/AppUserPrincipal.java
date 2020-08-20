package pl.euvic.configuration;

import org.springframework.security.core.userdetails.UserDetails;
import pl.euvic.model.entities.ClientEntity;

public class AppUserPrincipal implements UserDetails {
    public AppUserPrincipal(ClientEntity clientEntity) {
    }
}
