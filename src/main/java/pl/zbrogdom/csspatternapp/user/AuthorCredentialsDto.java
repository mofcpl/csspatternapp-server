package pl.zbrogdom.csspatternapp.user;

public record AuthorCredentialsDto(String password, String username, long id, String homepage, String email) {
}
