package pl.zbrogdom.csspatternapp.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.validation.FieldError;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService service;
    private final ObjectMapper objectMapper;
    private final Validator validator;
    private final AuthorNewDtoMapper authorNewDtoMapper;

    public AuthorController(AuthorService service, ObjectMapper objectMapper, Validator validator, AuthorNewDtoMapper authorNewDtoMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.validator = validator;
        this.authorNewDtoMapper = authorNewDtoMapper;
    }

    @GetMapping()
    List<AuthorDto> getAuthors() {
        return service.getAuthors();
    }

    @GetMapping("/{id}")
    ResponseEntity<AuthorDto> getAuthor(@PathVariable Long id) {
        return service.getAuthor(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<AuthorDto> save(@Valid @RequestBody AuthorNewDto authorNewDto) {
        AuthorDto savedAuthor = this.service.saveProject(authorNewDto);
        URI savedEntityLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAuthor.getId())
                .toUri();
        return ResponseEntity.created(savedEntityLocation).body(savedAuthor);
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody JsonMergePatch patch) throws NoSuchMethodException, MethodArgumentNotValidException {
        try {
            AuthorNewDto dto = service.getAuthorForPatch(id).orElseThrow();
            AuthorNewDto authorPatched = applyPatch(dto, patch);
            BeanPropertyBindingResult result = new BeanPropertyBindingResult(authorPatched, "author");
            SpringValidatorAdapter adapter = new SpringValidatorAdapter(this.validator);
            adapter.validate(authorPatched, result);
            if(result.hasErrors()) {
                throw new MethodArgumentNotValidException(new MethodParameter(
                        this.getClass().getDeclaredMethod("updateAuthor", AuthorController.class), 1), result);
            }
            Author author = authorNewDtoMapper.map(authorPatched);
            author.setId(id);
            service.updateProject(author);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private AuthorNewDto applyPatch(AuthorNewDto authorDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode jobOfferNode = objectMapper.valueToTree(authorDto);
        JsonNode jobOfferPatchedNode = patch.apply(jobOfferNode);
        return objectMapper.treeToValue(jobOfferPatchedNode, AuthorNewDto.class);
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
