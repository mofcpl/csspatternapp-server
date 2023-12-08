package pl.zbrogdom.csspatternapp.user;

import org.springframework.stereotype.Service;

@Service
public class AuthorFromClientDtoMapper {

    Author map(AuthorFromClientDto dto) {
        Author author = new Author();
        author.setEmail(dto.getEmail());
        author.setName(dto.getName());
        author.setHomepage(dto.getHomepage());
        author.setPassword(dto.getPassword());
        return author;
    }

    AuthorFromClientDto map(Author author) {
        AuthorFromClientDto dto = new AuthorFromClientDto();
        dto.setHomepage(author.getHomepage());
        dto.setName(author.getName());
        dto.setEmail(author.getEmail());
        dto.setPassword(author.getPassword());
        return dto;
    }

}
