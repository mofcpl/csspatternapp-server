package pl.zbrogdom.csspatternapp.user;

public class AuthorForClientDto {

    private Long id;
    private String name;
    private String email;
    private String homepage;
    private Long[] projects;

    public AuthorForClientDto() {
    }

    public AuthorForClientDto(Long id, String name, String email, String homePage, Long[] projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.homepage = homePage;
        this.projects = projects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long[] getProjects() {
        return projects;
    }

    public void setProjects(Long[] projects) {
        this.projects = projects;
    }
}
