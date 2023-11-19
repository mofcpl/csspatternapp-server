package pl.zbrogdom.csspatternapp.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public class AuthorNewDto {

    @NotNull
    @Size(min = 5, max = 50)
    private String name;

    @Email
    @Size(max = 50)
    private String email;

    @URL
    private String homePage;

    @NotNull
    @Size(min = 8)
    private String password;

    public AuthorNewDto() {
    }

    public AuthorNewDto(String name, String email, String homePage, String password) {
        this.name = name;
        this.email = email;
        this.homePage = homePage;
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

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
