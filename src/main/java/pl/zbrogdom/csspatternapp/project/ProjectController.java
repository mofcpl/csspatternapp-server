package pl.zbrogdom.csspatternapp.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService service;
    private final ObjectMapper objectMapper;

    public ProjectController(ProjectService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping()
    List<ProjectDto> getProjects() {
        return service.getProjects();
    }

    @GetMapping("/{id}")
    ResponseEntity<ProjectDto> getProject(@PathVariable long id) {
        return service.getProject(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<ProjectDto> save(@RequestBody ProjectNewDto projectDto) {
        ProjectDto savedProject = this.service.saveProject(projectDto);
        URI savedEntityLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProject.getId())
                .toUri();
        return ResponseEntity.created(savedEntityLocation).body(savedProject);
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody JsonMergePatch patch) {
        try {
            ProjectDto project = service.getProject(id).orElseThrow();
            ProjectDto projectPatched = applyPatch(project, patch);
            service.updateProject(projectPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private ProjectDto applyPatch(ProjectDto projectDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode jobOfferNode = objectMapper.valueToTree(projectDto);
        JsonNode jobOfferPatchedNode = patch.apply(jobOfferNode);
        return objectMapper.treeToValue(jobOfferPatchedNode, ProjectDto.class);
    }

}
