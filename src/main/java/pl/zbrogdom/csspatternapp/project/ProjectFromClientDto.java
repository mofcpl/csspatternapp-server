package pl.zbrogdom.csspatternapp.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProjectFromClientDto {

    @NotNull
    @Size(min = 5, max = 50)
    private String title;

    @NotNull
    private String data;

    @NotNull
    @Column(columnDefinition = "json")
    private String style;

    public ProjectFromClientDto() {
    }

    public ProjectFromClientDto(String title, String data, String style) {
        this.title = title;
        this.data = data;
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
