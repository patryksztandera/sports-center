package pl.euvic.configuration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.euvic.model.entities.ClientEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AppUserPrincipal implements UserDetails {

    private final ClientEntity clientEntity;

    public AppUserPrincipal(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    @Override
    public String getUsername() {
        return clientEntity.getUsername();
    }

    @Override
    public String getPassword() {
        return clientEntity.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("User"));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }
}
