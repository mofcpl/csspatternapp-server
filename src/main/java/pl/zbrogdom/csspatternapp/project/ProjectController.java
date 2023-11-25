package pl.zbrogdom.csspatternapp.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import pl.zbrogdom.csspatternapp.user.Author;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService service;
    private final ObjectMapper objectMapper;
    private final Validator validator;
    private final ProjectFromClientDtoMapper projectFromClientDtoMapper;

    public ProjectController(ProjectService service, ObjectMapper objectMapper, Validator validator, ProjectFromClientDtoMapper projectFromClientDtoMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.validator = validator;
        this.projectFromClientDtoMapper = projectFromClientDtoMapper;
    }

    @GetMapping()
    List<ProjectForClientDto> getProjects() {
        return service.getProjects();
    }

    @GetMapping("/{id}")
    ResponseEntity<ProjectForClientDto> getProject(@PathVariable long id) {
        return service.getProject(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<ProjectForClientDto> save(@Valid @RequestBody ProjectFromClientDto projectDto) {
        ProjectForClientDto savedProject = this.service.saveProject(projectDto);
        URI savedEntityLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProject.getId())
                .toUri();
        return ResponseEntity.created(savedEntityLocation).body(savedProject);
    }


    @PatchMapping("/{id}")
    ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody JsonMergePatch patch) throws MethodArgumentNotValidException {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Project project = service.getProjectForPatch(id).orElseThrow();
            if(!Objects.equals(email, project.getAuthor().getEmail())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            Project projectPatched = applyPatch(project, patch);
            manualValidation(projectFromClientDtoMapper.map(projectPatched));
            service.updateProject(projectPatched);
        } catch (JsonPatchException | NoSuchMethodException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private Project applyPatch(Project projectDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode jobOfferNode = objectMapper.valueToTree(projectDto);
        JsonNode jobOfferPatchedNode = patch.apply(jobOfferNode);
        return objectMapper.treeToValue(jobOfferPatchedNode, Project.class);
    }

    private void manualValidation(ProjectFromClientDto author) throws MethodArgumentNotValidException, NoSuchMethodException {
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(author, "author");
        SpringValidatorAdapter adapter = new SpringValidatorAdapter(this.validator);
        adapter.validate(author, result);
        Method method = getClass().getDeclaredMethod("updateProject", Long.class, JsonMergePatch.class);
        if(result.hasErrors()) {
            throw new MethodArgumentNotValidException(new MethodParameter(method, 0), result);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
