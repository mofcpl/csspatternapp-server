package pl.zbrogdom.csspatternapp.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public class AuthorFromClientDto {

    @NotNull
    @Size(min = 5, max = 50)
    private String name;

    @Email
    @Size(max = 50)
    private String email;

    @URL
    private String homepage;

    @NotNull
    @Size(min = 8)
    private String password;

    public AuthorFromClientDto() {
    }

    public AuthorFromClientDto(String name, String email, String homePage, String password) {
        this.name = name;
        this.email = email;
        this.homepage = homePage;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
