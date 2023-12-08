package pl.zbrogdom.csspatternapp.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private final String password;
    private final String username;
    private final long id;
    private final String homepage;
    private final String email;
    private Set<GrantedAuthority> authorities;

    public CustomUserDetails(String password, String username, long id, String homepage, String email) {
        this.password = password;
        this.username = username;
        this.id = id;
        this.homepage = homepage;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

    public long getId() {
        return id;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getEmail() {
        return email;
    }
}
