package pl.zbrogdom.csspatternapp.user;

import org.springframework.stereotype.Service;
import pl.zbrogdom.csspatternapp.project.Project;
import pl.zbrogdom.csspatternapp.project.ProjectRepository;

import java.util.Arrays;

@Service
public class AuthorForClientDtoMapper {

    private final ProjectRepository repository;

    public AuthorForClientDtoMapper(ProjectRepository repository) {
        this.repository = repository;
    }

    AuthorForClientDto map(Author author) {
        AuthorForClientDto dto = new AuthorForClientDto();
        dto.setId(author.getId());
        dto.setEmail(author.getEmail());
        dto.setName(author.getName());
        dto.setHomepage(author.getHomepage());
        dto.setProjects(author.getProjects().stream().map(Project::getId).toArray(Long[]::new));
        return dto;
    }

    Author map(AuthorForClientDto dto) {
        Author author = new Author();
        author.setEmail(dto.getEmail());
        author.setId(dto.getId());
        author.setName(dto.getName());
        author.setHomepage(dto.getHomepage());
        author.setProjects(Arrays.stream(dto.getProjects()).map(projectId -> repository.findById(projectId).orElse(null)).toList());
        return author;
    }

}
