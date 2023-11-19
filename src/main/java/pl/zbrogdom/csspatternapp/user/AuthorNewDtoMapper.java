package pl.zbrogdom.csspatternapp.user;

import org.springframework.stereotype.Service;

@Service
public class AuthorNewDtoMapper {

    Author map(AuthorNewDto dto) {
        Author author = new Author();
        author.setEmail(dto.getEmail());
        author.setName(dto.getName());
        author.setHomePage(dto.getHomePage());
        author.setPassword(dto.getPassword());
        return author;
    }

    AuthorNewDto map(Author author) {
        AuthorNewDto dto = new AuthorNewDto();
        dto.setHomePage(author.getHomePage());
        dto.setName(author.getName());
        dto.setEmail(author.getEmail());
        dto.setPassword(author.getPassword());
        return dto;
    }

}
