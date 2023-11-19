package pl.zbrogdom.csspatternapp.user;

import jakarta.persistence.*;
import pl.zbrogdom.csspatternapp.project.Project;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String password;
    private String homePage;
    @OneToMany(mappedBy = "author")
    private List<Project> projects = new ArrayList<>();

    public Author() {
    }

    public Author(Long id, String name, String email, String password, String homePage, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.homePage = homePage;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
