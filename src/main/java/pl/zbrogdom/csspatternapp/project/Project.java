package pl.zbrogdom.csspatternapp.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import pl.zbrogdom.csspatternapp.user.Author;

import java.time.LocalDateTime;


@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Author author;
    private String title;
    private LocalDateTime publishDate;
    private String data;
    private String style;
    private int downloads;

    public Project() {
    }

    public Project(long id, Author author, String title, LocalDateTime publishDate, String data, String style, int downloads) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.publishDate = publishDate;
        this.data = data;
        this.style = style;
        this.downloads = downloads;
    }

    public Project(Author author, String title, LocalDateTime publishDate, String data, String style, int downloads) {
        this.author = author;
        this.title = title;
        this.publishDate = publishDate;
        this.data = data;
        this.style = style;
        this.downloads = downloads;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }
}
