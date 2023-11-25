package pl.zbrogdom.csspatternapp.project;

import org.springframework.stereotype.Service;
import pl.zbrogdom.csspatternapp.user.Author;
import pl.zbrogdom.csspatternapp.user.AuthorRepository;

@Service
public class ProjectForClientDtoMapper {

    private final AuthorRepository authorRepository;

    public ProjectForClientDtoMapper(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    ProjectForClientDto map(Project project) {
        ProjectForClientDto dto = new ProjectForClientDto();
        dto.setAuthor(project.getAuthor().getId());
        dto.setData(project.getData());
        dto.setDownloads(project.getDownloads());
        dto.setId(project.getId());
        dto.setStyle(project.getStyle());
        dto.setPublishDate(project.getPublishDate());
        dto.setTitle(project.getTitle());
        return dto;
    }

    Project map(ProjectForClientDto dto) {
        Project project = new Project();
        project.setData(dto.getData());
        project.setDownloads(dto.getDownloads());
        project.setId(dto.getId());
        project.setStyle(dto.getStyle());
        project.setPublishDate(dto.getPublishDate());
        project.setTitle(dto.getTitle());
        Author author = authorRepository.findById(dto.getAuthor()).orElseThrow();
        project.setAuthor(author);
        return project;
    }


}
