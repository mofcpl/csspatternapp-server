package pl.zbrogdom.csspatternapp.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.zbrogdom.csspatternapp.user.AuthorCredentialsDto;
import pl.zbrogdom.csspatternapp.user.AuthorCredentialsService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthorCredentialsService service;

    public CustomUserDetailsService(AuthorCredentialsService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return service.findCredentialsByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", email)));
    }

    private UserDetails createUserDetails(AuthorCredentialsDto credentials) {
        return new CustomUserDetails(credentials.password(),credentials.username(), credentials.id(), credentials.homepage(), credentials.email());
    }
}
