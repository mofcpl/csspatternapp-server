package pl.zbrogdom.csspatternapp.project;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final ProjectDtoMapper projectDtoMapper;

    public ProjectService(ProjectRepository repository, ProjectDtoMapper projectDtoMapper) {
        this.repository = repository;
        this.projectDtoMapper = projectDtoMapper;
    }

    public Optional<ProjectDto> getProject(long id) {
       return this.repository.findById(id).map(projectDtoMapper::map);
    }

    public List<ProjectDto> getProjects() {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                .map(projectDtoMapper::map)
                .toList();
    }

    public ProjectDto saveProject(ProjectNewDto projectDto) {
        Project projectToSave = projectDtoMapper.map(projectDto);
        projectToSave.setPublishDate(LocalDateTime.now());
        projectToSave.setDownloads(0);
        Project savedProject = this.repository.save(projectToSave);
        return projectDtoMapper.map(savedProject);
    }

    public void updateProject(ProjectDto projectDto) {
        Project project = projectDtoMapper.map(projectDto);
        repository.save(project);
    }

    public void deleteProject(long id) {
        this.repository.deleteById(id);
    }


}

