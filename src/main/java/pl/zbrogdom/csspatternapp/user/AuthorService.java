package pl.zbrogdom.csspatternapp.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class AuthorService {

    private final AuthorRepository repository;
    private final AuthorDtoMapper authorDtoMapper;
    private final AuthorNewDtoMapper authorNewDtoMapper;

    public AuthorService(AuthorRepository repository, AuthorDtoMapper dtoMapper, AuthorNewDtoMapper authorNewDtoMapper) {
        this.repository = repository;
        this.authorDtoMapper = dtoMapper;
        this.authorNewDtoMapper = authorNewDtoMapper;
    }

    public List<AuthorDto> getAuthors() {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                .map(authorDtoMapper::map)
                .toList();
    }

    public Optional<AuthorDto> getAuthor(Long id) {
        return this.repository.findById(id).map(authorDtoMapper::map);
    }

    public Optional<AuthorNewDto> getAuthorForPatch(Long id) {
        return this.repository.findById(id).map(authorNewDtoMapper::map);
    }

    public AuthorDto saveProject(AuthorNewDto projectDto) {
        Author projectToSave = authorNewDtoMapper.map(projectDto);
        Author savedProject = this.repository.save(projectToSave);
        return authorDtoMapper.map(savedProject);
    }

    public void updateProject(Author author) {
        repository.save(author);
    }

}
