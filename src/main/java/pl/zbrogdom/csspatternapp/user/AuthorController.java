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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.validation.FieldError;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService service;
    private final ObjectMapper objectMapper;
    private final Validator validator;
    private final AuthorFromClientDtoMapper authorFromClientDtoMapper;

    public AuthorController(AuthorService service, ObjectMapper objectMapper, Validator validator, AuthorFromClientDtoMapper authorNewDtoMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.validator = validator;
        this.authorFromClientDtoMapper = authorNewDtoMapper;
    }

    @GetMapping()
    List<AuthorForClientDto> getAuthors() {
        return service.getAuthors();
    }

    @GetMapping("/{id}")
    ResponseEntity<AuthorForClientDto> getAuthor(@PathVariable Long id) {
        return service.getAuthor(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<AuthorForClientDto> save(@Valid @RequestBody AuthorFromClientDto authorNewDto) {
        AuthorForClientDto savedAuthor = this.service.saveAuthor(authorNewDto);
        URI savedEntityLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAuthor.getId())
                .toUri();
        return ResponseEntity.created(savedEntityLocation).body(savedAuthor);
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> updateAuthor(@PathVariable("id") Long id, @RequestBody JsonMergePatch patch) throws MethodArgumentNotValidException {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Author dto = service.getAuthorByEmail(email).orElseThrow();
            if(!Objects.equals(id, dto.getId())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            Author authorPatched = applyPatch(dto, patch);
            manualValidation(authorFromClientDtoMapper.map(authorPatched));
            service.updateProject(authorPatched);
        } catch (JsonPatchException | NoSuchMethodException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private void manualValidation(AuthorFromClientDto author) throws MethodArgumentNotValidException, NoSuchMethodException {
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(author, "author");
        SpringValidatorAdapter adapter = new SpringValidatorAdapter(this.validator);
        adapter.validate(author, result);
        Method method = getClass().getDeclaredMethod("updateAuthor", Long.class, JsonMergePatch.class);
        if(result.hasErrors()) {
            throw new MethodArgumentNotValidException(new MethodParameter(method, 0), result);
        }
    }

    private Author applyPatch(Author authorDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode jobOfferNode = objectMapper.valueToTree(authorDto);
        JsonNode jobOfferPatchedNode = patch.apply(jobOfferNode);
        return objectMapper.treeToValue(jobOfferPatchedNode, Author.class);
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
