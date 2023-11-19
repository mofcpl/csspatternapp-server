package pl.zbrogdom.csspatternapp.user;

import org.springframework.stereotype.Service;
import pl.zbrogdom.csspatternapp.project.Project;
import pl.zbrogdom.csspatternapp.project.ProjectRepository;

import java.util.Arrays;

@Service
public class AuthorDtoMapper {

    private final ProjectRepository repository;

    public AuthorDtoMapper(ProjectRepository repository) {
        this.repository = repository;
    }

    AuthorDto map(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setEmail(author.getEmail());
        dto.setName(author.getName());
        dto.setHomePage(author.getHomePage());
        dto.setProjects(author.getProjects().stream().map(Project::getId).toArray(Long[]::new));
        return dto;
    }

    Author map(AuthorDto dto) {
        Author author = new Author();
        author.setEmail(dto.getEmail());
        author.setId(dto.getId());
        author.setName(dto.getName());
        author.setHomePage(dto.getHomePage());
        author.setProjects(Arrays.stream(dto.getProjects()).map(projectId -> repository.findById(projectId).orElse(null)).toList());
        return author;
    }

}
