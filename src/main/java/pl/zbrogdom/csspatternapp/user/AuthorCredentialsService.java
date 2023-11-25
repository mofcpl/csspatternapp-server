package pl.zbrogdom.csspatternapp.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorCredentialsService {

    private final AuthorRepository repository;

    public AuthorCredentialsService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Optional<AuthorCredentialsDto> findCredentialsByEmail(String email) {
        return repository.findByEmail(email)
                .map(AuthorCredentialsDtoMapper::map);
    }
}
