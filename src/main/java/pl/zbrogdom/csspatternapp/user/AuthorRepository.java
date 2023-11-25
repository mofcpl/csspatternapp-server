package pl.zbrogdom.csspatternapp.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findByEmail(String email);
}
