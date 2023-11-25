package pl.zbrogdom.csspatternapp.project;

import org.springframework.stereotype.Service;
import pl.zbrogdom.csspatternapp.user.Author;
import pl.zbrogdom.csspatternapp.user.AuthorRepository;

@Service
public class ProjectFromClientDtoMapper {

    private final AuthorRepository authorRepository;

    public ProjectFromClientDtoMapper(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    Project map(ProjectFromClientDto dto) {
        Project project = new Project();
        project.setData(dto.getData());
        project.setStyle(dto.getStyle());
        project.setTitle(dto.getTitle());
        return project;
    }

    ProjectFromClientDto map(Project project) {
        ProjectFromClientDto dto = new ProjectFromClientDto();
        dto.setData(project.getData());
        dto.setTitle(project.getTitle());
        dto.setStyle(project.getStyle());
        return dto;
    }
}
