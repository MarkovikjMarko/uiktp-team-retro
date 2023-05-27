package uikt.uiktpteamretrobnd.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "templates")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("format")
    @Column(length = 500)
    private String format;

    private String name;

    private String imageName;

    public Template() {

    }

    public Template(String format, String name, String imageName) {
        this.format = format;
        this.name = name;
        this.imageName = imageName;
    }

    public Template(String format) {
        this.format = format;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
