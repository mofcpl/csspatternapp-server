package pl.zbrogdom.csspatternapp.project;

import org.springframework.stereotype.Service;
import pl.zbrogdom.csspatternapp.user.Author;
import pl.zbrogdom.csspatternapp.user.AuthorFromClientDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final ProjectForClientDtoMapper projectForClientDtoMapper;
    private final ProjectFromClientDtoMapper projectFromClientDtoMapper;

    public ProjectService(ProjectRepository repository, ProjectForClientDtoMapper projectDtoMapper, ProjectFromClientDtoMapper projectFromClientDtoMapper) {
        this.repository = repository;
        this.projectForClientDtoMapper = projectDtoMapper;
        this.projectFromClientDtoMapper = projectFromClientDtoMapper;
    }

    public Optional<ProjectForClientDto> getProject(long id) {
       return this.repository.findById(id).map(projectForClientDtoMapper::map);
    }

    public List<ProjectForClientDto> getProjects() {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                .map(projectForClientDtoMapper::map)
                .toList();
    }



    public ProjectForClientDto saveProject(ProjectFromClientDto projectDto) {
        Project projectToSave = projectFromClientDtoMapper.map(projectDto);
        projectToSave.setPublishDate(LocalDateTime.now());
        projectToSave.setDownloads(0);
        Project savedProject = this.repository.save(projectToSave);
        return projectForClientDtoMapper.map(savedProject);
    }

    public Optional<Project> getProjectForPatch(Long id) {
        return this.repository.findById(id);
    }

    public void updateProject(Project project) {
        repository.save(project);
    }

    public void deleteProject(long id) {
        this.repository.deleteById(id);
    }


}

