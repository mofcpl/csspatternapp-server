package pl.zbrogdom.csspatternapp.user;

public class AuthorCredentialsDtoMapper {

    static AuthorCredentialsDto map(Author author) {
        return new AuthorCredentialsDto(author.getEmail(), author.getPassword());
    }
}
