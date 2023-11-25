package pl.zbrogdom.csspatternapp.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class AuthorService {

    private final AuthorRepository repository;
    private final AuthorForClientDtoMapper authorDtoMapper;
    private final AuthorFromClientDtoMapper authorNewDtoMapper;

    public AuthorService(AuthorRepository repository, AuthorForClientDtoMapper dtoMapper, AuthorFromClientDtoMapper authorNewDtoMapper) {
        this.repository = repository;
        this.authorDtoMapper = dtoMapper;
        this.authorNewDtoMapper = authorNewDtoMapper;
    }

    public List<AuthorForClientDto> getAuthors() {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                .map(authorDtoMapper::map)
                .toList();
    }

    public Optional<AuthorForClientDto> getAuthor(Long id) {
        return this.repository.findById(id).map(authorDtoMapper::map);
    }

    public Optional<Author> getAuthorByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    public AuthorForClientDto saveProject(AuthorFromClientDto projectDto) {
        Author projectToSave = authorNewDtoMapper.map(projectDto);
        Author savedProject = this.repository.save(projectToSave);
        return authorDtoMapper.map(savedProject);
    }

    public void updateProject(Author author) {
        repository.save(author);
    }

}
