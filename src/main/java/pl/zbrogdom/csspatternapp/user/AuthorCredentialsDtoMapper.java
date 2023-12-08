package pl.zbrogdom.csspatternapp.user;

public class AuthorCredentialsDtoMapper {

    static AuthorCredentialsDto map(Author author) {
        return new AuthorCredentialsDto(author.getPassword(), author.getName(), author.getId(), author.getHomepage(), author.getEmail());
    }
}
